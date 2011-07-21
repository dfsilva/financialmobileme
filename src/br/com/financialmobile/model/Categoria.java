package br.com.financialmobile.model;

import org.json.me.JSONException;
import org.json.me.JSONObject;

public class Categoria {

    private int idCategoria;
    private String descCategoria;

    public Categoria() {
    }

    public String getDescCategoria() {
        return descCategoria;
    }

    public void setDescCategoria(String descCategoria) {
        this.descCategoria = descCategoria;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Categoria other = (Categoria) obj;
        if (this.idCategoria != other.idCategoria) {
            return false;
        }
        if ((this.descCategoria == null) ? (other.descCategoria != null) : !this.descCategoria.equals(other.descCategoria)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.idCategoria;
        hash = 53 * hash + (this.descCategoria != null ? this.descCategoria.hashCode() : 0);
        return hash;
    }

    public String toString() {
        return descCategoria;
    }

}
