package com.grupo.backend;

public class Funciones {
    public static boolean tieneSoloLetras (String texto) {
        return texto.matches("[a-zA-Z\\s']+");
    }

    public static boolean esNumero (String texto) {
        return texto.matches("[0-9][0-9][0-9][0-9]");
    }

    public static boolean esFecha(String texto) {
        return texto.matches("[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]");
    }

    public static boolean esEmail(String texto) {
        return texto.matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b");
    }
}
