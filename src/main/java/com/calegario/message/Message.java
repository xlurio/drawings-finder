package com.calegario.message;

import javax.swing.JOptionPane;

public class Message {
    /**
     * Holds the message dialogs for the program
    **/
    public static void showErrorDialog(String msg){
        /**
         * Paints a error dialog
        **/
        JOptionPane.showMessageDialog(
            null,
            msg,
            "ERRO",
            JOptionPane.ERROR_MESSAGE
        );
    }
}
