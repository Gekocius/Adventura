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
import utils.Observer;

/**
 *
 * @author vrad00
 */
public class BatohGui extends TilePane implements Observer {
    
    ImageView[] okna;
    Hrac hrac;
    
    public BatohGui(Hrac hrac)
    {
        this.hrac = hrac;
        init();
    }
    
    private void init()
    {
        okna = new ImageView[hrac.getMaxKapacita()];
        this.getChildren().addAll(okna);
        update();
    }

    @Override
    public void update() {
        
        int i = 0;
        for (Vec vec  : hrac.getInvetar().values()) 
        {
            okna[i].setImage(new Image("zdroje/" + vec.getNazev()));
        }
    }
    
    
}
