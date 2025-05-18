package org.example.model;

import org.example.utils.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um voluntário de vendas.
 */
public class VoluntarioVendas extends Voluntario implements Classificacao {
    private List<Venda> vendas;

    /**
     * Construtor da classe VoluntarioVendas.
     * @param nome Nome do voluntário.
     * @param numeroAluno Número de aluno.
     * @param curso Curso do voluntário.
     * @param password Senha do voluntário.
     * @param instituicao Instituição do voluntário.
     */
    public VoluntarioVendas(String nome, String numeroAluno, String curso, String password, String instituicao) {
        super(nome, numeroAluno, curso, password, instituicao);
        this.vendas = new ArrayList<>();
    }

    /**
     * Registra uma venda realizada pelo voluntário.
     * @param venda Venda a registrar.
     * @return true se a venda foi registrada, false caso contrário.
     */
    public boolean registrarVenda(Venda venda) {
        if (venda == null) return false;
        return vendas.add(venda);
    }

    /**
     * Calcula o total de vendas em uma data.
     * @param data Data das vendas.
     * @return Total das vendas.
     */
    public double calcularVendasDiarias(Data data) {
        double total = 0.0;
        for (Venda venda : vendas) {
            if (venda.getData().equals(data)) {
                total += venda.getValorTotal();
            }
        }
        return total;
    }

    /**
     * Classifica o voluntário com base nas vendas diárias.
     * @param data Data para a classificação.
     * @return "Ouro" (vendas >1000), "Prata" (vendas 500-1000), ou "Bronze" (vendas <500).
     */
    @Override
    public String classificar(Data data) {
        double vendasDiarias = calcularVendasDiarias(data);
        if (vendasDiarias > 1000) return "Ouro";
        if (vendasDiarias >= 500) return "Prata";
        return "Bronze";
    }
}