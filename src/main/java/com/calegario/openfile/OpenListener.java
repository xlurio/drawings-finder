package com.calegario.openfile;

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
        this.drawList = drawList;
        this.fileType = fileType;
        this.openMethod = openMethod;
    }

    public void actionPerformed(ActionEvent e) {
        frame.dispose();
        String drawPath = getDrawPath();
        if (openMethod.equals(SearchListener.OPEN_FOLDER_OPTION)) {
            String dir_path = Paths.get(drawPath).getParent().getFileName();
            Desktop.getDesktop().open(new File(dir_path));
        } else {
            Desktop.getDesktop().open(new File(dir_path));
        }
    }

    public String getDrawPath() {
        int extColIndex = 1;
        int pathColIndex = 2;
        String extToLook = fileType.toLowerCase();
        for (int i = 0; i < drawList.size(); i++) {
            // Code
        }
    }
}
