package gui;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import main.Main;

/**
 * 
 * Třída, která slouží jako okénko pro grafické zobrazení předmětů s jejich jménem
 * 
 * @author Daniel Vrána
 *
 */
public class BatohPredmet extends VBox {

	ImageView obrazek;
	Label popisek;
	Main main;
	String prikaz;
	
	/**
	 * Konstruktor. 
	 * 
	 * @param main hlavní třída aplikace
	 * @param prikaz příkaz, který má okénko vyvolat na MouseClick
	 */
	public BatohPredmet(Main main, String prikaz)
	{
		this.prikaz = prikaz;
		this.main = main;
		obrazek = new ImageView();
		popisek = new Label();
		init();
	}
	
	private void init()
	{
	    this.setOnMouseClicked(new EventHandler<MouseEvent>() {

	        @Override
	        public void handle(MouseEvent event) {
	        	if(event.getButton() == MouseButton.PRIMARY)
	        		onMouseClicked();
	        }
	    });
	    
	    this.getChildren().addAll(obrazek,popisek);
	    this.setAlignment(Pos.CENTER);
	}
	
	private void onMouseClicked()
	{
		 String odpoved = main.getHra().zpracujPrikaz(prikaz + " " + popisek.getText());
		 main.getCentralText().appendText("\n"+ odpoved);
	}
	
	/**
	 * Nastaví obrázek předmětu k zobrazení
	 * 
	 * @param obrazek
	 */
	public void setObrazek(Image obrazek)
	{
		this.obrazek.setImage(obrazek);
	}
	
	/**
	 * Nastaví jméno předmětu
	 * 
	 * @param popisek
	 */
	public void setPopisek(String popisek)
	{
		this.popisek.setText(popisek);
	}
}
