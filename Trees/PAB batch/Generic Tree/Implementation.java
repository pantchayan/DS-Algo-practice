import java.util.ArrayList;
import java.util.Stack;
public class Implementation {
    public static class GenericTree {
        Node root;
        public static class Node {
            int data;
            ArrayList<Node> children;

            Node() {
                children = new ArrayList<>();
            }

            Node(int data) {
                this();
                this.data = data;
            }
        }
        public Node construct(Integer[] input) {
            Node root = new Node(input[0]);

            Stack<Node> st = new Stack<>();
            st.push(root);
            for(int i=1;i<input.length;i++){
                Integer val = input[i];

                if(val==null){
                    st.pop();
                }
                else{
                    Node node = new Node(val);
                    st.peek().children.add(node);
                    st.push(node);
                }
            }
            return root;
        }

        public void display(Node node){
            System.out.print(node.data+" -> ");
            for(Node child:node.children){
                System.out.print(child.data+" ");
            }
            System.out.print(".\n");
            for(Node child:node.children){
                // System.out.print(child.data+" -> ");
                display(child);
            }
        }

        int ceil;
        int floor;
        public void ceilAndFloor(Node node, int val){

            if(node.data>val){
                if(node.data < ceil){
                    ceil = node.data;
                }
            }

            if(node.data<val){
                if(node.data > floor){
                    floor = node.data;
                }
            }

            for(Node child : node.children){
                ceilAndFloor(child, val);
            }
        }

        public void KthLargest(Node node, int k){
            int ans = Integer.MAX_VALUE;
            
            for(int i=0;i<k;i++){
                // ceil = Integer.MAX_VALUE;
                floor = Integer.MIN_VALUE;
            
                ceilAndFloor(node, ans);
                ans = floor;
            }
            System.out.println(k+"th Largest Element is : "+ans);
        }
    }

    public static void Set2(GenericTree tree1){
        tree1.ceil = Integer.MAX_VALUE;
        tree1.floor = Integer.MIN_VALUE;
        tree1.ceilAndFloor(tree1.root, 120);
        System.out.println("CEIL : "+tree1.ceil+"\nFLOOR : "+tree1.floor);


        tree1.KthLargest(tree1.root, 8);
    }

    public static void main(String[] args) {
        Integer input[] = { 10, 20, 50, null, 60, null, null, 30, 70, 90, null, 100, 110, null, 120, null, null, null,
                80, 130, null, 140, null, null, null, 40, 150, 160, null, null, null, null };

        GenericTree tree1 = new GenericTree();
        tree1.root = tree1.construct(input);

        tree1.display(tree1.root);

        Set2(tree1);
    }

}