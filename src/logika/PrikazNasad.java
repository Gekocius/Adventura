package logika;

/**
 * Třída, která implementuje příkaz nasaď pro hru.
 * 
 * @author Daniel Vrána
 * @version 1.0.0
 */
public class PrikazNasad implements IPrikaz {

	private static final String NAZEV = "nasad";
	private Hrac hrac;
	
	/**
	 * Konstruktor, který vytváří novou instanci třídy.
	 * 
	 * @param hrac s kterým má příkaz interagovat.
	 */
	public PrikazNasad(Hrac hrac)
	{
		this.hrac = hrac;
	}
	
	/**
	 * Metoda, která nasadí oblek na hráče pokud ho má u sebe v inventáři.
	 * 
	 * @param oblek který chce hráč nasadit
	 * @return výsledek akce
	 */
	@Override
	public String provedPrikaz(String... parametry) {
		
		Vec nasazovana = hrac.odeberVecZInvetare(parametry[0]);
		
		if(parametry.length == 0)
		{
			return "Co si mám nasadit?";
		}
		else if(parametry.length > 1)
		{
			return "Zadal jsi příliš mnoho parametrů";
		}
		else if(nasazovana == null)
		{
			return "Takový oblek u sebe nemáš";
		}
		else if(!nasazovana.jeNositelna())
		{
			hrac.vlozVecDoInvetare(nasazovana);
			return "Tohle nelze vzít na sebe";
		}
		else
		{
			hrac.setOblek(nasazovana);
			return "Nyní máš na sobě " + nasazovana.getNazev();
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
