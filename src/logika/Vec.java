package logika;

/**
 * Třída, která představuje věc ve hře.
 * 
 * @author Daniel Vrána
 * @version 1.0.0
 *
 */
public class Vec {
	
	private String nazev;
	private boolean prenositelnost;
	private boolean nositelna;
	private String prozkoumej;

	/**
	 * Konstruktor třídy. Nastavuje název věci a zda je možné ji sebrat do inventáře
	 * 
	 * @param nazev věci
	 * @param prenositelnost true pokud je  možno přenášet, jinak false
	 */
	public Vec(String nazev, boolean prenositelnost)
	{
		this.nazev = nazev;
		this.prenositelnost = prenositelnost;
	}
	
	/**
	 * Vrátí název věci
	 * 
	 * @return název věc
	 */
	public String getNazev() {
		return nazev;
	}
	
	/**
	 * Nastaví název věci
	 * 
	 * @param nazev
	 */
	public void setNazev(String nazev) {
		this.nazev = nazev;
	}

	/**
	 * Určuje, zda hráč danou věc může sebrat a dát do svého inventáře
	 * 
	 * @return
	 */
	public boolean jePrenositelna() {
		return prenositelnost;
	}
	
	/**
	 * Text, který se vrátí pokud se hráč pokusí sebrat nepřenositelnou věc.
	 * 
	 * @return zprava
	 */
	public String neprenositelnaZprava()
	{
		return "Tuto věc nemůžeš sebrat";
	}
	
	/**
	 * Nastaví zprávu používanou příkazem prozkoumej
	 * 
	 * @param zprava
	 */
	public void setProzkoumejZprava(String zprava)
	{
		prozkoumej = zprava;
	}
	
	/**
	 * Navrátí zprávu s popisem věci po použití příkazu prozkoumej pokud je nastavená, jinak vrací výchozí zprávu.
	 * 
	 * @return zprava
	 */
	public String prozkoumejZprava()
	{
		if(prozkoumej != null)
		{
			return prozkoumej;
		}
		else
		{
			return "Nevím co k tomu říct";
		}
		
	}
	
	/**
	 * Vrací, jestli hráč může tuto věc použít jako oblek
	 * 
	 * @return true pokud je věc nositelná, jinak false
	 */
	public boolean jeNositelna() {
		return nositelna;
	}
	/**
	 * Nastaví, jestli hráč může tuto věc použít jako oblek
	 * 
	 * @param nositelnost true pokud má být věc nositelná, jinak false
	 */
	public void setNositelna(boolean nositelna) {
		this.nositelna = nositelna;
	}

	
}
