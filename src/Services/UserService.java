/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.User;
import Technique.DataSource;
import java.io.Console;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bn-sk
 */
public class UserService {
    private Connection con= DataSource.getInstance().getConnexion();
    private Statement statement;
    public UserService()
    {
        try {
            statement= con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int VerificationUtilisateur(User u)
    {
        int roles = -1;
        String req="select id,username,password,roles,enabled from user where username=(?) and password=(?) AND enabled IN (0,1)";
        try {
            PreparedStatement prepared= con.prepareStatement(req);
            prepared.setString(1,u.getUsername());
            prepared.setString(2,u.getPassword());
            ResultSet resultat=prepared.executeQuery();
            
            
                while(resultat.next())
                {
                    
                    u.setId(resultat.getInt(1));
                    u.setUsername(resultat.getString(2));
                    u.setPassword(resultat.getString(3));
                    u.setRoles(resultat.getString(4));
                    u.setEtat(resultat.getInt(5));
                    
  
                }
                if((u.getRoles().equalsIgnoreCase("ROLE_CLIENT"))||(u.getRoles().equalsIgnoreCase("ROLE_PETSITTER"))||(u.getRoles().equalsIgnoreCase("ROLE_VETERINAIRE")))
                    {
                        roles=1;
                    }
                    else
                if(u.getRoles().equalsIgnoreCase("ROLE_ADMIN"))
                    {
                        roles=0;
                    }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        return roles;
    }
    
    public void loggin(User u, int id) 
    {
       String req="UPDATE `user` SET `enabled`=0 WHERE `id`=? AND enabled=1 ";
        try {
            PreparedStatement prepared= con.prepareStatement(req);
            prepared.setInt(1,id);
            prepared.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void signIn(User u) {
        try {
            String req = "INSERT INTO `user`(nom,prenom,adresse,email,telephone,username,password,roles,enabled) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement prepared= con.prepareStatement(req);
            prepared.setString(1, u.getNom());
            prepared.setString(2, u.getPrenom());
            prepared.setString(3, u.getAdresse());
            prepared.setString(4, u.getEmail());
            prepared.setInt(5, u.getTel());
            prepared.setString(6, u.getUsername());
            prepared.setString(7, u.getPassword());
            prepared.setString(8, u.getRoles());
            prepared.setInt(9, 1);
            prepared.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    
}

    


