/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package babyjoey.Entities;

import java.time.LocalDateTime;

/**
 *
 * @author aouin
 */
public class MessageDetails {
    private int id;
    private String message;
    private LocalDateTime date;
    private String SenderUsername;
    private String RecipientUsername;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getSenderUsername() {
        return SenderUsername;
    }

    public void setSenderUsername(String SenderUsername) {
        this.SenderUsername = SenderUsername;
    }

    public String getRecipientUsername() {
        return RecipientUsername;
    }

    public void setRecipientUsername(String RecipientUsername) {
        this.RecipientUsername = RecipientUsername;
    }

    
    
}
