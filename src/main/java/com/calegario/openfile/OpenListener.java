package com.calegario.openfile;

import com.calegario.dtmanager.DTManager;
import java.util.List;
import java.nio.file.Paths;
import com.calegario.searchfield.SearchListener;
import com.calegario.defaultwins.twocombobox.TwoComboBox;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.format.*;
import javax.swing.*;
import java.util.*;

public class OpenListener implements ActionListener {
    private TwoComboBox frame;
    private List<String[]> drawList = new ArrayList<String[]>();
    private String fileType;
    private String openMethod;

    public OpenListener(TwoComboBox frame, List<String[]> drawList,
                        String fileType, String openMethod) {
        this.frame = frame;
        this.drawList = DTManager.filterTable(drawList, fileType, 1);
        this.drawList = DTManager.getLastObjects(
            this.drawList,
            0,
            3,
            new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd'T'HH:mm:ss:SSSSSS'Z'")
            .toFormatter()
        );
        this.openMethod = openMethod;
    }

    public void actionPerformed(ActionEvent e) {
        String drawPath = this.drawList.get(0)[2];
        try {
            if (openMethod.equals(SearchListener.OPEN_FOLDER_OPTION)) {
                frame.dispose();
                String dirPath =
                    Paths.get(drawPath).getParent().getFileName().toString();
                Desktop.getDesktop().open(new File(dirPath));
            } else {
                frame.dispose();
                Desktop.getDesktop().open(new File(drawPath));
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(
                null,
                "'" + drawPath + "' não encontrado",
                "ERRO",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
