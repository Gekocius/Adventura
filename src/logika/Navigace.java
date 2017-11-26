package logika;

/**
 * Třída představující speciální předmět navigace. Skrze tento předmět hráč dosáhne krátkého konce
 * 
 * @author Daniel Vrána
 * @version 1.0.0
 */
public class Navigace extends Vec implements IPouzitelna {

	private HerniPlan plan;
	private Athena athena;
	/**
	 * Konstruktor volající předka pro specifikování názvu. Dále nastavuje Herní plán ke kterému předmět patří
	 * a instanci třídy Athena se kterou bude předmět reagovat.
	 * 
	 * @param nazev pro předmět
	 * @param plan ke kterému předmět patří
	 * @param athena se kterou bude předmět reagovat
	 */
	public Navigace(String nazev, HerniPlan plan, Athena athena)
	{
		super(nazev, false);
		this.plan = plan;
		this.athena = athena;
	}
	/**
	 * Text, který se vrátí pokud se hráč pokusí sebrat nepřenositelnou věc.
	 * 
	 * @return zprava
	 */
	@Override
	public String neprenositelnaZprava()
	{
		return "Opravdu to nebudu odmontovávat";
	}
	
	/**
	 * Metoda z rozhraní IPouzitelna. Implementuje závěrečnou akci pro krátký konec
	 * 
	 * @param název nástroje, který se hráč pokouší na předmět použít. V tomto případě musí být null pro úspěšnou akci
	 * @return výsledek akce
	 * 
	 */
	public String pouzij(String nazevNastroje)
	{
		if(nazevNastroje == null)
		{
			if(athena.getStavPolozka("motoryOnline"))
			{
				plan.getHra().setEpilog("Vrátil ses úspěšně s lodí na Zemi, avšak bez posádky. Všechny systémy lodi byli při hyper skoku lodi zničeny"
						+ "díky chybě v AI jádře a všechna data nenávratně ztracena. Nikdo se nikdy nedozví co se pokazilo ani kde jsou všichni ostatní.");
				plan.getHra().setKonecHry(true);
				return "Destination - Planet Earth\n Departure in 10..9..";
			}
		return "Motory nejsou opravené!";
		}
		return "Tohle nepůjde. Navigace se používá bez nástrojů";

	}

}
