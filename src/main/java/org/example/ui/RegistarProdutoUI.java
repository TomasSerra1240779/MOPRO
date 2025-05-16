package org.example.ui;

import org.example.model.Federacao;
import org.example.model.Produto;
import org.example.utils.Utils;

/**
 * Interface de usuário para registrar produtos no sistema.
 */
public class RegistarProdutoUI {
    private Federacao federacao;

    /**
     * Construtor da classe RegistarProdutoUI.
     * @param federacao Federação onde o produto será registrado.
     */
    public RegistarProdutoUI(Federacao federacao) {
        this.federacao = federacao;
    }

    /**
     * Executa a interface para registrar um produto.
     */
    public void run() {
        System.out.println("Novo Produto:");
        try {
            Produto novoProduto = introduzDados();
            apresentaDados(novoProduto);
            if (Utils.confirma("Confirma os dados? (S/N)")) {
                if (federacao.adicionarProduto(novoProduto)) {
                    System.out.println("Dados do produto guardados com sucesso.");
                } else {
                    System.out.println("Não foi possível guardar os dados do produto.");
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    /**
     * Solicita os dados do produto ao usuário.
     * @return Novo produto criado.
     * @throws IllegalArgumentException Se os dados forem inválidos.
     */
    private static Produto introduzDados() {
        String nome = Utils.readLineFromConsole("Introduza o nome do produto: ");
        double preco = Utils.readDoubleFromConsole("Introduza o preço: ");
        return new Produto(nome, preco);
    }

    /**
     * Exibe os dados do produto.
     * @param produto Produto a ser exibido.
     */
    private void apresentaDados(Produto produto) {
        System.out.println("Produto: " + produto.toString());
    }
}