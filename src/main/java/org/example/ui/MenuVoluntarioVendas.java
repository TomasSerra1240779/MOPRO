package org.example.ui;

import org.example.model.Federacao;
import org.example.model.VoluntarioVendas;
import org.example.utils.Utils;

import java.io.IOException;

/**
 * Interface de usuário para voluntários de vendas.
 */
public class MenuVoluntarioVendas {
    private Federacao federacao;
    private VoluntarioVendas voluntario;
    private String opcao;

    /**
     * Construtor da classe MenuVoluntarioVendas.
     * @param federacao Instância da federação.
     * @param voluntario Voluntário de vendas autenticado.
     */
    public MenuVoluntarioVendas(Federacao federacao, VoluntarioVendas voluntario) {
        this.federacao = federacao;
        this.voluntario = voluntario;
    }

    /**
     * Executa o menu do voluntário de vendas.
     * @throws IOException Se ocorrer um erro de entrada/saída.
     */
    public void run() throws IOException {
        do {
            System.out.println("###### MENU VOLUNTÁRIO VENDAS #####");
            System.out.println("1. Fazer Venda");
            System.out.println("0. Voltar");
            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            if (opcao.equals("1")) {
                new FazerVendaUI(federacao, voluntario).run();
            }
        } while (!opcao.equals("0"));
    }
}