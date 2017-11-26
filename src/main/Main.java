/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import gui.BatohGui;
import gui.Mapa;
import gui.MenuLista;
import gui.PrikazyVstup;
import gui.ProstorGui;
import gui.TextVstup;
import gui.VychodyPanel;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logika.Hra;
import logika.IHra;
import uiText.TextoveRozhrani;
import utils.Observer;

/**
 * Hlavní třída aplikace, vstupní bod. Zde se nastavuje GUI.
 *
 * @author Daniel Vrána
 */
public class Main extends Application implements Observer {
    
    private TextArea centralText;
    private Mapa mapa;
    private BatohGui batohGui;
    private ProstorGui prostorGui;
    private MenuLista menuLista;
    private VychodyPanel vychodyPanel;
    private Hra hra;
    private BorderPane borderPane;
    private boolean mapaZobrazena;
      
    @Override
    public void start(Stage primaryStage) {
        initGui(primaryStage);
    }
    
    private void initGui(Stage primaryStage)
    {
        centralText = new TextArea();
        hra = new Hra();
        hra.registerObserver(this);
        
        mapa = new Mapa(hra);
        batohGui = new BatohGui(this);
        prostorGui = new ProstorGui(this);
        menuLista = new MenuLista(hra, this);
        vychodyPanel = new VychodyPanel(this);
        hra.getHerniPlan().setProstorGui(prostorGui);
        hra.getPlatnePrikazy().odeberPrikaz("konec");
        centralText.setWrapText(true);
        
        Label zadejPrikazLabel = new Label("Zadej příkaz");
        zadejPrikazLabel.setFont(Font.font("Arial", FontWeight.THIN, 14));
        
        centralText.setEditable(false);
        centralText.setText(hra.vratUvitani());
        
        Stage secondary = new Stage();
        Scene mapaScene = new Scene(mapa);
        
        secondary.setScene(mapaScene);
        secondary.setTitle("Mapa");
       
        Button zobrazMapu = new Button("Otevři mapu");
        zobrazMapu.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(mapaZobrazena)
				{
					secondary.close();
					zobrazMapu.setText("Otevři mapu");
					mapaZobrazena = false;
				}
				else
				{
					secondary.show();
					zobrazMapu.setText("Zavři mapu");
					mapaZobrazena = true;
				}
			}
		});
        
        secondary.setOnCloseRequest(new EventHandler<WindowEvent>() {
        	
        	@Override
        	public void handle(WindowEvent event)
        	{
        		mapaZobrazena = false;
        		zobrazMapu.setText("Otevři mapu");
        	}
		});
             
        // GUI elementy       
        borderPane = new BorderPane();    
        
        FlowPane dolniLista = new FlowPane();
        VBox dolni = new VBox();
        PrikazyVstup prikazyVstup = new PrikazyVstup(this);
        TextVstup textVstup = new TextVstup(this, prikazyVstup);
        dolni.getChildren().addAll(textVstup,prikazyVstup);
        dolniLista.getChildren().add(dolni);
        dolniLista.setAlignment(Pos.CENTER);
        
        VBox levaStrana = new VBox();
        Label batohLabel = new Label("Inventář hráče");
        Label veciVProstoruLabel = new Label("Věci v místnosti");
        levaStrana.getChildren().addAll(batohLabel, batohGui, veciVProstoruLabel, prostorGui, zobrazMapu);
        levaStrana.setAlignment(Pos.TOP_CENTER);
        levaStrana.setLayoutY(0);
        
        VBox pravaStrana = new VBox();
        Label vychodyLabel = new Label("Východy z prostoru");
        pravaStrana.getChildren().addAll(vychodyLabel, vychodyPanel);
        pravaStrana.setAlignment(Pos.TOP_CENTER);
        
        borderPane.setCenter(centralText);
        borderPane.setTop(menuLista);
        borderPane.setLeft(levaStrana);
        borderPane.setBottom(dolniLista);
        borderPane.setRight(pravaStrana);
                       
        Scene scene = new Scene(borderPane, 750, 500);
        
        primaryStage.setTitle("Adventura");
        primaryStage.setScene(scene);
        primaryStage.show();    
        
    }
 
    /**
     * Vrátí odkaz na výpisovou TextArea v hlavním okně
     * 
     * @return centralText
     */
    public TextArea getCentralText() {
        return centralText;
    }

    /**
     * Vrátí odkaz na instanci mapy
     * 
     * @return mapa
     */
    public Mapa getMapa() {
        return mapa;
    }

    /**
     * Nastaví novou instanci hry pro hlavní okno
     * 
     * @param hra k nastavení
     */
    public void setHra(Hra hra) {
        this.hra = hra;
    }
    
    /**
     * Vrátí aktuální instanci hry
     * 
     * @return aktuální instance hry
     */
    public Hra getHra()
    {
    	return this.hra;
    }
    

    /**
     * Vstupní metoda aplikace
     * 
     * @param args argumenty z příkazové řádky
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
                ui.hraj();
            }
            else
            {
                System.out.println("Neplatný argument");
                System.exit(1);
            }
        }
        
    }

    /**
     * Slouží ke zjišťování stavu hry
     * 
     */
	@Override
	public void update() {
		
		if(hra.konecHry())
		{
			centralText.appendText("\n" + hra.vratEpilog());
			borderPane.setBottom(null);
			borderPane.setLeft(null);
			borderPane.setRight(null);
		}
	}
    
}
