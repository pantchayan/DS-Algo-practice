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
        int[] arr = {1,2,3,4,6,7,8,9,10,11};
        int high = arr.length-1;
        int low = 0;
        root = inOrderToBST(arr, high, low);

        display(root);
    }


    public static void solve(){
        constructBST();
    }


    public static void main(String args[]){
        solve();
    }
}