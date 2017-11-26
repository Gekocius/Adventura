package logika;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Třída, která implementuje příkaz nasaď pro hru.
 * 
 * @author Daniel Vrána
 * @version 1.0.0
 */
public class PrikazNasad implements IPrikaz {

	private static final String NAZEV = "nasad";
	private int maxParametry = 1;
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
		Collection<String> tempList = new ArrayList<>();
		for(Vec vec : hrac.getInvetar().values())
		{
			if(vec.jeNositelna())
				tempList.add(vec.getNazev());
		}
		HashMap<Integer,Collection<String>> temp = new HashMap<>();
		temp.put(1,tempList);
		return temp;
	}

}
