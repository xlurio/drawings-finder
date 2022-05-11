package com.calegario.drawingsfinder;

import com.calegario.defaultwins.inputbox.InputBox;
import com.calegario.csvdb.CSVDBManager;
import com.calegario.searchfield.SearchListener;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
import java.nio.charset.*;
import static com.calegario.message.Message.*;

public class App
{
    public static void main( String[] args )
    {
        /**
         * Program that finds and opens pdf, dwg, stp and step files by a
         keyword
        **/
        if (!Settings.CSV_PATH.isEmpty()) {
            try{
                showSearchField();
            } catch (FileNotFoundException ex) {
                showErrorDialog("'" + Settings.CSV_PATH + "' não encontrado");
            } catch (IOException ex) {
                showErrorDialog("Não foi possível gravar o arquivo '" +
                                Settings.CSV_PATH + "'");
            }
        } else {
            showErrorDialog("CSV_PATH não configurado");
        }
    }

    private static void showSearchField()
        throws FileNotFoundException, IOException
    {
        /**
         * Paint the drawings search field
        **/
        String[] header = new String[]{
            "file_name", "extension", "path", "last_mod"
        };
        CSVDBManager manager = new CSVDBManager(
            Settings.CSV_PATH,
            header,
            '|'
        );
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
    }
}
