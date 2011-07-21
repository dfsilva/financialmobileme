package br.com.financialmobile.model;

public class Custo {

    private int idCusto;
    private String descricaoGasto;
    private int idUsuario;
    private int idCategoriaGasto;

    public String getDescricaoGasto() {
        return descricaoGasto;
    }

    public void setDescricaoGasto(String descricaoGasto) {
        this.descricaoGasto = descricaoGasto;
    }

    public int getIdCategoriaGasto() {
        return idCategoriaGasto;
    }

    public void setIdCategoriaGasto(int idCategoriaGasto) {
        this.idCategoriaGasto = idCategoriaGasto;
    }

    public int getIdCusto() {
        return idCusto;
    }

    public void setIdCusto(int idCusto) {
        this.idCusto = idCusto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Custo other = (Custo) obj;
        if (this.idCusto != other.idCusto) {
            return false;
        }
        if ((this.descricaoGasto == null) ? (other.descricaoGasto != null) : !this.descricaoGasto.equals(other.descricaoGasto)) {
            return false;
        }
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        if (this.idCategoriaGasto != other.idCategoriaGasto) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.idCusto;
        hash = 97 * hash + (this.descricaoGasto != null ? this.descricaoGasto.hashCode() : 0);
        hash = 97 * hash + this.idUsuario;
        hash = 97 * hash + this.idCategoriaGasto;
        return hash;
    }
}
