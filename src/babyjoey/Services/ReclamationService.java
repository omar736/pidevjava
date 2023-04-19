/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package babyjoey.Services;

import babyjoey.Entities.Reclamation;
import babyjoey.Iservices.Iservices;
import babyjoey.Utils.MyConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author aouin
 */
public class ReclamationService implements Iservices<Reclamation>{
    private Connection c = MyConnexion.getInsCon().getcnx();
 /******************************* AJOUTER RECLAMATION ********************************************/
    @Override
    public void Ajouter(Reclamation reclamation) throws SQLException {
        LocalDateTime now= LocalDateTime.now();   
        reclamation.setDate_envoi(now);
        reclamation.setEtat(0);
        reclamation.setUser_id(1);
        PreparedStatement ps;
        String query = "INSERT INTO `reclamation`( `user_id`,`objet`, `contenu`, `etat`, `date_envoi`) VALUES ('"+reclamation.getUser_id()+"','"+reclamation.getObjet()+"','"+reclamation.getContenu()+"','"+reclamation.getEtat()+"','"+reclamation.getDate_envoi()+"')";
        try {

            ps = c.prepareStatement(query);
            ps.execute();    
            System.out.println(reclamation);
        } catch (Exception e) { 
            System.out.println(e);
        }  


    }
 /******************************* SUPPRIMER RECLAMATION *********************************************/
    @Override
    public void Supprimer( int id) throws SQLException {
        PreparedStatement ps;
        String query = "  DELETE FROM `reclamation` WHERE `reclamation`.`id` ='"+id+"'";
        try {
            ps = c.prepareStatement(query);
            ps.execute();
            System.out.println("suppression ");
        } catch (SQLException e) {
            System.out.println(e);
        } 
    }
 /******************************* MODIFIER RECLAMATION *********************************************/
    @Override
    public void Modifier(Reclamation reclamation, int id) throws SQLException {
         PreparedStatement ps;
        String query = "UPDATE `reclamation` SET `objet`=?,`contenu`=? WHERE `id`="+id;
        try {
            ps = c.prepareStatement(query);
            ps.setString(1, reclamation.getObjet());
            ps.setString(2, reclamation.getContenu());
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
 /******************************* AFFICHER RECLAMATION *********************************************/
    @Override
    public List<Reclamation> Affichertout() throws SQLException {
        List<Reclamation> list = new ArrayList();
        String requete = "SELECT * FROM `reclamation`";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Reclamation r = new Reclamation();
                r.setId(rs.getInt(1));
                r.setUser_id(rs.getInt(2));
                r.setObjet(rs.getString(3));
                r.setContenu(rs.getString(4));
                r.setEtat(rs.getInt(5));
                if(rs.getTimestamp(6)!=null)
                    { r.setDate_envoi(rs.getTimestamp(6).toLocalDateTime());}
                list.add(r);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    
    }
    /******************************* AFFICHER Archive *********************************************/
    public List<Reclamation> AfficherSelonEtat(Integer E) throws SQLException {
        List<Reclamation> list = new ArrayList();
        String requete = "SELECT * FROM `reclamation` WHERE `etat`="+E;
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Reclamation r = new Reclamation();
                r.setId(rs.getInt(1));
                r.setUser_id(rs.getInt(2));
                r.setObjet(rs.getString(3));
                r.setContenu(rs.getString(4));
                r.setEtat(rs.getInt(5));
                if(rs.getTimestamp(6)!=null)
                    { r.setDate_envoi(rs.getTimestamp(6).toLocalDateTime());}
                list.add(r);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    
    }
 /******************************* TRAITER RECLAMATION *********************************************/
  public void Traiter(int id){
         PreparedStatement ps;
        String query = "UPDATE `reclamation` SET `etat`=? WHERE `id`="+id;
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, 1);
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
 /******************************* UNTRAITER RECLAMATION *********************************************/
    public void Untraiter(int id){
         PreparedStatement ps;
        String query = "UPDATE `reclamation` SET `etat`=? WHERE `id`="+id;
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, 0);
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
  /******************************* COUNT RECLAMATION *********************************************/

      public long calculSelonEtat(int e) throws SQLException {     
        List<Reclamation> jeu = AfficherSelonEtat(e);
        return jeu.stream().count();
    }
    
  /******************************* RECHERCHE RECLAMATION *********************************************/

        public List<Reclamation> RechercheSelonEtat(String keyword, int e) throws SQLException {

        return AfficherSelonEtat(e).stream().filter(a -> a.getObjet().contains(keyword) ||
                a.getContenu().contains(keyword)).collect(Collectors.toList());

    }
}
