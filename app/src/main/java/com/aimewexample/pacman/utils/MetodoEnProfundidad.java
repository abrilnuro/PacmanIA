package com.aimewexample.pacman.utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by aimew on 14/03/2017.
 */

public class MetodoEnProfundidad {

    static ArrayList nodes=new ArrayList();
    ArrayList<String> recorrido;

    Node node40 =new MetodoEnProfundidad.Node(40);
    Node node10 =new MetodoEnProfundidad.Node(10);
    Node node20 =new MetodoEnProfundidad.Node(20);
    Node node30 =new MetodoEnProfundidad.Node(30);
    Node node60 =new MetodoEnProfundidad.Node(60);
    Node node50 =new MetodoEnProfundidad.Node(50);
    Node node70 =new MetodoEnProfundidad.Node(70);


    int adjacency_matrix[][] = {
            {0,1,1,0,0,0,0},  // Node 1: 40
            {0,0,0,1,0,0,0},  // Node 2 :10
            {0,1,0,1,1,1,0},  // Node 3: 20
            {0,0,0,0,1,0,0},  // Node 4: 30
            {0,0,0,0,0,0,1},  // Node 5: 60
            {0,0,0,0,0,0,1},  // Node 6: 50
            {0,0,0,0,0,0,0},  // Node 7: 70
    };

    public MetodoEnProfundidad() {
        recorrido = new ArrayList<String>();
        dfs(adjacency_matrix, node40);
    }

    public void initArray(){
        nodes.add(node40);
        nodes.add(node10);
        nodes.add(node20);
        nodes.add(node30);
        nodes.add(node60);
        nodes.add(node50);
        nodes.add(node70);
    }

    public static class Node {
        int data;
        boolean visited;

        public Node(int data)
        {
            this.data=data;
        }
    }

    // find neighbors of node using adjacency matrix
    // if adjacency_matrix[i][j]==1, then nodes at index i and index j are connected
    public ArrayList findNeighbours(int adjacency_matrix[][],Node x) {
        int nodeIndex=-1;

        ArrayList neighbours=new ArrayList();
        for (int i = 0; i < nodes.size(); i++) {
            if(nodes.get(i).equals(x))
            {
                nodeIndex=i;
                break;
            }
        }

        if(nodeIndex!=-1)
        {
            for (int j = 0; j < adjacency_matrix[nodeIndex].length; j++) {
                if(adjacency_matrix[nodeIndex][j]==1)
                {
                    neighbours.add(nodes.get(j));
                }
            }
        }
        return neighbours;
    }

    // Recursive DFS
    public  void dfs(int adjacency_matrix[][], Node node) {

        Log.i("DATOS: ", String.valueOf(node.data));
        recorrido.add(String.valueOf(node.data));
        ArrayList neighbours=findNeighbours(adjacency_matrix,node);

        for (int i = 0; i < neighbours.size(); i++) {
            Node n= (Node) neighbours.get(i);
            if(n!=null && !n.visited)
            {
                Log.i("NODE: ", String.valueOf(n.data));
                dfs(adjacency_matrix,n);
                n.visited=true;
            }
        }//for
        Log.i("RECORRIDO: ", recorrido.toString());
    }

    // Iterative DFS using stack
    public  void dfsUsingStack(int adjacency_matrix[][], Node node) {
        Stack stack = new Stack();
        stack.add(node);
        node.visited = true;
        while (!stack.isEmpty()) {
            Node element = (Node) stack.pop();
            System.out.print(element.data + "t");

            ArrayList neighbours = findNeighbours(adjacency_matrix, element);
            for (int i = 0; i < neighbours.size(); i++) {
                Node n = (Node) neighbours.get(i);
                if (n != null && !n.visited) {
                    stack.add(n);
                    n.visited = true;

                }
            }
        }
    }
}


