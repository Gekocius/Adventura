package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import main.Main;
import utils.Observer;

/**
 * Třída představující rozhraní pro komunikaci s herním chatbotem
 * 
 * @author Daniel Vrana
 *
 */
public class TextVstup extends VBox implements Observer {

	private Main main;
	TextField vstup;
	Label vstupLabel;
	PrikazyVstup prikazyVstup;
	
	public TextVstup(Main main, PrikazyVstup prikazyVstup)
	{
		this.main = main;
		this.prikazyVstup = prikazyVstup;
		init();
	}
	
	private void init()
	{
		vstup = new TextField("...");
		vstupLabel = new Label("Chatbot rozhraní");
		main.getHra().getHerniPlan().getAthena().registerObserver(this);
		this.getChildren().addAll(vstupLabel, vstup);
		
		vstup.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				String vstupniText = vstup.getText();
				String odpoved = main.getHra().zpracujPrikaz(vstupniText);
				main.getCentralText().appendText("\n" + odpoved);
			}
		});
		
		update();
	}

	/**
	 * Zjistí, jestli je bot aktivní a podle toho schová nebo zobrazí rozhraní
	 * 
	 */
	@Override
	public void update() {
		if(main.getHra().getHerniPlan().getAthena().jeAktivni())
		{
			vstup.setEditable(true);
			prikazyVstup.setVisible(false);
		}
		else
		{
			vstup.setEditable(false);
			prikazyVstup.setVisible(true);
		}
		
	}
	
}
