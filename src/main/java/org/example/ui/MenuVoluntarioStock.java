package org.example.ui;

import org.example.model.*;
import org.example.utils.Utils;

import java.io.IOException;

/**
 * Interface de usuário para voluntários de estoque.
 */
public class MenuVoluntarioStock {
    private Federacao federacao;
    private String opcao;

    /**
     * Construtor da classe MenuVoluntarioStock.
     * @param federacao Instância da federação.
     */
    public MenuVoluntarioStock(Federacao federacao) {
        this.federacao = federacao;
    }

    /**
     * Executa o menu do voluntário de estoque.
     * @throws IOException Se ocorrer um erro de entrada/saída.
     */
    public void run() throws IOException {
        do {
            System.out.println("###### MENU VOLUNTÁRIO STOCK #####");
            System.out.println("1. Adicionar Estoque");
            System.out.println("0. Voltar");
            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            if (opcao.equals("1")) {
                adicionarEstoque();
            }
        } while (!opcao.equals("0"));
    }

    /**
     * Permite ao voluntário adicionar estoque a uma barraca.
     */
    private void adicionarEstoque() {
        System.out.println("Selecione a barraca:");
        Utils.apresentaLista(federacao.getBarracas(), "Barracas disponíveis:");
        Object selectedBarraca = Utils.selecionaObject(federacao.getBarracas());
        if (!(selectedBarraca instanceof Barraca)) {
            System.out.println("Seleção inválida.");
            return;
        }
        Barraca barraca = (Barraca) selectedBarraca;

        System.out.println("Selecione o produto:");
        Utils.apresentaLista(federacao.getProdutos(), "Produtos disponíveis:");
        Object selectedProduto = Utils.selecionaObject(federacao.getProdutos());
        if (!(selectedProduto instanceof Produto)) {
            System.out.println("Seleção inválida.");
            return;
        }
        Produto produto = (Produto) selectedProduto;

        int quantidade = Utils.readIntFromConsole("Quantidade: ");
        if (quantidade <= 0) {
            System.out.println("Quantidade inválida.");
            return;
        }

        VoluntarioStock voluntario = new VoluntarioStock("Temp", "Temp", "Temp", "Temp", barraca.getInstituicao());
        if (voluntario.adicionarEstoque(barraca, produto, quantidade)) {
            System.out.println("Estoque adicionado com sucesso!");
        } else {
            System.out.println("Erro ao adicionar estoque. Verifique a instituição.");
        }
    }
}