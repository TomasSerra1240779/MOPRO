package org.example.ui;

import org.example.model.Administrador;
import org.example.model.Pessoa;
import org.example.model.VoluntarioStock;
import org.example.model.VoluntarioVendas;
import org.example.utils.Utils;
import org.example.model.Federacao;

/**
 * Interface de usuário para registrar pessoas (administradores ou voluntários) na federação.
 */
public class RegistarPessoaUI {
    private Federacao federacao;

    /**
     * Construtor da classe RegistarPessoaUI.
     * @param federacao A federação do sistema.
     */
    public RegistarPessoaUI(Federacao federacao) {
        this.federacao = federacao;
    }

    /**
     * Executa a interface para registrar uma pessoa.
     */
    public void run() {
        System.out.println("\n--- REGISTAR PESSOA ---");
        String nome = Utils.readLineFromConsole("Introduza o nome: ");
        String numeroAluno = Utils.readLineFromConsole("Introduza o número de aluno: ");
        String curso = Utils.readLineFromConsole("Introduza o curso: ");
        String senha = Utils.readLineFromConsole("Introduza a senha: ");
        String tipo = Utils.readLineFromConsole("Tipo (1 - Administrador, 2 - Voluntário Vendas, 3 - Voluntário Stock): ");
        String instituicao = tipo.equals("1") ? "" : Utils.readLineFromConsole("Introduza a instituição: ");

        if (nome == null || nome.trim().isEmpty() || numeroAluno == null || numeroAluno.trim().isEmpty() ||
                curso == null || curso.trim().isEmpty() || senha == null || senha.trim().isEmpty() ||
                (!tipo.equals("1") && (instituicao == null || instituicao.trim().isEmpty()))) {
            System.out.println("Dados inválidos.");
            return;
        }

        try {
            Pessoa pessoa;
            switch (tipo) {
                case "1":
                    pessoa = new Administrador(nome, numeroAluno, curso, senha);
                    break;
                case "2":
                    pessoa = new VoluntarioVendas(nome, numeroAluno, curso, senha, instituicao);
                    break;
                case "3":
                    pessoa = new VoluntarioStock(nome, numeroAluno, curso, senha, instituicao);
                    break;
                default:
                    System.out.println("Tipo inválido.");
                    return;
            }
            if (federacao.adicionarPessoa(pessoa)) {
                System.out.println("Pessoa registrada com sucesso.");
            } else {
                System.out.println("Erro ao registrar pessoa.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}