package org.example.ui;

import org.example.model.Federacao;
import org.example.model.Voluntario;
import org.example.model.VoluntarioStock;
import org.example.model.VoluntarioVendas;
import org.example.utils.Utils;

import java.io.IOException;

/**
 * Classe que representa o menu inicial do sistema.
 */
public class MenuInicialUI {
    private Federacao federacao;

    /**
     * Construtor da classe MenuInicialUI.
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

    /**
     * Autentica um administrador.
     * @throws IOException Se ocorrer um erro de entrada/saída.
     */
    private void autenticarAdministrador() throws IOException {
        String username = Utils.readLineFromConsole("Username (admin): ");
        String password = Utils.readLineFromConsole("Password: ");
        if (username.equals("admin") && password.equals("admin123")) {
            new MenuAdministrador(federacao).run();
        } else {
            System.out.println("Credenciais inválidas!");
        }
    }

    /**
     * Autentica um voluntário de estoque.
     * @throws IOException Se ocorrer um erro de entrada/saída.
     */
    private void autenticarVoluntarioStock() throws IOException {
        String numero = Utils.readLineFromConsole("Número de voluntário: ");
        String password = Utils.readLineFromConsole("Password: ");
        Voluntario voluntario = federacao.getVoluntarios().stream()
                .filter(v -> v.getNumeroAluno().equals(numero) && v instanceof VoluntarioStock)
                .findFirst()
                .orElse(null);
        if (voluntario != null && voluntario.autenticar(password)) {
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
        Voluntario voluntario = federacao.getVoluntarios().stream()
                .filter(v -> v.getNumeroAluno().equals(numero) && v instanceof VoluntarioVendas)
                .findFirst()
                .orElse(null);
        if (voluntario != null && voluntario.autenticar(password)) {
            new MenuVoluntarioVendas(federacao).run();
        } else {
            System.out.println("Credenciais inválidas ou voluntário não é do tipo Vendas!");
        }
    }
}