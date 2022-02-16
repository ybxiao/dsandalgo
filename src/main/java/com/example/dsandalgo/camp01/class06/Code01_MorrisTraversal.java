package com.example.dsandalgo.camp01.class06;

/**
 * Morris遍历 : 对二叉树递归遍历的增强，时间复杂度保持O(N)的情况下
 * 空间复杂度优化到了O(1),通过利用原树中大量空闲指针的方式，达到节省空间的目的
 *
 * 遍历细节：
 * 假设来到当前节点cur，开始时cur来到头节点位置
 * 1）如果cur没有左孩子，cur向右移动(cur = cur.right)
 * 2）如果cur有左孩子，找到左子树上最右的节点mostRight：
 * 	a.如果mostRight的右指针指向空，让其指向cur，
 * 	然后cur向左移动(cur = cur.left)
 * 	b.如果mostRight的右指针指向cur，让其指向null，
 * 	然后cur向右移动(cur = cur.right)
 * 3）cur为空时遍历停止
 *
 * 遍历实质：
 * 建立一种机制：
 * 对于没有左子树的节点只到达一次，
 * 对于有左子树的节点会到达两次
 * morris遍历时间复杂度依然是O(N)
 *
 *
 *
 *
 */
public class Code01_MorrisTraversal {

    public static class Node{
        public int value;
        Node left;
        Node right;
        public Node(int val){
            this.value =  val;
        }
    }


    /**
     * 每一个有左孩子的节点都会被经过两次
     * 只经过一次的节点直接打印
     * 经过两次的节点在第二次打印就是中序遍历
     *
     *
     * @param head
     */
    public static void morrisIn(Node head){
        if (head == null){
            return;
        }
        Node cur  = head;
        //cur2 代表左树的最右节点
        Node cur2 = null;

        while (cur != null){
            cur2 = cur.left;
            //左孩子存在的情况下
            if (cur2 != null){
                //找左树的最右节点
                while (cur2.right != null && cur2.right != cur){
                    cur2 = cur2.right;
                }
                if (cur2.right == null){
                    cur2.right = cur;
                    cur = cur.left;
                    continue;
                }else{
                    cur2.right = null;
                }

            }
            System.out.println(cur.value);
            cur = cur.right;

        }


    }

    /**
     * Morris序与中序相对而言，对于经过两次的节点
     * 第一次的时候就打印就是先序
     *
     * @param head
     */
    public static void morrisPre(Node head){

        if (head == null){
            return;
        }
        Node cur =  head;
        Node cur1 = null;
        while (cur != null){
            cur1 = cur.left;
            if (cur1 != null){
                while (cur1.right != null  &&  cur1.right != cur){
                    cur1 = cur1.right;
                }
                if (cur1.right == null){
                    System.out.println(cur.value);
                    cur1.right = cur;
                    cur = cur.left;
                    continue;
                }else{
                    cur1.right = null ;
                }
            }else{
                System.out.println(cur.value);
            }
            cur = cur.right;
        }


    }

    public static void morrisPos(Node head){
        if (head == null){
            return;
        }
        Node cur =  head;
        Node cur1;
        while (cur != null){
            cur1 = cur.left;
            if (cur1 != null){
                while (cur1.right != null  &&  cur1.right != cur){
                    cur1 = cur1.right;
                }
                if (cur1.right == null){
                    System.out.println(cur.value);
                    cur1.right = cur;
                    cur = cur.left;
                    continue;
                }else{
                    printEdge(cur.left);
                    cur1.right = null ;
                }
            }else{
                System.out.println(cur.value);
            }
            cur = cur.right;
        }
        printEdge(head);
    }

    private static void printEdge(Node head) {
        Node tail =  reverse(head);
        Node cur = tail;
        while (cur != null){
            System.out.println(cur.value);
            cur = cur.right;
        }
        reverse(tail);

    }

    private static Node reverse(Node head) {
        Node pre = null;
        Node next;
        while (head != null){
            next = head.right;
            head.right = pre;
            pre = head;
            //next = pre;
            head = next;
        }

        return pre;
    }

    public static void printTree(Node head){
        System.out.println("print binary tree :");
        printInOrder(head,1,"H",17);
        System.out.println();


    }

    private static void printInOrder(Node head, int height, String to, int len) {
        if (head == null){
            return;
        }
        printInOrder(head.left,height+1,"v",len);
        String val = to+head.value+ to;
        int lenM = val.length();
        int lenL = (len - lenM)/2;
        int lenR= len -lenL - lenM;
        val =  getSpace(lenL)+ val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);

        printInOrder(head.right,height +1,"^",len);


    }

    private static String getSpace(int lenL) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < lenL; i++) {
            sb.append(" ");
        }
        return sb.toString();

    }

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);
        head.right.right = new Node(7);
        printTree(head);
        morrisIn(head);
        morrisPre(head);
        morrisPos(head);
        printTree(head);

    }


}
