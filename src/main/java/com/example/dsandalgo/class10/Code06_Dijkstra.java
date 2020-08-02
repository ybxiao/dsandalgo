package com.example.dsandalgo.class10;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 迪杰特斯拉算法
 *
 *  限制条件：
 *      不能有权值为负数的边（其实是不能权值为负数的环）
 *
 * 给定一个源点，求源点到图中每个点的最短距离
 * 返回个一维数组
 *
 */
public class Code06_Dijkstra {

    public static Map<Node,Integer> dijkstra1(Node from){
        Map<Node,Integer>  distanceMap = new HashMap<>();
        distanceMap.put(from,0);
        Set<Node> selectNodes = new HashSet<>();
        Node  cur  = getMinandUnSelectedNode(distanceMap,selectNodes);

        while (cur != null){
            for (Edge e :
                    cur.edges) {
                Node fNode = e.from;
                Integer distance = distanceMap.get(fNode);
                Node to = e.to;
                if (!distanceMap.containsKey(to)){
                    distanceMap.put(to,e.weight + distance);
                }else{

                    distanceMap.put(to,Math.min(distanceMap.get(to), distance + e.weight ));
                }
                selectNodes.add(cur);
            }
            cur = getMinandUnSelectedNode(distanceMap,selectNodes);


        }



    return distanceMap;

    }

    private static Node getMinandUnSelectedNode(Map<Node, Integer> distanceMap, Set<Node> selectNodes) {
        Integer max = Integer.MAX_VALUE;
        Node minNode = null ;
        for (Map.Entry<Node,Integer> entry :
                distanceMap.entrySet()) {
            if (!selectNodes.contains(entry.getKey()) && distanceMap.get(entry.getKey()) <max ) {
                max = distanceMap.get(entry.getKey());
                minNode =  entry.getKey();

            }
        }
            return minNode;
    }


    public static class NodeRecord{
        private Node node;
        private int distance;
        public NodeRecord(Node node,int distance){
            this.node = node;
            this.distance = distance;
        }


    }
    public static class NodeHeap{
        public Node[] heap;
        //记录Node节点在堆中的索引位置，如果为-1，代表增加加入过，已弹出
        //Map中没有记录代表没有加入过
        public Map<Node,Integer> indexMap;
        //记录了源点到每个节点的距离
        public Map<Node,Integer> distanceMap;
        public int size;

        public NodeHeap(int heapSize){
            heap = new Node[heapSize];
            indexMap = new HashMap();
            distanceMap =  new HashMap();
            size = 0;
        }

        public boolean isEmpty(){
            return size ==0;
        }
        public boolean isEntered(Node node){
            return indexMap.containsKey(node);
        }
        public boolean inHeap(Node node){
            return isEntered(node) && (indexMap.get(node) != -1);
        }

        public void addOrUpdateOrIgnore(Node node,int distance){
            if (isEntered(node)){
                if (inHeap(node)){
                    Integer curDis = distanceMap.get(node);
                    distanceMap.put(node,Math.min(curDis,distance));
                    heapInsert(indexMap.get(node));
                }

            }else{
                heap[size] = node;
                indexMap.put(node,size);
                distanceMap.put(node,distance);
                size++;

            }

        }

        public NodeRecord pop(){
            NodeRecord nodeRecord = new NodeRecord(heap[0], distanceMap.get(heap[0]));
            indexMap.put(heap[0],-1);
            swap(0,--size);
            //这一步，为什么要移除呢
            distanceMap.remove(heap[size]);
            heap[size] = null;
            heapify(0,size);


            return nodeRecord;
        }

        private void heapify(int i, int size) {
            int left = i*2 +1;
            while (left < size){
                int smallest = left+1 < size && distanceMap.get(heap[left+1]) < distanceMap.get(heap[left])
                        ? left+1:left;
                smallest =  distanceMap.get(heap[i]) > distanceMap.get(heap[smallest]) ? smallest:i;
                if (smallest == i) {
                    return;
                }
                swap(i,smallest);
                i = smallest;
                left = i*2+1;

            }
        }

        private void heapInsert(Integer index) {
            while (distanceMap.get(heap[index]) > distanceMap.get(heap[index/2-1])){
                swap(index,index/2-1);
                index = index/2-1;
            }

        }

        private void swap(Integer index, int i) {
            indexMap.put(heap[index],i);
            indexMap.put(heap[i],index);
            Node temp =  heap[index];
            heap[i] =  heap[index];
            heap[index]  =temp;
        }


    }

    /**
     * 优化点：
     * 每次取最小距离元素时，使用小根堆。
     * 自己重写实现满足需求的小根堆。
     *
     * @param from
     * @return
     */
    public static Map<Node,Integer> dijkistra2(Node from,int size){

        Map<Node ,Integer> recordMap = new HashMap<>();
        NodeHeap nodeHeap  = new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(from,0);
        while (!nodeHeap.isEmpty()){
            NodeRecord nr  = nodeHeap.pop();
            int distance =  nr.distance;
            for (Edge edge:
            nr.node.edges ) {
                nodeHeap.addOrUpdateOrIgnore(edge.to,distance + edge.weight );
            }

            recordMap.put(nr.node,distance);
        }

        return recordMap;

    }

}
