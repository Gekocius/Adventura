package logika;
/**
 * Třída která implementuje pro hru příkaz prozkoumej
 * 
 * @author Daniel Vrána
 * @version 1.0.0
 *
 */
public class PrikazProzkoumej implements IPrikaz {

	private static final String NAZEV = "prozkoumej";
	private HerniPlan plan;
	
	/**
	 * Konstruktor třídy, který vytváří novou instanci.
	 * 
	 * @param plan herní plán, s jehož pomocí příkaz reaguje s okolím ve hře
	 */
	public PrikazProzkoumej(HerniPlan plan)
	{
		this.plan = plan;
	}
	
	/**
	 * Vrátí popis pro předmět v místnosti. Pokud je použit parametr inventar, vrátí popis pro předmět z hráčova inventáře
	 * 
	 * @param inventar vrátí popis předmětu z hráčova inventáře, bez parametru popis předmětu z místnosti
	 * @return výsledek akce
	 */
	@Override
	public String provedPrikaz(String... parametry) {
		
		Vec zkoumanaVec;
		
		if(parametry.length == 0)
		{
			return "Co mám prozkoumat?";
		}
		
		if(parametry[0].equals("inventar"))
		{
			zkoumanaVec = ziskejVecZInventare(parametry[1]);
			if(zkoumanaVec == null)
			{
				return "Takovou věc u sebe nemáš";
			}
			else
			{
				return zkoumanaVec.prozkoumejZprava();
			}
			
		}
		else
		{
			zkoumanaVec = ziskejVecZProstoru(parametry[0]);
			if(zkoumanaVec == null)
			{
				return "Taková věc tu není";
			}
			else
			{
				return zkoumanaVec.prozkoumejZprava();
			}
		}
	}

    /**
    *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
    *  
    *  @ return nazev prikazu
    */
	@Override
	public String getNazev() {
		return NAZEV;
	}
	
	/**
	 * Pomocná metoda, která získá předmět z prostoru bez toho, aniž by jej trvale odebrala
	 * 
	 * @param nazev předmětu který hledáme
	 * @return odkaz na předmět
	 */
	private Vec ziskejVecZProstoru(String nazev)
	{
		Vec ziskanaVec = plan.getAktualniProstor().odeberVec(nazev);
		if(ziskanaVec != null)
		{
			plan.getAktualniProstor().vlozVec(ziskanaVec);
		}
		return ziskanaVec;
	}
	
	/**
	 * Pomocná metoda, která získá předmět z hráčova inventáře bez toho, aniž by jej trvale odebrala.
	 * 
	 * @param nazev předmětu který hledáme
	 * @return odkaz na předmět
	 */
	private Vec ziskejVecZInventare(String nazev)
	{
		Vec ziskanaVec = plan.getHrac().odeberVecZInvetare(nazev);
		if(ziskanaVec != null)
		{
			plan.getHrac().vlozVecDoInvetare(ziskanaVec);
		}
		return ziskanaVec;
	}


}
