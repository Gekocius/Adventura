package logika;
/**
 * Třída, která představuje předmět TimeDrive ve hře. Hráč jej použije k opravě lodi. Je jedním z poždavků na úplný konec.
 * 
 * @author Daniel Vrána
 * @version 1.0.0
 *
 */
public class TimeDrive extends Vec implements IPouzitelna {

	private Athena athena;
	/**
	 * Konstruktor který vytvoří instanci třídy LangModule. Volá konstruktor předka kde nastaví název
	 * a poté chat bota, se kterým předmět interaguje.
	 * 
	 * @param nazev předmětu
	 * @param athena chat bot, se kterým předmět interaguje
	 */
	public TimeDrive(String nazev, Athena athena)
	{
		super(nazev, false);
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
		return "Tohle úplně nepůjde";
	}
	
	/**
	 * Metoda z rozhraní IPouzitelna. Implementuje akci, kterou má předmět vykonat po příkazu použij.
	 * Pokud má hráč potřebný nástroj opraví time drive, který je důležitý k úplnému vítězství.
	 * 
	 * @param nazevNastroje název nástroje který chceme použít na tento předmět
	 * @return výsledek akce po použití
	 */
	@Override
	public String pouzij(String nazevNastroje) {
		
		if (nazevNastroje != null) {
			
			if (nazevNastroje.equals("součástky")) 
			{
				athena.setStavPolozka("timeDriveOnline", true);
				return "Time drive je aktivní!";
			}
		}
		return "Musíš použít součástky";
	}
}
