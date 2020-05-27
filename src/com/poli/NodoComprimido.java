/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poli;

public class NodoComprimido {
    private String dato;
    private String codigo;
   
    public NodoComprimido(String dato, String codigo) {
        this.dato = dato;
        this.codigo = codigo;
    }
  
    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

}
