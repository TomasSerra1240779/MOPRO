package org.example.test;

import org.example.model.*;
import org.example.utils.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VoluntarioTest {
    private Voluntario voluntario;
    private Federacao federacao;
    private Barraca barraca;
    private Data data;

    @BeforeEach
    void setUp() {
        federacao = new Federacao("Teste FAP");
        voluntario = new VoluntarioVendas("João", "2023001", "Eng", "123", "FEUP");
        barraca = new Barraca("Barraca FEUP", "FEUP");
        data = new Data(2025, 5, 18);
        federacao.adicionarPessoa(voluntario);
        federacao.adicionarBarraca(barraca);
        voluntario.setFederacao(federacao);
    }

    @Test
    void testConstrutor() {
        assertDoesNotThrow(() -> new VoluntarioVendas("João", "2023001", "Eng", "123", "FEUP"),
                "Deveria criar voluntário com parâmetros válidos");

        assertThrows(IllegalArgumentException.class, () -> new VoluntarioVendas(null, "2023001", "Eng", "123", "FEUP"),
                "Deveria falhar com nome nulo");
        assertThrows(IllegalArgumentException.class, () -> new VoluntarioVendas("", "2023001", "Eng", "123", "FEUP"),
                "Deveria falhar com nome vazio");
        assertThrows(IllegalArgumentException.class, () -> new VoluntarioVendas("João", null, "Eng", "123", "FEUP"),
                "Deveria falhar com número de aluno nulo");
        assertThrows(IllegalArgumentException.class, () -> new VoluntarioVendas("João", "2023001", null, "123", "FEUP"),
                "Deveria falhar com curso nulo");
        assertThrows(IllegalArgumentException.class, () -> new VoluntarioVendas("João", "2023001", "Eng", null, "FEUP"),
                "Deveria falhar com senha nula");
        assertThrows(IllegalArgumentException.class, () -> new VoluntarioVendas("João", "2023001", "Eng", "123", null),
                "Deveria falhar com instituição nula");
        assertThrows(IllegalArgumentException.class, () -> new VoluntarioVendas("João", "2023001", "Eng", "123", ""),
                "Deveria falhar com instituição vazia");
    }

    @Test
    void setFederacao() {
        Federacao novaFederacao = new Federacao("Nova FAP");
        voluntario.setFederacao(novaFederacao);
        assertEquals(novaFederacao, voluntario.getFederacao(), "Deveria definir nova federação");

        voluntario.setFederacao(null);
        assertNull(voluntario.getFederacao(), "Deveria aceitar federação nula");
    }

    @Test
    void getInstituicao() {
        assertEquals("FEUP", voluntario.getInstituicao(), "Deveria retornar FEUP");
    }

    @Test
    void getFederacao() {
        assertEquals(federacao, voluntario.getFederacao(), "Deveria retornar a federação definida");

        voluntario.setFederacao(null);
        assertNull(voluntario.getFederacao(), "Deveria retornar null após definir nulo");
    }

    @Test
    void autenticar() {
        assertTrue(voluntario.autenticar("123"), "Deveria autenticar com senha correta");
        assertFalse(voluntario.autenticar("errada"), "Deveria falhar com senha incorreta");
    }

    @Test
    void getBarracaAtual() {
        // Configurar escala para hoje
        Escala escala = new Escala(data, barraca);
        escala.adicionarVoluntario(voluntario);
        barraca.getEscalas().add(escala);

        // Mock da data atual (assumindo que Data() retorna 2025-05-18)
        assertEquals(barraca, voluntario.getBarracaAtual(), "Deveria retornar a barraca escalada");

        // Sem escala
        barraca.getEscalas().clear();
        assertNull(voluntario.getBarracaAtual(), "Deveria retornar null sem escala");

        // Sem federação
        voluntario.setFederacao(null);
        assertNull(voluntario.getBarracaAtual(), "Deveria retornar null sem federação");
    }
}