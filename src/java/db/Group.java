/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

/**
 *
 * @author ANDre1
 */
public class Group {

    public String nome;
    public String proprietario;
    public String id;
    public Boolean chiuso;

    public Boolean getChiuso() {
        return chiuso;
    }

    public void setChiuso(Boolean chiuso) {
        this.chiuso = chiuso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String name) {
        this.nome = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String id) {
        this.proprietario = id;
    }
}
