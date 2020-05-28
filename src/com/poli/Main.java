package com.poli;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion;
        while(!salir){
            System.out.println("1. Comprimir");
            System.out.println("2. Descomprimir");
            System.out.println("3. Salir");
            try {
                System.out.println("Escribe una de las opciones \n");
                opcion = sn.nextInt();
                switch(opcion){
                    case 1:
                        comprimir();
                        break;
                    case 2:
                        descomprimir();
                        break;
                    case 3:
                        salir=true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 3");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }
    }

    private static void comprimir() throws IOException{
        File archivo = new File("src/archivoComprimir.txt");
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);
        String cadena = br.readLine();

        // Comprimir
        CodificarHuffman codificarHuffman = new CodificarHuffman();
        ArrayList<NodoComprimir> nodosComprimir = codificarHuffman.calcularFrecuenciaCaracteres(cadena);
        Nodo nodoRaiz = codificarHuffman.comprimir(nodosComprimir);
        codificarHuffman.imprimirDiccionario(nodoRaiz, cadena);
    }

    private static void descomprimir() throws IOException{
        // Descomprimir
        File archivo = new File("./archivoDiccionario.txt");
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);

        String cadenaADescomprimir = "";
        String linea = "";
        while ((linea=br.readLine()) != null)
            cadenaADescomprimir += linea + "\n";

        DescomprimirHuffman descomprimirHuffman = new DescomprimirHuffman();
        System.out.println("Mensaje desencriptado: " + descomprimirHuffman.descomprimir(cadenaADescomprimir) + "\n");
    }
}
