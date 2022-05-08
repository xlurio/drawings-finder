package com.calegario.openfile;

import java.time.format.DateTimeFormatter;
import static com.calegario.dtmanager.DTManager;
import com.calegario.searchfield.SearchListener;
import com.calegario.defaultwins.twocombobox.TwoComboBox;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class OpenListener implements ActionListener {
    private TwoComboBox frame;
    private List<String[]> drawList;
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
            new DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss:SSSSSS'Z'")
        );
        this.openMethod = openMethod;
    }

    public void actionPerformed(ActionEvent e) {
        frame.dispose();
        String drawPath = this.drawList.get(0)[2];
        if (openMethod.equals(SearchListener.OPEN_FOLDER_OPTION)) {
            String dirPath = Paths.get(drawPath).getParent().getFileName();
            Desktop.getDesktop().open(new File(dirPath));
        } else {
            Desktop.getDesktop().open(new File(drawPath));
        }
    }
}
