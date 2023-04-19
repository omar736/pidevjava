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
public class Messages {
    private int id;
    private int sender_id,recipient_id;
    private String message;
    private LocalDateTime date;

    public Messages() {
    }

    public Messages(String message) {
        this.message = message;
    }

    public Messages(int id, int sender_id, int recipient_id, String message, LocalDateTime date) {
        this.id = id;
        this.sender_id = sender_id;
        this.recipient_id = recipient_id;
        this.message = message;
        this.date = date;
    }

    public Messages(int sender_id, int recipient_id, String message, LocalDateTime date) {
        this.sender_id = sender_id;
        this.recipient_id = recipient_id;
        this.message = message;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSender_id() {
        return sender_id;
    }

    public void setSender_id(int sender_id) {
        this.sender_id = sender_id;
    }

    public int getRecipient_id() {
        return recipient_id;
    }

    public void setRecipient_id(int recipient_id) {
        this.recipient_id = recipient_id;
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

    @Override
    public String toString() {
        return "Messages{" + "id=" + id + ", sender_id=" + sender_id + ", recipient_id=" + recipient_id + ", message=" + message + ", date=" + date + '}';
    }
    
    
    
}
