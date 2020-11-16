import java.util.ArrayList;

public class BST{

    public static class Node{
        Node left;
        Node right;
        int data;

        Node(int data){
            this.data = data;
        }
    }

    static Node root;

    public static Node inOrderToBST(int[] arr, int high, int low){
        while(low<=high){
            int mid = (high+low)/2;
            Node node = new Node(arr[mid]);
            node.left = inOrderToBST(arr, mid-1,low);
            node.right = inOrderToBST(arr, high, mid+1);
            return node;
        }
        return null;
    }

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



    public static void constructBST(){

        // Convert using Binary Search algorithm .
        int[] arr = {10,20,30,40,50,60,70,80,90,100,110,120,130};
        int high = arr.length-1;
        int low = 0;
        root = inOrderToBST(arr, high, low);

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
        if (node.right == null)
            return node.data;
        return Maximum(node.right);
    }

    public static int Minimum(Node node) {
        if (node.left == null)
            return node.data;
        return Minimum(node.left);
    }

    public static boolean Find(Node node, int data) {
        if (node == null)
            return false;
        if (node.data == data)
            return true;

        boolean res = false;
        if(data > node.data){
            res = res||Find(node.right, data);
        }
        if(data < node.data){
            res = res ||  Find(node.left,data);
        }
        return res;
    }

    // LCA of a BST ====================================================================================== 

    public static int LCAofBST_rec(Node node, int a, int b){
        if(node==null) return -1;

        if(a<node.data && b<node.data){
            return LCAofBST_rec(node.left,a,b);
        }
        else if(node.data<a && node.data<b){
            return LCAofBST_rec(node.right,a,b);
        }
        else{
            return node.data;
        }
    }

    public static int LCAofBST_ite(Node node, int a, int b){
        Node curr = node;
        while(curr!=null){
            if(b<curr.data){
                curr = curr.left;
            }
            else if(a>curr.data){
                curr = curr.right;
            }
            else{
                return curr.data; //LCA point 
            }
        }
        return -1; // NO LCA
    }

    // All data in range (a,b) in ArrayList ========================================================================================

    public static void allDataInRange(Node node, int a, int b, ArrayList<Integer> ans){
        if(node==null) return;

        allDataInRange(node.left, a, b, ans);

        if(node.data>=a && node.data<=b){
            ans.add(node.data);
        }

        allDataInRange(node.right, a, b, ans);

    }


    // Leetcode 98 Validate BST =================================================




    public static void set1(){
        System.out.println(LCAofBST_rec(root,12,4));
        System.out.println(LCAofBST_ite(root,12,4));

        ArrayList<Integer> ans = new ArrayList<>();

        allDataInRange(root, 20, 60, ans);

        System.out.println(ans);
    }


    public static void basics(){
        System.out.println(Find(root,8));

        System.out.println(Maximum(root)+" "+Minimum(root));
    }

    public static void solve(){
        constructBST();
        // basics();
        set1();
    }


    public static void main(String args[]){
        solve();
    }
}