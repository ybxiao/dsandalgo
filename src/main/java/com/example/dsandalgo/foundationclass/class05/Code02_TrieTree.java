package com.example.dsandalgo.foundationclass.class05;

import java.util.HashMap;

public class Code02_TrieTree {

    public static class Node1 {
        private int pass;
        private int end;
        private Node1[] nexts;

        public Node1(){
            this.pass = 0;
            this.end = 0;
            this.nexts = new Node1[26];

        }
    }

    public static class TrieTree1 {
        private Node1 root;

        public TrieTree1(){
            this.root = new Node1();
        }

        public void insert(String string){
            if (string == null){
                return;
            }
            char[] chars = string.toCharArray();
            root.pass++;
            Node1 path = root;

            for (int i = 0; i < chars.length; i++) {
                int i1 = chars[i] - 'a';
                if (path.nexts[i1]==null){
                    path.nexts[i1] = new Node1();
                }
                path.nexts[i1].pass++;
                path = path.nexts[i1];

            }
            path.end++;


        }
        public int search(String s){

            if (s == null){
                return 0;
            }
            char[] chars = s.toCharArray();
            Node1 path = root;
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (path.nexts[index] == null){
                    return 0;
                }
                path =path.nexts[index];

            }
            return path.end;


        }


        public void delete(String s){
            if (s == null){return;}


            if (search(s) != 0){
                char[] chars = s.toCharArray();
                Node1 path = root;
                path.pass --;
                for (int i = 0; i < chars.length; i++) {
                    path.nexts[chars[i]-'a'].pass--;
                    if (path.nexts[chars[i]-'a'].pass == 0){
                        path.nexts[chars[i]-'a'] =null;
                        return;
                    }

                    path = path.nexts[chars[i]-'a'];

                }
                path.end --;



            }






        }

        public int prefixNumber(String string){
            if (string == null){
                return 0;
            }
            char[] chars = string.toCharArray();
            Node1 path = root;
            for (int i = 0; i < chars.length; i++) {
                if (path.nexts[chars[i]-'a'] == null){
                    return 0;
                }
                path = path.nexts[chars[i]-'a'];


            }
            return path.pass;


        }




    }


    public static class Node2{
        private int pass;
        private int end;
        private HashMap<Integer,Node2> nexts;

        public Node2(){
            this.pass = 0;
            this.end = 0;
            this.nexts = new HashMap<>();
        }

    }

    public static class TrieTree2{

        private Node2 root;


        public TrieTree2(){
            this.root = new Node2();
        }

        public void insert(String string){
            if (string == null){
                return;
            }
            char[] chars = string.toCharArray();
            root.pass++;
            Node2 path = root;

            for (int i = 0; i < chars.length; i++) {
                int i1 = (int)chars[i];
                if (!path.nexts.containsKey(i1)){
                   path.nexts.put(i1,new Node2());
                }
                path.nexts.get(i1).pass++;
                path = path.nexts.get(i1);

            }
            path.end++;


        }
        public int search(String s){

            if (s == null){
                return 0;
            }
            char[] chars = s.toCharArray();
            Node2 path = root;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i];
                if (!path.nexts.containsKey(index)){
                    return 0;
                }
                path =path.nexts.get(index);

            }
            return path.end;


        }


        public void delete(String s){
            if (s == null){return;}


            if (search(s) != 0){
                char[] chars = s.toCharArray();
                Node2 path = root;
                path.pass --;
                int index = 0;
                for (int i = 0; i < chars.length; i++) {
                    index = (int)chars[i];
                    path.nexts.get(index).pass--;
                    if (path.nexts.get(index).pass == 0){
                        path.nexts.remove(index);
                        return;
                    }

                    path = path.nexts.get(index);

                }
                path.end --;



            }


        }

        public int prefixNumber(String string){
            if (string == null){
                return 0;
            }
            char[] chars = string.toCharArray();
            Node2 path = root;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i];
                if (path.nexts.get(index) == null){
                    return 0;
                }
                path = path.nexts.get(index);


            }
            return path.pass;


        }


    }

    public static class Right {

        private HashMap<String, Integer> box;

        public Right() {
            box = new HashMap<>();
        }

        public void insert(String word) {
            if (!box.containsKey(word)) {
                box.put(word, 1);
            } else {
                box.put(word, box.get(word) + 1);
            }
        }

        public void delete(String word) {
            if (box.containsKey(word)) {
                if (box.get(word) == 1) {
                    box.remove(word);
                } else {
                    box.put(word, box.get(word) - 1);
                }
            }
        }

        public int search(String word) {
            if (!box.containsKey(word)) {
                return 0;
            } else {
                return box.get(word);
            }
        }

        public int prefixNumber(String pre) {
            int count = 0;
            for (String cur : box.keySet()) {
                if (cur.startsWith(pre)) {
                    count += box.get(cur);
                }
            }
            return count;
        }
    }

    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 6);
            ans[i] = (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 100;
        int strLen = 20;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            String[] arr = generateRandomStringArray(arrLen, strLen);
            TrieTree1 trie1 = new TrieTree1();
            TrieTree2 trie2 = new TrieTree2();
            Right right = new Right();
            for (int j = 0; j < arr.length; j++) {
                double decide = Math.random();
                if (decide < 0.25) {
                    trie1.insert(arr[j]);
                    trie2.insert(arr[j]);
                    right.insert(arr[j]);
                } else if (decide < 0.5) {
                    trie1.delete(arr[j]);
                    trie2.delete(arr[j]);
                    right.delete(arr[j]);
                } else if (decide < 0.75) {
                    int ans1 = trie1.search(arr[j]);
                    int ans2 = trie2.search(arr[j]);
                    int ans3 = right.search(arr[j]);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                    }
                } else {
                    int ans1 = trie1.prefixNumber(arr[j]);
                    int ans2 = trie2.prefixNumber(arr[j]);
                    int ans3 = right.prefixNumber(arr[j]);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        System.out.println("finish!");

    }


}
