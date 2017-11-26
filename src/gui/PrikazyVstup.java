package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;
import logika.Hra;
import logika.IPrikaz;
import main.Main;
import utils.Observer;

/**
 * Třída, která slouží k zobrazení Combo boxů pro zadávání příkazů
 * 
 * @author Daniel Vrána
 *
 */
public class PrikazyVstup extends HBox implements Observer {

	private ComboBox<IPrikaz> prikaz;
	private ComboBox<String> parametr1;
	private ComboBox<String> parametr2;
	private Button tlacitko;
	private Hra hra;
	private Main main;
	
	/**
	 * Konstruktor. Přiřadí Combo boxy a akce k nim.
	 * 
	 * @param hra
	 */
	public PrikazyVstup(Main main)
	{
		this.main = main;
		this.hra = main.getHra();
		hra.getHerniPlan().registerObserver(this);
		hra.getHerniPlan().getHrac().registerObserver(this);
		init();
	}
	
	private void init()
	{
		prikaz = new ComboBox<>();
		
		parametr1 = new ComboBox<>();
		parametr2 = new ComboBox<>();
		
		parametr1.setVisible(false);
		parametr2.setVisible(false);
		prikaz.getItems().addAll(hra.getPlatnePrikazy().getPrikazy());
		prikaz.getSelectionModel().selectFirst();
		
		prikaz.setConverter(new StringConverter<IPrikaz>() {
			
			@Override
			public String toString(IPrikaz object) {
				return object.getNazev();
			}
			
			@Override
			public IPrikaz fromString(String string) {
				return null;
			}
		});
		
		prikaz.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				update();
			}
		});
		
		tlacitko = new Button("Potvrdit příkaz");
		
		tlacitko.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				String prikazKOdeslani = prikaz.getSelectionModel().getSelectedItem().getNazev();
				
				if(parametr1.getSelectionModel().getSelectedItem() != null)
					prikazKOdeslani += " " + parametr1.getSelectionModel().getSelectedItem();
				
				if(parametr2.getSelectionModel().getSelectedItem() != null)
					prikazKOdeslani += " " + parametr2.getSelectionModel().getSelectedItem();
				
				main.getCentralText().appendText("\n" + hra.zpracujPrikaz(prikazKOdeslani));
			}
		});
		
		this.getChildren().addAll(prikaz,parametr1,parametr2,tlacitko);
	}
	
    /**
     * Aktualizuje stav objektu, znovu načte prvky ComboBoxů po změnách ve hře
     * 
     */
	@Override
	public void update()
	{		
		main.getHra().getHerniPlan().getAktualniProstor().registerObserver(this);
		
		parametr1.getItems().clear();
		parametr2.getItems().clear();
		
		if(prikaz.getSelectionModel().getSelectedItem().getMaxParametry() == 2)
		{
			IPrikaz zvolenyPrikaz = prikaz.getSelectionModel().getSelectedItem();
			parametr1.getItems().addAll(zvolenyPrikaz.getParametry().get(1));
			parametr2.getItems().addAll(zvolenyPrikaz.getParametry().get(2));
			parametr1.setVisible(true);
			parametr2.setVisible(true);
		}
		else if(prikaz.getSelectionModel().getSelectedItem().getMaxParametry() == 1)
		{
			IPrikaz zvolenyPrikaz = prikaz.getSelectionModel().getSelectedItem();
			parametr1.getItems().addAll(zvolenyPrikaz.getParametry().get(1));
			parametr1.setVisible(true);
			parametr2.setVisible(false);
		}
		else
		{
			parametr1.setVisible(false);
			parametr2.setVisible(false);
		}
	}
	
	
}
