package com.aimewexample.pacman.models;

import java.util.List;

/**
 * Created by aimew on 16/03/2017.
 */

public class Nodo {

    private int numero;
    private Nodo Izquierda;
    private Nodo Derecha;
    private List<Nodo>nodosAdyacentes;

    public Nodo() {
    }

    public Nodo(int numero) {
        this.numero = numero;
    }

    public Nodo(int numero, Nodo izquierda, Nodo derecha) {
        super();
        this.numero = numero;
        Izquierda = izquierda;
        Derecha = derecha;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Nodo getIzquierda() {
        return Izquierda;
    }

    public void setIzquierda(Nodo izquierda) {
        Izquierda = izquierda;
    }

    public Nodo getDerecha() {
        return Derecha;
    }

    public void setDerecha(Nodo derecha) {
        Derecha = derecha;
    }

    public List<Nodo> getNodosAdyacentes() {
        return nodosAdyacentes;
    }

    public void setNodosAdyacentes(List<Nodo> nodosAdyacentes) {
        this.nodosAdyacentes = nodosAdyacentes;
    }
}
