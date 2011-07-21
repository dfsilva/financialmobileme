package br.com.financialmobile.ui;

import br.com.financialmobile.service.CategoriaService;
import br.com.financialmobile.utils.UiUtils;
import com.sun.lwuit.Button;
import com.sun.lwuit.Command;
import com.sun.lwuit.Image;
import com.sun.lwuit.Label;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.GridLayout;
import java.io.IOException;

/**
 *
 * @author diego
 */
public class FmMain extends FinancialMobileForm {

    private FinancialMobileMid midlet;
    private Button btnAddCusto;
//    private Button btnManter;
//    private Button btnRelatorios;
    private Command cmExit;

    FmMain(String string, FinancialMobileMid p) {
        super(string);
        this.midlet = p;
        this.setLayout(new GridLayout(3, 2));
        CategoriaService.CATEGORIAS_CACHE = CategoriaService.findAll();
        try {
            criaBotoes();
        } catch (IOException ex) {
            UiUtils.getErrorDialog("Erro ao criar botões: " + ex.getMessage());
        }
    }

    private void criaBotoes() throws IOException {

        cmExit = new Command("Sair") {

            public void actionPerformed(ActionEvent ae) {
                sair();
            }
        };
        this.addCommand(cmExit);

        Image imagem;
        imagem = Image.createImage("/br/com/financialmobile/res/img/gastos.png");
        btnAddCusto = new Button("Adicionar", imagem);
        btnAddCusto.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                UiUtils.showLoading();
                new Thread() {

                    public void run() {
                        adicionaCusto();
                    }
                }.start();
            }
        });
        btnAddCusto.setTextPosition(BOTTOM);
        btnAddCusto.setAlignment(Label.CENTER);
        btnAddCusto.getSelectedStyle().setBgImage(null);
        btnAddCusto.getSelectedStyle().setBgColor(0xffffff);
        btnAddCusto.getSelectedStyle().setBgTransparency(50);
        btnAddCusto.getPressedStyle().setBgImage(null);
        btnAddCusto.getPressedStyle().setBgTransparency(150);
        btnAddCusto.getStyle().setBorder(null);
        btnAddCusto.getStyle().setBgImage(null);
        btnAddCusto.getStyle().setBgTransparency(0);
        this.addComponent(btnAddCusto);

//        imagem = Image.createImage("/br/com/financialmobile/res/img/manter.png");
//        btnManter = new Button("Manter Custos", imagem);
//        btnManter.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent arg0) {
//                manterCustos();
//            }
//        });
//        btnManter.setTextPosition(BOTTOM);
//        btnManter.setAlignment(Label.CENTER);
//        btnManter.getStyle().setBorder(null);
//        btnManter.getStyle().setBgTransparency(0);
//        btnManter.getStyle().setBgImage(null);
//        btnManter.getSelectedStyle().setBgColor(0xffffff);
//        btnManter.getSelectedStyle().setBgImage(null);
//        btnManter.getSelectedStyle().setBgTransparency(50);
//        btnManter.getPressedStyle().setBgImage(null);
//        btnManter.getPressedStyle().setBgTransparency(150);
//        this.addComponent(btnManter);
//
//        imagem = Image.createImage("/br/com/financialmobile/res/img/relatorios.png");
//        btnRelatorios = new Button("Relatório", imagem);
//        btnRelatorios.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent arg0) {
//                gerarRelatorio();
//            }
//        });
//
//        btnRelatorios.setTextPosition(BOTTOM);
//        btnRelatorios.setAlignment(Label.CENTER);
//        btnRelatorios.getStyle().setBorder(null);
//        btnRelatorios.getStyle().setBgImage(null);
//        btnRelatorios.getStyle().setBgTransparency(0);
//        btnRelatorios.getSelectedStyle().setBgImage(null);
//        btnRelatorios.getSelectedStyle().setBgColor(0xffffff);
//        btnRelatorios.getSelectedStyle().setBgTransparency(50);
//        btnRelatorios.getPressedStyle().setBgTransparency(150);
//        btnRelatorios.getPressedStyle().setBgImage(null);
//        this.addComponent(btnRelatorios);
    }

    private void sair() {
        this.midlet.destroyApp(true);
        this.midlet.notifyDestroyed();
    }

    private void adicionaCusto() {
        try {
            FmManterCusto fmCadCusto = new FmManterCusto(this);
            fmCadCusto.adicionaTransicaoSlide();
            fmCadCusto.show();
        } catch (Exception e) {
            UiUtils.getErrorDialog(e.getMessage()).show();
            this.show();
        }
    }
//
//    private void manterCustos() {
//    }
//
//    private void gerarRelatorio() {
//    }
}
