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

    public OpenListener(TwoComboBox frame, List<String[]> drawList) {
        this.frame = frame;
        this.drawList = drawList;
    }

    public void actionPerformed(ActionEvent e) {
        // Get user preferences
        final String fileType =
            String.valueOf(frame.getCombo1().getCombo().getSelectedItem());
        final String openMethod =
            String.valueOf(frame.getCombo2().getCombo().getSelectedItem());

        // Filter
        drawList = DTManager.filterTable(drawList,
                                         fileType.toLowerCase(),
                                         1);
         drawList = DTManager.getLastObjects(
             drawList,
             0,
             3,
             new DateTimeFormatterBuilder()
             .appendPattern("yyyy-MM-dd'T'HH:mm:ss[.SSSSSSS]'Z'")
             .toFormatter()
         );

        // On click
        String drawPath = drawList.get(0)[2];
        try {
            if (openMethod.equals(SearchListener.OPEN_FOLDER_OPTION)) {
                frame.dispose();
                String dirPath =
                    (new File(drawPath)).getParentFile().getAbsolutePath();
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
