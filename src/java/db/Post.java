/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Date;

/**
 *
 * @author ANDre1
 */
public class Post {

    public String idPost;
    public String Autore;
    public String contenuto;
    public Date data;
    public Image avatar;
    public String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Image getAvatar() {
        return avatar;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }

    public String getId() {
        return idPost;
    }

    public void setId(String id) {
        this.idPost = id;
    }

    public String getAutore() {
        return Autore;
    }

    public void setAutore(String autore) {
        this.Autore = autore;
    }

    public String getContenuto() {
        return contenuto;
    }

    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
