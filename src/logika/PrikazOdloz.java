package logika;
/**
 * Třída která implementuje pro hru příkaz odlož. Pomocí něj hráč odkládá předměty do aktuálního prostoru
 * 
 * @author Daniel Vrána
 * @version 1.0.0
 *
 */
public class PrikazOdloz implements IPrikaz {

	private String NAZEV = "odloz";
	private HerniPlan plan;
	
	/**
	 * Konstruktor třídy, který vytváří novou instanci.
	 * 
	 * @param plan herní plán, s jehož pomocí příkaz reaguje s okolím ve hře
	 */
    public PrikazOdloz(HerniPlan plan) {
        this.plan = plan;
    }
	
    /**
     * Odloží daný předmět z hráčova inventáře do prostoru
     * 
     * @param předmět který odkládáme
     * @return výsledek akce
     */
	@Override
	public String provedPrikaz(String... parametry) {
		
        if (parametry.length == 0) {
            return "Co mám odložit?";
        }

        String nazevOdkladaneVeci = parametry[0];

        Vec odkladanaVec = plan.getHrac().odeberVecZInvetare(nazevOdkladaneVeci);

        if (odkladanaVec == null) {
            return "Takovou věc u sebe nemáš";
        }
        else {
        	
        	Prostor aktualni = plan.getAktualniProstor();
        	aktualni.vlozVec(odkladanaVec);
        	return "Odložil jsi " + nazevOdkladaneVeci;
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
