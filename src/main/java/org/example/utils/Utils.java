package org.example.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class Utils {
    static public String readLineFromConsole(String strPrompt) {
        try {
            System.out.println(strPrompt);
            InputStreamReader converter = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(converter);
            return in.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int readIntFromConsole(String strPrompt) {
        do {
            try {
                String strInt = readLineFromConsole(strPrompt);
                int iInt = Integer.parseInt(strInt);
                return iInt;
            } catch (NumberFormatException ex) {
                //Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    public static double readDoubleFromConsole(String strPrompt) {
        do {
            try {
                String strDouble = readLineFromConsole(strPrompt);
                double iDouble = Double.parseDouble(strDouble);
                return iDouble;
            } catch (NumberFormatException ex) {
                //Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    static public Data readDateFromConsole(String strPrompt) {
        do {
            try {
                String strData = readLineFromConsole(strPrompt);
                // dd-MM-yyyy
                String[] arr = strData.split("-");
                int dia = Integer.parseInt(arr[0]);
                int mes = Integer.parseInt(arr[1]);
                int ano = Integer.parseInt(arr[2]);
                return new Data(ano, mes, dia);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                System.out.println("--> " + ex.toString());
            }
        } while (true);
    }

    static public boolean confirma(String sMessage) {
        String strConfirma;
        do {
            strConfirma = Utils.readLineFromConsole(sMessage);
        } while (!strConfirma.equalsIgnoreCase("s") && !strConfirma.equalsIgnoreCase("n"));

        return strConfirma.equalsIgnoreCase("s");
    }

    static public void apresentaLista(List list, String sHeader) {
        System.out.println(sHeader);

        int index = 0;
        for (Object o : list) {
            index++;

            System.out.println(index + ". " + o.toString());
        }
        System.out.println("");
        System.out.println("0 - Cancelar");
    }

    static public Object selecionaObject(List list) {
        String opcao;
        int nOpcao;
        do {
            nOpcao = Utils.readIntFromConsole("Introduza opção: ");

        } while (nOpcao < 0 || nOpcao > list.size());

        if (nOpcao == 0) {
            return null;
        } else {
            return list.get(nOpcao - 1);
        }
    }

}
