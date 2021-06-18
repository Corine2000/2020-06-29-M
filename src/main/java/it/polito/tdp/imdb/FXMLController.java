/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.imdb;

import java.net.URL;
import java.time.Year;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.imdb.model.Director;
import it.polito.tdp.imdb.model.Model;
import it.polito.tdp.imdb.model.RegistriVicini;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnAdiacenti"
    private Button btnAdiacenti; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaAffini"
    private Button btnCercaAffini; // Value injected by FXMLLoader

    @FXML // fx:id="boxAnno"
    private ComboBox<Integer> boxAnno; // Value injected by FXMLLoader

    @FXML // fx:id="boxRegista"
    private ComboBox<Director> boxRegista; // Value injected by FXMLLoader

    @FXML // fx:id="txtAttoriCondivisi"
    private TextField txtAttoriCondivisi; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	
    	if(boxAnno.getValue() == null) {
    		txtResult.setText("Scegli un anno nella tendina");
    		return;
    	}
    	
    	Integer anno = boxAnno.getValue();
    	model.creaGrafo(anno);
    	
    	txtResult.appendText("Grafo Creato\n");
    	txtResult.appendText("#Vertici: "+model.NumVertici()+"\n");
    	txtResult.appendText("#Archi: "+model.NumArchi()+"\n");
    	
    	boxRegista.getItems().addAll(model.getRegistriGrafo());
    }

    @FXML
    void doRegistiAdiacenti(ActionEvent event) {
    	txtResult.clear();
    	
           if(!model.esisteGrafo()) {
        	   txtResult.setText("Crea il grafo");
        	   return;
           }
           
           Director d = boxRegista.getValue();
           if(d==null) {
        	   txtResult.setText("Scegli un registro");
        	   return;
           }
           
           List<RegistriVicini> result = model.getVicini(d);
           if(result.size()==0) {
        	   txtResult.setText("il registro scelto non ha adiacenti"); 
        	   return;
           }
           
           txtResult.appendText("REGISTRI ADIACENTI A: "+d+"\n");
           for(RegistriVicini r: result) {
        	   txtResult.appendText(r.getRegistro()+" #Attori Condivisi| "+r.getAttoriCondivisi()+"\n");
           }
    }

    @FXML
    void doRicorsione(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAdiacenti != null : "fx:id=\"btnAdiacenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaAffini != null : "fx:id=\"btnCercaAffini\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxRegista != null : "fx:id=\"boxRegista\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtAttoriCondivisi != null : "fx:id=\"txtAttoriCondivisi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
   public void setModel(Model model) {
    	
    	this.model = model;
    	
    	for(int i=2004; i<2007; i++) {
    		boxAnno.getItems().add(i);
    	}
    	
    }
    
}
