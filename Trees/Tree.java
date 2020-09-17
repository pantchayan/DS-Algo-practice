public class Tree {
    public static class Node{
        int data = 0;
        Node left = null;
        Node right = null;

        Node(int data){
            this.data = data;
        }
    }


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

    static int idx = 0;

    
    // preOrder -- > Binary Tree
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
        Node root = preConstruct(arr);

        display(root);
    }


    public static void main(String[] args){
        constructTree();
    }

}
