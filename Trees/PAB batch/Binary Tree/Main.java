import java.util.Stack;
public class Main{

    public static class BinaryTree{
        Node root;
        public class Node{
            int data;
            Node left;
            Node right;

            Node(int data){
                this.data = data;
            }
        }


        public class Pair{
            Node node;
            int state;

            Pair(Node node, int state){
                this.node = node;
                this.state = state;
            }
        }

        public Node construct(Integer[] arr){
            root = new Node(arr[0]);
            Stack<Pair> st = new Stack<>();

            st.push(new Pair(root,1));

            int idx = 1;
            while(st.size()>0){
                if(st.peek().state == 1){
                    // left child
                    Integer val = arr[idx++];
                    if(val == null){
                        st.peek().state++;
                    }
                    else{
                        Node temp = new Node(val);
                        st.peek().node.left = temp;
                        st.peek().state++;
                        st.push(new Pair(temp,1));
                    }

                }
                else if(st.peek().state == 2){
                    // right child
                    Integer val = arr[idx++];
                    if(val == null){
                        st.peek().state++;
                    }
                    else{
                        Node temp = new Node(val);
                        st.peek().node.right = temp;
                        st.peek().state++;
                        st.push(new Pair(temp,1));
                    }
                }
                else if(st.peek().state==3){
                    st.pop();
                }
            }

            return root;
        }

        public void display(Node node){
            if(node == null) return;
            String str = node.left == null ? ". " : node.left.data+"";
            str+= " <- "+ node.data +" -> "; 
            str+= node.right == null ? " ." : node.right.data+"";
            System.out.println(str);

            display(node.left);
            display(node.right);
        
        }

        public void preOrder(Node node){
            if(node == null){
                System.out.print("NULL"+" ");
                return;
            }
            
            System.out.print(node.data+" ");
            preOrder(node.left);
            preOrder(node.right);
        }

    }


    public static void main(String[] args) {
        BinaryTree tree1 = new BinaryTree();
        Integer[] input = {10,20,40,null,null,50,80,null,null,null,30,60,null,90,null,null,70,null,null};
        tree1.construct(input);
        
        // tree1.preOrder(tree1.root);
        // System.out.println();
        tree1.display(tree1.root);
    }
}