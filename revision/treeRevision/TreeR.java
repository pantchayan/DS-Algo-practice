
public class TreeR {
    
    public static class Node{
        int data = 0;
        Node left = null;
        Node right = null;
        Node(int data){
            this.data = data; 
        }
    }

    static Node root;

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

    static int idx =0;
    public static Node preOrderToTree(int[] arr){
        if(arr[idx]==-1 || idx>=arr.length){
            idx++;
            return null;
        }
        Node node = new Node(arr[idx++]);
        node.left = preOrderToTree(arr);
        node.right = preOrderToTree(arr);

        return node;
    }

    public static void constructTree(){
        int[] preOrdArr = {1,2,4,-1,-1,5,8,-1,-1,9,-1,-1,3,6,10,-1,-1,-1,7,11,-1,-1,-1};
        root = preOrderToTree(preOrdArr);

        display(root);
    }

    public static void main(String args[]){
        constructTree();
    }

}
