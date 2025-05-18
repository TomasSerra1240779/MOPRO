package org.example.ui;

import org.example.model.*;
import org.example.utils.Data;
import org.example.utils.Utils;

import java.util.List;

/**
 * Interface de usuário para voluntários de vendas registrarem vendas.
 */
public class FazerVendaUI {
    private Federacao federacao;
    private VoluntarioVendas voluntario;

    /**
     * Construtor da classe FazerVendaUI.
     * @param federacao A federação do sistema.
     * @param voluntario O voluntário de vendas autenticado.
     */
    public FazerVendaUI(Federacao federacao, VoluntarioVendas voluntario) {
        this.federacao = federacao;
        this.voluntario = voluntario;
    }

    /**
     * Executa a interface para registrar uma venda.
     */
    public void run() {
        Barraca barraca = voluntario.getBarracaAtual();
        if (barraca == null) {
            System.out.println("Você não está escalado para nenhuma barraca hoje!");
            return;
        }

        System.out.println("\n--- FAZER VENDA ---");
        System.out.println("Barraca: " + barraca.getNome());

        List<Produto> produtos = barraca.getProdutos();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto disponível no estoque!");
            return;
        }

        System.out.println("Selecione o produto:");
        Utils.apresentaLista(produtos, "Produtos disponíveis:");
        Produto produto = (Produto) Utils.selecionaObject(produtos);
        if (produto == null) {
            System.out.println("Nenhum produto selecionado.");
            return;
        }

        int quantidade = Utils.readIntFromConsole("Quantidade: ");
        if (quantidade <= 0) {
            System.out.println("Quantidade inválida.");
            return;
        }

        int stockDisponivel = barraca.getQuantidadeStock(produto);
        if (quantidade > stockDisponivel) {
            System.out.println("Estoque insuficiente! Disponível: " + stockDisponivel);
            return;
        }

        Data data = new Data();
        Venda venda = new Venda(data, produto, quantidade, voluntario, barraca);
        if (barraca.registrarVenda(venda) && voluntario.registrarVenda(venda)) {
            System.out.println("Venda registrada com sucesso!");
            System.out.println("Total: €" + String.format("%.2f", venda.getValorTotal()));
        } else {
            System.out.println("Erro ao registrar a venda. Verifique o estoque.");
        }
    }
}