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
import java.time.temporal.ChronoField;
import static com.calegario.message.Message.*;
import static com.calegario.utf8debugger.UTF8Debugger.*;
import java.lang.ProcessBuilder;
import java.lang.ArrayIndexOutOfBoundsException;

public class OpenListener implements ActionListener {
    private TwoComboBox frame;
    private List<String[]> drawList = new ArrayList<String[]>();

    public OpenListener(TwoComboBox frame, List<String[]> drawList) {
        this.frame = frame;
        this.drawList = drawList;
    }

    public void actionPerformed(ActionEvent e) {
        /**
         * Open the file or directory of file of the last modified file
         in the folder to look with the specified file name
        **/
        // Get user preferences
        final String fileType =
            String.valueOf(frame.getCombo1().getCombo().getSelectedItem());
        final String openMethod =
            String.valueOf(frame.getCombo2().getCombo().getSelectedItem());

        // Filter
        drawList = filterDrawList(drawList, fileType.toLowerCase());

        // On click
        if (!drawList.isEmpty()){
            String drawPath = drawList.get(0)[2];
            String cleanedDrawPath = replaceUnmappedChars(drawPath);
            try {
                File drawFile = new File(cleanedDrawPath);
                if (openMethod.equals(SearchListener.OPEN_FOLDER_OPTION)) {
                    frame.dispose();
                    File drawDirectory = drawFile.getParentFile();
                    Desktop.getDesktop().open(drawDirectory);
                } else {
                    frame.dispose();
                    Desktop.getDesktop().open(drawFile);
                }
            } catch (IOException ex) {
                showErrorDialog("'" + cleanedDrawPath + "' não encontrado");
            }
        }
    }

    private List<String[]> filterDrawList(
        List<String[]> drawListToFilter, String fileTypeToFilter
    )
    {
        /**
         * Returns filtered draw list
        **/
        drawListToFilter = DTManager.filterTable(drawListToFilter,
                                                 fileTypeToFilter,
                                                 1);
        if (!drawListToFilter.isEmpty()) {
            return DTManager.getLastObjects(
                 drawListToFilter,
                 0,
                 3,
                 DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
             );
         } else {
             frame.dispose();
             showErrorDialog(
                "O formato '" + fileTypeToFilter +
                "' não está disponível para este desenho"
            );
            return new ArrayList<String[]>();
         }
    }
}
