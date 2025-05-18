package org.example.test;

import org.example.model.*;
import org.example.utils.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EscalaTest {
    private Escala escala;
    private Federacao federacao;
    private Barraca barraca;
    private VoluntarioVendas voluntario1;
    private VoluntarioVendas voluntario2;
    private Data dataAtual;

    @BeforeEach
    void setUp() {
        federacao = new Federacao("Teste FAP");
        barraca = new Barraca("Barraca FEUP", "FEUP");
        dataAtual = new Data(2025, 5, 18);
        voluntario1 = new VoluntarioVendas("João", "2023001", "Eng", "123", "FEUP");
        voluntario2 = new VoluntarioVendas("Maria", "2023002", "Eng", "123", "FEUP");
        federacao.adicionarBarraca(barraca);
        federacao.adicionarPessoa(voluntario1);
        federacao.adicionarPessoa(voluntario2);
        escala = new Escala(dataAtual, barraca);
    }

    @Test
    void adicionarVoluntario() {
        assertTrue(escala.adicionarVoluntario(voluntario1), "Deveria adicionar voluntário");
        assertEquals(1, escala.getVoluntarios().size(), "Deveria ter 1 voluntário");
        assertEquals(voluntario1, escala.getVoluntarios().get(0), "Voluntário deveria ser João");

        // Testar voluntário nulo
        assertFalse(escala.adicionarVoluntario(null), "Não deveria adicionar voluntário nulo");

        // Testar instituição diferente
        VoluntarioVendas voluntarioErrado = new VoluntarioVendas("Ana", "2023003", "Eng", "123", "ISEP");
        federacao.adicionarPessoa(voluntarioErrado);
        assertFalse(escala.adicionarVoluntario(voluntarioErrado), "Não deveria adicionar voluntário de outra instituição");
    }

    @Test
    void adicionarVoluntario_JaEscalado() {
        barraca.getEscalas().add(escala); // Adiciona a escala à barraca
        escala.adicionarVoluntario(voluntario1);
        Barraca outraBarraca = new Barraca("Barraca FEUP 2", "FEUP"); // Mesma instituição
        federacao.adicionarBarraca(outraBarraca);
        Escala outraEscala = new Escala(dataAtual, outraBarraca);
        outraBarraca.getEscalas().add(outraEscala); // Adiciona a outra escala à outra barraca
        assertThrows(IllegalArgumentException.class, () -> outraEscala.adicionarVoluntario(voluntario1),
                "Deveria lançar exceção para voluntário já escalado");
    }

    @Test
    void validarEscala() {
        assertFalse(escala.validarEscala(), "Escala com 0 voluntários não deveria ser válida");
        escala.adicionarVoluntario(voluntario1);
        assertFalse(escala.validarEscala(), "Escala com 1 voluntário não deveria ser válida");
        escala.adicionarVoluntario(voluntario2);
        assertTrue(escala.validarEscala(), "Escala com 2 voluntários deveria ser válida");
    }

    @Test
    void getData() {
        assertEquals(dataAtual, escala.getData(), "Data deveria ser 2025-05-18");
    }

    @Test
    void getVoluntarios() {
        assertEquals(0, escala.getVoluntarios().size(), "Lista de voluntários deveria estar vazia");
        escala.adicionarVoluntario(voluntario1);
        assertEquals(1, escala.getVoluntarios().size(), "Deveria ter 1 voluntário");
        assertEquals(voluntario1, escala.getVoluntarios().get(0), "Voluntário deveria ser João");
    }

    @Test
    void testToString() {
        assertFalse(escala.toString().contains("Escala em 2025-05-18"), "Deveria conter a data");
        assertTrue(escala.toString().contains("Barraca: Barraca FEUP"), "Deveria conter o nome da barraca");
        assertTrue(escala.toString().contains("(NENHUM)"), "Deveria indicar nenhum voluntário");
        escala.adicionarVoluntario(voluntario1);
        assertTrue(escala.toString().contains("João (2023001)"), "Deveria conter o voluntário João");
    }
}