package br.com.financialmobile.ui;

import br.com.financialmobile.utils.UiUtils;
import br.com.financialmobile.model.Categoria;
import br.com.financialmobile.model.to.CustoSaveTo;
import br.com.financialmobile.model.to.RepeticaoParcelaTo;
import br.com.financialmobile.service.CategoriaService;
import br.com.financialmobile.service.CustoService;
import br.com.financialmobile.utils.Constants;
import com.sun.lwuit.Calendar;
import com.sun.lwuit.CheckBox;
import com.sun.lwuit.ComboBox;
import com.sun.lwuit.Command;
import com.sun.lwuit.Container;
import com.sun.lwuit.Label;
import com.sun.lwuit.TextField;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.impl.midp.VirtualKeyboard;
import com.sun.lwuit.layouts.BoxLayout;
import com.sun.lwuit.list.DefaultListModel;
import com.sun.lwuit.list.ListModel;

/**
 *
 * @author diego
 */
public final class FmManterCusto extends FinancialMobileForm {

    private Label lblDescricao;
    private TextField txDescricao;
    private Label lblValor;
    private TextField txValor;
    private Label lblDataGasto;
    private Calendar calDataGasto;
    private Label lblCategoria;
    private ComboBox cbCategoria;
    private Command cmSalvar;
    private ListModel categoriList;
    private CheckBox chekParcelas;
    private Container contInforParcela;
    private Label lblNrParcelas;
    private ComboBox cbNrParcelas;
    private ListModel nrParcelasList;
    private Label lblTpParcelas;
    private ComboBox cbTpParcelas;
    private ListModel tpParcelasList;

    public FmManterCusto(FinancialMobileForm p) {
        super("Inserção de Custo", p);
        inicializarListas();
        initComponents();
        initForm();
    }

    /**
     * Instancia componentes da pagina
     */
    public void initComponents() {
        super.initComponents();
        lblDescricao = new Label("Descrição");
        txDescricao = new TextField();
        lblCategoria = new Label("Categoria");
        cbCategoria = new ComboBox(categoriList);
        lblValor = new Label("Valor");
        txValor = new TextField();

        VirtualKeyboard vkb = new VirtualKeyboard();
        vkb.setInputModeOrder(new String[]{VirtualKeyboard.NUMBERS_SYMBOLS_MODE});
        VirtualKeyboard.bindVirtualKeyboard(txValor, vkb);

        lblDataGasto = new Label("Data");
        calDataGasto = new Calendar();

        chekParcelas = new CheckBox("Lançamento Parcelado");
        chekParcelas.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                if (((CheckBox) ae.getSource()).isSelected()) {
                    contInforParcela.setVisible(true);
                    repaint();
                } else {
                    contInforParcela.setVisible(false);
                    repaint();
                }
            }
        });

        contInforParcela = new Container();
        contInforParcela.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        lblNrParcelas = new Label("Nr. Parcelas");
        cbNrParcelas = new ComboBox(nrParcelasList);

        lblTpParcelas = new Label("Tipo Repetição");
        cbTpParcelas = new ComboBox(tpParcelasList);

        cmSalvar = new Command("Salvar") {

            public void actionPerformed(ActionEvent ae) {
                UiUtils.showLoading();
                new Thread() {

                    public void run() {
                        salvarCusto();
                    }
                }.start();
            }
        };
    }

    /**
     * Configura layout do form e adiciona os componentes no mesmo
     */
    public void initForm() {
        super.initForm();
        addComponent(lblDescricao);
        addComponent(txDescricao);
        addComponent(lblCategoria);
        addComponent(cbCategoria);
        addComponent(lblValor);
        addComponent(txValor);
        addComponent(lblDataGasto);
        addComponent(calDataGasto);
        addComponent(chekParcelas);
        contInforParcela.addComponent(lblNrParcelas);
        contInforParcela.addComponent(cbNrParcelas);
        contInforParcela.addComponent(lblTpParcelas);
        contInforParcela.addComponent(cbTpParcelas);
        contInforParcela.setVisible(chekParcelas.isSelected());
        addComponent(contInforParcela);
        addCommand(cmSalvar);
    }

    private boolean validarCamposObrigatorios() {
        if (txDescricao.getText().equals("")) {
            UiUtils.getErrorDialog("Campo Descrição é obrigatório!").show();
            return false;
        } else if (txValor.getText().equals("")) {
            UiUtils.getErrorDialog("Campo Valor é obrigatório!").show();
            return false;
        }
        return true;
    }

    /**
     * Inicializa lista de componentes que serÃ£o utilizados nos combos do form
     */
    private void inicializarListas() {
        try {
            Categoria[] categoriasList = null;
            if (CategoriaService.CATEGORIAS_CACHE != null) {
                categoriasList = CategoriaService.CATEGORIAS_CACHE;
            } else {
                categoriasList = CategoriaService.findAll();
                CategoriaService.CATEGORIAS_CACHE = categoriasList;
            }
            if (categoriasList != null && categoriasList.length > 0) {
                categoriList = new DefaultListModel();
                for (int i = 0; i < categoriasList.length; i++) {
                    categoriList.addItem((Categoria) categoriasList[i]);
                }
            }

            nrParcelasList = new DefaultListModel();
            for (int i = 0; i < Constants.NR_PARCELAS.length; i++) {
                nrParcelasList.addItem(new Integer(Constants.NR_PARCELAS[i]));
            }

            tpParcelasList = new DefaultListModel();
            for (int i = 0; i < Constants.REPETICOES_PARCELA.length; i++) {
                tpParcelasList.addItem(Constants.REPETICOES_PARCELA[i]);
            }
            tpParcelasList.setSelectedIndex(2);
        } catch (Exception ex) {
            UiUtils.getErrorDialog("Erro ao montar lista de categorias e status: " + ex.getMessage()).show();
        }
    }

    private void salvarCusto() {
        try {
            if (validarCamposObrigatorios()) {
                CustoSaveTo custoTo = new CustoSaveTo();
                custoTo.setDescricaoGasto(txDescricao.getText());
                custoTo.setDataVencimento(calDataGasto.getDate());
                custoTo.setValorParcelaStr(txValor.getText());
                custoTo.setIdCategoriaGasto(((Categoria) cbCategoria.getSelectedItem()).getIdCategoria());

                if (chekParcelas.isSelected()) {
                    custoTo.setParcelado(chekParcelas.isSelected());
                    custoTo.setNumParcelas(((Integer) cbNrParcelas.getSelectedItem()).intValue());
                    custoTo.setTipoRepeticao(((RepeticaoParcelaTo) cbTpParcelas.getSelectedItem()).getId());
                }

                int idCusto = CustoService.save(custoTo);
                if (idCusto > 0) {
                    UiUtils.getInfoDialog("Gasto adicionado com sucesso!").show();
                } else {
                    UiUtils.getInfoDialog("Ocorreu um erro no servidor ao gravar o custo").show();
                }
            }
            this.show();
        } catch (Exception ex) {
            UiUtils.getErrorDialog(ex.getMessage()).show();
            this.show();
        }
    }
}
