/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package babyjoey.Gui;

import babyjoey.Entities.MessageDetails;
import babyjoey.Entities.Messages;
import babyjoey.Services.MessagesService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ChatController implements Initializable {

   private static Integer id_user;
    @FXML
    private TextField id_message;
    @FXML
    private Button id_Ajouter;
    @FXML
    private ComboBox<String> id_choice_users;
    @FXML
    private TableView<MessageDetails> id_listt;
    @FXML
    private Button id_supprimer;
    @FXML
    private TableColumn<MessageDetails, Integer> colId;
    @FXML
    private TableColumn<MessageDetails, String> colSender;
    @FXML
    private TableColumn<MessageDetails, String> colRecipient;
    @FXML
    private TableColumn<MessageDetails, String> colMessage;
    @FXML
    private Label erreur_user;
    @FXML
    private ImageView UserCM;
    @FXML
    private Label erreur_message;
    @FXML
    private ImageView MessageCM;
    
    private boolean verificationMessage;
    @FXML
    private HBox chosenhotelCard;
    @FXML
    private ImageView objetCM1;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MessagesService sr = new MessagesService();
        try {
            id_choice_users.getItems().addAll(sr.GetUsers());
        } catch (SQLException ex) {
            Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
        }
       try {
            afficher();
        } catch (SQLException ex) {
            Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }    
    public void afficher() throws SQLException{
    

            MessagesService sr = new MessagesService();

            ObservableList<MessageDetails> list= FXCollections.observableArrayList(sr.AfficherMessage());
            colId.setCellValueFactory(new PropertyValueFactory<MessageDetails, Integer>("id"));
            colSender.setCellValueFactory(new PropertyValueFactory<MessageDetails, String>("SenderUsername"));
            colRecipient.setCellValueFactory(new PropertyValueFactory<MessageDetails, String>("RecipientUsername"));
            colMessage.setCellValueFactory(new PropertyValueFactory<MessageDetails, String>("message"));
            id_listt.setItems(list);

    }
    public void afficherMessageForUser() throws SQLException {
                id_user = Integer.parseInt(id_choice_users.getValue());
                MessagesService sr = new MessagesService();
                List<MessageDetails> list= sr.AfficherMessageUser(id_user);
                 id_listt.getItems().clear();
                 id_listt.getItems().removeAll();
                 id_listt.getItems().addAll(list);
    }


    @FXML
    private void Ajout(ActionEvent event) throws SQLException {
        MessagesService ms=new MessagesService();
        Messages m =new Messages();
        String idd=id_choice_users.getValue();
        if (idd != null) {
            if(verificationMessage){
        m.setMessage(id_message.getText());
        m.setRecipient_id(Integer.parseInt(idd));
        ms.Ajouter(m);
        afficherMessageForUser();
            } else         {    
        MessageCM.setImage(new Image("/babyjoey/Ressources/warning.png"));
        erreur_message.setText("Entrez au moins un caractere");
        }
        }
        else 
        {    
        UserCM.setImage(new Image("/babyjoey/Ressources/warning.png"));
        erreur_user.setText("Selectionnez un Utilisateur");
        }
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        if(id_listt.hasProperties() ){
            MessagesService sr = new MessagesService();
            Messages p = new Messages();
           
                    sr.Supprimer(id_listt.getSelectionModel().getSelectedItem().getId());
                     System.out.print("message supprimer avec succes");
                    afficherMessageForUser();
                   
        }else{
             
             System.out.print("Veuillez selectionner un message");
        }
    }

    @FXML
    private void afficherMessageUser(ActionEvent event) throws SQLException {    
        afficherMessageForUser();
        UserCM.setImage(new Image("/babyjoey/Ressources/check-mark.png"));
        erreur_user.setText("Utlisateur selectioné");
    }

    @FXML
    private void testMessage(KeyEvent event) {
         int nbNonChar = 0;
        for (int i = 0; i < id_message.getText().trim().length(); i++) {
        char ch = id_message.getText().charAt(i);
        if (!Character.isSpaceChar(ch)) {
            nbNonChar++;
            }
        }
        
        if (nbNonChar != 0 && id_message.getText().trim().length() >=1) {
        MessageCM.setImage(new Image("/babyjoey/Ressources/check-mark.png"));
        erreur_message.setText("Message valide");
        
        verificationMessage = true;
        } else {
        MessageCM.setImage(new Image("/babyjoey/Ressources/warning.png"));
        erreur_message.setText("Il faut au moins 1 caractere");
        verificationMessage = false;
    }
    }


    @FXML
    private void GoReclamation(ActionEvent event) {
        try {
            Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            stageclose.close();
            Parent root=FXMLLoader.load(getClass().getResource("Ajouter_Reclamation.fxml"));
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
