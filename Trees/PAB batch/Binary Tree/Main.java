import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class Main {

    public static class BinaryTree {
        Node root;

        public class Node {
            int data;
            Node left;
            Node right;

            Node(int data) {
                this.data = data;
            }
        }

        public class Pair {
            Node node;
            int state;

            Pair(Node node, int state) {
                this.node = node;
                this.state = state;
            }
        }

        public Node construct(Integer[] arr) {
            root = new Node(arr[0]);
            Stack<Pair> st = new Stack<>();

            st.push(new Pair(root, 1));

            int idx = 1;
            while (st.size() > 0) {
                if (st.peek().state == 1) {
                    // left child
                    Integer val = arr[idx++];
                    if (val == null) {
                        st.peek().state++;
                    } else {
                        Node temp = new Node(val);
                        st.peek().node.left = temp;
                        st.peek().state++;
                        st.push(new Pair(temp, 1));
                    }

                } else if (st.peek().state == 2) {
                    // right child
                    Integer val = arr[idx++];
                    if (val == null) {
                        st.peek().state++;
                    } else {
                        Node temp = new Node(val);
                        st.peek().node.right = temp;
                        st.peek().state++;
                        st.push(new Pair(temp, 1));
                    }
                } else if (st.peek().state == 3) {
                    st.pop();
                }
            }

            return root;
        }

        public void display(Node node) {
            if (node == null)
                return;
            String str = node.left == null ? ". " : node.left.data + "";
            str += " <- " + node.data + " -> ";
            str += node.right == null ? " ." : node.right.data + "";
            System.out.println(str);

            display(node.left);
            display(node.right);

        }

        public int size(Node node) {
            if (node == null)
                return 0;
            return size(node.left) + size(node.right) + 1;
        }

        public int sum(Node node) {
            if (node == null)
                return 0;
            return sum(node.left) + sum(node.right) + node.data;
        }

        public int height(Node node) {
            if (node == null)
                return -1;
            return Math.max(height(node.left), height(node.right)) + 1;
        }

        public int max(Node node) {
            if (node == null)
                return Integer.MIN_VALUE;
            return Math.max(Math.max(max(node.left), max(node.right)), node.data);
        }

        public void levelOrder01(Node node) {
            Queue<Node> mQue = new ArrayDeque<>();
            Queue<Node> hQue = new ArrayDeque<>();

            mQue.add(node);
            while (mQue.size() > 0) {

                Node curr = mQue.remove();
                System.out.print(curr.data + " ");
                if (curr.left != null)
                    hQue.add(curr.left);
                if (curr.right != null)
                    hQue.add(curr.right);

                if (mQue.size() == 0) {

                    Queue<Node> temp = mQue;
                    mQue = hQue;
                    hQue = temp;
                    System.out.println();
                }
            }
        }

        public void levelOrder02(Node node) {
            Queue<Node> mQue = new ArrayDeque<>();
            // Queue<Node> hQue = new ArrayDeque<>();

            mQue.add(node);
            while (mQue.size() > 0) {
                int size = mQue.size();
                while (size-- > 0) {
                    Node curr = mQue.remove();
                    System.out.print(curr.data + " ");
                    if (curr.left != null)
                        mQue.add(curr.left);
                    if (curr.right != null)
                        mQue.add(curr.right);

                }
                System.out.println();
            }
        }

        public boolean find(Node node, int data) {
            if(node == null) return false;
            if(node.data == data) return true;

            boolean flag = false;

            flag = flag || find(node.left, data) || find(node.right, data);
            return flag;
        }

        public ArrayList<Integer> nodeToRootPath(Node node, int data) {
            if(node == null) return new ArrayList<>();
            if(node.data == data){
                ArrayList<Integer> base = new ArrayList<>();
                base.add(node.data);
                return base;
            }

            ArrayList<Integer> leftAns = nodeToRootPath(node.left, data);
            if(leftAns.size()>0){
                leftAns.add(node.data);
                return leftAns;
            } 
            ArrayList<Integer> rightAns = nodeToRootPath(node.right, data);
            if(rightAns.size()>0){
                rightAns.add(node.data);
                return rightAns;
            } 
        
            return new ArrayList<>();

        }

        public void preOrder(Node node) {
            if (node == null) {
                System.out.print("NULL" + " ");
                return;
            }

            System.out.print(node.data + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
        
        int dia;
        public void diameter01(Node node){
            if(node == null) return;

            int lh = height(node.left);
            int rh = height(node.right);

            if(lh+rh+2>dia){
                dia = lh+rh+2;
            }

            diameter01(node.left);
            diameter01(node.right);
        } 

        public int diameter02(Node node){
            if(node==null) return -1;

            int lh = diameter02(node.left);
            int rh = diameter02(node.right);

            if(lh+rh+2>dia){
                dia = lh+rh+2;
            }

            return Math.max(lh,rh) +1;
        }

    }

    public static void set1(BinaryTree tree){
        tree.dia = Integer.MIN_VALUE;
        tree.diameter01(tree.root);
        System.out.println("Diameter is "+tree.dia);

        tree.dia = Integer.MIN_VALUE;
        int height = tree.diameter02(tree.root);
        System.out.println("Height of the tree :"+height);
        System.out.println("Diameter is "+tree.dia);
    }

    public static void basic(BinaryTree tree) {
        System.out.println("Max : " + tree.max(tree.root));
        System.out.println("Height : " + tree.height(tree.root));
        System.out.println("Sum : " + tree.sum(tree.root));
        System.out.println("Size : " + tree.size(tree.root));

        tree.levelOrder01(tree.root);

        tree.levelOrder02(tree.root);

        System.out.println(tree.nodeToRootPath(tree.root, 80));
    }

    public static void main(String[] args) {
        BinaryTree tree1 = new BinaryTree();
        Integer[] input = { 10, 20, 40, null, null, 50, 80, null, null, null, 30, 60, null, 90, null, null, 70, null,
                null };
        tree1.construct(input);

        // tree1.preOrder(tree1.root);
        // System.out.println();
        tree1.display(tree1.root);
        // basic(tree1);
        set1(tree1);
    }
}