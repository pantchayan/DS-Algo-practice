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
            for (int i = 1; i < input.length; i++) {
                Integer val = input[i];

                if (val == null) {
                    st.pop();
                } else {
                    Node node = new Node(val);
                    st.peek().children.add(node);
                    st.push(node);
                }
            }
            return root;
        }

        public void display(Node node) {
            System.out.print(node.data + " -> ");
            for (Node child : node.children) {
                System.out.print(child.data + " ");
            }
            System.out.print(".\n");
            for (Node child : node.children) {
                // System.out.print(child.data+" -> ");
                display(child);
            }
        }

        public int size(Node node) {
            int s = 1;
            for (Node child : node.children) {
                s += size(child);
            }
            return s;
        }

        public int max(Node node) {
            int m = node.data;
            for (Node child : node.children) {
                m = Math.max(m, max(child));
            }
            return m;
        }

        public void traversals(Node node) {
            System.out.println("Node Pre " + node.data);
            for (Node child : node.children) {
                System.out.println("Edge Pre " + node.data + "--" + child.data);
                traversals(child);
                System.out.println("Edge Post " + node.data + "--" + child.data);
            }
            System.out.println("Node Post " + node.data);
        }

        public boolean find(Node node, int data) {
            boolean flag = false;

            if (node.data == data)
                return true;
            for (Node child : node.children) {
                flag = flag || find(child, data);
            }

            return flag;
        }

        public ArrayList<Integer> nodeToRootPath(Node node, int data){
            if(node.data==data){
                ArrayList<Integer> base = new ArrayList<>();
                base.add(node.data);
                return base;
            }
            ArrayList<Integer> ans = new ArrayList<>();
            for(Node child:node.children){
                ArrayList<Integer> smallAns = nodeToRootPath(child, data);
                if(smallAns.size()>0){
                    smallAns.add(node.data);
                }
                ans.addAll(smallAns);
            }
            return ans;
        }

        public ArrayList<Integer> nodeToRootPath2(Node node, int data){
            if(node.data==data){
                ArrayList<Integer> base = new ArrayList<>();
                base.add(node.data);
                return base;
            }
            for(Node child:node.children){
                ArrayList<Integer> ans = nodeToRootPath2(child, data);
                if(ans.size()>0){
                    ans.add(node.data);
                    return ans;
                }
            }
            return new ArrayList<>();
        }

        public int height(Node node) {
            // h -> node's height
            // ch -> child's height
            int h = -1;

            for (Node child : node.children) {
                int ch = height(child);
                if (ch > h) {
                    h = ch;
                }
            }

            return h + 1;
        }

        int dia;

        public int diameter(Node node) {
            int lh = -1, slh = -1;

            for (Node child : node.children) {
                int ch = diameter(child);

                if (ch > lh) {
                    slh = lh;
                    lh = ch;
                } else if (ch > slh) {
                    slh = ch;
                }
            }

            if (lh + slh + 2 > dia)
                dia = lh + slh + 2;

            return lh + 1;
        }

        int ceil;
        int floor;

        public void ceilAndFloor(Node node, int val) {

            if (node.data > val) {
                if (node.data < ceil) {
                    ceil = node.data;
                }
            }

            if (node.data < val) {
                if (node.data > floor) {
                    floor = node.data;
                }
            }

            for (Node child : node.children) {
                ceilAndFloor(child, val);
            }
        }

        public void KthLargest(Node node, int k) {
            int ans = Integer.MAX_VALUE;

            for (int i = 0; i < k; i++) {
                // ceil = Integer.MAX_VALUE;
                floor = Integer.MIN_VALUE;

                ceilAndFloor(node, ans);
                ans = floor;
            }
            System.out.println(k + "th Largest Element is : " + ans);
        }

        Node predecessor;
        Node successor;
        int state;

        public void succAndPred(Node node, int val) {
            for (Node child : node.children) {
                if (child.data == val) {
                    state = 1;
                } else if (state == 0) {
                    predecessor = child;
                } else if (state == 1) {
                    successor = child;
                    state++;
                }
                succAndPred(child, val);
            }
        }

        Node subTreeRoot;
        int maxSum;

        public int maxSumSubtree(Node node) {
            int sum = node.data;
            for (Node child : node.children) {
                sum += maxSumSubtree(child);
            }
            if (sum > maxSum) {
                maxSum = sum;
                subTreeRoot = node;
            }
            return sum;
        }

    }

    public static void basic(GenericTree tree1) {
        // System.out.println("Size of the tree is : " + tree1.size(tree1.root));
        // System.out.println("Maximum of the tree is : " + tree1.max(tree1.root));

        // tree1.traversals(tree1.root);

        // System.out.println(tree1.find(tree1.root,160));
        // System.out.println(tree1.find(tree1.root,190));

        System.out.println(tree1.nodeToRootPath(tree1.root, 120));
        System.out.println(tree1.nodeToRootPath2(tree1.root, 120));

    }

    // Successor Predecessor
    // Max Sum Subtree
    public static void Set3(GenericTree tree1) {
        tree1.successor = null;
        tree1.predecessor = null;
        tree1.state = 0;
        tree1.succAndPred(tree1.root, 30);

        System.out.println("Predecessor : " + tree1.predecessor.data);
        System.out.println("Successor : " + tree1.successor.data);
        System.out.println(tree1.state);

        tree1.maxSum = Integer.MIN_VALUE;
        tree1.subTreeRoot = null;
        int sum = tree1.maxSumSubtree(tree1.root);

        System.out.println(sum + " " + tree1.maxSum);
    }

    // Ceil Floor
    // Kth Largest Element
    public static void Set2(GenericTree tree1) {
        tree1.ceil = Integer.MAX_VALUE;
        tree1.floor = Integer.MIN_VALUE;
        tree1.ceilAndFloor(tree1.root, 120);
        System.out.println("CEIL : " + tree1.ceil + "\nFLOOR : " + tree1.floor);

        tree1.KthLargest(tree1.root, 8);
    }

    // Height of the tree
    // Diameter of the tree
    public static void Set1(GenericTree tree) {
        int height = tree.height(tree.root);
        System.out.println(height);

        tree.dia = 0;
        height = tree.diameter(tree.root);
        System.out.println(height + " " + tree.dia);

    }

    public static void main(String[] args) {
        Integer input[] = { 10, 20, 50, null, 60, null, null, 30, 70, 90, null, 100, 110, null, 120, null, null, null,
                80, 130, null, 140, null, null, null, 40, 150, 160, null, null, null, null };

        GenericTree tree1 = new GenericTree();
        tree1.root = tree1.construct(input);

        tree1.display(tree1.root);

        basic(tree1);
        // Set1(tree1);
        // Set2(tree1);
        // Set3(tree1);
    }

}