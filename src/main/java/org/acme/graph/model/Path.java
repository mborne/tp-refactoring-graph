package org.acme.graph.model;

import java.util.ArrayList;
import java.util.List;

public class Path {

    /**
     * List d'edge
     */
    private ArrayList<Edge> edges;


    public Path(){
        this.edges = new ArrayList<Edge>();
    }


    /**
     * renvoie la longueur des edges
     * @return
     */
    public double getLenght(){

        double lenght = 0.0;
        for (Edge edge : edges) {

            lenght+=edge.getCost();
            
        }

        return lenght;
    }

    public ArrayList<Edge> getEdges(){
        return edges;
    }

    public Edge getEdgeById(int id){

        return edges.get(id);

    }

    
    

    
}
