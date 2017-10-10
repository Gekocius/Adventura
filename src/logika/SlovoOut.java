package logika;
/**
 * Třída, která implementuje speciální slovo rozpoznávané chat botem. Vypne chat bota aby hráč mohl pokračovat ve hře.
 * 
 * @author Daniel Vrána
 * @version 1.0.0
 *
 */
public class SlovoOut extends AkcniSlovo {

	/**
	 * Konstruktor, který vytváří novou instanci třídy. Přijímá název.
	 * 
	 * @param nazev kterým bude slovo voláno
	 */
	public SlovoOut(String nazev)
	{
		super(nazev);
	}
	
	/**
	 * Metoda, která provede akci ve hře a vypíše zprávu pro hráče. Vypne zachytávání textu chat botem,
	 * aby hráč mohl používat klasické příkazy
	 * 
	 * @param athena chat bot, ke kterému je slovo přiřazené.
	 * @return výsledek akce
	 */
	@Override
	public String provedAkci(Athena athena) {
		
		athena.setAktivni(false);
		return "Uvidíme se později";
	}

}
