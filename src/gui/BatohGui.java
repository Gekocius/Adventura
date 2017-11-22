/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import logika.Hrac;
import logika.Vec;
import main.Main;
import utils.Observer;

/**
 * Třída, která slouží k zobrazení batohu hráče na gui 
 *
 * @author vrad00
 */
public class BatohGui extends TilePane implements Observer {
    
    ImageView[] okna;
    Hrac hrac;
    
    
    /**
     * Konstruktor, který vytváří a nsatavuje jednotlivé prvky grafického rozhraní batohu
     * 
     */
    public BatohGui(Hrac hrac)
    {
        this.hrac = hrac;
        hrac.registerObserver(this);
        init();
    }
    
    private void init()
    {
        okna = new ImageView[hrac.getMaxKapacita()];       
        this.setMinWidth(200);
        this.setHgap(3);
        this.setVgap(3);
        this.setPrefColumns(3);
        this.setPrefRows(2);
        update();
        
    }

    /**
     * Aktualizuje stav objektu, znovu načte batoh hráče po změně
     * 
     */
    @Override
    public void update() {
        
        for(int i = 0; i< okna.length; i++)
        {
        	okna[i] = new ImageView();
        }
    	
        int i = 0;
        for (Vec vec : hrac.getInvetar().values()) 
        {
            okna[i].setImage(new Image("./zdroje/"+vec.getNazev()+".png", 64, 64, false, true));
            i++;
        }
        this.getChildren().clear();
        this.getChildren().addAll(okna);
    }
    
    
}
