package br.com.financialmobile.model;

public class Usuario {

    private int idUsuario;
    private String loginUsuario;
    private String senhaUsuario;

    public Usuario() {
    }

    public Usuario(int idUsuario, String loginUsuario, String senhaUsuario) {
        this.idUsuario = idUsuario;
        this.loginUsuario = loginUsuario;
        this.senhaUsuario = senhaUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        if ((this.loginUsuario == null) ? (other.loginUsuario != null) : !this.loginUsuario.equals(other.loginUsuario)) {
            return false;
        }
        if ((this.senhaUsuario == null) ? (other.senhaUsuario != null) : !this.senhaUsuario.equals(other.senhaUsuario)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.idUsuario;
        hash = 89 * hash + (this.loginUsuario != null ? this.loginUsuario.hashCode() : 0);
        hash = 89 * hash + (this.senhaUsuario != null ? this.senhaUsuario.hashCode() : 0);
        return hash;
    }
}
