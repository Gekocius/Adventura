/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logika.Hra;
import logika.IHra;
import main.Main;

/**
 *Třída, která slouží jako horní lišta menu na hlavním okně.
 *
 * @author Daniel Vrana
 */
public class MenuLista extends MenuBar {
    
    IHra hra;
    Main main;
    /**
     * Konstruktor. Vytváří všechny potřebné prvky na liště.
     * 
     * @param hra
     * @param main
     */
    public MenuLista(IHra hra, Main main)
    {
        this.hra = hra;
        this.main = main;
        init();
    }
    
    private void init()
    {
        Menu novySoubor = new Menu("Adventura");
        Menu about = new Menu ("Napoveda");
        
        MenuItem novaHra = new MenuItem("Nová hra");
        //new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/ikona.png")))
        novaHra.setAccelerator(KeyCombination.keyCombination("Ctrl+H"));
        MenuItem konecHry = new MenuItem("Konec hry");
        konecHry.setAccelerator(KeyCombination.keyCombination("Ctrl+K"));
        
        novySoubor.getItems().addAll(novaHra, konecHry);
        
        MenuItem oProgramu = new MenuItem("O programu");
        MenuItem napoveda = new MenuItem("Napoveda");
        
        about.getItems().addAll(oProgramu, napoveda);
        
        this.getMenus().addAll(novySoubor, about);
        
        konecHry.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        
        novaHra.setOnAction((new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                hra = new Hra();
                main.setHra(hra);
                main.getMapa().newGame(hra);
                main.getCentralText().setText(hra.vratUvitani());
            }
        }));
        
        oProgramu.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("O programu");
                alert.setContentText("Verze 1.0.0");
                alert.setHeaderText("Adventura");
                alert.show();
            }
        });
        
        napoveda.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                stage.setTitle("Napoveda");
                
                WebView webView = new WebView();
                
                webView.getEngine().load(Main.class.getResource("/zdroje/napoveda.html").toExternalForm());
                
                stage.setScene(new Scene(webView, 500,500));
                stage.show();
            }
        });
        
        
    }
}
