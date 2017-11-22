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
import utils.Observer;

/**
 * Třída, která slouží k zobrazení inventáře prostoru na gui 
 *
 * @author vrad00
 */
public class ProstorGui extends TilePane implements Observer {
    
    List<ImageView> okna;
    HerniPlan plan;
    
    /**
     * Konstruktor, který vytváří a nastavuje jednotlivé prvky grafického rozhraní inventáře místnosti
     * 
     */
    public ProstorGui(HerniPlan plan)
    {
        this.plan = plan;
        plan.registerObserver(this);
        plan.getAktualniProstor().registerObserver(this);
        init();
    }
    
    private void init()
    {
        okna = new ArrayList<ImageView>();      
        this.setMinWidth(200);
        this.setHgap(3);
        this.setVgap(3);
        this.setPrefColumns(3);
        this.setPrefRows(2);
        update();
        
    }

    /**
     * Aktualizuje stav objektu, znovu načte inventář prostoru po změně
     * 
     */
    @Override
    public void update() {
        
    	for(Vec vec : plan.getAktualniProstor().getVeciVProstoru().values())
    	{
    		okna.add(new ImageView(new Image("/zdroje/" + vec.getNazev() + ".png",64,64,false,true)));
    	}
    	
        this.getChildren().clear();
        this.getChildren().addAll(okna);
    }
    
    
}
