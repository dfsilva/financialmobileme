/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financialmobile.service;

import br.com.financialmobile.http.Result;
import br.com.financialmobile.model.to.CustoSaveTo;
import br.com.financialmobile.utils.ConexaoUtils;
import br.com.financialmobile.utils.DateUtils;

/**
 *
 * @author diego
 */
public class CustoService {

    private static String SAVE_COMMAND = "/in/ccusto/cadastrarAlterar";

    public static int save(CustoSaveTo custo) {
        try {

            StringBuffer params = new StringBuffer("idUsuario=" + UsuarioService.usuarioLogado.getIdUsuario()
                    +"&descricaoGasto="+custo.getDescricaoGasto()+"&idCategoriaGasto="+custo.getIdCategoriaGasto()
                    +"&dataVencimento="+DateUtils.getDateStringddMMYYYY(custo.getDataVencimento())
                    +"&valorParcela="+custo.getValorParcelaStr());

            if(custo.isParcelado()){
                params.append("&tipoRepeticao="+custo.getTipoRepeticao()+"&qtdParcelas="+custo.getNumParcelas());
            }

            Result res = ConexaoUtils.doPost(SAVE_COMMAND, params.toString());

            String sucesso = res.getAsString("success");

            if(sucesso != null && "true".equals(sucesso)){
                return res.getAsInteger("idCusto");
            }else{
                return -1;
            }
        } catch (Exception e) {
            return -1;
        }
    }
}
