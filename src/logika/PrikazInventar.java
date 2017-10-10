package logika;
/**
 * Třída, která implementuje příkaz inventar pro hru. Zajistí vypsání hráčova inventáře
 * 
 * @author Daniel Vrána
 * @version 1.0.0
 *
 */
public class PrikazInventar implements IPrikaz {

	private static final String NAZEV = "inventar";
	private Hrac hrac;
	/**
	 * Konstruktor který založí novou instanci třídy.
	 * 
	 * @param hrac pro kterého se bude vypisovat inventář.
	 */
	public PrikazInventar(Hrac hrac)
	{
		this.hrac = hrac;
	}
	/**
	 * Metoda, která vypíše hráčův inventář na obrazovku. Nepotřebuje parametry
	 * 
	 */
	@Override
	public String provedPrikaz(String... parametry) {

		String vypis = "Věci v inventáři: ";
		for(String nazevVeci : hrac.getInvetar().keySet())
		{
			vypis += nazevVeci + " ";
		}
		return vypis;
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
