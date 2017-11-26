package logika;

import java.util.Collection;
import java.util.Map;

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
	private int maxParametry = 0;
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
	/**
	 * Metoda vrací maximální počet parametrů pro daný příkaz
	 * 
	 */
	@Override
	public int getMaxParametry() {
		
		return maxParametry;
	}
	/**
	 * Metoda vrací mapu kolekcí. Kolekce obsahují všechny možné parametry pro příkaz.
	 * 
	 * @return mapa kolekcí
	 */
	@Override
	public Map<Integer, Collection<String>> getParametry()
	{
		return null;
	}

}
