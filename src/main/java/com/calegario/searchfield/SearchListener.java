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
import static com.calegario.message.Message.*;

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
        /**
         * Look if there is a file name with containing the specified keyword
         and:
         * If it does: call the open parameters dialog;
         * If it does not: Tells the user the file was not found
        **/
        String drawRef = frame.getUserInput();
        List<String[]> drawList = new ArrayList<String[]>();
        try {
            drawList = manager.getDB();
            drawList = DTManager.filterTable(manager.getDB(), drawRef, 0);
            if (!drawList.isEmpty()){
                TwoComboBox comboBox = new TwoComboBox(
                    "Abrir desenho",
                    "Informe o tipo de arquivo e forma que deseja abrir:",
                    false,
                    new String[]{"PDF", "DWG", "ZIP", "STP", "STEP"},
                    new String[]{OPEN_DIRECTLY_OPTION, OPEN_FOLDER_OPTION},
                    352,
                    192
                );
                comboBox.setBtnListener(new OpenListener(
                    comboBox,
                    drawList
                ));
                comboBox.showBox();
            } else {
                showErrorDialog("Desenho '" + drawRef + "' não encontrado");
            }
        } catch (IOException ex){
            showErrorDialog("Não foi possível gravar o arquivo '" +
                            Settings.CSV_PATH + "'");
        }
    }
}
