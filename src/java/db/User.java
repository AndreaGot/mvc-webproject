/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.awt.Image;
import java.io.File;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ANDre1
 */
public class User implements Serializable {

    public String username;
    public String password;
    public String email;
    public String nome_completo;
    public String lastLogin;
    public String id;
    public Boolean moder;

    public Boolean getModer() {
        return moder;
    }

    public void setModer(Boolean moder) {
        this.moder = moder;
    }


    public String getUserName() {
        return username;
    }

    public void setUserName(String name) {
        this.username = name;
    }

    public String getName() {
        return nome_completo;
    }

    public void setName(String name) {
        this.nome_completo = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

        public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String login) {
        this.lastLogin = login;
    }
}