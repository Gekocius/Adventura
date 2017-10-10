package logika;

/**
 *  Třída Hra - třída představující logiku adventury.
 * 
 *  Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan, která inicializuje mistnosti hry
 *  a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 *  Vypisuje uvítací a ukončovací text hry.
 *  Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Daniel Vrána
 *@version    pro školní rok 2016/2017 - 1.0.0
 */

public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    private HerniPlan herniPlan;
    private boolean konecHry = false;
    private String epilog;

    /**
     *  Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */
    public Hra() {
        herniPlan = new HerniPlan();
        herniPlan.setHra(this);
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazSeber(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazPouzij(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazProzkoumej(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazInventar(herniPlan.getHrac()));
        platnePrikazy.vlozPrikaz(new PrikazNasad(herniPlan.getHrac()));
    }

    /**
     *  Vrátí úvodní zprávu pro hráče.
     *  
     *  @return zpráva která uvadí hráče do hry
     */
    public String vratUvitani() {
        return "**CRITICAL. SYSTEM FAILURE**\n" +
               "*Alarm pomalu utichá, zvuky kolem se zdají být stále dál a dál, ztrácíš vědomí*\n"+
               "Probouzíš se s bolestí hlavy, na zemi, pomalu se zvedáš a rozhlížíš se kolem dokola. Stázové komory?\n"+
               "Co tady dělají stázové komory a kde to vůbec jsi? Poslední vzpomínka… ženský hlas oznamující\n"+
               "STABILITY CRITICAL a jméno.. Athena?\n" +
               herniPlan.getAktualniProstor().dlouhyPopis();
    }
    /**
     * Nastaví závěrečnou zprávu pro hráče - epilog příběhu
     * 
     * @param epilog co se má nstavit
     */
    public void setEpilog(String epilog)
    {
    	this.epilog = epilog;
    }
    
    /**
     *  Vrátí závěrečnou zprávu pro hráče. Pokud není nastavena, vrátí výchozí.
     *  
     *  @return epilog závěrečná zpráva pro hráče
     */
    public String vratEpilog() {
    	
    	if(epilog != null)
    	{
    		return epilog;
    	}
    		
    	
        return "Dík, že jste si zahráli.  Ahoj.";
    }
    
    /** 
     * Vrací true, pokud hra skončila.
     * 
     * @return konecHry
     */
     public boolean konecHry() {
        return konecHry;
    }

    /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *  Pokud je aktivní třída instance třídy Athena, posílá text k dalšímu zpracování.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return        vrací se řetězec, který se má vypsat na obrazovku
     */
     public String zpracujPrikaz(String radek) {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        for(int i=0 ;i<parametry.length;i++){
           	parametry[i]= slova[i+1];  	
        }
        String textKVypsani=" .... ";
        
        if(herniPlan.getAthena().jeAktivni())
        {
        	textKVypsani = herniPlan.getAthena().zpracujText(radek);
        }
        else
        {
            if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
                IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
                textKVypsani = prikaz.provedPrikaz(parametry);
            }
            else {
                textKVypsani="Nevím co tím myslíš? Tento příkaz neznám. ";
            }
        }

        return textKVypsani;
    }
    
     /**
     *  Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     *  mohou ji použít i další implementace rozhraní Prikaz.
     *  
     *  @param  konecHry  hodnota false = konec hry, true = hra pokračuje
     */
    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }
    
     /**
     *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     *  @return     odkaz na herní plán
     */
     public HerniPlan getHerniPlan(){
        return herniPlan;
     }
    
}

