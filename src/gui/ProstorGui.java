/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import logika.HerniPlan;
import logika.Vec;
import main.Main;
import utils.Observer;

/**
 * Třída, která slouží k zobrazení inventáře prostoru na gui 
 *
 * @author Daniel Vrána
 */
public class ProstorGui extends TilePane implements Observer {
    
    private List<BatohPredmet> okna;
    private HerniPlan plan;
    private Main main;
    
    /**
     * Konstruktor, který vytváří a nastavuje jednotlivé prvky grafického rozhraní inventáře místnosti
     * 
     * @param main
     * 
     */
    public ProstorGui(Main main)
    {
    	this.main = main;
        this.plan = main.getHra().getHerniPlan();
        plan.registerObserver(this);
        plan.getAktualniProstor().registerObserver(this);
        init();
    }
    
    private void init()
    {
        okna = new ArrayList<BatohPredmet>();      
        this.setMinWidth(200);
        this.setHgap(3);
        this.setVgap(3);
        this.setPrefColumns(3);
        this.setPrefRows(2);
        plan.getHrac().registerObserver(this);
        update();
        
    }

    /**
     * Aktualizuje stav objektu, znovu načte inventář prostoru po změně
     * 
     */
    @Override
    public void update() {
    	
    	plan.getAktualniProstor().registerObserver(this);
    	okna.clear();
        
    	for(Vec vec : plan.getAktualniProstor().getVeciVProstoru().values())
    	{
    		BatohPredmet temp = new BatohPredmet(main, "seber");
    		temp.setObrazek(new Image("/zdroje/" + vec.getNazev() + ".png",64,64,false,true));
    		temp.setPopisek(vec.getNazev());
    		okna.add(temp);
    	}
    	
        this.getChildren().clear();
        this.getChildren().addAll(okna);
    }
    
    
}
