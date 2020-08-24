package com.example.dsandalgo.foundationclass.class07;


/**
 * 二叉树的前继节点和后续节点
 *
 * 二叉树结构如下定义：
 * Class Node {
 * 	V value;
 * 	Node left;
 * 	Node right;
 * 	Node parent;
 * }
 * 给你二叉树中的某个节点，返回该节点的后继节点
 */
public class Code07_SuccessorNode {

    public static class Node{
        private int v;
        private Node left;
        private Node right;
        private Node parent;

        public Node(int val){
            this.v =  val;
        }
    }

    public static Node getSuccessorNode(Node node){
        if (node == null){
            return null;
        }
        if (node.right == null){
            Node parent = node.parent;
            while (parent !=null && parent.left != node){
                node = parent;
                parent = node.parent;
            }
            return node;
        }
        if (node.right != null){
            node = getMostLeftNode(node.right);

        }

        return node;
    }

    private static Node getMostLeftNode(Node node) {
        while (node.left!= null){
            node = node.left;
        }
        return node;
    }


}
