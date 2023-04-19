/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package babyjoey.Entities;

/**
 *
 * @author aouin
 */
public class User {
    private int id;
    private String email,password,role;
    private int isVerified;

    public User(int id, String email, String password, String role, int isVerified) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.isVerified = isVerified;
    }
    
    public User() {
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(int isVerified) {
        this.isVerified = isVerified;
    }
    
}
