package org.example.ui;

import org.example.model.*;
import org.example.utils.Data;
import org.example.utils.Utils;

import java.io.IOException;
import java.util.List;

/**
 * Interface de usuário para administradores da Federação Académica do Porto.
 */
public class MenuAdministrador {
    private Federacao federacao;
    private String opcao;

    /**
     * Construtor da classe MenuAdministrador.
     * @param federacao A federação associada ao menu.
     */
    public MenuAdministrador(Federacao federacao) {
        this.federacao = federacao;
    }

    /**
     * Executa o menu do administrador.
     * @throws IOException Se ocorrer um erro ao salvar ou carregar dados.
     */
    public void run() throws IOException {
        do {
            System.out.println("###### MENU ADMINISTRADOR #####");
            System.out.println("1. Registar Produto");
            System.out.println("2. Registar Barraca");
            System.out.println("3. Registar Voluntário");
            System.out.println("4. Criar Escala");
            System.out.println("5. Listar Voluntários por Número de Aluno");
            System.out.println("6. Listar Barracas por Vendas");
            System.out.println("7. Salvar Dados");
            System.out.println("8. Carregar Dados");
            System.out.println("9. Visualizar Dados");
            System.out.println("0. Voltar");

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            try {
                switch (opcao) {
                    case "1":
                        new RegistarProdutoUI(federacao).run();
                        break;
                    case "2":
                        registarBarraca();
                        break;
                    case "3":
                        registarVoluntario();
                        break;
                    case "4":
                        criarEscala();
                        break;
                    case "5":
                        listarVoluntarios();
                        break;
                    case "6":
                        listarBarracas();
                        break;
                    case "7":
                        federacao.salvarDados();
                        System.out.println("Dados salvos com sucesso.");
                        break;
                    case "8":
                        federacao.carregarDados();
                        System.out.println("Dados carregados com sucesso.");
                        break;
                    case "9":
                        visualizarDados();
                        break;
                    case "0":
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (!opcao.equals("0"));
    }

    /**
     * Registra uma nova barraca.
     */
    private void registarBarraca() {
        String nome = Utils.readLineFromConsole("Introduza o nome da barraca: ");
        String instituicao = Utils.readLineFromConsole("Introduza a instituição: ");
        if (nome == null || nome.trim().isEmpty() || instituicao == null || instituicao.trim().isEmpty()) {
            System.out.println("Nome ou instituição inválidos.");
            return;
        }
        Barraca barraca = new Barraca(nome, instituicao);
        if (federacao.adicionarBarraca(barraca)) {
            System.out.println("Barraca registrada com sucesso.");
        } else {
            System.out.println("Erro ao registrar barraca.");
        }
    }

    /**
     * Registra um novo voluntário.
     */
    private void registarVoluntario() {
        String nome = Utils.readLineFromConsole("Introduza o nome do voluntário: ");
        String numeroAluno = Utils.readLineFromConsole("Introduza o número de aluno: ");
        String curso = Utils.readLineFromConsole("Introduza o curso: ");
        String senha = Utils.readLineFromConsole("Introduza a senha: ");
        String instituicao = Utils.readLineFromConsole("Introduza a instituição: ");
        String tipo = Utils.readLineFromConsole("Tipo (1 - Vendas, 2 - Stock): ");
        if (nome == null || nome.trim().isEmpty() || numeroAluno == null || numeroAluno.trim().isEmpty() ||
                curso == null || curso.trim().isEmpty() || senha == null || senha.trim().isEmpty() ||
                instituicao == null || instituicao.trim().isEmpty()) {
            System.out.println("Dados do voluntário inválidos.");
            return;
        }
        Voluntario voluntario;
        if (tipo.equals("1")) {
            voluntario = new VoluntarioVendas(nome, numeroAluno, curso, senha, instituicao);
        } else if (tipo.equals("2")) {
            voluntario = new VoluntarioStock(nome, numeroAluno, curso, senha, instituicao);
        } else {
            System.out.println("Tipo de voluntário inválido.");
            return;
        }
        if (federacao.adicionarVoluntario(voluntario)) {
            System.out.println("Voluntário registrado com sucesso.");
        } else {
            System.out.println("Erro ao registrar voluntário.");
        }
    }

    /**
     * Cria uma nova escala para uma barraca.
     */
    private void criarEscala() {
        System.out.println("Selecione a barraca:");
        Utils.apresentaLista(federacao.getBarracas(), "Barracas disponíveis:");
        Object selectedBarraca = Utils.selecionaObject(federacao.getBarracas());
        if (!(selectedBarraca instanceof Barraca)) {
            System.out.println("Seleção inválida.");
            return;
        }
        Barraca barraca = (Barraca) selectedBarraca;

        Data data;
        try {
            data = Utils.readDateFromConsole("Introduza a data da escala (dd-MM-yyyy): ");
        } catch (Exception e) {
            System.out.println("Data inválida: " + e.getMessage());
            return;
        }

        Escala escala = new Escala(data, barraca);

        while (true) {
            System.out.println("Selecione um voluntário para adicionar à escala (ou 0 para terminar):");
            Utils.apresentaLista(federacao.getVoluntarios(), "Voluntários disponíveis:");
            Object selectedVoluntario = Utils.selecionaObject(federacao.getVoluntarios());
            if (selectedVoluntario == null) {
                break;
            }
            if (!(selectedVoluntario instanceof Voluntario)) {
                System.out.println("Seleção inválida.");
                continue;
            }
            Voluntario voluntario = (Voluntario) selectedVoluntario;
            if (escala.adicionarVoluntario(voluntario)) {
                System.out.println("Voluntário adicionado à escala.");
            } else {
                System.out.println("Erro ao adicionar voluntário. Verifique a instituição ou conflitos de escala.");
            }
        }

        if (escala.isValida()) {
            barraca.getEscalas().add(escala);
            System.out.println("Escala criada com sucesso.");
        } else {
            System.out.println("Escala inválida: mínimo de 2 voluntários requerido.");
        }
    }

    /**
     * Lista os voluntários ordenados por número de aluno.
     */
    private void listarVoluntarios() {
        List<Voluntario> voluntarios = federacao.listarVoluntariosPorNumeroAluno();
        Utils.apresentaLista(voluntarios, "Voluntários ordenados por número de aluno:");
    }

    /**
     * Lista as barracas ordenadas por vendas.
     */
    private void listarBarracas() {
        List<Barraca> barracas = federacao.listarBarracasPorVendas();
        Utils.apresentaLista(barracas, "Barracas ordenadas por vendas (decrescente):");
    }

    /**
     * Exibe todos os dados do sistema (produtos, barracas, voluntários, escalas).
     */
    private void visualizarDados() {
        System.out.println("\nProdutos:");
        for (Produto p : federacao.getProdutos()) {
            System.out.println("\t" + p.toString());
        }
        System.out.println("\nBarracas:");
        for (Barraca b : federacao.getBarracas()) {
            System.out.println("\t" + b.getNome() + " (" + b.getInstituicao() + ")");
            System.out.println("\tEstoque:");
            for (Produto p : b.getEstoque().keySet()) {
                System.out.println("\t\t" + p.getNome() + ": " + b.getEstoque().get(p));
            }
        }
        System.out.println("\nVoluntários:");
        for (Voluntario v : federacao.getVoluntarios()) {
            System.out.println("\t" + v.getNome() + " (" + v.getNumeroAluno() + ")");
        }
        System.out.println("\nEscalas:");
        for (Barraca b : federacao.getBarracas()) {
            for (Escala e : b.getEscalas()) {
                System.out.println("\t" + b.getNome() + " em " + e.getData().toAnoMesDiaString() + ":");
                for (Voluntario v : e.getVoluntarios()) {
                    System.out.println("\t\t" + v.getNome());
                }
            }
        }
    }
}