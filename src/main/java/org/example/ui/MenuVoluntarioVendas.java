package org.example.ui;

import org.example.model.*;
import org.example.utils.Data;
import org.example.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MenuVoluntarioVendas {
    private Federacao federacao;
    private String opcao;

    public MenuVoluntarioVendas(Federacao federacao) {
        this.federacao = federacao;
    }

    public void run() throws IOException {
        do {
            System.out.println("###### MENU #####");
            System.out.println("1. Fazer venda");
            System.out.println("0. Voltar");
            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            if (opcao.equals("1")) {
                realizarVenda();
            }
        } while (!opcao.equals("0"));
    }

    private void realizarVenda() {
        System.out.println("Selecione a barraca:");
        Utils.apresentaLista(federacao.getBarracas(), "Barracas disponíveis:");
        Barraca barraca = (Barraca) Utils.selecionaObject(federacao.getBarracas());
        if (barraca == null) {
            return;
        }

        System.out.println("Selecione o voluntário de vendas:");
        List<Voluntario> voluntariosVendas = new ArrayList<>();
        for (int i = 0; i < federacao.getVoluntarios().size(); i++) {
            Voluntario v = federacao.getVoluntarios().get(i);
            if (v instanceof VoluntarioVendas) {
                voluntariosVendas.add(v);
            }
        }
        Utils.apresentaLista(voluntariosVendas, "Voluntários de Vendas disponíveis:");
        VoluntarioVendas voluntario = (VoluntarioVendas) Utils.selecionaObject(voluntariosVendas);
        if (voluntario == null) {
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

        Data data = Utils.readDateFromConsole("Introduza a data (dd-MM-yyyy): ");

        Venda venda = new Venda(data, produto, quantidade, voluntario, barraca);
        if (barraca.registrarVenda(venda) && voluntario.registrarVenda(venda)) {
            System.out.println("Venda registrada com sucesso: " + venda);
        } else {
            System.out.println("Erro ao registrar a venda. Verifique o estoque.");
        }
    }
}