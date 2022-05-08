package com.calegario.searchfield;

import com.calegario.defaultwins.inputbox.InputBox;
import com.calegario.csvdb.CSVDBManager;
import static com.calegario.dtmanager.DTManager;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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
        drawList = DTManager.filterTable(manager.getDB(), drawRef, 0);
        if (!drawList.isEmpty()) {
            TwoComboBox comboBox = new TwoComboBox(
                "Abrir desenho",
                "Informe o tipo de arquivo e forma que deseja abrir:",
                new String[]{"PDF", "DWG", "STEP"},
                new String[]{OPEN_DIRECTLY_OPTION, OPEN_FOLDER_OPTION}
            );
            comboBox.setBtnListener(new OpenListener(
                comboBox,
                drawList,
                comboBox.getCombo1().getCombo().getSelectedItem().toString(),
                comboBox.getCombo2().getCombo().getSelectedItem().toString()
            ));
            comboBox.showBox();
        }
    }
}
