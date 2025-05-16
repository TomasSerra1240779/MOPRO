package org.example.model;

import java.io.Serializable;

public abstract class Voluntario extends Pessoa implements Serializable {
    private String instituicao;


    public Voluntario(String nome, String numeroAluno, String curso, String password, String instituicao) {
        super(nome, numeroAluno, curso, password);
        this.instituicao = instituicao;
    }

    public String getInstituicao() {
        return instituicao; }


    public boolean autenticar(String password) {

        return getPassword().equals(password);
    }


    @Override
    public String toString() {
        return super.toString() + " - " + instituicao;
    }
}