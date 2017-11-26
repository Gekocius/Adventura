package gui;

import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import logika.HerniPlan;
import logika.IHra;
import logika.Prostor;
import main.Main;
import utils.Observer;

/**
 * Třída dědící z ListView. Zobrazuje východy z prostorů
 * 
 * @author Daniel Vrána
 * 
 */
public class VychodyPanel extends ListView<String> implements Observer {

	IHra hra;
	HerniPlan plan;
	Main main;
	
    /**
     * Konstruktor, který registruje observera a nastavuje MouseClick event
     * 
     * @param hra co bude instace pouzivat
     */
	public VychodyPanel(Main main)
	{
		this.main = main;
		this.hra = main.getHra();
		this.plan = hra.getHerniPlan();
		plan.registerObserver(this);
		init();
		update();
		
	}
	
	private void init()
	{
	    this.setOnMouseClicked(new EventHandler<MouseEvent>() {

	        @Override
	        public void handle(MouseEvent event) {
	        	onMouseClicked();
	        }
	    });
	}
	
	private void onMouseClicked()
	{
		String odpoved = hra.zpracujPrikaz("jdi " + this.getSelectionModel().getSelectedItem());
		main.getCentralText().appendText("\n" + odpoved);
	}

	@Override
    /**
     * Aktualizuje stav objektu, načte východy pro daný prostor
     * 
     */
	public void update() {
		
		this.getItems().clear();
		for(Prostor vychod : plan.getAktualniProstor().getVychody())
		{
			this.getItems().add(vychod.getNazev());
		}
		
		
	}
}
