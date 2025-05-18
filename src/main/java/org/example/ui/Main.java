package org.example.ui;

import org.example.model.Federacao;

public class Main {
    public static void main(String[] args) {
        try {
            // Construção da empresa
            Federacao fap = new Federacao("Federação Académica do Porto");
            fap.inicializarDadosTeste();
            System.out.println(fap);

            MenuInicialUI uiMenu = new MenuInicialUI(fap);
            uiMenu.run();

            System.out.println(fap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}