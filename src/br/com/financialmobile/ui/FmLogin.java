package br.com.financialmobile.ui;

import br.com.financialmobile.utils.UiUtils;
import br.com.financialmobile.service.UsuarioService;
import com.sun.lwuit.Button;
import com.sun.lwuit.Component;
import com.sun.lwuit.Container;
import com.sun.lwuit.Form;
import com.sun.lwuit.Label;
import com.sun.lwuit.TextField;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.events.FocusListener;
import com.sun.lwuit.layouts.BoxLayout;
import com.sun.lwuit.layouts.GridLayout;

/**
 *
 * @author diego
 */
public class FmLogin extends Form {

    private Label lbLogin;
    private Label lbPasswd;
    private TextField txLogin;
    private TextField txPasswd;
    private Button btnEnter;
    private Button btnSair;
    private FinancialMobileMid midlet;
    private Container container;

    public FmLogin(FinancialMobileMid midlet) {
        super("Login");
        this.midlet = midlet;
        initComponents();
        initForm();
    }

    private void initComponents() {
        lbLogin = new Label("Usuário");
        lbPasswd = new Label("Senha");
        txLogin = new TextField();
        txLogin.setInputMode("abc");

        txPasswd = new TextField();
        txPasswd.setInputModeOrder(new String[]{"Abc"});
        txPasswd.setConstraint(TextField.PASSWORD);
        txPasswd.addFocusListener(new FocusListener() {

            public void focusGained(Component cmp) {
                if (cmp == txPasswd) {
                    ((TextField) cmp).clear();
                }
            }

            public void focusLost(Component cmp) {
            }
        });

        btnEnter = new Button("Entrar");

        btnEnter.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                UiUtils.showLoading();
                new Thread() {

                    public void run() {
                        logar();
                    }
                }.start();
            }
        });

        btnEnter.setAlignment(Label.CENTER);
        btnSair = new Button("Sair");
        btnSair.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                finalizar();
            }
        });
        btnSair.setAlignment(Label.CENTER);
    }

    private void initForm() {
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        this.addComponent(lbLogin);
        this.addComponent(txLogin);
        this.addComponent(lbPasswd);
        this.addComponent(txPasswd);
        this.container = new Container();
        container.setLayout(new GridLayout(1, 2));
        container.addComponent(btnEnter);
        container.addComponent(btnSair);
        this.addComponent(container);
    }

    private void logar() {
        if (UsuarioService.login(txLogin.getText(), txPasswd.getText())) {
            FmMain fm = new FmMain("Financial Mobile", midlet);
            fm.show();
        } else {
            UiUtils.hideLoading();
            this.show();
            UiUtils.getErrorDialog("Usuário ou senha inválidos!").show();
        }
    }

    private void finalizar() {
        getMidlet().destroyApp(true);
        getMidlet().notifyDestroyed();
    }

    public FinancialMobileMid getMidlet() {
        return midlet;
    }
}
