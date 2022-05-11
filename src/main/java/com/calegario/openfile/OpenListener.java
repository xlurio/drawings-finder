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
            showErrorDialog("'" + drawPath + "' não encontrado");
        }
    }

    private static List<String[]> filterDrawList(
        List<String[]> drawListToFilter, String fileTypeToFilter
    )
    {
        /**
         * Returns filtered draw list
        **/
        drawListToFilter = DTManager.filterTable(drawListToFilter,
                                                 fileTypeToFilter,
                                                 1);
        return DTManager.getLastObjects(
             drawListToFilter,
             0,
             3,
             new DateTimeFormatterBuilder()
             .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
             .optionalStart()
             .appendPattern(".")
             .appendFraction(ChronoField.MICRO_OF_SECOND, 1, 7, false)
             .optionalEnd()
             .appendPattern("'Z'")
             .toFormatter()
         );
    }
}
