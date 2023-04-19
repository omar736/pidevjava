/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package babyjoey.Gui;

import babyjoey.Entities.Reclamation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import babyjoey.Services.ReclamationService;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author aouin
 */
public class Gestion_ReclamationController implements Initializable {

    @FXML
    private TextField tfobjet;
    @FXML
    private Label erreur_contenu;
    @FXML
    private TextField search_active;
    @FXML
    private Button btnDeconnexion;
    @FXML
    private TableView<Reclamation> list_active;
    @FXML
    private TableColumn<Reclamation, Integer> colId;
    @FXML
    private TextArea tfcontenu;
    @FXML
    private Button treat;
    @FXML
    private Button untreat;
    @FXML
    private TextField search_archive;
    @FXML
    private Label erreur_objet;
    @FXML
    private TableColumn<Reclamation, String> colUserID;
    @FXML
    private TableColumn<Reclamation, String> colObjet;
    @FXML
    private TableColumn<Reclamation, String> colContenu;
    @FXML
    private TableColumn<Reclamation, Integer> colIdA;
    @FXML
    private TableColumn<Reclamation, String> colUserIDA;
    @FXML
    private TableColumn<Reclamation, String> colObjetA;
    @FXML
    private TableColumn<Reclamation, String> colContenuA;
    @FXML
    private TextField tfetat;
    @FXML
    private ImageView objetCM;
    @FXML
    private Label erreur_etat;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private TextField ActiveCount;
    @FXML
    private TextField ArchiveCount;
    static Integer id;
    @FXML
    private TableView<Reclamation> list_archive;
    private String recherche ="";
    
    private boolean verificationObjet;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            afficher();
        } catch (SQLException ex) {
            Logger.getLogger(Gestion_ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            
    }    
    }
    public void afficher() throws SQLException {
        afficherActive();
        afficherArchive();
    }
        public void afficherActive() throws SQLException{
    
      
        ReclamationService sr = new ReclamationService();

        ObservableList<Reclamation> list= FXCollections.observableArrayList(sr.AfficherSelonEtat(0));
        colId.setCellValueFactory(new PropertyValueFactory<Reclamation, Integer>("id"));
        colUserID.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("user"));
        colObjet.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("objet"));
        colContenu.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("contenu"));       
        
        list_active.setItems(list);
        ActiveCount.setText(String.valueOf(sr.calculSelonEtat(0)));

    }
    public void afficherArchive() throws SQLException{
    
      
        ReclamationService sr = new ReclamationService();

        ObservableList<Reclamation> list= FXCollections.observableArrayList(sr.AfficherSelonEtat(1));
        colIdA.setCellValueFactory(new PropertyValueFactory<Reclamation, Integer>("id"));
        colUserIDA.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("user"));
        colObjetA.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("objet"));
        colContenuA.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("contenu"));       
        
        list_archive.setItems(list);
        ArchiveCount.setText(String.valueOf(sr.calculSelonEtat(1)));

    }

//    @FXML
//    private void search(ActionEvent event) throws SQLException {
//                {
//            afficherActive();
//        }
//    }

    @FXML
    private void search(KeyEvent event) throws SQLException {
        recherche=search_active.getText();
        ReclamationService sr = new ReclamationService();
        if(recherche.isEmpty() )
        {
            afficherActive();
        }
        else{
                 List<Reclamation> p=sr.RechercheSelonEtat(search_active.getText(),0);
                 list_active.getItems().clear();
                 list_active.getItems().removeAll();
                 list_active.getItems().addAll(p);
                 
        }
    }
//        @FXML
//    private void search1(ActionEvent event) throws SQLException {
//        if(recherche.isEmpty() )
//            {
//                afficherArchive();
//            }
//    }
        @FXML
    private void search1(KeyEvent event) throws SQLException {
        recherche=search_archive.getText();
        ReclamationService sr = new ReclamationService();
        if(recherche.isEmpty() )
            {
                afficherArchive();
            }
        else{
                 List<Reclamation> p=sr.RechercheSelonEtat(search_archive.getText(),1);
                 list_archive.getItems().clear();
                 list_archive.getItems().removeAll();
                 list_archive.getItems().addAll(p);

        }
        
    }



    @FXML
    private void fill(MouseEvent event) {
       if (list_active.getSelectionModel().getSelectedItem()!= null){
        Reclamation d= list_active.getSelectionModel().getSelectedItem();
        id = d.getId();
        tfobjet.setText(d.getObjet());
        tfcontenu.setText(d.getContenu());
        tfetat.setText(String.valueOf(d.getEtat()));}
       else Logger.getLogger("error");
    }
        @FXML
    private void fill1(MouseEvent event) {
       if (list_archive.getSelectionModel().getSelectedItem()!= null){
        Reclamation d= list_archive.getSelectionModel().getSelectedItem();
        id = d.getId();
        tfobjet.setText(d.getObjet());
        tfcontenu.setText(d.getContenu());
        tfetat.setText(String.valueOf(d.getEtat()));}
       else Logger.getLogger("error");
    }

    @FXML
    private void modifier(ActionEvent event) {
        if (verificationObjet){
            Reclamation d = new Reclamation();
            ReclamationService sr = new ReclamationService();
            d.setObjet(tfobjet.getText());
            d.setContenu(tfcontenu.getText());          
            try {
                sr.Modifier(    d,id);
            } catch (SQLException ex) {
                Logger.getLogger(Gestion_ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                afficher();
            } catch (SQLException ex) {
                Logger.getLogger(Gestion_ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void supprimer(ActionEvent event) {
                 if(id!=null){
                     try {
                         ReclamationService sr = new ReclamationService();
                         Reclamation p = new Reclamation();
                         sr.Supprimer(id);
                         System.out.print("reclamation supprimer avec succes");
                         afficher();
                     } catch (SQLException ex) {
                         Logger.getLogger(Gestion_ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
                     }
                   
        }else{
             
             System.out.print("Veuillez selectionner une reclamation");
        }
        
    }

    @FXML
    private void treat(ActionEvent event) {
        try {
            ReclamationService sr = new ReclamationService();
            sr.Traiter(id);
            afficher();
            tfetat.setText("1");
        } catch (SQLException ex) {
            Logger.getLogger(Gestion_ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void untreat(ActionEvent event) {
           try {
            ReclamationService sr = new ReclamationService();
            sr.Untraiter(id);
            afficher(); 
        tfetat.setText("0");
        } catch (SQLException ex) {
            Logger.getLogger(Gestion_ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void testObjet(KeyEvent event) {
                 int nbNonChar = 0;
        for (int i = 0; i < tfobjet.getText().trim().length(); i++) {
        char ch = tfobjet.getText().charAt(i);
        if (!Character.isLetter(ch)) {
            nbNonChar++;
            }
        }
        
        if (nbNonChar == 0 && tfobjet.getText().trim().length() >=4) {
        objetCM.setImage(new Image("/babyjoey/Ressources/check-mark.png"));
        erreur_objet.setText("Objet valide");
        
        verificationObjet = true;
        } else {
        objetCM.setImage(new Image("/babyjoey/Ressources/warning.png"));
        erreur_objet.setText("Il faut au moins 4 caracteres et uniquement des caracteres");
        verificationObjet = false;
    }
    }

    @FXML
    private void GoHome(ActionEvent event) {
               try {
           Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
           stageclose.close();
           Parent root=FXMLLoader.load(getClass().getResource("Home.fxml"));
           Stage stage =new Stage();
           Scene scene = new Scene(root);
           stage.setScene(scene);
           stage.show();
       } 
       catch (IOException ex) {
           Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }






    
}
