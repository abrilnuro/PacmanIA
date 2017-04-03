package com.aimewexample.pacman.utils;

import com.aimewexample.pacman.models.Nodo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aimew on 21/03/2017.
 */

public class Aasterisco {
    private int[][] matriz = MatrizDeAdyacencia.matriz;;
    private List<Nodo> nodosList = new ArrayList<>();
    private List<Nodo> recorrido = new ArrayList<>();


    public void addAdyacentes() {
        matriz = MatrizDeAdyacencia.matriz;
        for (int i = 0; i < 24; i++) {
            Nodo nodo = new Nodo(i);
            List<Nodo> arrayAdyacentes = new ArrayList<>();
            for (int j = 0; j < 24; j++) {
                if (matriz[i][j] == 1) {
                    Nodo nodoAdyacente = new Nodo(j);
                    arrayAdyacentes.add(nodoAdyacente);
                }
            }
            nodo.setNodosAdyacentes(arrayAdyacentes);
            nodosList.add(nodo);
        }
    }


    public void busqueda(int nI, int nD) {
        int nodoFn = 0;
        Nodo nextNodo = new Nodo(0);
        List<Nodo> adyacentes = nodosList.get(nI).getNodosAdyacentes();

        for (int i = 0; i < adyacentes.size(); i++) {
            int hn = nD - adyacentes.get(i).getNumero();
            int gn = adyacentes.get(i).getNumero() - nI;
            int fn = hn + gn;

            if (fn > nodoFn) {
                nodoFn = fn;
                nextNodo = adyacentes.get(i);
            }
        }
        recorrido.add(nextNodo);
        if (nextNodo.getNumero() == nD) {
            return;
        }
        busqueda(nextNodo.getNumero(), nD);
    }

    public void busquedaAsterisco(int nI, int nD) {
        Nodo nextNodo = new Nodo();

        //obtener los nodos adyacentes del nodo n
        for (int j = 0; j < 24; j++) {
            int nodoFn = 0;
            if (matriz[nI][j] == 1) {
                //calcular el peso del nodo
                int hn = Math.abs(nD - j);
                int gn = Math.abs(j - nI);
                int fn = Math.abs(hn + gn);

                //agregar el peso del primer nodo adyacente
                if(j == 0){
                    nodoFn = fn;
                }else {
                    //si el peso del nodo adyacente actual es menor que el peso de nodo adyacente anterior.
                    if (fn < nodoFn) {
                        //almacenar el nuevo peso mas pequeño.
                        nodoFn = fn;
                        //asignar el nodo al que se va a hacer el siguiente salto.
                        nextNodo.setNumero(j);
                    }
                }//else
            }
        }//for
        recorrido.add(nextNodo);
        if (nextNodo.getNumero()  == nD) {
            return;
        }
        busquedaAsterisco(nextNodo.getNumero(), nD);
    }

    public List<Nodo> getRecorrido(int nI, int nD) {
        busquedaAsterisco(nI, nD);
        return recorrido;
    }

    public Aasterisco() {
    }
}


