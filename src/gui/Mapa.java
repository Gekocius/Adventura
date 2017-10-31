/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import logika.Hra;
import logika.IHra;
import main.Main;
import utils.Observer;

/**
 *
 * @author vrad00
 */
public class Mapa extends AnchorPane implements Observer {
    
    public IHra hra;
    Circle tecka;
    public Mapa(IHra hra)
    {
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        init();
    }
    
    private void init()
    {
        ImageView obrazekImageView = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/mapa.jpg"), 300, 300, false, true));
        
        tecka = new Circle(20, Paint.valueOf("red"));
        
        //this.setTopAnchor(tecka, 25.0);
        //this.setLeftAnchor(tecka, 25.0);
        
        this.getChildren().addAll(obrazekImageView, tecka);
        update();
        
    }

    @Override
    public void update() {
        this.setTopAnchor(tecka, hra.getHerniPlan().getAktualniProstor().getPosTop());
        this.setLeftAnchor(tecka, hra.getHerniPlan().getAktualniProstor().getPosLeft());
    }
    
}
