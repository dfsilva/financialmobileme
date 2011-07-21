package br.com.financialmobile.ui;

import br.com.financialmobile.utils.UiUtils;
import com.sun.lwuit.Form;
import com.sun.lwuit.Image;
import com.sun.lwuit.Label;
import com.sun.lwuit.animations.CommonTransitions;
import com.sun.lwuit.layouts.BorderLayout;
import com.sun.lwuit.plaf.Style;
import java.io.IOException;

/**
 *
 * @author diego
 */
public class fmSplash extends Form {

    private Label lblImagem;
    private Label lbMsg;
    private Label lblVersion;

    public fmSplash() {
        this.setLayout(new BorderLayout());
        try {
            Image imgJava = Image.createImage("/br/com/financialmobile/res/img/logo.png");
            lblImagem = new Label(imgJava);
            lblImagem.setAlignment(CENTER);
            this.addComponent(BorderLayout.CENTER, lblImagem);

        } catch (IOException ex) {
            UiUtils.getErrorDialog(ex.getMessage());
        }
        lbMsg = new Label("Criado por DiegoSilva");
        lbMsg.setAlignment(CENTER);
        this.addComponent(BorderLayout.SOUTH, lbMsg);
        //lblVersion = new Label("Versão 1.0 Alpha");
       // this.addComponent(BorderLayout.SOUTH, this.lblVersion);
        this.setTransitionOutAnimator(CommonTransitions.createFade(500));
        Style style = this.getStyle();
        style.setBgImage(null, true);
    }
}
