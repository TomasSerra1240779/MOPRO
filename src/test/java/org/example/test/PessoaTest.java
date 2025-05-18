package org.example.test;

import org.example.model.Pessoa;
import org.example.model.VoluntarioVendas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PessoaTest {
    private Pessoa pessoa;

    @BeforeEach
    void setUp() {
        pessoa = new VoluntarioVendas("João", "2023001", "Eng", "123", "FEUP");
    }

    @Test
    void testConstrutor() {
        assertDoesNotThrow(() -> new VoluntarioVendas("João", "2023001", "Eng", "123", "FEUP"),
                "Deveria criar pessoa com parâmetros válidos");

        assertThrows(IllegalArgumentException.class, () -> new VoluntarioVendas(null, "2023001", "Eng", "123", "FEUP"),
                "Deveria lançar exceção para nome nulo");
        assertThrows(IllegalArgumentException.class, () -> new VoluntarioVendas("", "2023001", "Eng", "123", "FEUP"),
                "Deveria lançar exceção para nome vazio");
        assertThrows(IllegalArgumentException.class, () -> new VoluntarioVendas("João", null, "Eng", "123", "FEUP"),
                "Deveria lançar exceção para número de aluno nulo");
        assertThrows(IllegalArgumentException.class, () -> new VoluntarioVendas("João", "2023001", null, "123", "FEUP"),
                "Deveria lançar exceção para curso nulo");
        assertThrows(IllegalArgumentException.class, () -> new VoluntarioVendas("João", "2023001", "Eng", null, "FEUP"),
                "Deveria lançar exceção para password nula");
    }

    @Test
    void getNome() {
        assertEquals("João", pessoa.getNome(), "Nome deveria ser João");
    }

    @Test
    void getNumeroAluno() {
        assertEquals("2023001", pessoa.getNumeroAluno(), "Número de aluno deveria ser 2023001");
    }

    @Test
    void getCurso() {
        assertEquals("Eng", pessoa.getCurso(), "Curso deveria ser Eng");
    }

    @Test
    void getPassword() {
        assertEquals("123", pessoa.getPassword(), "Password deveria ser 123");
    }

    @Test
    void testToString() {
        assertEquals("João (2023001)", pessoa.toString(), "toString deveria ser João (2023001)");
    }
}