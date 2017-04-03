package com.aimewexample.pacman.utils;

import android.util.Log;

import com.aimewexample.pacman.models.Nodo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aimew on 16/03/2017.
 */

public class Profundidad {

    public static Nodo[] profundidad;
    public static int tamaño;
    public static int numeroNodos;
    public static Nodo[] NodoVisitados;
    public static int contadorVisitados=0;
    public static List<Nodo> recorridoFinal = new ArrayList<Nodo>();
    public static boolean bandFin = false;
    public static boolean encontroAestrella;
    public static List<Nodo> recorridoFinalAestrella = new ArrayList<Nodo>();

    public static void crearArbolProfundidad()
    {
        if(bandFin == false)
        {
            Log.i("PROFUNDIDAD:", "Creando Arbol");
            contadorVisitados=0;
            tamaño = 8; //TAMAÑO DEL ARBOL
            numeroNodos = tamaño*tamaño;
            profundidad = new Nodo[numeroNodos];
            NodoVisitados = new Nodo[numeroNodos+1];
            CrearNodos();
            for(int i=0;i<profundidad.length;i++)
            {
                int izq = 0,der = 0;
                if(!lateralesIzquierda(i+1))
                {
                    profundidad[i].setIzquierda(buscaNodo((i+1)+tamaño));
                    izq = (i+1)+tamaño;
                }
                if(!lateralesDerecho(i+1))
                {
                    profundidad[i].setDerecha(buscaNodo((i+1)+1));
                    der = (i+1)+1;
                }
            }
            busquedaProfundidad();
            busquedaAestrella();
        }
    }

    public static Nodo buscaNodo(int num)
    {
        for(int i=0;i<profundidad.length;i++)
        {
            if(profundidad[i].getNumero() == num)
            {
                return profundidad[i];
            }
        }
        return null;
    }

    public static void CrearNodos()
    {
        for(int i=0;i<profundidad.length; i++)
        {
            Nodo x = new Nodo(i+1,null,null);
            profundidad[i] = x;
        }
    }

    public static boolean lateralesDerecho(int i)
    {
        boolean band = false;
        int[] numeros = new int[tamaño];
        for(int j=0;j<tamaño;j++)
        {
            numeros[j] = tamaño*(j+1);
        }
        for(int j=0;j<numeros.length;j++)
        {
            if(numeros[j]==i)
            {
                band = true;
            }
        }
        return band;
    }

    public static boolean lateralesIzquierda(int i)
    {
        boolean band = false;
        int[] numeros = new int[tamaño];
        for(int j=0;j<tamaño;j++)
        {
            numeros[j] = numeroNodos-j;
        }
        for(int j=0;j<numeros.length;j++)
        {
            if(numeros[j]==i)
            {
                band = true;
            }
        }
        return band;
    }

    public static void busquedaProfundidad()
    {
         int meta = 40;
        Log.i("BUSCAR:", String.valueOf(meta));
        coreProfundiad(meta,profundidad[0]);
    }
    public static void busquedaAestrella()
    {
        int meta = 40;
        Log.i("BUSCAR:", String.valueOf(meta));
        metodoAestrella(meta,profundidad[0]);
    }

    public static void coreProfundiad(int meta,Nodo nodoActual)
    {
        boolean band = false;
        if(nodoActual == null)
        {
            //System.out.println("NULL");
            return;
        }
        if(bandFin==false)
        {
            Log.d("pacman",""+nodoActual.getNumero());
            recorridoFinal.add(nodoActual);
        }
        if(nodoActual.getIzquierda()!=null && nodoActual.getDerecha() == null){
            band= false;
        }
        if(nodoActual.getNumero() == meta)
        {
            System.out.println("ENCONTRO!!");
            bandFin = true;
            band = true;
            crearArbolProfundidad();
        }
        if(nodoActual.getIzquierda() != null && nodoActual.getDerecha()!= null)
        {
            if(!buscaVisitadocore(nodoActual.getIzquierda()))
            {
                coreProfundiad(meta,nodoActual.getIzquierda());
                if(bandFin==false)
                {
                    Log.d("pacman",""+nodoActual.getNumero());
                    recorridoFinal.add(nodoActual);
                }
            }
        }
        NodoVisitados[contadorVisitados] = nodoActual;
        contadorVisitados++;

        coreProfundiad(meta,nodoActual.getDerecha());
        if(bandFin==false)
        {
            Log.d("pacman",""+nodoActual.getNumero());
            recorridoFinal.add(nodoActual);
        }
    }

    public static boolean buscaVisitadocore(Nodo x)
    {
        boolean band = false;
        for(Nodo i : NodoVisitados)
        {
            if(i == null)
            {

            }
            else if(i.getNumero()==x.getNumero())
            {
                band = true;
            }
        }
        return band;
    }

    public static List<Nodo> mostrarRecorridoFinal()
    {
        List<Nodo> recorrido= new ArrayList<Nodo>();
        for(Nodo x : recorridoFinal)
        {
            //x.setNumero(x.getNumero()-1);
            //Log.d("pacman","Recorrido final: "+x.getNumero());
            Nodo nodo = new Nodo(x.getNumero(),x.getIzquierda(),x.getDerecha());
            nodo.setNumero(nodo.getNumero()-1);
            recorrido.add(nodo);

        }
        return recorrido;
    }

    //----------------------------------------------------------------------------
    public static void metodoAestrella(int meta,Nodo nodoActual)
    {
        if(encontroAestrella == false)
        {
            Log.d("pacman: ",""+nodoActual.getNumero());
            recorridoFinalAestrella.add(nodoActual);
            if(nodoActual.getNumero() == meta)
            {
                System.out.println("ENCONTRO!!");
                encontroAestrella = true;
                return;
            }
            else
            {
                boolean irPorIzquierda = false;
                if(nodoActual.getDerecha().getNumero() <= meta)
                {
                    if(nodoActual.getIzquierda() == null)
                    {

                    }
                    else
                    if(nodoActual.getIzquierda().getNumero() <= meta)
                    {
                        irPorIzquierda = true;
                    }
                }
                if(irPorIzquierda == true)
                {
                    metodoAestrella(meta,nodoActual.getIzquierda());
                }
                else
                {
                    metodoAestrella(meta,nodoActual.getDerecha());
                }
            }
        }
    }

    public static List<Nodo> mostrarRecorridoFinalAestrella()
    {
        List<Nodo> recorrido= new ArrayList<Nodo>();
        for(Nodo x : recorridoFinalAestrella)
        {
            Nodo nodo = new Nodo(x.getNumero(),x.getIzquierda(),x.getDerecha());
            nodo.setNumero(nodo.getNumero()-1);
            recorrido.add(nodo);

        }
        return recorrido;
    }
}
