/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financialmobile.model.to;

import java.util.Date;

/**
 *
 * @author diego
 */
public class CustoSaveTo {

    private String descricaoGasto;
    private int idCategoriaGasto;
    private Date dataVencimento;
    private float valorParcela;
    private String valorParcelaStr;
    private boolean parcelado = false;
    private int numParcelas;
    private int tipoRepeticao;

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

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

    public int getNumParcelas() {
        return numParcelas;
    }

    public void setNumParcelas(int numParcelas) {
        this.numParcelas = numParcelas;
    }

    public boolean isParcelado() {
        return parcelado;
    }

    public void setParcelado(boolean parcelado) {
        this.parcelado = parcelado;
    }

    public int getTipoRepeticao() {
        return tipoRepeticao;
    }

    public void setTipoRepeticao(int tipoRepeticao) {
        this.tipoRepeticao = tipoRepeticao;
    }

    public float getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(float valorParcela) {
        this.valorParcela = valorParcela;
    }

    public String getValorParcelaStr() {
        return valorParcelaStr;
    }

    public void setValorParcelaStr(String valorParcelaStr) {
        this.valorParcelaStr = valorParcelaStr;
    }
}
