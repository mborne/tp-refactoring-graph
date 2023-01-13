package org.acme.graph.model;

import java.util.ArrayList;
import java.util.List;

public class Path {

    private List<Edge> edges= new ArrayList<>();
    private double length;

    public Path(List<Edge> edges){
        this.edges = edges;

        this.length = 0;
        for (Edge edge : edges){
            this.length += edge.getCost();
        }
    }

    public double getLength(){
        return this.length;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
