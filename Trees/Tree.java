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




















    public static  void solve(){
        constructTree();
    }

    public static void main(String[] args){
        solve();
    }

}
