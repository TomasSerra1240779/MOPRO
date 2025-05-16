package org.example.ui;

import org.example.model.*;
import org.example.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MenuVoluntarioStock {
    private Federacao federacao;
    private String opcao;

    public MenuVoluntarioStock(Federacao federacao) {
        this.federacao = federacao;
    }

    public void run() throws IOException {
        do {
            System.out.println("###### MENU #####");
            System.out.println("1. Adicionar Stock");
            System.out.println("0. Voltar");
            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            if (opcao.equals("1")) {
                adicionarStock();
            }
        } while (!opcao.equals("0"));
    }

    private void adicionarStock() {
        System.out.println("Selecione o voluntário de stock:");
        List<Voluntario> voluntariosStock = new ArrayList<>();
        for (int i = 0; i < federacao.getVoluntarios().size(); i++) {
            Voluntario v = federacao.getVoluntarios().get(i);
            if (v instanceof VoluntarioStock) {
                voluntariosStock.add(v);
            }
        }
        Utils.apresentaLista(voluntariosStock, "Voluntários de Stock disponíveis:");
        VoluntarioStock voluntario = (VoluntarioStock) Utils.selecionaObject(voluntariosStock);
        if (voluntario == null) {
            return;
        }

        System.out.println("Selecione a barraca:");
        Utils.apresentaLista(federacao.getBarracas(), "Barracas disponíveis:");
        Barraca barraca = (Barraca) Utils.selecionaObject(federacao.getBarracas());
        if (barraca == null) {
            return;
        }

        System.out.println("Selecione o produto:");
        Utils.apresentaLista(federacao.getProdutos(), "Produtos disponíveis:");
        Produto produto = (Produto) Utils.selecionaObject(federacao.getProdutos());
        if (produto == null) {
            return;
        }

        int quantidade = Utils.readIntFromConsole("Introduza a quantidade: ");
        if (quantidade <= 0) {
            System.out.println("Quantidade inválida.");
            return;
        }

        if (voluntario.adicionarEstoque(barraca, produto, quantidade)) {
            System.out.println("Estoque adicionado com sucesso.");
        } else {
            System.out.println("Erro ao adicionar estoque.");
        }
    }
}
