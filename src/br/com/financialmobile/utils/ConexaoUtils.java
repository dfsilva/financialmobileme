package br.com.financialmobile.utils;

import br.com.financialmobile.http.Result;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

public class ConexaoUtils {

    private static final String URL_APP = "http://www.diegosilva.com.br/financialmobile";
    //private static final String URL_APP = "http://localhost/financialmobile";

    public static Result doPost(String actionUrl, String params) throws IOException {
        HttpConnection httpConn = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            httpConn = (HttpConnection) Connector.open(URL_APP + actionUrl);
            httpConn.setRequestMethod(HttpConnection.POST);
            httpConn.setRequestProperty("User-Agent", "Profile/MIDP-2.1 Confirguration/CLDC-1.1");
            httpConn.setRequestProperty("Accept_Language", "pt-BR");
            httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            os = httpConn.openOutputStream();
            os.write(params.getBytes());
            //os.flush();
            StringBuffer sb = new StringBuffer();
            is = httpConn.openDataInputStream();
            int chr;
            while ((chr = is.read()) != -1) {
                sb.append((char) chr);
            }
            return Result.fromContent(sb.toString(), "application/json");
        } catch (Exception e) {
            UiUtils.getErrorDialog(e.getMessage()).show();
        } finally {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }
            if (httpConn != null) {
                httpConn.close();
            }
        }
        return null;
    }

    public static Result doGet(String actionUrl) throws IOException {
        HttpConnection httpConn = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            System.out.println("Abringo conexao: " + URL_APP + actionUrl);

            httpConn = (HttpConnection) Connector.open(URL_APP + actionUrl);
            httpConn.setRequestMethod(HttpConnection.GET);
            httpConn.setRequestProperty("User-Agent", "Profile/MIDP-2.1 Confirguration/CLDC-1.1");

            int respCode = httpConn.getResponseCode();

            if (respCode == HttpConnection.HTTP_OK) {
                StringBuffer sb = new StringBuffer();
                os = httpConn.openOutputStream();
                is = httpConn.openDataInputStream();
                int chr;
                while ((chr = is.read()) != -1) {
                    sb.append((char) chr);
                }
                return Result.fromContent(sb.toString(), "application/json");
            }
        } catch (Exception e) {
            UiUtils.getErrorDialog(e.getMessage()).show();
        } finally {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }
            if (httpConn != null) {
                httpConn.close();
            }
        }
        return null;
    }
}
