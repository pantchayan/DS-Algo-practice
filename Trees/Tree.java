public class Tree {

    // Structure ===========================

    public static class Node{
        int data = 0;
        Node left = null;
        Node right = null;

        Node(int data){
            this.data = data;
        }
    }

    static Node root = null;

    // Creation and display =================

    public static void display(Node node){
        if(node==null) return;

        String str = "";
        str += (node.left!=null)?node.left.data:".";
        str += " <- "+node.data+" -> ";
        str += (node.right!=null)?node.right.data:".";

        System.out.println(str);

        display(node.left);
        display(node.right);

    } 
    
    // preOrder -- > Binary Tree 
    static int idx = 0;
    public static Node preConstruct(int[] arr){
        if(idx==arr.length || arr[idx] == -1){ 
            idx++;
            return null;
        }
        Node node = new Node(arr[idx++]);
        node.left = preConstruct(arr);
        node.right = preConstruct(arr);
        return node;
    } 
 
    public static void constructTree(){
        int[] arr = {13,3,31,1,-1,-1,2001,-1,-1,-1,2002,-1,-1};
        root = preConstruct(arr);
        display(root);
    }

    // Basics ==================================================================================================================

    public static int size(Node node){
        if(node==null) return 0;
        return size(node.left)+size(node.right)+1;
    }

    public static int height(Node node){
        if(node==null) return -1;
        return Math.max(height(node.left),height(node.right))+1;
    }

    public static int Maximum(Node node){
        if(node==null) return Integer.MIN_VALUE;
        if(node.left == null && node.right==null) return node.data;
        return Math.max(Math.max(Maximum(node.left),Maximum(node.right)),node.data);
    }
    
    public static int Minimum(Node node){
        if(node==null) return Integer.MAX_VALUE;
        if(node.left == null && node.right==null) return node.data;
        return Math.min(Math.min(Minimum(node.left),Minimum(node.right)),node.data);
    }


    public static boolean Find(Node node,int data){
        if(node==null) return false;
        if(node.data==data) return true;
        
        // boolean res = false;
        // res = res || Find(node.left,data) || Find(node.right,data);
        // return res;

        return Find(node.left,data) || Find(node.right,data);
    }








    public static void set1(){
        int size = size(root);
        int height = height(root);
        System.out.println(size +" "+height);

        System.out.println(Maximum(root));
        System.out.println(Minimum(root));

        System.out.println(Find(root,2001));
    }


    public static  void solve(){
        constructTree();
        set1();
    }

    public static void main(String[] args){
        solve();
    }

}
