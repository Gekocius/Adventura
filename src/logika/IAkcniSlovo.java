package logika;
/**
 * Rozhraní, které je použito základní třidou AkcniSlovo pro speciální slova 
 * 
 * @author Daniel Vrána
 * @version 1.0.0
 *
 */
public interface IAkcniSlovo {

	/**
	 * Provede akci ve hře skrze speciální slovo. 
	 * 
	 * @param athena instnace třídy Athena, ke které slovo patří
	 * @return výsledek akce
	 */
	public String provedAkci(Athena athena);
}
