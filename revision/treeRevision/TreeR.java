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

    // BASIC FUNCTIONS ============================================================

    public static void inorder(Node node){
        if(node==null) return;

        inorder(node.left);
        System.out.print(node.data+" ");
        inorder(node.right);
    }

    public static void preorder(Node node){
        if(node==null) {
            System.out.print("-1 ");
            return;
        }
        
        System.out.print(node.data+" ");
        preorder(node.left);
        preorder(node.right);
    }

    public static void postorder(Node node){
        if(node==null) return;

        postorder(node.left);
        postorder(node.right);
        System.out.print(node.data+" ");
    }


    public static boolean find(Node node,int data){
        if(node == null) return false;
        if(data==node.data) return true;

        return find(node.left,data)||find(node.right,data);

    }

    public static int minimum(Node node){
        if(node==null) return 100000;
        if(node.left==null && node.right==null) return node.data;

        return Math.min(Math.min(minimum(node.left),minimum(node.right)),node.data);
    }


    public static void set1(){
        inorder(root);
        System.out.println();
        preorder(root);
        System.out.println();
        postorder(root);
        System.out.println();

        System.out.println(find(root,11));
        System.out.println(minimum(root));
    }

    public static void main(String args[]){
        constructTree();
        set1();
    }

}
