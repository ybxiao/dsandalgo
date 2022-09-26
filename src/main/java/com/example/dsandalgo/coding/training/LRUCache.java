package com.example.dsandalgo.coding.training;

import java.util.HashMap;

/**
 * 实现一个LRU算法
 * 分析：首先是一个<key,value>的缓存，因此需要一个哈希表记录缓存
 * 需要保证最新访问到的数据最后淘汰，所以需要一个双向的链表来实时的更新数据的优先级
 * 一个缓存算法需要哪几个方法呢？
 * 1， get
 * 2，update
 * 3，delete（必须告诉我缓存的容量，我才能知道什么时候该淘汰里面的数据）
 *
 */
public class LRUCache {

    /**
     * 缓存的内部存储结构
     */
    private MyCache<Integer, Integer> cache;
    public  LRUCache(int capacity){
        cache = new MyCache<>(capacity);
    }

    public Integer get(int key){
        Integer integer = cache.get(key);
        return integer == null ? -1 : integer;
    }
    public void put(int key, int value){
        cache.set(key, value);
    }


    private static class NodeDoubleLinkedList<K, V>{
        public Node<K,V> head;
        public Node<K,V> tail;
        public NodeDoubleLinkedList(){
            head = null;
            tail = null;
        }

        /**
         * 新增一个节点
         * @param newNode
         * @return
         */
        public void addNode(Node newNode){
            if (newNode == null){
               return;
            }
            if (head == null){
                head = newNode;
                tail = newNode;
            }else{
                tail.next = newNode;
                newNode.last = tail;
                tail = newNode;
            }


        }

        /**
         * 一定要保证node在双向链表里面
         * 把node节点从链表中分离出来，左右重新连好，然后把node挂到链表的尾巴上去
         * 又分成两种情况，一种node恰好是头节点，一种node在中间节点的情况
         * @param node
         */
        public void moveNodeToTail(Node node){
            if (tail == node){
                return;
            }
            if (head == node){
                head = head.next;
                head.last = null;
            }else{
                node.last.next = node.next;
                node.next.last = node.last;
            }
            node.last =tail;
            node.next = null;
            tail.next = node;
            tail = node;

        }

        /**
         * 如果删除后链表不为空，则需要返回新的头部
         * @return
         */
        public Node<K,V> removeHead(){
            if (head == null){
                return null;
            }
            // 记录一个临时变量
            Node<K,V> res =  head;
            if (head == tail){
                head = null;
                tail = null;
            }else {
                head = res.next;
                res.next =null;
                head.last = null;

            }
            return res;
        }



    }

    public static class MyCache<K,V>{
        private HashMap<K , Node<K,V>> keyNodeMap;
        private NodeDoubleLinkedList<K,V> nodeList;
        private final int capacity;

        public MyCache(int capacity){
            keyNodeMap = new HashMap<>();
            nodeList =  new NodeDoubleLinkedList<>();
            this.capacity = capacity;
        }

        public V get(K key){
            if (keyNodeMap.containsKey(key)){
                Node<K, V> res = keyNodeMap.get(key);
                nodeList.moveNodeToTail(res);
                return res.value;
            }
            return null;
        }

        //更新或者删除
        public void set(K key, V value){
            if (keyNodeMap.containsKey(key)){
                Node<K, V> node =  keyNodeMap.get(key);
                node.value = value;
                nodeList.moveNodeToTail(node);
            }else{
                Node<K,V> newNode = new Node<K, V>(key, value);
                keyNodeMap.put(key, newNode);
                nodeList.addNode(newNode);
                if (keyNodeMap.size() > capacity){
                    reomoveMostUnUsedNode();
                }
            }
        }

        private void reomoveMostUnUsedNode(){
            Node<K,V> node = nodeList.removeHead();
            keyNodeMap.remove(node.key);
        }
    }


    /**
     * 双向链表的节点
     */
    public static class Node<K, V> {
        public K key;
        public V value;
        public Node<K, V> last;
        public Node<K, V> next;

        public Node(K key, V value){
            this.key = key ;
            this.value = value;
        }
    }


}
