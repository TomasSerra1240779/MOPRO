package org.example.ui;

import org.example.model.Barraca;
import org.example.model.Federacao;
import org.example.utils.Utils;

/**
 * Interface de usuário para registrar barracas na federação.
 */
public class RegistarBarracaUI {
    private Federacao federacao;

    /**
     * Construtor da classe RegistarBarracaUI.
     * @param federacao A federação do sistema.
     */
    public RegistarBarracaUI(Federacao federacao) {
        this.federacao = federacao;
    }

    /**
     * Executa a interface para registrar uma barraca.
     */
    public void run() {
        System.out.println("\n--- REGISTAR BARRACA ---");
        String nome = Utils.readLineFromConsole("Introduza o nome da barraca: ");
        String instituicao = Utils.readLineFromConsole("Introduza a instituição: ");

        if (nome == null || nome.trim().isEmpty() || instituicao == null || instituicao.trim().isEmpty()) {
            System.out.println("Nome ou instituição inválidos.");
            return;
        }

        try {
            Barraca barraca = new Barraca(nome, instituicao);
            if (federacao.adicionarBarraca(barraca)) {
                System.out.println("Barraca registrada com sucesso.");
            } else {
                System.out.println("Erro ao registrar barraca.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}