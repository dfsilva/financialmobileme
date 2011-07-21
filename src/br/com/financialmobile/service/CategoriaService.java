package br.com.financialmobile.service;

import br.com.financialmobile.http.Result;
import br.com.financialmobile.model.Categoria;
import br.com.financialmobile.model.utils.CategoriaUtils;
import br.com.financialmobile.utils.ConexaoUtils;
import br.com.financialmobile.utils.UiUtils;

public class CategoriaService {

    public static Categoria[] CATEGORIAS_CACHE;
    private static final String GET_CATEGORIAS_CMD = "/in/ccategoria/getCategorias/";

    public static Categoria[] findAll() {
        try {
            Result res = ConexaoUtils.doGet(GET_CATEGORIAS_CMD+UsuarioService.usuarioLogado.getIdUsuario());
            int size = res.getSizeOfArray("categorias");
            Categoria[] retorno = new Categoria[size];

            for (int i = 0; i < size; ++i) {
                retorno[i] = CategoriaUtils.fromJson(res.getJSONObject("categorias", i));
            }
            return retorno;
        } catch (Exception e) {
            UiUtils.getErrorDialog(e.getMessage()).show();
        }
        return null;
    }

    static int save(Categoria categoria) {
        return 0;
    }

    public static Categoria load(int idCategoria) {
        return null;
    }

    public static void deleteAll() {
    }
}
