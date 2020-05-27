/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poli;

public class NodoComprimir {
    
    private String dato;
    private int frecuencia;
    
    public NodoComprimir(String dato, int frecuencia) {
        this.dato = dato;
        this.frecuencia = frecuencia;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }
}
