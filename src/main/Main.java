/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import gui.BatohGui;
import gui.Mapa;
import gui.MenuLista;
import gui.ProstorGui;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logika.Hra;
import logika.IHra;
import uiText.TextoveRozhrani;

/**
 *
 * @author Daniel Vrána
 */
public class Main extends Application {
    
    private TextArea centralText;
    private Mapa mapa;
    private BatohGui batohGui;
    private ProstorGui prostorGui;
    private MenuLista menuLista;
    IHra hra;
    TextField zadejPrikazTextField;
    
    
    @Override
    public void start(Stage primaryStage) {
        
        centralText = new TextArea();
        hra = new Hra();
        
        mapa = new Mapa(hra);
        batohGui = new BatohGui(hra.getHerniPlan().getHrac());
        prostorGui = new ProstorGui(hra.getHerniPlan());
        menuLista = new MenuLista(hra, this);
        hra.getHerniPlan().setProstorGui(prostorGui);
        
        BorderPane borderPane = new BorderPane();    
        centralText.setEditable(false);
        centralText.setText(hra.vratUvitani());
        borderPane.setCenter(centralText);
        borderPane.setTop(menuLista);
        
        Label zadejPrikazLabel = new Label("Zadej prikaz");
        zadejPrikazLabel.setFont(Font.font("Arial", FontWeight.THIN, 14));
        
        zadejPrikazTextField = new TextField("...");
        zadejPrikazTextField.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                String vstupniPrikaz = zadejPrikazTextField.getText();
                String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);
                
                centralText.appendText("\n" + vstupniPrikaz + "\n");
                centralText.appendText(odpovedHry + "\n");
                
                if (hra.konecHry()) {
                    zadejPrikazTextField.setEditable(false);
                    centralText.appendText(hra.vratEpilog());
                }
            }
        });
        
        
        // GUI elementy
        FlowPane dolniLista = new FlowPane();
        dolniLista.setAlignment(Pos.CENTER);
        dolniLista.getChildren().addAll(zadejPrikazLabel,zadejPrikazTextField);
        
        borderPane.setLeft(prostorGui);
        borderPane.setBottom(dolniLista);
                
        Scene scene = new Scene(borderPane, 750, 500);
        
        primaryStage.setTitle("Adventura");
        primaryStage.setScene(scene);
        primaryStage.show();
        zadejPrikazTextField.requestFocus();
    }
 
    public TextArea getCentralText() {
        return centralText;
    }

    public Mapa getMapa() {
        return mapa;
    }

    public void setHra(IHra hra) {
        this.hra = hra;
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            launch(args);
        }
        else
        {
            if(args[0].equals("text"))
            {
                IHra hra = new Hra();
                TextoveRozhrani ui =  new TextoveRozhrani(hra);
            }
            else
            {
                System.out.println("Neplatný argument");
                System.exit(1);
            }
        }
        
    }
    
}
