package org.example.ui;

import org.example.model.Federacao;
import org.example.model.Produto;
import org.example.utils.Utils;

/**
 * Interface de usuário para registrar produtos na federação.
 */
public class RegistarProdutoUI {
    private Federacao federacao;

    /**
     * Construtor da classe RegistarProdutoUI.
     * @param federacao A federação do sistema.
     */
    public RegistarProdutoUI(Federacao federacao) {
        this.federacao = federacao;
    }

    /**
     * Executa a interface para registrar um produto.
     */
    public void run() {
        System.out.println("\n--- REGISTAR PRODUTO ---");
        String nome = Utils.readLineFromConsole("Nome do produto: ");
        double preco = Utils.readDoubleFromConsole("Preço do produto: ");

        try {
            Produto produto = new Produto(nome, preco);
            if (federacao.adicionarProduto(produto)) {
                System.out.println("Produto registrado com sucesso!");
            } else {
                System.out.println("Erro ao registrar produto. Verifique se o produto já existe.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}