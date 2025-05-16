package org.example.ui;

import org.example.model.*;
import org.example.utils.Utils;

import java.util.List;

public class FazerVendaUI {
    private Federacao federacao;
    private VoluntarioVendas voluntario;

    public FazerVendaUI(Federacao federacao, VoluntarioVendas voluntario) {
        this.federacao = federacao;
        this.voluntario = voluntario;
    }

    public void run() {
        if (voluntario.getBarracaAtual() == null) {
            System.out.println("Você não está associado a nenhuma barraca!");
            return;
        }

        Barraca barraca = voluntario.getBarracaAtual();
        List<Produto> produtos = barraca.getProdutos();

        System.out.println("\n--- FAZER VENDA ---");
        System.out.println("Barraca: " + barraca.getNome());

        Produto produto = (Produto) Utils.selecionaObject(produtos);
        if (produto == null) return;

        int quantidade = Utils.readIntFromConsole("Quantidade: ");
        int stockDisponivel = barraca.getQuantidadeStock(produto);

        if (quantidade > stockDisponivel) {
            System.out.println("Stock insuficiente! Disponível: " + stockDisponivel);
            return;
        }

        Venda venda = new Venda(produto, quantidade, new Data(), voluntario);
        voluntario.registrarVenda(venda);
        barraca.atualizarStock(produto, -quantidade);

        System.out.println("Venda registrada com sucesso!");
        System.out.println("Total: " + (produto.getPreco() * quantidade) + "€");
    }
}