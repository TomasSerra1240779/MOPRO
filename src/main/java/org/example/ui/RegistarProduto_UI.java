package org.example.ui;

import org.example.model.Federacao;
import org.example.model.Produto;
import org.example.utils.Utils;

public class RegistarProduto_UI {
    private Federacao federacao;

    public RegistarProduto_UI(Federacao federacao) {
        this.federacao = federacao;
    }

    public void run() {
        System.out.println("Novo Produto:");

        Produto novoProduto = introduzDados();
        apresentaDados(novoProduto);

        if (Utils.confirma("Confirma os dados? (S/N)")) {
            if (federacao.adicionarProduto(novoProduto)) {
                System.out.println("Dados do produto guardados com sucesso.");
            } else {
                System.out.println("Não foi possível guardar os dados do produto.");
            }
        }
    }

    private static Produto introduzDados() {
        String nome = Utils.readLineFromConsole("Introduza o nome do produto: ");
        double preco = Utils.readDoubleFromConsole("Introduza o preço: ");
        return new Produto(nome, preco);
    }

    private void apresentaDados(Produto produto) {
        System.out.println("Produto: " + produto.toString());
    }

}
