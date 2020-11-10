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

    public static Node inOrderToBST(Node node, int[] arr, int high, int low){
        while(low<=high){
            int mid = (high+low)/2;
            node = new Node(arr[mid]);
            node.left = inOrderToBST(node.left, arr, mid-1,low);
            node.right = inOrderToBST(node.right, arr, high, mid+1);
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
        inOrderToBST(root , arr, high, low);

        display(root);
    }


    public static void solve(){
        constructBST();
    }


    public static void main(String args[]){
        solve();
    }
}