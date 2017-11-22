package logika;

import java.util.ArrayList;
import java.util.List;

import gui.ProstorGui;
import utils.Observer;
import utils.Subject;


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
public class HerniPlan implements Subject {
    
    private Prostor aktualniProstor;
    private Hrac hrac;
    private Athena athena;
    private Hra hra;
    private ProstorGui prostorGui;
    private List<Observer> observers =  new ArrayList<Observer>();
    
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
        Prostor komory = new Prostor("komory", "Místnost se stázovými komorami, všechny jsou prázdné.",5,0);
        Prostor chodbaA = new Prostor("chodba-a", "Obyčejná chodba",10,0);
        Prostor laborator = new Prostor("laborator", "Laboratoř s různým vybavením",15,0);
        Prostor timeDrive = new Prostor("time-drive", "Místnost s časoprostorovým pohonem, není tu žádný vzduch.",20,0);
        Prostor motory = new Prostor("motory", "Místnost s hyperpohonem",25,0);
        Prostor aiControl = new Prostor("AI-Control", "Kulatá místnost s holografickým projektorem uprostřed",30,0);
        Prostor mustek = new Prostor("můstek", "Hlavní kontrolní středisko lodi. Zde se nachází všechny řidící systémy",0,0);
        Prostor chodbaB = new Prostor("chodba-b", "Další obyčejná chodba",0,0);
        Prostor obytne = new Prostor("obydlí", "V této místnosti přespává celá posádka",0,0);
        Prostor aiCore = new Prostor("AI-Core","Jádro AI. Zde se nachází všechny systémy a moduly AI",0,0);
        
        
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
        hrac.vlozVecDoInvetare(new Vec("test",true));
        komory.vlozVec(new Vec("test2",true));
        komory.vlozVec(new Vec("test3",true));
        komory.vlozVec(new Vec("test4",true));
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
                
        aktualniProstor = komory;;  // hra začíná u stázových komor
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
       if(prostorGui != null)
       {
           aktualniProstor.removeObserver(prostorGui);
           aktualniProstor = prostor;
           aktualniProstor.registerObserver(prostorGui);
           notifyObservers();
       }
       else
       {
    	   aktualniProstor = prostor;
    	   notifyObservers();
       }

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
    
    public void setProstorGui(ProstorGui prostorGui)
    {
    	this.prostorGui = prostorGui;
    }

    /**
     * Přidá observera
     * 
     * @param observer k přidání
     */
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Odebere specifikovaného observera
     * 
     * @param observer k odebrání
     */
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Oznámí změnu stavu objektu všem registrovaným observerům
     * 
     */
    @Override
    public void notifyObservers() {
        
         for (Observer observer : observers) {
            observer.update();
        }
    }
    

}
