package logika;
/**
 * Třída, která implementuje speciální slovo rozpoznávané chat botem. Použito k vrácení zprávě o možných opravách lodi
 * 
 * @author Daniel Vrána
 * @version 1.0.0
 *
 */

public class SlovoOpravit extends AkcniSlovo {

	/**
	 * Konstruktor třídy, přijímá jeho název a možné aliasy.
	 * 
	 * @param nazev výchozí podoba slova
	 * @param aliasy kterými bude slovo také vyvoláno
	 */
	public SlovoOpravit(String nazev, String[] aliasy)
	{
		super(nazev,aliasy);
	}
	
	/**
	 * Metoda, která provede akci ve hře a vypíše zprávu pro hráče. Zde zjišťujeme, zda má hráč na sobě správý oblek
	 * a vypíše zprávu, co je třeba opravit na lodi.
	 * 
	 * @param athena chat bot, ke kterému slovo patří
	 * @return výsledek akce
	 */
	@Override
	public String provedAkci(Athena athena) {

		if(athena.getPlan().getHrac().getOblek().getNazev().equals("skafandr"))
		{
			athena.getTimeDrive().setOdemceno(true);
			athena.getPlan().getHrac().vlozVecDoInvetare(new Vec("součástky", true));
			return "Máš nasazený skafandr, výborně. Nyní můžeš jít opravit Time Drive. Tohle ti pomůže.\n"
					+ "*Součástky byly přidány do tvého inventáře*";
		}
		else
		{
			return "Je potřeba opravit Time Drive ke spuštění recall protocolu. Ovšem v prostoru není žádný tlak, došlo k proražení vnějšího pláště."
			+ "Potřebuješ skafandr aby ses tam mohl pohybovat. Zkus se podívat do obytných prostorů.";
		}
	}

}
