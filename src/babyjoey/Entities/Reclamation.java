/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package babyjoey.Entities;

import java.time.LocalDateTime;

/**
 *
 * @author aouin
 */
public class Reclamation {
   private int id;
   private int user_id,etat;
   private String objet,contenu;
   private LocalDateTime date_envoi;

    public Reclamation() {
    }

    public Reclamation(String objet, String contenu) {
        this.objet = objet;
        this.contenu = contenu;
    }

    public Reclamation(int id, int user_id, int etat, String objet, String contenu, LocalDateTime date_envoi) {
        this.id = id;
        this.user_id = user_id;
        this.etat = etat;
        this.objet = objet;
        this.contenu = contenu;
        this.date_envoi = date_envoi;
    }

    public Reclamation(int user_id, int etat, String objet, String contenu, LocalDateTime date_envoi) {
        this.user_id = user_id;
        this.etat = etat;
        this.objet = objet;
        this.contenu = contenu;
        this.date_envoi = date_envoi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public LocalDateTime getDate_envoi() {
        return date_envoi;
    }

    public void setDate_envoi(LocalDateTime date_envoi) {
        this.date_envoi = date_envoi;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", user_id=" + user_id + ", etat=" + etat + ", objet=" + objet + ", contenu=" + contenu + ", date_envoi=" + date_envoi + '}';
    }
   
}
