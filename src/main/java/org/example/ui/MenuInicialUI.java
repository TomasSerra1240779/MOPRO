package org.example.ui;

import org.example.model.*;
import org.example.utils.Utils;

import java.io.IOException;

/**
 * Classe que representa o menu inicial do sistema.
 */
public class MenuInicialUI {
    private Federacao federacao;

    /**
     * Construtor da classe MenuInicial_UI.
     * @param federacao Instância da federação.
     */
    public MenuInicialUI(Federacao federacao) {
        this.federacao = federacao;
    }

    /**
     * Executa o menu inicial.
     * @throws IOException Se ocorrer um erro de entrada/saída.
     */
    public void run() throws IOException {
        String opcao;
        do {
            System.out.println("\n###### MENU INICIAL ######");
            System.out.println("1. Login Administrador");
            System.out.println("2. Login Voluntário Stock");
            System.out.println("3. Login Voluntário Vendas");
            System.out.println("0. Sair");
            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            switch (opcao) {
                case "1":
                    autenticarAdministrador();
                    break;
                case "2":
                    autenticarVoluntarioStock();
                    break;
                case "3":
                    autenticarVoluntarioVendas();
                    break;
                case "0":
                    System.out.println("A sair do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (!opcao.equals("0"));
    }

    private void salvarDados() {
        try {
            federacao.saveToFile("federacao.dat");
            System.out.println("Dados salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    private void carregarDados() {
        try {
            Federacao novaFederacao = Federacao.loadFromFile("federacao.dat");
            // Copiar dados para a federação atual, mantendo referências
            federacao.getProdutos().clear();
            federacao.getProdutos().addAll(novaFederacao.getProdutos());
            federacao.getBarracas().clear();
            federacao.getBarracas().addAll(novaFederacao.getBarracas());
            federacao.getPessoas().clear();
            federacao.getPessoas().addAll(novaFederacao.getPessoas());
            System.out.println("Dados carregados com sucesso!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        }
    }

    /**
     * Autentica um administrador.
     * @throws IOException Se ocorrer um erro de entrada/saída.
     */
    private void autenticarAdministrador() throws IOException {
        String numero = Utils.readLineFromConsole("Número de administrador: ");
        String password = Utils.readLineFromConsole("Password: ");
        Pessoa pessoa = federacao.getPessoas().stream()
                .filter(p -> p.getNumeroAluno().equals(numero) && p instanceof Administrador)
                .findFirst()
                .orElse(null);
        if (pessoa != null && ((Administrador) pessoa).autenticar(password)) {
            new MenuAdministrador(federacao).run();
        } else {
            System.out.println("Credenciais inválidas ou pessoa não é administrador!");
        }
    }

    /**
     * Autentica um voluntário de estoque.
     * @throws IOException Se ocorrer um erro de entrada/saída.
     */
    private void autenticarVoluntarioStock() throws IOException {
        String numero = Utils.readLineFromConsole("Número de voluntário: ");
        String password = Utils.readLineFromConsole("Password: ");
        Pessoa pessoa = federacao.getPessoas().stream()
                .filter(p -> p.getNumeroAluno().equals(numero) && p instanceof VoluntarioStock)
                .findFirst()
                .orElse(null);
        if (pessoa != null && ((VoluntarioStock) pessoa).autenticar(password)) {
            new MenuVoluntarioStock(federacao).run();
        } else {
            System.out.println("Credenciais inválidas ou voluntário não é do tipo Stock!");
        }
    }

    /**
     * Autentica um voluntário de vendas.
     * @throws IOException Se ocorrer um erro de entrada/saída.
     */
    private void autenticarVoluntarioVendas() throws IOException {
        String numero = Utils.readLineFromConsole("Número de voluntário: ");
        String password = Utils.readLineFromConsole("Password: ");
        Pessoa pessoa = federacao.getPessoas().stream()
                .filter(p -> p.getNumeroAluno().equals(numero) && p instanceof VoluntarioVendas)
                .findFirst()
                .orElse(null);
        if (pessoa != null && ((VoluntarioVendas) pessoa).autenticar(password)) {
            new MenuVoluntarioVendas(federacao, (VoluntarioVendas) pessoa).run();
        } else {
            System.out.println("Credenciais inválidas ou voluntário não é do tipo Vendas!");
        }
    }
}