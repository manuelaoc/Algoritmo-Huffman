package com.poli;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) throws IOException {
        File archivo = new File("D:\\Personal\\Universidad\\2020-1\\Arquitectura\\Proyecto Huffman\\src\\archivoComprimir.txt");
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);
        String cadena = br.readLine();

        // Comprimir
        CodificarHuffman codificarHuffman = new CodificarHuffman();
        ArrayList<NodoComprimir> nodosComprimir = codificarHuffman.calcularFrecuenciaCaracteres(cadena);
        Nodo nodoRaiz = codificarHuffman.comprimir(nodosComprimir);
        codificarHuffman.imprimirDiccionario(nodoRaiz, cadena);

        // Descomprimir
        archivo = new File("D:\\Personal\\Universidad\\2020-1\\Arquitectura\\Proyecto Huffman\\archivoDiccionario.txt");
        fr = new FileReader(archivo);
        br = new BufferedReader(fr);

        String cadenaADescomprimir = "";
        String linea = "";
        while ((linea=br.readLine()) != null)
            cadenaADescomprimir += linea + "\n";

        DescomprimirHuffman descomprimirHuffman = new DescomprimirHuffman();
        System.out.println(descomprimirHuffman.descomprimir(cadenaADescomprimir));
    }
}
