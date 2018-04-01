/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Categorie;
import Entity.CentreDressage;
import Entity.Produit;
import Services.CategorieService;
import Services.CentreDressageService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jabou
 */
public class Back_VentePageController implements Initializable {

    @FXML
    private TableView<Categorie> CategorieView;
    @FXML
    private TableColumn<Categorie, String> Column_libelle;
    @FXML
    private Button AjouterC;
    @FXML
    private Button ModifierC;
    @FXML
    private Button SupprimerC;
    @FXML
    private TableView<Produit> ProduitView;
    @FXML
    private TableColumn<Produit, Integer> Column_Categorie;
    @FXML
    private TableColumn<Produit, String> Column_libelleP;
    @FXML
    private TableColumn<Produit, String> Column_description;
    @FXML
    private TableColumn<Produit, Integer> Column_prix;
    @FXML
    private TableColumn<Produit, String> Column_animal;
    @FXML
    private TableColumn<Produit, String> Column_image;
    @FXML
    private TableColumn<Produit, Integer> Column_quantite;
    @FXML
    private Button AjouterP;
    @FXML
    private Button ModifierP;
    @FXML
    private Button SupprimerP;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ModifierC.setDisable(true);
        SupprimerC.setDisable(true);
        Column_libelle.setCellValueFactory(new PropertyValueFactory<> ("libelle"));
        
        try {
                 CategorieService cs=new CategorieService();
                 CategorieView.setItems(cs.getObservableCategorie());
        } 
        catch (SQLException ex) {
            Logger.getLogger(Back_VentePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                                        /*Produit*/
                //**********************************************************************************//
                ModifierP.setDisable(true);
                SupprimerP.setDisable(true);
                
                

    }    

    @FXML
    private void Back_AdminLayout(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/adminLayout.fxml"));
        Parent root =loader.load();
        CategorieView.getScene().setRoot(root);
    }

    @FXML
    private void selected(MouseEvent event) {
        SupprimerC.setDisable(false);
        ModifierC.setDisable(false);
    }

    @FXML
    private void AjouterCategorie(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/Back_AjouterCategoriePage.fxml"));
           Parent root =loader.load();
           CategorieView.getScene().setRoot(root);
    }

    
    public void changeScene(ActionEvent event, String viewName,String title,Categorie c,ControllerClass controllerC) throws IOException
    {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource(viewName));
        Parent parent=loader.load();
        
        controllerC=loader.getController();
        controllerC.preloadData(c);
        
         Scene CategoriePage= new Scene (parent);
         Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
         window.setTitle(title);
         window.setScene(CategoriePage);
         window.show();
   }
    @FXML
    private void ModifierCategorie(ActionEvent event) throws IOException
    {
        Categorie c=this.CategorieView.getSelectionModel().getSelectedItem();
        Back_AjouterCategoriePageController controllerC=new Back_AjouterCategoriePageController();
        changeScene(event, "../GUI/Back_AjouterCategoriePage.fxml", "Modifier la catégorie", c, controllerC);
    }

    @FXML
    private void SupprimerCategorie(ActionEvent event) throws IOException, SQLException 
    {
        Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sure de vouloir supprimer ?");
        Optional<ButtonType> ok =alert.showAndWait();
        if(ok.get()==ButtonType.OK)
        {
           Categorie c=this.CategorieView.getSelectionModel().getSelectedItem();
           CategorieService cs= new CategorieService();
           System.out.println(c.getId());
           cs.SupprimerCategorie(c.getId());
           FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/Back_VentePage.fxml"));
           Parent root =loader.load();
           CategorieView.getScene().setRoot(root);
        }
    }
    
                                   /*Produit*/
         //***************************************************************************************//
    @FXML
    private void AjouterProduit(ActionEvent event) {
    }

    @FXML
    private void ModifierProduit(ActionEvent event) {
    }

    @FXML
    private void SupprimerProduit(ActionEvent event) {
    }
    
    
    
}
