/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package babyjoey.Services;

import babyjoey.Entities.MessageDetails;
import babyjoey.Entities.Messages;
import babyjoey.Entities.Reclamation;
import babyjoey.Entities.User;
import babyjoey.Iservices.Iservices;
import babyjoey.Utils.MyConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author aouin
 */
public class MessagesService implements Iservices<Messages>{

   private Connection c = MyConnexion.getInsCon().getcnx();
 /******************************* AJOUTER MESSAGE *********************************************/
    @Override
    public void Ajouter(Messages messages) throws SQLException {
        PreparedStatement ps;
        LocalDateTime now= LocalDateTime.now();   
        messages.setDate(now);
        messages.setSender_id(1); //"1" est moi. (à changer aprés integration à user_sessions_id)
        String query = "INSERT INTO `messages`( `sender_id_id`,`recipient_id_id`, `message`, `date`) VALUES ('"+messages.getSender_id()+"','"+messages.getRecipient_id()+"','"+messages.getMessage()+"','"+messages.getDate()+"')";
        try {
            ps = c.prepareStatement(query);
            ps.execute();    
            System.out.println(messages);
        } catch (Exception e) { 
            System.out.println(e);
        }  
    }
 /******************************* SUPPRIMER MESSAGE *********************************************/
    @Override
    public void Supprimer(int id) throws SQLException {
       PreparedStatement ps;
        String query = "  DELETE FROM `messages` WHERE `messages`.`id` ='"+id+"'";
        try {
            ps = c.prepareStatement(query);
            ps.execute();
            System.out.println("suppression ");
        } catch (SQLException e) {
            System.out.println(e);
        } 
    }
 /******************************* MODIFIER MESSAGE *********************************************/
    @Override
    public void Modifier(Messages messages, int id) throws SQLException {
        PreparedStatement ps;
        String query = "UPDATE `messages` SET `message`=?,`date`=? WHERE `id`="+id;
        try {
            ps = c.prepareStatement(query);
            ps.setString(1, messages.getMessage());
            LocalDateTime ldt = messages.getDate(); // replace this with your LocalDateTime object
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // define the format of the output String
            String formattedDateTime = ldt.format(formatter); // use the formatter to format the LocalDateTime as a String
            ps.setString(2, formattedDateTime);
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
/******************************* AFFICHER MESSAGES *********************************************/
    public List<MessageDetails> AfficherMessage() throws SQLException {
        List<MessageDetails> list = new ArrayList();
        String requete = "SELECT m.id, m.message, m.date, u1.user_name as sender_username, u2.user_name as recipient_username " +
                        "FROM messages m " +
                        "JOIN user u1 ON 1 = u1.id " + //user "1" est moi. (à changer aprés integration à user_sessions_id)
                        "JOIN user u2 ON m.recipient_id_id = u2.id "+
                        "ORDER BY date desc";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MessageDetails md = new MessageDetails();
                md.setId(rs.getInt("id"));
                md.setMessage(rs.getString("message"));
                md.setDate(rs.getTimestamp("date").toLocalDateTime());
                md.setSenderUsername(rs.getString("sender_username"));
                md.setRecipientUsername(rs.getString("recipient_username"));
                list.add(md);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
/******************************* AFFICHER MESSAGES FOR ONE USER *********************************************/

        public List<MessageDetails> AfficherMessageUser(int id_user) throws SQLException {
        List<MessageDetails> list = new ArrayList();
        String requete = "SELECT m.id, m.message, m.date, u1.user_name as sender_username, u2.user_name as recipient_username " +
                        "FROM messages m " +
                        "JOIN user u1 ON 1 = u1.id " + //user "1" est moi. (à changer aprés integration à user_sessions_id)
                        "JOIN user u2 ON recipient_id_id = u2.id " +
                        "WHERE u2.id = "+id_user+
                        " ORDER BY date desc";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MessageDetails md = new MessageDetails();
                md.setId(rs.getInt("id"));
                md.setMessage(rs.getString("message"));
                md.setDate(rs.getTimestamp("date").toLocalDateTime());
                md.setSenderUsername(rs.getString("sender_username"));
                md.setRecipientUsername(rs.getString("recipient_username"));

                list.add(md);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
/******************************* AFFICHER USERS*********************************************/
public List<String> GetUsers()throws SQLException{
                String a=null;
                List<String> l = new ArrayList();
                String req = "select * from user ";      
                PreparedStatement ps = c.prepareStatement(req);
                ResultSet rs =  ps.executeQuery();
      
               while (rs.next()) {              
                    a = String.valueOf(rs.getInt("id"));
                    l.add(a);
                   }
               return l;
}
/******************************* AFFICHER MESSAGES (Ne fait rien)*********************************************/

    @Override
    public List<Messages> Affichertout() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

    
}
