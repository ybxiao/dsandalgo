package com.example.dsandalgo.class10;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public int v;
    public int in;
    public int out;
    public List<Node> nexts;
    public List<Edge> edges;

    public Node(int v){
        this.v = v;
        this.in = 0;
        this.out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }


}
