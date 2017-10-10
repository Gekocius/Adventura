package logika;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 
 * Třída představující palubní AI. Implementováno jako jednoduchý chat bot reagující na klíčová slova v textu
 * 
 * @author Daniel Vrána
 * @version 1.0.0
 *
 */

public class Athena extends Vec implements IPouzitelna {
	
	private boolean aktivni;
	private HashSet<AkcniSlovo> akcniSlova;
	private HashMap<String, String> database;
	private HashMap<String, Boolean> stav;
	private Prostor timeDrive;
	private HerniPlan plan;
	/**
	 * Konstruktor třídy. Volá předka aby nastavil název a přenositelnost. Také přiřazuje herní plán
	 * 
	 * @param nazev název předmětu
	 * @param prenositelnost zda je možné přenést předmět - zde false
	 * @param plan herní plán do kterého předmět patří
	 */
	public Athena(String nazev, boolean prenositelnost, HerniPlan plan)
	{
		super(nazev, prenositelnost);
		
		database = new HashMap<>();
		stav = new HashMap<>();
		akcniSlova = new HashSet<>();
		this.plan = plan;
		
		setStavPolozka("motoryOnline", false);
		setStavPolozka("timeDriveOnline", false);
		
	}
	/**
	 * Zpracuje zadaný text a vrátí odpověď. Pokud se slovo nachází ve slovech se speciální akcí,
	 * pošle dál ke zpracování.
	 * 
	 * @param text který má být zpracován
	 * @return odpověď která se vrátí po zpracování
	 */
	public String zpracujText(String text)
	{
		// Pokud není athena opravena, slova jsou prázdná
		if(database.isEmpty() && akcniSlova.isEmpty())
		{
			setAktivni(false);
			return "*ERROR* - Language module malfunction. Attend repair in AI-Core";
		}
		
		String[] slova = text.toLowerCase().split("[ \t]+");
		
		for(String slovo : slova)
		{
			for(AkcniSlovo akcniSlovo : akcniSlova)
			{
				if(akcniSlovo.getNazev().equals(slovo) || akcniSlovo.jeValidniAlias(slovo))
				{
					return akcniSlovo.provedAkci(this);
				}
			}
			
			if(database.containsKey(slovo))
			{
				return database.get(slovo);
			}
			
			if(slovo.equals("out"))
			{
				setAktivni(false);
			}
		}
		
		return "Požadavek nerozpoznán";
	}
	
	/**
	 * Značí, jestli hráč právě komunikuje s AI
	 * 
	 * @return true pokud hráč mluví s AI
	 */
	public boolean jeAktivni() {
		return aktivni;
	}

	/**
	 * Nastaví, jestli hráč právě komunikuje s AI
	 * 
	 * @param aktivni co se má nastavit
	 */
	public void setAktivni(boolean aktivni) {
		this.aktivni = aktivni;
	}
	
	/**
	 * Metoda z rozhraní IPouzitelna. Po příkazu použij "zapne" AI. Veškerý text z UI je pak přesměrován do této třídy.
	 * 
	 *  return vysledek akce použij kterou hráč provedl
	 */
	@Override
	public String pouzij(String nazevNastroje) {
		if(nazevNastroje == null)
		{
			setAktivni(true);
			return "A.T.H.E.N.A. Online - You can talk to me now\n Type \"out\" to end the interface";
		}
		return "Na to nemůžu použít " + "nazevNastroje";
	}
	
	/**
	 * Napamuje slova do HashMap, aby byla rozpozantelná AI. Slouží hlavně když hráč opraví AI v příběhu
	 */
	public void mapujSlova()
	{
		database.put("kdo", "Advanced Technologcal Human Emulation and Neural Assistant - A.T.H.E.N.A\n Ty jsi byl rozpoznán jako John Palmer - lodní inženýr");
		database.put("kde", "Nacházíme se na lodi s označením Zeus, poblíž orbitu Marsu, avšak chybí nám zbytek posádky. Přeješ si znát stav lodi?");
		database.put("posádka", "Podle lodních záznamů, poslední experiment o skok v čase nevyšel podle plánu, systém spadnul. Posádka je nyní roztroušena skrze čas."
				      + "Je potřeba spustit recall protocol");
	}
	/**
	 * Přidá slova se speciální akcí, aby byla rozpoznatelná AI. Slouží hlavně když hráč opraví AI v příběhu
	 */
	public void pridejSpecialniSlova()
	{
		SlovoStav aSlovo = new SlovoStav("stav", new String[]{"status"});
		SlovoOut aSlovo2 = new SlovoOut("out");
		SlovoOpravit aSlovo3 = new SlovoOpravit("opravit", new String[]{"oprava"});
		SlovoRecall aSlovo4 = new SlovoRecall("recall");
		
		akcniSlova.add(aSlovo);
		akcniSlova.add(aSlovo2);
		akcniSlova.add(aSlovo3);
		akcniSlova.add(aSlovo4);
		
	}
	/**
	 * Vrací stav funkčnosti pro danou část lodi
	 * 
	 * @param klic jméno stavu součásti který hledáme
	 * @return stav vrací true pokud byla součást opravena
	 */
	public boolean getStavPolozka(String klic) {
		return stav.get(klic);
	}
	
	/**
	 * Nastaví nebo přidá nový stav pro loď
	 * 
	 * @param klic jméno stavu který nastavujeme
	 * @param hodnota která se má přiřadit ke stavu
	 */
	public void setStavPolozka(String klic, boolean hodnota) {
		
		stav.put(klic, hodnota);
	}
	/**
	 * Vrátí herní plán
	 * 
	 * @return plan ve kterém je umístěna instance třídy Athena
	 */
	public HerniPlan getPlan() {
		return plan;
	}
	/**
	 * Navrátí prostor timeDrive. Použito hlavně pro akční slovo opravit.
	 * 
	 * @return timeDrive prostor přiřazený k instanci třídy
	 */
	public Prostor getTimeDrive() {
		return timeDrive;
	}
	/**
	 * Nastaví prostor timeDrive.
	 * 
	 * @param timeDrive prostor který se má přiřadit k instanci třídy
	 */
	public void setTimeDrive(Prostor timeDrive) {
		this.timeDrive = timeDrive;
	}
}
