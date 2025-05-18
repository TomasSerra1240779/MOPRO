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
            System.out.println("3. Registar Pessoa");
            System.out.println("4. Criar Escala");
            System.out.println("5. Listar Voluntários por Número de Aluno");
            System.out.println("6. Listar Barracas por Vendas");
            System.out.println("7. Visualizar Dados");
            System.out.println("0. Voltar");

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");
            if (opcao == null) opcao = "";

            try {
                switch (opcao) {
                    case "1":
                        new RegistarProdutoUI(federacao).run();
                        break;
                    case "2":
                        new RegistarBarracaUI(federacao).run();
                        break;
                    case "3":
                        new RegistarPessoaUI(federacao).run();
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
            if (data == null) {
                System.out.println("Data inválida.");
                return;
            }
        } catch (Exception e) {
            System.out.println("Data inválida: " + e.getMessage());
            return;
        }

        Escala escala = new Escala(data, barraca);

        while (escala.getVoluntarios().size() < 2) {
            System.out.println("Selecione um voluntário para adicionar à escala:");
            List<Pessoa> voluntarios = federacao.getPessoas().stream()
                    .filter(p -> p instanceof Voluntario).toList();
            Utils.apresentaLista(voluntarios, "Voluntários disponíveis:");
            Object selectedVoluntario = Utils.selecionaObject(voluntarios);
            if (selectedVoluntario == null) {
                System.out.println("Nenhum voluntário selecionado. A escala deve ter pelo menos 2 voluntários.");
                continue;
            }
            if (!(selectedVoluntario instanceof Voluntario)) {
                System.out.println("Seleção inválida.");
                continue;
            }
            Voluntario voluntario = (Voluntario) selectedVoluntario;
            try {
                if (escala.adicionarVoluntario(voluntario)) {
                    System.out.println("Voluntário adicionado à escala.");
                } else {
                    System.out.println("Erro ao adicionar voluntário. Verifique a instituição.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        barraca.getEscalas().add(escala);
        System.out.println("Escala criada com sucesso.");
    }

    /**
     * Lista os voluntários ordenados por número de aluno.
     */
    private void listarVoluntarios() {
        List<Voluntario> voluntarios = federacao.listarVoluntariosPorNumeroAluno();
        if (voluntarios.isEmpty()) {
            System.out.println("Nenhum voluntário registrado.");
        } else {
            Utils.apresentaLista(voluntarios, "Voluntários ordenados por número de aluno:");
        }
    }

    /**
     * Lista as barracas agrupadas por classificação e ordenadas por vendas.
     */
    private void listarBarracas() {
        Data data = Utils.readDateFromConsole("Introduza a data para listagem (dd-MM-yyyy): ");
        if (data == null) {
            System.out.println("Data inválida.");
            return;
        }
        System.out.println(federacao.listarBarracasPorVendas(data));
    }

    /**
     * Exibe todos os dados do sistema (produtos, barracas, voluntários, escalas, vendas, classificações).
     */
    private void visualizarDados() {
        Data data = new Data();
        System.out.println("\n=== Visualização de Dados ===");

        System.out.println("\nProdutos:");
        if (federacao.getProdutos().isEmpty()) {
            System.out.println("\tNenhum produto registrado.");
        } else {
            for (Produto p : federacao.getProdutos()) {
                System.out.println("\t" + p.toString());
            }
        }

        System.out.println("\nBarracas:");
        if (federacao.getBarracas().isEmpty()) {
            System.out.println("\tNenhuma barraca registrada.");
        } else {
            for (Barraca b : federacao.getBarracas()) {
                System.out.println("\t" + b.getNome() + " (" + b.getInstituicao() + ")");
                System.out.println("\tClassificação: " + b.classificar(data));
                System.out.println("\tEstoque:");
                if (b.getProdutos().isEmpty()) {
                    System.out.println("\t\tSem estoque.");
                } else {
                    for (Produto p : b.getProdutos()) {
                        System.out.println("\t\t" + p.getNome() + ": " + b.getQuantidadeStock(p));
                    }
                }
                System.out.println("\tVendas:");
                boolean hasVendas = false;
                for (Venda v : b.getVendas()) {
                    System.out.println("\t\t" + v.toString());
                    hasVendas = true;
                }
                if (!hasVendas) {
                    System.out.println("\t\tNenhuma venda registrada.");
                }
            }
        }

        System.out.println("\nPessoas:");
        if (federacao.getPessoas().isEmpty()) {
            System.out.println("\tNenhuma pessoa registrada.");
        } else {
            for (Pessoa p : federacao.getPessoas()) {
                System.out.print("\t" + p.getNome() + " (" + p.getNumeroAluno() + ")");
                if (p instanceof VoluntarioVendas) {
                    System.out.println(" - Classificação: " + ((VoluntarioVendas) p).classificar(data));
                } else {
                    System.out.println();
                }
            }
        }

        System.out.println("\nEscalas:");
        boolean hasEscalas = false;
        for (Barraca b : federacao.getBarracas()) {
            if (!b.getEscalas().isEmpty()) {
                hasEscalas = true;
                for (Escala e : b.getEscalas()) {
                    System.out.println("\t" + b.getNome() + " em " + e.getData().toAnoMesDiaString() + ":");
                    for (Voluntario v : e.getVoluntarios()) {
                        System.out.println("\t\t" + v.getNome() + " (" + v.getNumeroAluno() + ")");
                    }
                }
            }
        }
        if (!hasEscalas) {
            System.out.println("\tNenhuma escala registrada.");
        }
    }
}