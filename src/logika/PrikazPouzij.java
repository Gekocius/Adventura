package logika;
/**
 * Třída, která implementuje pro hru příkaz pouzij. Hráč pomocí tohoto příkazu může interagovat s různými předměty.
 * 
 * @author Daniel Vrána
 * @version 1.0.0
 *
 */
public class PrikazPouzij implements IPrikaz {

	private static final String NAZEV = "pouzij";
	private HerniPlan plan;
	
	/**
	 * Konstruktor třídy, který vytváří novou instanci.
	 * 
	 * @param plan herní plán, s jehož pomocí příkaz reaguje s okolím ve hře
	 */
	public PrikazPouzij(HerniPlan plan)
	{
		this.plan = plan;
	}
	/**
	 * Použije daný předmět specifikovaný v parametru. Pokud jsou specifikovány dva parametry
	 * použije první předmět na druhý. S jedním parametrem provede akci definovanou u předmětu.
	 * 
	 * @param použij co
	 * @param použij na co
	 * @return výsledek akce
	 */
	@Override
	public String provedPrikaz(String... parametry) {
		
		if(parametry.length == 0)
		{
			return "Co mám použít?";
		}
		else if(parametry.length > 2)
		{
			return "Zadal jsi příliš mnoho parametrů";
		}
		else
		{	
			String nazevNastroje = parametry.length == 1 ? null : parametry[0];
			String nazevVeci = parametry.length == 1 ? parametry[0] : parametry[1];
			
			Vec pouzivanaVec = ziskejVecZProstoru(nazevVeci);
			Hrac hrac = plan.getHrac();
			
			if(pouzivanaVec == null)
			{
				return "Taková věc tu není";
			}
			else if(!(pouzivanaVec instanceof IPouzitelna))
			{
				return "Tohle nelze použít";
			}
			else if(!hrac.jeVecVInventari(nazevNastroje) && nazevNastroje != null) //nástroj musí být v hráčově inventáři
			{
				return "Předmět " + nazevNastroje + " nemáš u sebe";
			}
			else
			{
				IPouzitelna vec = (IPouzitelna)pouzivanaVec;
				return vec.pouzij(nazevNastroje);
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
	 * Pomocná metoda použita pouze v této třídě. Získá odkaz na předmět bez toho aniž by byl trvale odebrán z prostoru.
	 * 
	 * @param předmět který hledáme
	 * @return získaný předmět.
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
	
	

}
