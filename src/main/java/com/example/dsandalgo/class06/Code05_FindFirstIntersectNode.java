package com.example.dsandalgo.class06;

public class Code05_FindFirstIntersectNode {

    public static class Node{
        private int value;
        private Node next;
        public Node(int value){
            this.value = value;
        }
    }


    /**
     *
     * 给你一个链表，判断该链表是否有环
     * 如果有返回入环节点，如果没有返回null
     */
    public static Node getLoopNode(Node head){
        if (head == null || head.next ==null || head.next.next ==null){
            return null;
        }

        Node slow = head.next;
        Node fast = head.next.next;
        while (slow != fast && fast.next !=null && fast.next.next !=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        if (slow == fast){
            fast = head;
            while (fast != slow){
                slow = slow.next;
                fast = fast.next;
            }
            return slow;
        }else{
            return null;
        }


    }

    public static Node getIntersectNode(Node head1,Node head2){
        if (head1 == null || head2 == null){
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);

        if (loop1 ==null && loop2 ==null){
            return noLoop(head1,head2);
        }
        if (loop1 != null && loop2 != null){
            return bothLoop(head1,loop1,head2,loop2);
        }
        return null;

    }

    /**
     * 擅用while
     * @param head1
     * @param head2
     * @return
     */
    public static Node noLoop(Node head1,Node head2){
        int n = 0;
        Node cur1 = head1;
        while (cur1 != null){
            cur1 = cur1.next;
            n++;
        }
        Node cur2 = head2;

        while (cur2 != null){
            cur2 = cur2.next;
            n--;

        }
        cur1 = n>0 ? head1:head2;
        cur2 = cur1==head1?head2:head1;
        n = Math.abs(n);
        while (n >0){
            cur1 =cur1.next;
        }
        //本质思考
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;







    }
    public static Node bothLoop(Node head1,Node loop1 ,Node head2,Node loop2){
        if (loop1 == loop2){
            int n  = 0;
            Node cur1 = head1;
            while (cur1 !=loop1){
                n++;
                cur1 = cur1.next;
            }
            Node cur2 = head2;
            while (cur2 != loop2){
                n--;
                cur2 = cur2.next;
            }
            cur1 = n>0?head1 : head2;
            cur2 = cur1 ==head1? head2:head1;

            n =  Math.abs(n);

            while (n>0){
                cur1 =  cur1.next;
                n--;
            }
            while (cur1 != cur2){
                cur1 = cur1.next;
                cur2 = cur2.next;
            }

            return cur1;

        }else {


            Node cur = loop1.next;
            while (cur != loop1){
                if (cur == loop2){

                  return loop2;
                }
                cur = cur.next;
            }
            return null;
        }

    }
}
