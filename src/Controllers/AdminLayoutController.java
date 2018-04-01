/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jabou
 */
public class AdminLayoutController implements Initializable {

    @FXML
    private Button Adoption_button;
    @FXML
    private Button Ventes_button;
    @FXML
    private Button Services_button;
    @FXML
    private Button hygiene_button;
    @FXML
    private Button event_button;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ServicePage(ActionEvent event) throws IOException {
         Parent service= FXMLLoader.load((getClass().getResource("../GUI/Back_ServicePage.fxml")));
         Scene CentreDPage= new Scene (service);
         Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
         window.setScene(CentreDPage);
         window.show();
    }

    @FXML
    private void VentePage(ActionEvent event) throws IOException {
         Parent vente= FXMLLoader.load((getClass().getResource("../GUI/Back_VentePage.fxml")));
         Scene ventePage= new Scene (vente);
         Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
         window.setScene(ventePage);
         window.show();
    }
    
}
