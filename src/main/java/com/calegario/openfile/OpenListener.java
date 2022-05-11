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
        String drawPath = replaceUnmappedChars(drawList.get(0)[2],
                                               getReplacePlan());
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

    private String replaceUnmappedChars(String stringToMap,
                                        List<String[]> replacePlan){
        /**
         * Replace unmapped characters by its correspondent
        **/
        String newStr = "";
        for (int i = 0; i < replacePlan.size(); i++) {
            newStr = stringToMap.replaceAll(replacePlan.get(i)[0],
                                            replacePlan.get(i)[1]);
        }
        return newStr;
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

    private static List<String[]> getReplacePlan(){
        /**
         * Returns the replace plan
        **/
        List<String[]> replacePlan = new ArrayList<String[]>();
        replacePlan.add(new String[]{"â‚¬", "€"});
        replacePlan.add(new String[]{"â€š", "‚"});
        replacePlan.add(new String[]{"Æ’", "ƒ"});
        replacePlan.add(new String[]{"â€ž", "„"});
        replacePlan.add(new String[]{"â€¦", "…"});
        replacePlan.add(new String[]{"â€", "†"});
        replacePlan.add(new String[]{"â€¡", "‡"});
        replacePlan.add(new String[]{"Ë†", "ˆ"});
        replacePlan.add(new String[]{"â€°", "‰"});
        replacePlan.add(new String[]{"â€¹", "‹"});
        replacePlan.add(new String[]{"Å’", "Œ"});
        replacePlan.add(new String[]{"Å½", "Ž"});
        replacePlan.add(new String[]{"â€˜", "‘"});
        replacePlan.add(new String[]{"â€™", "’"});
        replacePlan.add(new String[]{"â€œ", "“"});
        replacePlan.add(new String[]{"â€", "”"});
        replacePlan.add(new String[]{"â€¢", "•"});
        replacePlan.add(new String[]{"â€“", "–"});
        replacePlan.add(new String[]{"â€”", "—"});
        replacePlan.add(new String[]{"Ëœ", "˜"});
        replacePlan.add(new String[]{"â„¢", "™"});
        replacePlan.add(new String[]{"Å¡", "š"});
        replacePlan.add(new String[]{"â€º", "›"});
        replacePlan.add(new String[]{"Å“", "œ"});
        replacePlan.add(new String[]{"Å¾", "ž"});
        replacePlan.add(new String[]{"Å¸", "Ÿ"});
        replacePlan.add(new String[]{"Â¡", "¡"});
        replacePlan.add(new String[]{"Â¢", "¢"});
        replacePlan.add(new String[]{"Â£", "£"});
        replacePlan.add(new String[]{"Â¤", "¤"});
        replacePlan.add(new String[]{"Â¥", "¥"});
        replacePlan.add(new String[]{"Â¦", "¦"});
        replacePlan.add(new String[]{"Â§", "§"});
        replacePlan.add(new String[]{"Â¨", "¨"});
        replacePlan.add(new String[]{"Â©", "©"});
        replacePlan.add(new String[]{"Âª", "ª"});
        replacePlan.add(new String[]{"Â«", "«"});
        replacePlan.add(new String[]{"Â¬", "¬"});
        replacePlan.add(new String[]{"Â®", "®"});
        replacePlan.add(new String[]{"Â¯", "¯"});
        replacePlan.add(new String[]{"Â°", "°"});
        replacePlan.add(new String[]{"Â±", "±"});
        replacePlan.add(new String[]{"Â²", "²"});
        replacePlan.add(new String[]{"Â³", "³"});
        replacePlan.add(new String[]{"Â´", "´"});
        replacePlan.add(new String[]{"Âµ", "µ"});
        replacePlan.add(new String[]{"Â¶", "¶"});
        replacePlan.add(new String[]{"Â·", "·"});
        replacePlan.add(new String[]{"Â¸", "¸"});
        replacePlan.add(new String[]{"Â¹", "¹"});
        replacePlan.add(new String[]{"Âº", "º"});
        replacePlan.add(new String[]{"Â»", "»"});
        replacePlan.add(new String[]{"Â¼", "¼"});
        replacePlan.add(new String[]{"Â½", "½"});
        replacePlan.add(new String[]{"Â¾", "¾"});
        replacePlan.add(new String[]{"Â¿", "¿"});
        replacePlan.add(new String[]{"Ã€", "À"});
        replacePlan.add(new String[]{"Ã„", "Ä"});
        replacePlan.add(new String[]{"Ã†", "Æ"});
        replacePlan.add(new String[]{"Ã‡", "Ç"});
        replacePlan.add(new String[]{"Ãˆ", "È"});
        replacePlan.add(new String[]{"Ã‰", "É"});
        replacePlan.add(new String[]{"ÃŠ", "Ê"});
        replacePlan.add(new String[]{"Ã‹", "Ë"});
        replacePlan.add(new String[]{"ÃŒ", "Ì"});
        replacePlan.add(new String[]{"ÃŽ", "Î"});
        replacePlan.add(new String[]{"Ã‘", "Ñ"});
        replacePlan.add(new String[]{"Ã’", "Ò"});
        replacePlan.add(new String[]{"Ã“", "Ó"});
        replacePlan.add(new String[]{"Ã”", "Ô"});
        replacePlan.add(new String[]{"Ã•", "Õ"});
        replacePlan.add(new String[]{"Ã–", "Ö"});
        replacePlan.add(new String[]{"Ã—", "×"});
        replacePlan.add(new String[]{"Ã˜", "Ø"});
        replacePlan.add(new String[]{"Ã™", "Ù"});
        replacePlan.add(new String[]{"Ãš", "Ú"});
        replacePlan.add(new String[]{"Ã›", "Û"});
        replacePlan.add(new String[]{"Ãœ", "Ü"});
        replacePlan.add(new String[]{"Ãž", "Þ"});
        replacePlan.add(new String[]{"ÃŸ", "ß"});
        replacePlan.add(new String[]{"Ã¡", "á"});
        replacePlan.add(new String[]{"Ã¢", "â"});
        replacePlan.add(new String[]{"Ã£", "ã"});
        replacePlan.add(new String[]{"Ã¤", "ä"});
        replacePlan.add(new String[]{"Ã¥", "å"});
        replacePlan.add(new String[]{"Ã¦", "æ"});
        replacePlan.add(new String[]{"Ã§", "ç"});
        replacePlan.add(new String[]{"Ã¨", "è"});
        replacePlan.add(new String[]{"Ã©", "é"});
        replacePlan.add(new String[]{"Ãª", "ê"});
        replacePlan.add(new String[]{"Ã«", "ë"});
        replacePlan.add(new String[]{"Ã¬", "ì"});
        replacePlan.add(new String[]{"Ã­", "í"});
        replacePlan.add(new String[]{"Ã®", "î"});
        replacePlan.add(new String[]{"Ã¯", "ï"});
        replacePlan.add(new String[]{"Ã°", "ð"});
        replacePlan.add(new String[]{"Ã±", "ñ"});
        replacePlan.add(new String[]{"Ã²", "ò"});
        replacePlan.add(new String[]{"Ã³", "ó"});
        replacePlan.add(new String[]{"Ã´", "ô"});
        replacePlan.add(new String[]{"Ãµ", "õ"});
        replacePlan.add(new String[]{"Ã¶", "ö"});
        replacePlan.add(new String[]{"Ã·", "÷"});
        replacePlan.add(new String[]{"Ã¸", "ø"});
        replacePlan.add(new String[]{"Ã¹", "ù"});
        replacePlan.add(new String[]{"Ãº", "ú"});
        replacePlan.add(new String[]{"Ã»", "û"});
        replacePlan.add(new String[]{"Ã¼", "ü"});
        replacePlan.add(new String[]{"Ã½", "ý"});
        replacePlan.add(new String[]{"Ã¾", "þ"});
        replacePlan.add(new String[]{"Ã¿", "ÿ"});

        replacePlan.add(new String[]{"Ãƒ", "Ã"});

        replacePlan.add(new String[]{"Â", ""});
        replacePlan.add(new String[]{"Ã‚", "Â"});

        replacePlan.add(new String[]{"Å", "Š"});
        replacePlan.add(new String[]{"Ã…", "Å"});

        return replacePlan;
    }
}
