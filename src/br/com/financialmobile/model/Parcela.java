/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financialmobile.model;

import java.util.Date;

/**
 *
 * @author diego
 */
public class Parcela {

    private int idCusto;
    private int numeroParcela;
    private Date dataVencimento;
    private double valorParcela;

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public int getIdCusto() {
        return idCusto;
    }

    public void setIdCusto(int idCusto) {
        this.idCusto = idCusto;
    }

    public int getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(int numeroParcela) {
        this.numeroParcela = numeroParcela;
    }

    public double getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(double valorParcela) {
        this.valorParcela = valorParcela;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Parcela other = (Parcela) obj;
        if (this.idCusto != other.idCusto) {
            return false;
        }
        if (this.numeroParcela != other.numeroParcela) {
            return false;
        }
        if (this.dataVencimento != other.dataVencimento && (this.dataVencimento == null || !this.dataVencimento.equals(other.dataVencimento))) {
            return false;
        }
        if (Double.doubleToLongBits(this.valorParcela) != Double.doubleToLongBits(other.valorParcela)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + this.idCusto;
        hash = 73 * hash + this.numeroParcela;
        hash = 73 * hash + (this.dataVencimento != null ? this.dataVencimento.hashCode() : 0);
        hash = 73 * hash + (int) (Double.doubleToLongBits(this.valorParcela) ^ (Double.doubleToLongBits(this.valorParcela) >>> 32));
        return hash;
    }



}
