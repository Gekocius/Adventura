package logika;
/**
 * Třída která implementuje pro hru příkaz seber. Pomocí něj hráč sbírá předměty.
 * 
 * @author Daniel Vrána
 * @version 1.0.0
 *
 */
public class PrikazSeber implements IPrikaz {

	private String NAZEV = "seber";
	private HerniPlan plan;
	
	/**
	 * Konstruktor třídy, který vytváří novou instanci.
	 * 
	 * @param plan herní plán, s jehož pomocí příkaz reaguje s okolím ve hře
	 */
    public PrikazSeber(HerniPlan plan) {
        this.plan = plan;
    }
	
    /**
     * Sebere daný předmět z prostoru a dá ho do hráčova inventáře
     * 
     * @param předmět který sbíráme
     * @return výsledek akce
     */
	@Override
	public String provedPrikaz(String... parametry) {
		
        if (parametry.length == 0) {
            return "Co mám sebrat?";
        }

        String nazevSbiraneVeci = parametry[0];

        Vec sbiranaVec = plan.getAktualniProstor().odeberVec(nazevSbiraneVeci);

        if (sbiranaVec == null) {
            return "Taková věc tu není";
        }
        else {
        	
        	if(sbiranaVec.jePrenositelna())
        	{
        		Hrac hrac = plan.getHrac();
        		
        		if(hrac.getInvetar().size() != hrac.getMaxKapacita())
        		{
        			hrac.vlozVecDoInvetare(sbiranaVec);
        			return "Sebral jsi " + sbiranaVec.getNazev();
        		}
        		else
        		{
        			plan.getAktualniProstor().vlozVec(sbiranaVec);
        			return "Inventář je plný";
        		}
        		
        	}
        	else
        	{
        		plan.getAktualniProstor().vlozVec(sbiranaVec);
        		return sbiranaVec.neprenositelnaZprava();
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

}
