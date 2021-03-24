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
    }

    public static void main(String[] args) {
        Integer input[] = { 10, 20, 50, null, 60, null, null, 30, 70, 90, null, 100, 110, null, 120, null, null, null,
                80, 130, null, 140, null, null, null, 40, 150, 160, null, null, null, null };

        GenericTree tree1 = new GenericTree();
        tree1.root = tree1.construct(input);

        tree1.display(tree1.root);
    }

}