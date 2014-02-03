/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.Date;

/**
 *
 * @author ANDre1
 */
public class Update {

    public String getIDGruppo() {
        return IDGruppo;
    }

    public void setIDGruppo(String IDGruppo) {
        this.IDGruppo = IDGruppo;
    }

    public String getIDUtente() {
        return IDUtente;
    }

    public void setIDUtente(String IDUtente) {
        this.IDUtente = IDUtente;
    }

    public String getNomeGruppo() {
        return nomeGruppo;
    }

    public void setNomeGruppo(String nomeGruppo) {
        this.nomeGruppo = nomeGruppo;
    }

    public Date getUltimoPost() {
        return ultimoPost;
    }

    public void setUltimoPost(Date ultimoPost) {
        this.ultimoPost = ultimoPost;
    }


    public String IDGruppo;
    public String IDUtente;
    public String nomeGruppo;
    public Date ultimoPost;
}
