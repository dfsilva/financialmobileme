/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financialmobile.ui;

import com.sun.lwuit.Command;
import com.sun.lwuit.Form;
import com.sun.lwuit.animations.CommonTransitions;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.layouts.BoxLayout;

/**
 *
 * @author diego
 */
public class FinancialMobileForm extends Form implements IFinancialMobileForm {

    private FinancialMobileForm parent;
    private Command cmBack;

    protected FinancialMobileForm(String title, FinancialMobileForm parent) {
        super(title);
        this.parent = parent;
    }

    protected FinancialMobileForm(String title){
        super(title);
    }

    public void initComponents() {
        cmBack = new Command("Voltar") {
            public void actionPerformed(ActionEvent ae) {
                voltar();
            }
        };
    }

    public void initForm() {
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        addCommand(cmBack);
    }

    private void voltar() {
        this.setTransitionOutAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, true, 400));
        this.parent.removeTransicaoSlide();
        this.parent.show();
    }

    public void adicionaTransicaoSlide() {
        this.setTransitionInAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 400));
    }
    public void removeTransicaoSlide(){
        this.setTransitionInAnimator(null);
    }


}
