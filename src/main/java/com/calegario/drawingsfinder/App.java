package com.calegario.drawingsfinder;

import com.calegario.defaultwins.inputbox.InputBox;
import com.calegario.csvdb.CSVDBManager;
import com.calegario.searchfield.SearchListener;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class App
{
    public static void main( String[] args ) throws FileNotFoundException, IOException
    {
        if (!Settings.CSV_PATH.isEmpty()) {
            String[] header = new String[]{
                "file_name", "extension", "path", "last_mod"
            };
            CSVDBManager manager = new CSVDBManager(Settings.CSV_PATH,
                                                    header,
                                                    '|');
            List<String[]> drawList = manager.getDB();
            InputBox searchField = new InputBox(
                "Procurar desenhos",
                "Insira a referência do desenho que deseja encontrar:",
                true,
                352,
                160
            );
            searchField.setBtnListener(new SearchListener(searchField, manager));
            searchField.showBox();
        } else {
            JOptionPane.showMessageDialog(
                null,
                "CSV_PATH não configurado",
                "ERRO",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
