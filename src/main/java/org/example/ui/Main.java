package org.example.ui;

import org.example.model.Federacao;
import org.example.utils.Utils;

import java.io.*;

public class Main {
    private static final String FICHEIRO_DADOS = "dados_fap.dat";

    public static void main(String[] args) {
        Federacao fap = carregarDados();

        try {
            MenuInicialUI uiMenu = new MenuInicialUI(fap);
            uiMenu.run();

            guardarDados(fap);
        } catch (Exception e) {
            System.err.println("Erro no sistema: " + e.getMessage());
        }
    }

    private static Federacao carregarDados() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FICHEIRO_DADOS))) {
            return (Federacao) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro de dados não encontrado. Criando nova federação...");
            return new Federacao("Federação Académica do Porto");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar dados: " + e.getMessage());
            return new Federacao("Federação Académica do Porto");
        }
    }

    private static void guardarDados(Federacao fap) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHEIRO_DADOS))) {
            oos.writeObject(fap);
            System.out.println("Dados guardados com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao guardar dados: " + e.getMessage());
        }
    }
}