/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financialmobile.utils;

import com.sun.lwuit.Dialog;
import com.sun.lwuit.Display;
import com.sun.lwuit.Image;
import com.sun.lwuit.Label;
import com.sun.lwuit.TextArea;
import com.sun.lwuit.animations.CommonTransitions;
import com.sun.lwuit.plaf.Border;

/**
 *
 * @author diego
 */
public class UiUtils {

    private static Dialog progress;

    public static Dialog getErrorDialog(String content) {
        Dialog dialog = new Dialog("Erro!");
       // dialog.getStyle().setBgTransparency(0);
        dialog.getDialogStyle().setBorder(Border.createRoundBorder(6, 6, 0xe3ef5a));
       // dialog.setDialogType(Dialog.TYPE_ERROR);
        TextArea txArea = new TextArea(content, 4, 17);
        txArea.setEditable(false);
        dialog.addComponent(txArea);
        dialog.setTimeout(3000);
        dialog.setTransitionInAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_VERTICAL, true, 400));
        dialog.setTransitionOutAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_VERTICAL, true, 400));

        return dialog;

    }

    public static Dialog getInfoDialog(String content) {
        Dialog dialog = new Dialog("Aviso");
        //dialog.getStyle().setBgTransparency(0);
        dialog.getDialogStyle().setBorder(Border.createRoundBorder(6, 6, 0xe3ef5a));
       // dialog.setDialogType(Dialog.TYPE_INFO);
        TextArea txArea = new TextArea(content, 4, 17);
        txArea.setEditable(false);
        dialog.addComponent(txArea);
        dialog.setTimeout(3000);
        dialog.setTransitionInAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_VERTICAL, true, 400));
        dialog.setTransitionOutAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_VERTICAL, true, 400));
        return dialog;
    }

    public static Dialog getAlertDialog(String content) {
        Dialog dialog = new Dialog("Aviso");
        //dialog.getStyle().setBgTransparency(0);
        dialog.getDialogStyle().setBorder(Border.createRoundBorder(6, 6, 0xe3ef5a));
       // dialog.setDialogType(Dialog.TYPE_WARNING);
        TextArea txArea = new TextArea(content, 4, 17);
        txArea.setEditable(false);
        dialog.addComponent(txArea);
        dialog.setTimeout(3000);
        dialog.setTransitionInAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_VERTICAL, true, 400));
        dialog.setTransitionOutAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_VERTICAL, true, 400));

        return dialog;
    }

    /**
     *
     * @return
     * @throws IOException
     */
    public static void showLoading() {
        progress = new Dialog();
        progress.getDialogStyle().setBorder(Border.createRoundBorder(6, 6, 0xe3ef5a));
        progress.setTransitionInAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_VERTICAL, true, 400));
        progress.setTransitionOutAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_VERTICAL, false, 400));
        progress.addComponent(new Label("Aguarde"));
        try {
            progress.addComponent(new InfiniteProgressIndicator(Image.createImage("/br/com/financialmobile/res/img/loading.png")));
        } catch (Exception e) {
            //do nothing
        }
        int height = Display.getInstance().getDisplayHeight() - (progress.getContentPane().getPreferredH() + progress.getTitleComponent().getPreferredH());
        height /= 2;
        progress.show(height, height, 20, 20, true, false);
    }

    public static void hideLoading() {
        progress.dispose();
    }
}
