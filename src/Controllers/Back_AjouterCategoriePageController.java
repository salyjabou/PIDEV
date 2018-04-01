/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Categorie;
import Entity.CentreDressage;
import Services.CategorieService;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author jabou
 */
public class Back_AjouterCategoriePageController implements Initializable,ControllerClass {

    @FXML
    private JFXTextField libelle;
    @FXML
    private Label titre;
    Categorie c;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterCategorie(ActionEvent event) throws SQLException, IOException {
                
        CategorieService cs= new CategorieService();
         if(c!=null)
        {
            c.setLibelle(libelle.getText());
            cs.ModifierCategorie(c);

        }
         else
         {
            Categorie c= new Categorie();
            c.setLibelle(libelle.getText());
            cs.AjouterCategorie(c);
         }
        FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/Back_VentePage.fxml"));
        Parent root =loader.load();
        libelle.getScene().setRoot(root);
        
    }

    @FXML
    private void Back_adminLayout(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/adminLayout.fxml"));
        Parent root =loader.load();
        libelle.getScene().setRoot(root);
    }

     @Override
    public void preloadData(Categorie c) 
    {
        this.c=c;
        this.libelle.setText(c.getLibelle());
        this.titre.setText("Modifier Une Catégorie");

    }
   
   
      
    @Override
    public void preloadData(CentreDressage d) {
    }
}
