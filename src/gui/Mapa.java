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
import logika.IHra;
import main.Main;
import utils.Observer;

/**
 * Třída, která zobrazuje mapu a nastavuje pozici hráče
 * @author Daniel Vrana
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
        ImageView obrazekImageView = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/mapa.png"), 851, 300, false, true));
        
        tecka = new Circle(15, Paint.valueOf("red"));

        this.getChildren().addAll(obrazekImageView, tecka);
        update();
        
    }
    
    /**
     * Odregistruje Observer od staré instance hry, která byla ukončena a zaregistruje Observer na novou instanci
     * 
     * @param novaHra nová instance hry
     */
    public void newGame(IHra novaHra)
    {
        hra.getHerniPlan().removeObserver(this);
        hra = novaHra;
        hra.getHerniPlan().registerObserver(this);
        update();
    }

    /**
     * Aktualizuje stav instance. Nastaví novou pozici hráče.
     * 
     */
    @Override
    public void update() {
        this.setTopAnchor(tecka, hra.getHerniPlan().getAktualniProstor().getPosTop());
        this.setLeftAnchor(tecka, hra.getHerniPlan().getAktualniProstor().getPosLeft());
    }
    
}
