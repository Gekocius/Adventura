/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logika.Hra;
import logika.IHra;
import uiText.TextoveRozhrani;

/**
 *
 * @author vrad00
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        IHra hra = new Hra();
        BorderPane borderPane = new BorderPane();
        
        Text centralText = new Text();
        centralText.setText(hra.vratUvitani());
        borderPane.setCenter(centralText);
        
        Label zadejPrikaz = new Label("Zadej prikaz");
        zadejPrikaz.setFont(Font.font("Arial", FontWeight.THIN, 14));
        
        FlowPane dolniLista = new FlowPane();
        dolniLista.setAlignment(Pos.CENTER);
        dolniLista.getChildren().add(zadejPrikaz);
        borderPane.setBottom(dolniLista);
                
        Scene scene = new Scene(borderPane, 500, 500);
        
        primaryStage.setTitle("Adventura");
        primaryStage.setScene(scene);
        primaryStage.show();
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