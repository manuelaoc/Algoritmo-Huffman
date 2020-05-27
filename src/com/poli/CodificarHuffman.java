package com.poli;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.PriorityQueue;

public class CodificarHuffman {

    public ArrayList<NodoComprimir> calcularFrecuenciaCaracteres(String cadena) {

        boolean bandera;
        ArrayList<NodoComprimir> datos = new ArrayList<>();
        int frecuencia;

        for (int i = 0; i < cadena.length(); i++) {

            bandera = true;
            String dato = Character.toString(cadena.charAt(i));
            frecuencia = 0;

            for (NodoComprimir n : datos) {
                if (dato.equals("\r") || dato.equals("\n")) {
                    if (n.getDato().equals("\n"))
                        bandera = false;
                } else {
                    if (n.getDato().equals(dato)) {
                        bandera = false;
                    }
                }
            }

            if (bandera && !dato.equals("\r") && !dato.equals("\n")) {
                for (int j = 0; j < cadena.length(); j++) {
                    if (dato.equals(Character.toString(cadena.charAt(j)))) {
                        frecuencia++;
                    }
                }
                datos.add(new NodoComprimir(dato, frecuencia));

            } else if (bandera && dato.equals("\r")) {

                for (int j = 0; j < cadena.length(); j++) {
                    if (dato.equals(Character.toString(cadena.charAt(j)))) {
                        frecuencia++;
                    }
                }

                datos.add(new NodoComprimir("\n", frecuencia));
            }
        }

        return datos;
    }

    public Nodo comprimir(ArrayList<NodoComprimir> caracteres) {

        PriorityQueue<Nodo> cola = new PriorityQueue<Nodo>(caracteres.size(), new ComparatorHuffman());

        caracteres.stream().map((nodoComrprimir) -> {
            Nodo nodo = new Nodo();
            nodo.setCaracter(nodoComrprimir.getDato());
            nodo.setFrecuencia(nodoComrprimir.getFrecuencia());
            return nodo;
        }).map((nodo) -> {
            nodo.setIzq(null);
            return nodo;
        }).map((nodo) -> {
            nodo.setDer(null);
            return nodo;
        }).forEachOrdered((nodo) -> {
            cola.add(nodo);
        });

        Nodo raiz = null;

        while (cola.size() > 1) {
            Nodo x = cola.peek();
            cola.poll();

            Nodo y = cola.peek();
            cola.poll();

            // Crea el nodo union con la suma de la frecuencias.
            Nodo f = new Nodo();
            f.setFrecuencia(x.getFrecuencia() + y.getFrecuencia());
            f.setCaracter("union"); //Se coloca union para identificar que es un nodo de union
            f.setIzq(x);
            f.setDer(y);

            raiz = f;

            cola.add(f);
        }

        return raiz;
    }

    public String getCaracteresCadenaPorComa(Nodo raiz, String s, String print) {

        // Verifica si tiene hijos y si posee como caracter el union.
        if (Objects.isNull(raiz.getIzq()) && Objects.isNull(raiz.getDer())
                && !"union".equals(raiz.getCaracter())) {

            print += raiz.getCaracter() + ",";
            return print;
        }

        //Se imprime recursivamente
        print = getCaracteresCadenaPorComa(raiz.getIzq(), s + "0", print);
        print = getCaracteresCadenaPorComa(raiz.getDer(), s + "1", print);
        return print;
    }

    public String getCodigosPorComa(Nodo raiz, String s, String print) {

        // Verifica si tiene hijos y si posee como caracter el union.
        if (Objects.isNull(raiz.getIzq()) && Objects.isNull(raiz.getDer())
                && !"union".equals(raiz.getCaracter())) {

            print += s + ",";
            return print;
        }

        //Se imprime recursivamente
        print = getCodigosPorComa(raiz.getIzq(), s + "0", print);
        print = getCodigosPorComa(raiz.getDer(), s + "1", print);
        return print;
    }

    public void imprimirDiccionario(Nodo nodoRaiz, String cadena) {

        String caracteresCadenaPorComa = getCaracteresCadenaPorComa(nodoRaiz, "", "");
        String codigosPorComa = getCodigosPorComa(nodoRaiz, "", "");
        String codigos = getCodigos(nodoRaiz, "", "");

        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("./archivoDiccionario.txt");
            pw = new PrintWriter(fichero);
            pw.println(caracteresCadenaPorComa.substring(0, caracteresCadenaPorComa.length()-1));
            pw.println(codigosPorComa.substring(0, codigosPorComa.length()-1));
            pw.println("Ұ");
            pw.println(codificarTexto(codigos, cadena));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero)
                    fichero.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public String getCodigos(Nodo raiz, String s, String print) {

        // Verifica si tiene hijos y si posee como caracter el union.
        if (Objects.isNull(raiz.getIzq()) && Objects.isNull(raiz.getDer())
                && !"union".equals(raiz.getCaracter())) {

            print += raiz.getCaracter() + ":" + s + "\n";
            return print;
        }

        //Se imprime recursivamente
        print = getCodigos(raiz.getIzq(), s + "0", print);
        print = getCodigos(raiz.getDer(), s + "1", print);
        return print;
    }

    public String codificarTexto(String codigo, String palabras) {

        // Se colocan todas las letras en mayuscula 
        char[] charArrayP = palabras.toLowerCase().toCharArray();
        String palabra = "";
        String[] lineas = codigo.split("\n");

        for (char caracter : charArrayP) {
            palabra += getCodigoByCaracter(lineas, caracter);
        }

        return palabra;
    }

    public String getCodigoByCaracter(String[] lineas, char caracter) {

        String[] dic;

        for (String b : lineas) {
            dic = b.split(":");
            if (caracter == dic[0].charAt(0)) {
                // retorna el valor del código
                return String.valueOf(dic[1]);
            }
        }
        return "";
    }
}
