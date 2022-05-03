package com.calegario.drawingsmanager;

import com.calegario.defaultwins.inputbox.InputBox

public class App
{
    public static void main( String[] args )
    {
        InputBox searchField = new InputBox(
            "Procurar desenhos",
            "Insira a referência do desenho que deseja encontrar:"
        )
        searchField.setBtnListener()
    }
}
