package logika;


/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2016/2017
 */
public class HerniPlan {
    
    private Prostor aktualniProstor;
    private Hrac hrac;
    private Athena athena;
    private Hra hra;
    
     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  
     */
    public HerniPlan() {
        zalozProstoryHry();

    }
    
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {       
        // Hra
        hrac = new Hrac(5);
        
    	// Založení prostorů
        Prostor komory = new Prostor("komory", "Místnost se stázovými komorami, všechny jsou prázdné.");
        Prostor chodbaA = new Prostor("chodba-a", "Obyčejná chodba");
        Prostor laborator = new Prostor("laborator", "Laboratoř s různým vybavením");
        Prostor timeDrive = new Prostor("time-drive", "Místnost s časoprostorovým pohonem, není tu žádný vzduch.");
        Prostor motory = new Prostor("motory", "Místnost s hyperpohonem");
        Prostor aiControl = new Prostor("AI-Control", "Kulatá místnost s holografickým projektorem uprostřed");
        Prostor mustek = new Prostor("můstek", "Hlavní kontrolní středisko lodi. Zde se nachází všechny řidící systémy");
        Prostor chodbaB = new Prostor("chodba-b", "Další obyčejná chodba");
        Prostor obytne = new Prostor("obydlí", "V této místnosti přespává celá posádka");
        Prostor aiCore = new Prostor("AI-Core","Jádro AI. Zde se nachází všechny systémy a moduly AI");
        
        
        // Vytvoření věcí
        athena = new Athena("athena",false, this); //AI lodi, interakční věc ve hře
        athena.setTimeDrive(timeDrive);
        
        Vec idKarta = new Vec("karta", true);
        Vec adminKarta = new Vec("adminKarta", true);
        Vec cip = new Vec("čip", true);
        
        //Speciální předměty
        LangModule langModule = new LangModule("langModule", this);
        Navigace navigace = new Navigace("navigace", this, athena);
        Motory motor = new Motory("motor", this);
        TimeDrive timeDriveVec = new TimeDrive("time-drive", athena);
        
        Vec spaceSuit = new Vec("skafandr",true);
        spaceSuit.setNositelna(true);
        
        Konzole konzoleKomory = new Konzole("konzole", chodbaA);
        konzoleKomory.setKlic(idKarta);
        
        Konzole konzoleAIC = new Konzole("konzole-aic", aiControl);
        konzoleAIC.setKlic(adminKarta);
        
        //Nastavení zpráv pro příkaz prozkoumej
        idKarta.setProzkoumejZprava("Moje ID karta, můžu ji použít k otevírání dvěří");
        adminKarta.setProzkoumejZprava("Administrátorská karta, mohu jí použít k přístupu do prostorů s omezeným přístupem");
        langModule.setProzkoumejZprava("Jazykový modul AI. je potřeba ho opravit, myslím, že vím jak, ale potřebuji čip");
        cip.setProzkoumejZprava("S tímhle čipem mohu opravit AI");
        navigace.setProzkoumejZprava("Pokud jsou opraveny motory, mohu s lodí odletět");
        motor.setProzkoumejZprava("Po opravě motorů bude loď znovu letu schopná. Vím jak je opravit a nepotřebuji k tomu nic speciálního.");
        
        // Vložení věcí
        hrac.vlozVecDoInvetare(idKarta);
        komory.vlozVec(konzoleKomory);
        laborator.vlozVec(adminKarta);
        chodbaA.vlozVec(konzoleAIC);
        aiControl.vlozVec(athena);
        mustek.vlozVec(navigace);
        obytne.vlozVec(cip);
        obytne.vlozVec(spaceSuit);
        motory.vlozVec(motor);
        aiCore.vlozVec(langModule);
        timeDrive.vlozVec(timeDriveVec);
        
        // Přiřazení východů
        komory.setVychod(chodbaA);
        
        chodbaA.setVychod(komory);
        chodbaA.setVychod(laborator);
        chodbaA.setVychod(timeDrive);
        chodbaA.setVychod(motory);
        chodbaA.setVychod(aiControl);
        
        laborator.setVychod(chodbaA);
        laborator.setOdemceno(true);
        
        motory.setVychod(chodbaA);
        motory.setOdemceno(true);
        
        timeDrive.setVychod(chodbaA);
        
        aiControl.setVychod(chodbaA);
        aiControl.setVychod(chodbaB);
        aiControl.setVychod(mustek);
        
        mustek.setVychod(aiControl);
        mustek.setOdemceno(true);
        
        chodbaB.setVychod(aiControl);
        chodbaB.setVychod(obytne);
        chodbaB.setVychod(aiCore);
        chodbaB.setOdemceno(true);
        
        obytne.setVychod(chodbaB);
        obytne.setOdemceno(true);
        
        aiCore.setVychod(chodbaB);
        aiCore.setOdemceno(true);
                
        aktualniProstor = komory;  // hra začíná u stázových komor
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
    }
    
    /**
     * Metoda vrací instanci hráče v tomto herním plánu
     * 
     * @return hrac přiřazený k instanci
     */
    public Hrac getHrac()
    {
    	return hrac;
    }
    /**
     * Metoda vrací instanci AI (chat bota) přiřazené k tomuto plánu
     * 
     * @return
     */
    public Athena getAthena()
    {
    	return athena;
    }
    
    /**
     * Vrací instanci třídy hra, většinou té, které jí vytvořila
     * 
     * @return hra přiřazená k instanci 
     */
    public Hra getHra()
    {
    	return hra;
    }
    
    /**
     * Nastaví instanci třídy Hra k této instanci
     * 
     * @param hra co se má nastavit
     */
    public void setHra(Hra hra)
    {
    	this.hra = hra;
    }
    

}