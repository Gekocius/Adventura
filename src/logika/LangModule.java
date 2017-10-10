package logika;
/**
 * Třída, která představuje předmět LangModule ve hře. Hráč jej použije k opravě AI.
 * 
 * @author Daniel Vrána
 * @version 1.0.0
 *
 */
public class LangModule extends Vec implements IPouzitelna {

	private HerniPlan plan;
	
	/**
	 * Konstruktor který vytvoří instanci třídy LangModule. Volá konstruktor předka kde nastaví název
	 * a poté herní plán, ve kterém se instance třídy nachází.
	 * 
	 * @param nazev předmětu
	 * @param plan herní plán, ve kterém se nechází tento předmět.
	 */
	public LangModule(String nazev, HerniPlan plan)
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
		return "Opravdu to nebudu odmontovávat";
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
			
			if (nazevNastroje.equals("čip")) 
			{
				plan.getAthena().mapujSlova();
				plan.getAthena().pridejSpecialniSlova();
				plan.getAktualniProstor().odeberVec(this.getNazev());
				plan.getHrac().odeberVecZInvetare("čip");
				return "Jazykový modul je nyní opraven";
				
			} 
		}
		return "Bez čipu to neopravím";
		
	}

}
