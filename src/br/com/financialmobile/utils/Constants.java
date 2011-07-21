/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.financialmobile.utils;

import br.com.financialmobile.model.to.RepeticaoParcelaTo;

/**
 *
 * @author diego
 */
public interface Constants {

    int[] NR_PARCELAS = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36};
    RepeticaoParcelaTo[] REPETICOES_PARCELA = {new RepeticaoParcelaTo(1, "Semanas"), new RepeticaoParcelaTo(2, "Quinzenas"),
    new RepeticaoParcelaTo(3, "Meses"), new RepeticaoParcelaTo(4, "Bimestres"), new RepeticaoParcelaTo(5, "Trimestrais"),
    new RepeticaoParcelaTo(6, "Semestres")};

}
