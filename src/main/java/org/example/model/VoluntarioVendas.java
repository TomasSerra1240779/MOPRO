package org.example.model;

import org.example.utils.Data;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe que representa um voluntário responsável por vendas.
 */
public class VoluntarioVendas extends Voluntario implements Classificacao, Serializable {
    private ArrayList<Venda> vendas;

    /**
     * Construtor da classe VoluntarioVendas.
     * @param nome Nome do voluntário.
     * @param numeroAluno Número de aluno do voluntário.
     * @param curso Curso do voluntário.
     * @param senha Senha do voluntário.
     * @param instituicao Instituição do voluntário.
     */
    public VoluntarioVendas(String nome, String numeroAluno, String curso, String senha, String instituicao) {
        super(nome, numeroAluno, curso, senha, instituicao);
        this.vendas = new ArrayList<>();
    }

    /**
     * Adiciona uma venda à lista de vendas do voluntário.
     * @param venda Venda a ser adicionada (não pode ser nula).
     * @return true se a venda foi adicionada com sucesso.
     * @throws IllegalArgumentException Se a venda for nula.
     * @note Se o voluntário não realizar vendas, a lista permanecerá vazia, resultando em classificação "Bronze".
     */
    public boolean adicionarVenda(Venda venda) {
        if (venda == null) {
            throw new IllegalArgumentException("Venda não pode ser nula");
        }
        return vendas.add(venda);
    }

    /**
     * Registra uma venda, adicionando-a à lista do voluntário.
     * @param venda Venda a ser registrada.
     * @return true se a venda foi registrada, false caso contrário.
     */
    public boolean registrarVenda(Venda venda) {
        return adicionarVenda(venda);
    }


    /**
     * Obtém a barraca atual do voluntário com base na data atual e escalas.
     * @return A barraca onde o voluntário está escalado na data atual, ou null se não estiver escalado.
     */
    public Barraca getBarracaAtual() {
        Data hoje = new Data(); // Data atual
        for (Barraca barraca : getFederacao().getBarracas()) {
            for (Escala escala : barraca.getEscalas()) {
                if (escala.getData().equals(hoje) && escala.getVoluntarios().contains(this)) {
                    return barraca;
                }
            }
        }
        return null;
    }

    /**
     * Classifica o voluntário com base no total de vendas em uma data.
     * @param data Data para a classificação.
     * @return "Ouro" (>1000€), "Prata" (500-1000€), ou "Bronze" (<500€).
     * @note Se não houver vendas para a data, retorna "Bronze" (total = 0€).
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