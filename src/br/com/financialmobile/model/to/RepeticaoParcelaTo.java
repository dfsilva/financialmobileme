/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financialmobile.model.to;

/**
 *
 * @author diego
 */
public class RepeticaoParcelaTo {

    private int id;
    private String descricao;

    public RepeticaoParcelaTo(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return descricao;
    }
}
