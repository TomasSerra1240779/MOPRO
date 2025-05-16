package org.example.model;

import org.example.utils.Data;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um voluntário responsável por vendas.
 */
public class VoluntarioVendas extends Voluntario implements Classificacao, Serializable {
    private List<Venda> vendasRealizadas;

    /**
     * Construtor da classe VoluntarioVendas.
     * @param nome Nome do voluntário.
     * @param numeroAluno Número de aluno.
     * @param curso Curso do voluntário.
     * @param senha Senha do voluntário.
     * @param instituicao Instituição do voluntário.
     * @throws IllegalArgumentException Se algum parâmetro for nulo.
     */
    public VoluntarioVendas(String nome, String numeroAluno, String curso, String senha, String instituicao) {
        super(nome, numeroAluno, curso, senha, instituicao);
        this.vendasRealizadas = new ArrayList<>();
    }

    /**
     * Registra uma venda realizada pelo voluntário.
     * @param venda Venda a registrar.
     * @return true se a venda foi registrada, false caso contrário.
     */
    public boolean registrarVenda(Venda venda) {
        if (venda == null) return false;
        return vendasRealizadas.add(venda);
    }

    /**
     * Classifica o voluntário com base nas vendas diárias.
     * @param data Data para a classificação.
     * @return Categoria (Ouro, Prata, Bronze).
     */
    @Override
    public String classificar(Data data) {
        double totalVendas = vendasRealizadas.stream()
                .filter(v -> v.getData().equals(data))
                .mapToDouble(Venda::getValorTotal)
                .sum();
        if (totalVendas > 1000) return "Ouro";
        else if (totalVendas >= 500) return "Prata";
        else return "Bronze";
    }

    /**
     * Obtém a lista de vendas realizadas.
     * @return Lista de vendas.
     */
    public List<Venda> getVendasRealizadas() { return vendasRealizadas; }
}