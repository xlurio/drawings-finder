package com.calegario.drawingsfinder;

import com.calegario.defaultwins.inputbox.InputBox;
import com.calegario.csvdb.CSVDBManager;
import com.calegario.searchfield.SearchListener;

public class App
{
    public static void main( String[] args )
    {
        CSVDBManager manager = new CSVDBManager(Settings.CSV_PATH);
        InputBox searchField = new InputBox(
            "Procurar desenhos",
            "Insira a referência do desenho que deseja encontrar:"
        );
        searchField.setBtnListener(new SearchListener(searchField, manager));
        searchField.showBox();
    }
}
