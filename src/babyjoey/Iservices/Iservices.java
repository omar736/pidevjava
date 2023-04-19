/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package babyjoey.Iservices;

import java.sql.SQLException;
import java.util.List;

public interface Iservices<T> {
    
    public void Ajouter(T t) throws SQLException;
    public void Supprimer(int id) throws SQLException;
    public void Modifier(T t , int id) throws SQLException;
    public List<T> Affichertout() throws SQLException;
    
    
}
