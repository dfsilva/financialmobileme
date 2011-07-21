/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financialmobile.model.utils;

import br.com.financialmobile.model.Categoria;
import org.json.me.JSONException;
import org.json.me.JSONObject;

/**
 *
 * @author diego
 */
public class CategoriaUtils {

    public static Categoria fromJson(String obj) throws JSONException {
        JSONObject json = new JSONObject(obj.toString());
        return fromJson(json);
    }

    public static Categoria fromJson(JSONObject json) throws JSONException {
        Categoria cat = new Categoria();
        cat.setIdCategoria(json.getInt("idCategoria"));
        cat.setDescCategoria(json.getString("descCategoria"));
        return cat;
    }
}
