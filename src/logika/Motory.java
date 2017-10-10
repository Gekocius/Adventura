package logika;

/**
 * Třída, která představuje objekt motor ve hře. Hráč po opravení motorů může spustit krátký konec a jsou potřeba i pro úplný konec.
 * 
 * @author Daniel Vrána
 * @version 1.0.0
 *
 */
public class Motory extends Vec implements IPouzitelna {

	private HerniPlan plan;
	/**
	 * Konstuktor, který volá kontrusktor předka pro nastavení názvu. Dále nastaví herní plán, ke kterému je přiřazena instance třídy
	 * 
	 * @param nazev pro předmět
	 * @param plan ke kterému předmět patří
	 */
	public Motory( String nazev, HerniPlan plan)
	{
		super(nazev, false);
		this.plan = plan;
	}
	/**
	 * Text, který se vrátí pokud se hráč pokusí sebrat nepřenositelnou věc.
	 * 
	 * @return zprava
	 */
	@Override
	public String neprenositelnaZprava()
	{
		return "Motory skutečně nemohu přenášet kolem";
	}
	
	/**
	 * Metoda z rozhraní IPouzitelná. Implementuje akce přiřazené k opravě motoru
	 * 
	 * @param název nástroje, který chcem použít na motory, v tomto případě musí být null pro úspěšnou akci
	 * @return výsledek akce
	 * 
	 */
	@Override
	public String pouzij(String nazevNastroje) {
		
		if(nazevNastroje == null)
		{
			plan.getAthena().setStavPolozka("motoryOnline", true);
			return "Motory byly opraveny";
		}
		return "Motory můžu opravit i bez nástroje, je jich tu kolem dost.";
	}

}
