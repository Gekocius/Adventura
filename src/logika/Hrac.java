package logika;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.Observer;
import utils.Subject;
/**
 * 
 * Třída Hrac představuje postavu hráče ve hře. Je v ní definován i hráčův inventář a potřebné interakce
 * s inventářem.
 * 
 * @author Daniel Vrána
 * @version 1.0.0
 *
 */
public class Hrac implements Subject {
	
	private Map<String, Vec> inventar;
	private int maxKapacita;
	private Vec oblek;
	private List<Observer> observers =  new ArrayList<Observer>();
	
	/**
	 * Vytvoří instanci hráče s danou kapacitou inventáře
	 * 
	 * @param kapacita maximální kapacita inventáře
	 */
	public Hrac(int kapacita)
	{
		inventar = new HashMap<>();
		maxKapacita = kapacita;
		oblek = new Vec("žádný", true);
	}

	/**
	 * Vrátí maximální kapacitu inventáře
	 * 
	 * @return maxKapacita
	 */
	public int getMaxKapacita() {
		return maxKapacita;
	}
	
	/**
	 * Vrátí celý inventář
	 * 
	 * @return inventar
	 */
	public Map<String, Vec> getInvetar()
	{
		return inventar;
	}
	
	/**
	 * Vloží zadanou věc do invetáře
	 * 
	 * @param vec
	 */
	public void vlozVecDoInvetare(Vec vec)
	{
		inventar.put(vec.getNazev(), vec);
		notifyObservers();
	}
	/**
	 * Odebere zadanou věc z inventáře a vráti na ni odkaz
	 * 
	 * @param nazev věci co chceme odebrat
	 * @return odkaz na odebranou věc
	 */
	public Vec odeberVecZInvetare(String nazev)
	{
		Vec odebrana = inventar.remove(nazev);
		notifyObservers();
		return odebrana;
	}
	
	/**
	 * Zjistí jestli je věc v inventáři a vrátí výsledek
	 * 
	 * @param nazevVeci kterou hledáme v inventáři
	 * @return true pokud se věc v inventáři nachází, jinak false
	 */
	public boolean jeVecVInventari(String nazevVeci)
	{
		for(String nazev : inventar.keySet())
		{
			if(nazev.equals(nazevVeci))
			{
				return true;
			}
		}
		return false;

	}
	/**
	 * Vrací oblek, který má hráč právě na sobě
	 * 
	 * @return oblek
	 */
	public Vec getOblek() {
		return oblek;
	}
	
	/**
	 * Nastaví oblek na hráče
	 * 
	 * @return oblek
	 */
	
	public void setOblek(Vec oblek) {
		this.oblek = oblek;
	}

    /**
     * Přidá observera
     * 
     * @param observer k přidání
     */
	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);
		
	}
	
    /**
     * Odebere specifikovaného observera
     * 
     * @param observer k odebrání
     */
	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
		
	}
	
    /**
     * Oznámí změnu stavu objektu všem registrovaným observerům
     * 
     */
	@Override
	public void notifyObservers() {
		for(Observer observer : observers)
			observer.update();
		
	}
        
	
}
