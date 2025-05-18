package org.example.ui;

import org.example.model.Federacao;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Federacao fap;
            try {
                fap = Federacao.loadFromFile("federacao.dat");
                System.out.println("Dados carregados do arquivo.");
            } catch (IOException | ClassNotFoundException e) {
                fap = new Federacao("Federação Académica do Porto");
                fap.inicializarDadosTeste();
                System.out.println("Nenhum arquivo encontrado. Dados de teste inicializados.");
            }
            System.out.println(fap);
            MenuInicialUI uiMenu = new MenuInicialUI(fap);
            uiMenu.run();
            System.out.println(fap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}