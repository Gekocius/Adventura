package logika;

/**
 * Konzole fungují jako zámky pro jednotlivé prostory, po použití klíče odemknou k nim přiřazený východ.
 * 
 * @author Daniel Vrána
 * @version 1.0.0
 *
 */
public class Konzole extends Vec implements IPouzitelna {
	
	private Prostor pridruzeny;
	private Vec klic;

	/**
	 * Konstruktor, který nastavuje název instance voláním předka a přiřadí zamčený prostor ke konzoli
	 * 
	 * @param nazev věci ve hře
	 * @param pridruzeny prostor, který má konzole odemykat
	 */
	public Konzole(String nazev, Prostor pridruzeny)
	{
		super(nazev, false);
		this.pridruzeny = pridruzeny;
	}
	
	/**
	 * Vrátí věc, která slouží jako klíč ke konzoli
	 * 
	 * @return klic ke konzoli
	 */
	public Vec getKlic() {
		return klic;
	}

	/**
	 * Nastaví věc, která slouží jako klíč ke konzoli
	 * 
	 * @param klic k nastavení
	 */
	public void setKlic(Vec klic) {
		this.klic = klic;
	}
	/**
	 * Text, který se vrátí pokud se hráč pokusí sebrat nepřenositelnou věc.
	 * 
	 * @return zprava
	 */
	@Override
	public String neprenositelnaZprava()
	{
		return "Proč bych trhal konzoli ze zdi?";
	}
	/**
	 * Nastaví zprávu používanou příkazem prozkoumej
	 * 
	 * @param zprava
	 */
	@Override
	public String prozkoumejZprava()
	{
		return "Otevře východ do " + pridruzeny.getNazev();
	}
	
	/**
	 * Metoda z rozhraní IPouzitelna. Implementuje akci, kterou má předmět vykonat po příkazu použij
	 * 
	 * @param nazevNastroje název nástroje který chceme použít na tento předmět
	 * @return výsledek akce po použití
	 */
	public String pouzij(String nazevNastroje)
	{
		if (nazevNastroje != null) {
			if (nazevNastroje.equals(klic.getNazev())) {
				pridruzeny.setOdemceno(true);
				return "Východ " + pridruzeny.getNazev() + " je nyní odemčen";
			} else {
				return "Toto není správný klíč";
			} 
		}
		return "Musíš použít klíč";
		
	}
	
}
