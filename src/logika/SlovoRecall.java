package logika;
/**
 * Třída, která implementuje speciální slovo rozpoznávané chat botem. Toto slovo je používané pro závěrečnou akci úplného konce
 * 
 * @author Daniel Vrána
 * @version 1.0.0
 *
 */
public class SlovoRecall extends AkcniSlovo {

	
	public SlovoRecall(String nazev)
	{
		super(nazev);
	}
	
	/**
	 * Metoda, která provede akci ve hře a vypíše zprávu pro hráče. Zkontroluje požadavky pro úplný konec, 
	 * vypíše zprávu pro hráče a ukončí hru
	 * 
	 * @param athena chat bot, ke kterému je slovo přiřazené.
	 * @return výsledek akce
	 */
	@Override
	public String provedAkci(Athena athena) {
		if(athena.getStavPolozka("motoryOnline") && athena.getStavPolozka("timeDriveOnline"))
		{
			athena.getPlan().getHra().setEpilog("Recall activated, gathering four-dimensional locations.. launch in 10..5..\n"
					+ "Opět ztrácíš vědomí, když se po chvíli probudíš, celá posádka je zpátky. Po posledním experimentu o skok zpět v čase"
					+ "time drive nezvládl tok energie a spustil nouzový scatter protokol, který rozmístí členy posádky skrze čas a prostor."
					+ "Místost se stázovými komorami nebyla ovlivněna, prováděl si jejich rutiní údržbu proto si jako jediný zůstal na lodi."
					+ "Tedy skoro jediný, ještě s palubní AI A.T.H.E.N.A. S posádkou se nyní vratíš zpět na Zemi podat hlášení o všech"
					+ "provedených experimentech");
			athena.getPlan().getHra().setKonecHry(true);
			athena.setAktivni(false);
			return "Recall protocol zahájen";
		}
		
		return "Nelze provést. Máš už všechno opraveno?";
	}

}
