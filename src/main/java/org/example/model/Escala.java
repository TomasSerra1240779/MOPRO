package org.example.model;

import org.example.utils.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa uma escala de voluntários em uma barraca.
 */
public class Escala implements Serializable {
    private Data data;
    private Barraca barraca;
    private List<Voluntario> voluntarios;

    /**
     * Construtor da classe Escala.
     * @param data Data da escala.
     * @param barraca Barraca associada.
     * @throws IllegalArgumentException Se data ou barraca forem nulos.
     */
    public Escala(Data data, Barraca barraca) {
        if (data == null || barraca == null) {
            throw new IllegalArgumentException("Data e barraca não podem ser nulos");
        }
        this.data = data;
        this.barraca = barraca;
        this.voluntarios = new ArrayList<>();
    }

    /**
     * Adiciona um voluntário à escala.
     * @param voluntario Voluntário a adicionar.
     * @return true se o voluntário foi adicionado, false caso contrário.
     * @throws IllegalArgumentException Se o voluntário já está escalado em outra barraca na mesma data.
     */
    public boolean adicionarVoluntario(Voluntario voluntario) {
        if (voluntario == null || !voluntario.getInstituicao().equals(barraca.getInstituicao())) {
            return false;
        }
        for (Barraca b : voluntario.getFederacao().getBarracas()) {
            for (Escala e : b.getEscalas()) {
                if (e.getData().equals(data) && e.getVoluntarios().contains(voluntario)) {
                    throw new IllegalArgumentException("Voluntário já escalado em outra barraca para " + data.toAnoMesDiaString());
                }
            }
        }
        return voluntarios.add(voluntario);
    }

    /**
     * Valida a escala (mínimo de 2 voluntários).
     * @return true se a escala é válida, false caso contrário.
     */
    public boolean validarEscala() {
        return voluntarios.size() >= 2;
    }

    /**
     * Obtém a data da escala.
     * @return Data da escala.
     */
    public Data getData() {
        return data; }

    /**
     * Obtém a lista de voluntários.
     * @return Lista de voluntários.
     */
    public List<Voluntario> getVoluntarios() {
        return voluntarios; }

    /**
     * Representação textual da escala.
     * @return String com detalhes da escala.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Escala em " + data.toAnoMesDiaString() + " - Barraca: " + barraca.getNome() + "\nVoluntários:\n");
        if (voluntarios.isEmpty()) {
            result.append(" (NENHUM)\n");
        } else {
            for (Voluntario v : voluntarios) {
                result.append("\t- ").append(v.getNome()).append(" (").append(v.getNumeroAluno()).append(")\n");
            }
        }
        return result.toString();
    }
}