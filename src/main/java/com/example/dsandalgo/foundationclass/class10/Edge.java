package com.example.dsandalgo.foundationclass.class10;

public class Edge {
    public Node from;
    public Node to;
    public int weight;

    public Edge(int weight,Node from ,Node to){
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

}
