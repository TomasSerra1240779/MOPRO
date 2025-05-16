package org.example.model;

import org.example.utils.Data;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe para voluntários de vendas.
 */
public class VoluntarioVendas extends Voluntario implements Classificacao, Serializable {
    private ArrayList<Venda> vendas;

    /**
     * Construtor.
     */
    public VoluntarioVendas(String nome, String numeroAluno, String curso, String senha, String instituicao) {
        super(nome, numeroAluno, curso, senha, instituicao);
        this.vendas = new ArrayList<>();
    }

    /**
     * Adiciona uma venda.
     */
    public boolean adicionarVenda(Venda venda) {
        return vendas.add(venda);
    }

    /**
     * Classifica com base nas vendas.
     */
    @Override
    public String classificar(Data data) {
        double total = 0;
        for (Venda v : vendas) {
            if (v.getData().equals(data)) {
                total += v.getValorTotal();
            }
        }
        if (total > 1000) return "Ouro";
        if (total >= 500) return "Prata";
        return "Bronze";
    }
}