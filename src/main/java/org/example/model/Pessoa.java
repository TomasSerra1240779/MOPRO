package org.example.model;

import java.io.Serializable;

/**
 * Classe abstrata que representa uma pessoa no sistema.
 */
public abstract class Pessoa implements Serializable {
    private String nome;
    private String numeroAluno;
    private String curso;
    private String password;


    public Pessoa(String nome, String numeroAluno, String curso, String password) {
        if (nome == null || numeroAluno == null || curso == null || password == null) {
            throw new IllegalArgumentException("Parâmetros não podem ser nulos");
        }
        this.nome = nome;
        this.numeroAluno = numeroAluno;
        this.curso = curso;
        this.password = password;
    }

    public String getNome() {
        return nome; }


    public String getNumeroAluno() {
        return numeroAluno; }



    public String getCurso() {
        return curso; }


    public String getPassword() {
        return password; }


    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {

        return nome + " (" + numeroAluno + ") - " + curso;
    }
}