/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package babyjoey.Gui;

import babyjoey.Entities.Reclamation;
import babyjoey.Services.ReclamationService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aouin
 */
public class Ajouter_ReclamationController implements Initializable {

    @FXML
    private Button Ajouter;
    @FXML
    private HBox chosenhotelCard;
    @FXML
    private TextField tfobjet;
    @FXML
    private TextArea tfcontenu;
    @FXML
    private Label erreur_contenu;
    @FXML
    private Label erreur_objet;
    private boolean verificationObjet;
    private boolean verificationContenu;
    @FXML
    private ImageView contenuCM;
    @FXML
    private ImageView objetCM;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        ReclamationService rs=new ReclamationService();
        Reclamation r=new Reclamation();
        if(verificationObjet && verificationContenu)
        {
            r.setContenu(tfobjet.getText());
            r.setObjet(tfcontenu.getText());
            rs.Ajouter(r);
        }
        // else erreur
    }


    @FXML
    private void GoMessages(ActionEvent event) {
               try {
           Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
           stageclose.close();
           Parent root=FXMLLoader.load(getClass().getResource("Chat.fxml"));
           Stage stage =new Stage();
           Scene scene = new Scene(root);
           stage.setScene(scene);
           stage.show();
       } 
       catch (IOException ex) {
           Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @FXML
    private void testobjet(KeyEvent event) {
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
   private void testcontenu(KeyEvent event) {
        if (tfcontenu.getText().trim().length() >=10) {
        contenuCM.setImage(new Image("/babyjoey/Ressources/check-mark.png"));
        erreur_contenu.setText("contenu valide");
        
        verificationContenu = true;
        } else {
        contenuCM.setImage(new Image("/babyjoey/Ressources/warning.png"));
        erreur_contenu.setText("Il faut au moins 10 caracteres");
        verificationContenu = false;
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

