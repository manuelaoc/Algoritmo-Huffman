/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poli;

import java.util.ArrayList;

public class DescomprimirHuffman {
    
     public String descomprimir(String cadena){
        String[] lineasDiccionario = cadena.split("\n");
        ArrayList<NodoComprimido> nodosDiccionarios = listNodosDiccionarios(lineasDiccionario);
        return traducir(nodosDiccionarios, lineasDiccionario[3]);
    }
    
    public ArrayList<NodoComprimido> listNodosDiccionarios(String[] lineasDiccionario){
        String lineaCaracteres = lineasDiccionario[0];
        String lineaCodigos = lineasDiccionario[1];

        ArrayList<NodoComprimido> nodosDiccionarios = new ArrayList<>();

        String[] claves = lineaCaracteres.replaceAll(",,,", ",,").split(",");
        String[] valores = lineaCodigos.split(",");
        for (int i = 0; i < claves.length; i++) {
            String clave = claves[i];
            if (clave.equals("")) {
                clave = ",";
            }
            nodosDiccionarios.add(new NodoComprimido(clave,valores[i]));
        }
        return nodosDiccionarios;
    }
    
    public String traducir(ArrayList<NodoComprimido> diccionario, String mensaje){
        String mensajeTraducido="";
        String aux="";
        
        for(int i=0; i < mensaje.length(); i++){
            
            aux = aux + mensaje.charAt(i);
            
            for(NodoComprimido nodo: diccionario){
                if(aux.equals(nodo.getCodigo())){
                    mensajeTraducido = mensajeTraducido + nodo.getDato();
                    aux="";
                }
            }
        }
        return mensajeTraducido;
    }
    
}
