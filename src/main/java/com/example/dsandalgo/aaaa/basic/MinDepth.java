package com.example.dsandalgo.aaaa.basic;

public class MinDepth {


    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }


  public static int minDepth(TreeNode head){
        if (head == null){
            return 0;
        }

        return process(head);


  }

    private static int process(TreeNode head) {
        if (head.right == null && head.left == null){
            return 1;
        }
        int leftMinDepth = Integer.MAX_VALUE;
        if (head.left !=null){
            leftMinDepth = Math.min(leftMinDepth, process(head.left));

        }

        int rightMinDepth = Integer.MAX_VALUE;
        if (head.right != null){
            rightMinDepth = Math.min(rightMinDepth, process(head.left));
        }



        return 1  + Math.min(leftMinDepth, rightMinDepth);
    }
}
