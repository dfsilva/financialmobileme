package br.com.financialmobile.service;

import br.com.financialmobile.http.Result;
import br.com.financialmobile.model.Usuario;
import br.com.financialmobile.utils.ConexaoUtils;
import br.com.financialmobile.utils.UiUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;

public class UsuarioService {

    private static final String LOGIN_COMMAND = "/app/loginjson/";
    public static Usuario usuarioLogado;

    public static boolean existeUsuario() {
        try {
            RecordStore rs = RecordStore.openRecordStore("fnUsuario", true);
            if (rs.getNumRecords() > 0) {
                byte[] byteInputData = new byte[100];
                ByteArrayInputStream inputStream = new ByteArrayInputStream(byteInputData);
                DataInputStream inputDataStream = new DataInputStream(inputStream);

                rs.getRecord(1, byteInputData, 0);

                int idUsuario = inputDataStream.readInt();
                String logiUsuario = inputDataStream.readUTF();
                String senha = inputDataStream.readUTF();

                inputStream.reset();
                inputStream.close();
                inputDataStream.close();

                usuarioLogado = new Usuario(idUsuario, logiUsuario, senha);
                return true;
            }
        } catch (Exception ex) {
            UiUtils.getErrorDialog(ex.getMessage()).show();
            return false;
        }
        return false;
    }

    public static int saveAtivo(Usuario usu) {
        return 1;
    }

    public static int save(Usuario usu) {
        return 0;
    }

    public static boolean login(String usuario, String senha) {
        RecordStore rs = null;
        try {
            Result result = ConexaoUtils.doGet(LOGIN_COMMAND + usuario + "/" + senha);

            String sucesso = result.getAsString("success");
            String idUsuario = result.getAsString("idUsuario");

            if ("true".equals(sucesso)) {
                rs = RecordStore.openRecordStore("fnUsuario", true);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

                DataOutputStream outputDataStream = new DataOutputStream(outputStream);
                outputDataStream.writeInt(Integer.parseInt(idUsuario));
                outputDataStream.writeUTF(usuario);
                outputDataStream.writeUTF(senha);
                outputDataStream.flush();

                rs.addRecord(outputStream.toByteArray(), 0, outputStream.toByteArray().length);

                outputStream.reset();
                outputStream.close();
                outputDataStream.close();

                usuarioLogado = new Usuario(Integer.parseInt(idUsuario), usuario, senha);
                try {
                    rs.closeRecordStore();
                } catch (RecordStoreException e) {
                }
                return true;
            }
            String msg = result.getAsString("msg");
            System.out.println(msg);
        } catch (Exception ex) {
            return false;
        }
        return false;
    }
}
