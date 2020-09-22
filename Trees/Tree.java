import java.util.ArrayList;

public class Tree {

    // Structure ===========================

    public static class Node {
        int data = 0;
        Node left = null;
        Node right = null;

        Node(int data) {
            this.data = data;
        }
    }

    static Node root = null;

    // Creation and display =================

    public static void display(Node node) {
        if (node == null)
            return;

        String str = "";
        str += (node.left != null) ? node.left.data : ".";
        str += " <- " + node.data + " -> ";
        str += (node.right != null) ? node.right.data : ".";

        System.out.println(str);

        display(node.left);
        display(node.right);

    }

    // preOrder -- > Binary Tree
    static int idx = 0;

    public static Node preConstruct(int[] arr) {
        if (idx == arr.length || arr[idx] == -1) {
            idx++;
            return null;
        }
        Node node = new Node(arr[idx++]);
        node.left = preConstruct(arr);
        node.right = preConstruct(arr);
        return node;
    }

    public static void constructTree() {
        int[] arr = { 10, 20, 40, -1, -1, 50, 80, -1, -1, 90, -1, -1, 30, 60, 100, -1, -1, -1, 70, 110, -1, -1, 120, -1,
                -1 };
        root = preConstruct(arr);
        display(root);
    }

    // Basics
    // ==================================================================================================================

    public static int size(Node node) {
        if (node == null)
            return 0;
        return size(node.left) + size(node.right) + 1;
    }

    public static int height(Node node) {
        if (node == null)
            return -1;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    public static int Maximum(Node node) {
        if (node == null)
            return Integer.MIN_VALUE;
        if (node.left == null && node.right == null)
            return node.data;
        return Math.max(Math.max(Maximum(node.left), Maximum(node.right)), node.data);
    }

    public static int Minimum(Node node) {
        if (node == null)
            return Integer.MAX_VALUE;
        if (node.left == null && node.right == null)
            return node.data;
        return Math.min(Math.min(Minimum(node.left), Minimum(node.right)), node.data);
    }

    public static boolean Find(Node node, int data) {
        if (node == null)
            return false;
        if (node.data == data)
            return true;

        // boolean res = false;
        // res = res || Find(node.left,data) || Find(node.right,data);
        // return res;

        return Find(node.left, data) || Find(node.right, data);
    }

    // Traversals
    // ============================================================================================================

    public static void inorder(Node node) {
        if (node == null)
            return;

        inorder(node.left);
        System.out.print(node.data + ", ");
        inorder(node.right);
    }

    public static void preorder(Node node) {
        if (node == null)
            return;

        System.out.print(node.data + ", ");
        preorder(node.left);
        preorder(node.right);
    }

    public static void postorder(Node node) {
        if (node == null)
            return;

        postorder(node.left);
        postorder(node.right);
        System.out.print(node.data + ", ");
    }

    // Path in Arraylist
    // ==============================================================================================================

    public static boolean rootToNodePath01(Node node, ArrayList<Node> path, int data) {
        if (node == null)
            return false;
        if (node.data == data) {
            path.add(node);
            return true;
        }

        boolean res = false;

        res = res || rootToNodePath01(node.left, path, data) || rootToNodePath01(node.right, path, data);
        if (res)
            path.add(node);
        return res;
    }

    // Lowest Common Ancestor
    // ===========================================================================================================

    // 1st way is to find 2 different paths and find the LCA by linear search with 2
    // pointers from back O(n)+O(logn) time and space complexity.
    // 2nd way is to do in O(n)+O(1) time and space complexity.

    static Node LCANode = null;

    public static boolean LCA02(Node node, Node p, Node q) {
        boolean SelfDone = false;
        if (node == null)
            return false;
        if (node.data == p.data || node.data == q.data) {
            SelfDone = true;
        }
        boolean Left = LCA02(node.left, p, q);
        if (LCANode != null)
            return true;

        boolean Right = LCA02(node.right, p, q);
        if (LCANode != null)
            return true;

        if ((SelfDone && Left) || (SelfDone && Right) || (Right && Left)) {
            LCANode = node;
        }

        return SelfDone || Right || Left;
    }

    // Leet 863 - all Nodes K far away
    // =======================================================================================================
    // printing all nodes K away from the target ..
    //
    // Method 1:(simpler) Using (rootToNodePath and K-down) functions to solve.
    // Method 2: ??.

    public static void allNodesKaway01(Node root, int target, int K) {
        ArrayList<Node> path = new ArrayList<>();
        rootToNodePath01(root, path, target);

        for(int i=0;i<path.size();i++){
            if((K-i)<0) break;
            if(i==0)
                Kdown(path.get(i),K-i,null);
            else
                Kdown(path.get(i),K-i,path.get(i-1));
            
        }
    }

    public static void Kdown(Node node,int K,Node blocked){
        if(node==null || node==blocked) return;
        if(K<0) return;
        if(K==0) {
            System.out.println(node.data+" ");
            return;
        }

        Kdown(node.left, K-1, blocked);
        Kdown(node.right, K-1, blocked);
    }



    public static void set2() {
        // lets code for allNodesKaway
        System.out.println();
        allNodesKaway01(root, 50, 5);
    }

    public static void set1() {
        // int size = size(root);
        // int height = height(root);
        // System.out.println(size +" "+height);

        // System.out.println(Maximum(root));
        // System.out.println(Minimum(root));

        // System.out.println(Find(root,2001));

        // preorder(root);

        // ArrayList<Node> path = new ArrayList<>();
        // rootToNodePath01(root, path, 120);
        // // System.out.println(path);

        // for (Node node : path) {
        // System.out.print(node.data + " ");
        // }
        // System.out.println();
    }

    // https://www.pepcoding.com/most-important-interview-questions-list-for-product-based-companies

    public static void solve() {
        constructTree();
        set1();
        set2();
    }

    public static void main(String[] args) {
        solve();
    }

}
