package br.com.financialmobile.ui;

import br.com.financialmobile.utils.UiUtils;
import br.com.financialmobile.service.UsuarioService;
import com.sun.lwuit.Display;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.impl.midp.VKBImplementationFactory;
import com.sun.lwuit.plaf.UIManager;
import com.sun.lwuit.util.Resources;
import java.io.IOException;
import javax.microedition.midlet.MIDlet;

/**
 * @author diego
 */
public class FinancialMobileMid extends MIDlet implements ActionListener {

    private FmLogin fmLogin;
    private FmMain fmMain;

    public FinancialMobileMid() {
    }

    public FinancialMobileMid getInstance(){
        return this;
    }

    public void startApp() {
        VKBImplementationFactory.init();
        Display.init(this);
        try {
            Resources res = Resources.open("/br/com/financialmobile/res/theme/theme.res");
            UIManager.getInstance().setThemeProps(res.getTheme(res.getThemeResourceNames()[0]));
        } catch (IOException ioe) {
            UiUtils.getErrorDialog("Erro ao carregar tema: " + ioe.getMessage());
        }
        new fmSplash().show();

        new Thread(new Runnable() {
            public void run() {
                try {
                    if (UsuarioService.existeUsuario()) {
                        fmMain = new FmMain("Financial Mobile", getInstance());
                        fmMain.show();
                    } else {
                        fmLogin = new FmLogin(getInstance());
                        fmLogin.show();
                    }
                } catch (Exception ex) {
                    UiUtils.getErrorDialog(ex.getMessage());
                }
            }
        }).start();
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void actionPerformed(ActionEvent arg0) {
    }
}
