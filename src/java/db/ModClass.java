/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

/**
 *
 * @author ANDre1
 */
public class ModClass {

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getPubblico() {
        return pubblico;
    }

    public void setPubblico(Boolean pubblico) {
        this.pubblico = pubblico;
    }

    public Integer getPost() {
        return post;
    }

    public void setPost(Integer post) {
        this.post = post;
    }

    public Integer getUtenti() {
        return utenti;
    }

    public void setUtenti(Integer utenti) {
        this.utenti = utenti;
    }
    
    
    public String nome;
    public String id;
    public Boolean pubblico;
    public Integer post;
    public Integer utenti;
    public Boolean chiuso;

    public Boolean getChiuso() {
        return chiuso;
    }

    public void setChiuso(Boolean chiuso) {
        this.chiuso = chiuso;
    }
}
