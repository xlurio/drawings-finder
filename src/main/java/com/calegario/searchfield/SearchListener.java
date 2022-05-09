package com.calegario.searchfield;

import com.calegario.defaultwins.inputbox.InputBox;
import com.calegario.csvdb.CSVDBManager;
import com.calegario.dtmanager.DTManager;
import java.util.List;
import com.calegario.openfile.OpenListener;
import com.calegario.defaultwins.twocombobox.TwoComboBox;
import com.calegario.drawingsfinder.Settings;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.util.*;

public class SearchListener implements ActionListener {
    private InputBox frame;
    private CSVDBManager manager;
    public static final String OPEN_DIRECTLY_OPTION = "Abrir diretamente";
    public static final String OPEN_FOLDER_OPTION = "Abrir local do arquivo";

    public SearchListener(InputBox frame, CSVDBManager manager) {
        this.frame = frame;
        this.manager = manager;
    }

    public void actionPerformed(ActionEvent e) {
        String drawRef = frame.getUserInput();
        List<String[]> drawList = new ArrayList<String[]>();
        try {
            drawList = manager.getDB();
            drawList = DTManager.filterTable(manager.getDB(), drawRef, 0);
            if (!drawList.isEmpty()){
                TwoComboBox comboBox = new TwoComboBox(
                    "Abrir desenho",
                    "Informe o tipo de arquivo e forma que deseja abrir:",
                    new String[]{"PDF", "DWG", "STEP"},
                    new String[]{OPEN_DIRECTLY_OPTION, OPEN_FOLDER_OPTION}
                );
                comboBox.setBtnListener(new OpenListener(
                    comboBox,
                    drawList
                ));
                comboBox.showBox();
            } else {
                JOptionPane.showMessageDialog(
                    null,
                    "Desenho '" + drawRef + "' não encontrado",
                    "ERRO",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (IOException ex){
            JOptionPane.showMessageDialog(
                null,
                "'" + Settings.CSV_PATH + "' não encontrado",
                "ERRO",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
