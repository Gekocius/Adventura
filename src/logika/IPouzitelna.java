package logika;

public interface IPouzitelna {
	
	/**
	 * Metoda pro provedení akce ve hře.
	 * 
	 * @param nazevNastroje který hráč používá na jiný předmět
	 * @return výsledek akce
	 */
	public String pouzij(String nazevNastroje);

}
