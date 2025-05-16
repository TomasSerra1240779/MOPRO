package org.example.ui;

import org.example.model.*;
import org.example.utils.Data;
import org.example.utils.Utils;

import java.io.IOException;

public class MenuAdministrador {
    private Federacao federacao;
    private String opcao;

    public MenuAdministrador(Federacao federacao) {
        this.federacao = federacao;
    }

    public void run() throws IOException {
        do {
            System.out.println("###### MENU #####");
            System.out.println("1. Registar Produto");
            System.out.println("2. Registar Barraca");
            System.out.println("3. Registar Voluntário");
            System.out.println("4. Criar Escala");
            System.out.println("5. Listar Voluntários por Número de Aluno");
            System.out.println("6. Listar Barracas por Vendas");
            System.out.println("7. Salvar Dados");
            System.out.println("8. Carregar Dados");
            System.out.println("0. Voltar");

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            if (opcao.equals("1")) {
                RegistarProduto_UI ui = new RegistarProduto_UI(federacao);
                ui.run();
            } else if (opcao.equals("2")) {
                registarBarraca();
            } else if (opcao.equals("3")) {
                registarVoluntario();
            } else if (opcao.equals("4")) {
                criarEscala();
            } else if (opcao.equals("5")) {
                listarVoluntarios();
            } else if (opcao.equals("6")) {
                listarBarracas();
            } else if (opcao.equals("7")) {
                federacao.salvarDados();
                System.out.println("Dados salvos com sucesso.");
            } else if (opcao.equals("8")) {
                federacao.carregarDados();
                System.out.println("Dados carregados com sucesso.");
            }
        } while (!opcao.equals("0"));
    }

    private void registarBarraca() {
        String nome = Utils.readLineFromConsole("Introduza o nome da barraca: ");
        String instituicao = Utils.readLineFromConsole("Introduza a instituição: ");
        Barraca barraca = new Barraca(nome, instituicao);
        if (federacao.adicionarBarraca(barraca)) {
            System.out.println("Barraca registrada com sucesso.");
        } else {
            System.out.println("Erro ao registrar barraca.");
        }
    }

    private void registarVoluntario() {
        String nome = Utils.readLineFromConsole("Introduza o nome do voluntário: ");
        String numeroAluno = Utils.readLineFromConsole("Introduza o número de aluno: ");
        String curso = Utils.readLineFromConsole("Introduza o curso: ");
        String senha = Utils.readLineFromConsole("Introduza a senha: ");
        String instituicao = Utils.readLineFromConsole("Introduza a instituição: ");
        String tipo = Utils.readLineFromConsole("Tipo (1 - Vendas, 2 - Stock): ");
        Voluntario voluntario;
        if (tipo.equals("1")) {
            voluntario = new VoluntarioVendas(nome, numeroAluno, curso, senha, instituicao);
        } else {
            voluntario = new VoluntarioStock(nome, numeroAluno, curso, senha, instituicao);
        }
        if (federacao.adicionarVoluntario(voluntario)) {
            System.out.println("Voluntário registrado com sucesso.");
        } else {
            System.out.println("Erro ao registrar voluntário.");
        }
    }

    private void criarEscala() {
        System.out.println("Selecione a barraca:");
        Utils.apresentaLista(federacao.getBarracas(), "Barracas disponíveis:");
        Barraca barraca = (Barraca) Utils.selecionaObject(federacao.getBarracas());
        if (barraca == null) {
            return;
        }

        Data data = Utils.readDateFromConsole("Introduza a data da escala (dd-MM-yyyy): ");
        Escala escala = new Escala(data, barraca);

        while (true) {
            System.out.println("Selecione um voluntário para adicionar à escala:");
            Utils.apresentaLista(federacao.getVoluntarios(), "Voluntários disponíveis:");
            Voluntario voluntario = (Voluntario) Utils.selecionaObject(federacao.getVoluntarios());
            if (voluntario == null) {
                break;
            }
            if (escala.adicionarVoluntario(voluntario)) {
                System.out.println("Voluntário adicionado à escala.");
            } else {
                System.out.println("Erro ao adicionar voluntário. Verifique a instituição ou conflitos de escala.");
            }
        }

        if (escala.validarEscala()) {
            barraca.getEscalas().add(escala);
            System.out.println("Escala criada com sucesso.");
        } else {
            System.out.println("Escala inválida: mínimo de 2 voluntários requerido.");
        }
    }

    private void listarVoluntarios() {
        List<Voluntario> voluntarios = federacao.listarVoluntariosPorNumeroAluno();
        Utils.apresentaLista(voluntarios, "Voluntários ordenados por número de aluno:");
    }

    private void listarBarracas() {
        List<Barraca> barracas = federacao.listarBarracasPorVendas();
        Utils.apresentaLista(barracas, "Barracas ordenadas por vendas (decrescente):");
    }
}