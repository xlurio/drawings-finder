package com.calegario.searchfield;

import com.calegario.defaultwins.inputbox.InputBox;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SearchListener implements ActionListener {
    private InputBox frame;

    public SearchListener(InputBox frame) {
        this.frame = frame
    }

    public void actionPerformed(ActionEvent e) {
        String drawRef = frame.getUserInput();
    }
}
