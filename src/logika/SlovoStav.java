package logika;

/**
 * Třída, která implementuje speciální slovo rozpoznávané chat botem. Použito k vrácení stavu lodi
 * 
 * @author Daniel Vrána
 * @version 1.0.0
 *
 */
public class SlovoStav extends AkcniSlovo {
	
	/**
	 * Konstruktor třídy, přijímá jeho název a možné aliasy.
	 * 
	 * @param nazev výchozí podoba slova
	 * @param aliasy kterými bude slovo také vyvoláno
	 */
	public SlovoStav(String nazev, String[] aliasy)
	{
		super(nazev,aliasy);
	}
	
	/**
	 * Metoda, která provede akci ve hře a vypíše zprávu pro hráče. Zjistí a vrátí stav lodi
	 * 
	 * @param athena chat bot, ke kterému je slovo přiřazené.
	 * @return výsledek akce
	 */
	@Override
	public String provedAkci(Athena athena) 
	{
		String motoryStav = athena.getStavPolozka("motoryOnline") ? "Motory : Aktivní\n" : "Motory : Neaktivní - Vyžadována oprava\n";
		String timeDriveStav = athena.getStavPolozka("timeDriveOnline") ? "Time Drive: Aktivní\n" : "Time Drive : Neaktivní - Vyžadována oprava\n";
		return "Aktuální stav lodi:\n" + motoryStav + timeDriveStav;
	}

}
