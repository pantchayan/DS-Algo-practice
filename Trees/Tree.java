import java.util.ArrayList;
import java.util.LinkedList;

public class Tree {

    // Structure ===========================

    public static class Node {
        int data = 0;
        Node left = null;
        Node right = null;

        Node(int data) {
            this.data = data;
        }
    }

    static Node root = null;

    // Creation and display =================

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

    // preOrder -- > Binary Tree
    static int idx = 0;

    public static Node preConstruct(int[] arr) {
        if (idx == arr.length || arr[idx] == -1) {
            idx++;
            return null;
        }
        Node node = new Node(arr[idx++]);
        node.left = preConstruct(arr);
        node.right = preConstruct(arr);
        return node;
    }

    public static void constructTree() {
        int[] arr = { 10, 20, 40, -1, -1, 50, 80, -1, -1, 90, -1, -1, 30, 60, 100, -1, -1, -1, 70, 110, -1, -1, 120, -1,
                -1 };
        root = preConstruct(arr);
        display(root);
        System.out.println();
    }

    // Basics
    // ==================================================================================================================

    public static int size(Node node) {
        if (node == null)
            return 0;
        return size(node.left) + size(node.right) + 1;
    }

    public static int height(Node node) {
        if (node == null)
            return -1;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    public static int Maximum(Node node) {
        if (node == null)
            return Integer.MIN_VALUE;
        if (node.left == null && node.right == null)
            return node.data;
        return Math.max(Math.max(Maximum(node.left), Maximum(node.right)), node.data);
    }

    public static int Minimum(Node node) {
        if (node == null)
            return Integer.MAX_VALUE;
        if (node.left == null && node.right == null)
            return node.data;
        return Math.min(Math.min(Minimum(node.left), Minimum(node.right)), node.data);
    }

    public static boolean Find(Node node, int data) {
        if (node == null)
            return false;
        if (node.data == data)
            return true;

        // boolean res = false;
        // res = res || Find(node.left,data) || Find(node.right,data);
        // return res;

        return Find(node.left, data) || Find(node.right, data);
    }

    // Traversals
    // ============================================================================================================

    public static void inorder(Node node) {
        if (node == null)
            return;

        inorder(node.left);
        System.out.print(node.data + ", ");
        inorder(node.right);
    }

    public static void preorder(Node node) {
        if (node == null)
            return;

        System.out.print(node.data + ", ");
        preorder(node.left);
        preorder(node.right);
    }

    public static void postorder(Node node) {
        if (node == null)
            return;

        postorder(node.left);
        postorder(node.right);
        System.out.print(node.data + ", ");
    }

    // Path in Arraylist
    // ==============================================================================================================

    public static boolean rootToNodePath01(Node node, ArrayList<Node> path, int data) {
        if (node == null)
            return false;
        if (node.data == data) {
            path.add(node);
            return true;
        }

        boolean res = false;

        res = res || rootToNodePath01(node.left, path, data) || rootToNodePath01(node.right, path, data);
        if (res)
            path.add(node);
        return res;
    }

    // Lowest Common Ancestor
    // ===========================================================================================================

    // 1st way is to find 2 different paths and find the LCA by linear search with 2
    // pointers from back O(n)+O(logn) time and space complexity.
    // 2nd way is to do in O(n)+O(1) time and space complexity.

    static Node LCANode = null;

    public static boolean LCA02(Node node, Node p, Node q) {
        boolean SelfDone = false;
        if (node == null)
            return false;
        if (node.data == p.data || node.data == q.data) {
            SelfDone = true;
        }
        boolean Left = LCA02(node.left, p, q);
        if (LCANode != null)
            return true;

        boolean Right = LCA02(node.right, p, q);
        if (LCANode != null)
            return true;

        if ((SelfDone && Left) || (SelfDone && Right) || (Right && Left)) {
            LCANode = node;
        }

        return SelfDone || Right || Left;
    }

    // Leet 863 - all Nodes K far away
    // =======================================================================================================
    // printing all nodes K away from the target ..
    //
    // Method 1:(simpler) Using (rootToNodePath and K-down) functions to solve.
    // Method 2: ??.

    public static void allNodesKaway01(Node root, int target, int K) {
        ArrayList<Node> path = new ArrayList<>();
        rootToNodePath01(root, path, target);

        for (int i = 0; i < path.size(); i++) {
            if ((K - i) < 0)
                break;
            if (i == 0)
                kDown(path.get(i), K - i, null);
            else
                kDown(path.get(i), K - i, path.get(i - 1));

        }
    }

    public static void kDown(Node node, int K, Node blocked) {
        if (node == null || node == blocked)
            return;
        if (K < 0)
            return;
        if (K == 0) {
            System.out.println(node.data + " ");
            return;
        }

        kDown(node.left, K - 1, blocked);
        kDown(node.right, K - 1, blocked);
    }

    public static int allNodesKaway02(Node root, int target, int K) {
        if (root == null)
            return -1;

        if (root.data == target) {
            kDown(root, K, null);
            return 1;
        }

        int leftdistance = allNodesKaway02(root.left, target, K);
        if (leftdistance != -1) {
            if (K - leftdistance >= 0)
                kDown(root, K - leftdistance, root.left);
            return leftdistance + 1;
        }

        int rightdistance = allNodesKaway02(root.right, target, K);
        if (rightdistance != -1) {
            if (K - rightdistance >= 0)
                kDown(root, K - rightdistance, root.right);
            return rightdistance + 1;
        }

        return -1;

    }

    // Diameter of a Binary Tree
    // ==============================================================================
    // 2 solutions possible -- O(n**2) diameter01 and O(n) diameter02

    // In diameter01 we are computing Left height and Right height seperately

    public static int diameter01(Node node) {
        if (node == null)
            return 0;

        int lh = height(node.left);
        int rh = height(node.right);

        int ld = diameter01(node.left);
        int rd = diameter01(node.right);

        int currDia = lh + rh + 2;

        return Math.max(currDia, Math.max(ld, rd));

    }

    public static class diaPair {
        int dia = 0;
        int hei = 0;

        diaPair(int dia, int hei) {
            this.dia = dia;
            this.hei = hei;
        }
    }

    public static diaPair diameter02(Node node) {
        if (node == null)
            return new diaPair(0, -1);

        diaPair lr = diameter02(node.left); // left result
        diaPair rr = diameter02(node.right); // right result

        diaPair myRes = new diaPair(0, -1);
        myRes.dia = Math.max(Math.max(lr.dia, rr.dia), (lr.hei + rr.hei + 2));
        myRes.hei = Math.max(lr.hei, rr.hei) + 1;

        return myRes;
    }

    // PATH SUM SET
    // =======================================================================================

    // GFG => Maximum Path Sum between 2 Leaf Nodes ****
    // ==================================================================================
    static int maxPSL = Integer.MIN_VALUE;

    public static int maxPathSumLeaves(Node node) {
        if (node == null)
            return 0;
        if (node.left == null && node.right == null)
            return node.data;

        int lh = maxPathSumLeaves(node.left);
        int rh = maxPathSumLeaves(node.right);

        // PostOrder

        // both childs present ==> leaf to leaf path is possible
        if (node.left != null && node.right != null) {
            maxPSL = Math.max(maxPSL, lh + rh + node.data);

            return Math.max(lh, rh) + node.data;
        }

        // single child is present
        return node.left == null ? rh + node.data : lh + node.data;
    }

    // Leet 124- Maximum path sum between any nodes in the Binary Tree
    // ===============================================================

    static int maxPSN = Integer.MIN_VALUE;

    public static int maxPathSumNodes_(Node node) {
        if (node == null)
            return 0;

        int lm = maxPathSumNodes_(node.left);
        int rm = maxPathSumNodes_(node.right);

        int f = Math.max(lm + node.data, rm + node.data);
        int f2 = Math.max(f, node.data);
        int p = Math.max(lm + rm + node.data, f2);
        maxPSN = Math.max(maxPSN, p);

        return f2;
    }

    // LEVEL ORDER SET
    // ================================================================================================================

    // Level order traversal (BFS in trees)
    // ============================================================================================================
    // 1. Iterative
    // 2. Implemented using queues
    // 3. (LinkedList{addLast(n),removeFirst(),getFirst()} lib to be used in java).
    public static void levelOrder(Node node) {
        // basic bfs algo:
        // 1. add source to the queue
        // 2. while size of queue !=0
        // 2.1 pop Node --> (currNode)
        // 2.2 add all the child of the currNode to queue

        LinkedList<Node> que = new LinkedList<>();

        que.addLast(node);

        // No need of visited array here as the tree is rooted. (unlike graphs)
        int level = 0;
        while (que.size() != 0) {
            int size = que.size();
            System.out.println();
            System.out.print(level++ + "-> ");
            while (size-- > 0) {
                Node curr = que.getFirst();
                que.removeFirst();
                System.out.print(curr.data + " ");
                if (curr.left != null)
                    que.addLast(curr.left);
                if (curr.right != null)
                    que.addLast(curr.right);
            }
        }
        System.out.println();
    }

    // Left view
    // ===============================================================================

    public static void leftView(Node node) {
        System.out.println("Left view");
        LinkedList<Node> que = new LinkedList<>();

        que.addLast(node);
        while (que.size() != 0) {
            int size = que.size();
            System.out.println(que.getFirst().data);
            while (size-- > 0) {
                Node curr = que.getFirst();
                que.removeFirst();
                if (curr.left != null)
                    que.addLast(curr.left);
                if (curr.right != null)
                    que.addLast(curr.right);
            }
        }
    }

    // Right view
    // ===============================================================================

    public static void rightView(Node node) {
        System.out.println("Right view");
        LinkedList<Node> que = new LinkedList<>();

        que.addLast(node);
        while (que.size() != 0) {
            int size = que.size();
            System.out.println(que.getLast().data);
            while (size-- > 0) {
                Node curr = que.getFirst();
                que.removeFirst();
                if (curr.left != null)
                    que.addLast(curr.left);
                if (curr.right != null)
                    que.addLast(curr.right);
            }
        }
    }

    // Vertical order =======================================
    // 1. helps us in finding bottom and top view of the tree.

    static int minLeftLevel = Integer.MAX_VALUE;
    static int maxRightLevel = Integer.MIN_VALUE;

    public static void width(Node node, int vLevel) {
        if (node == null)
            return;

        minLeftLevel = Math.min(minLeftLevel, vLevel);
        maxRightLevel = Math.max(maxRightLevel, vLevel);
        width(node.left, vLevel - 1);
        width(node.right, vLevel + 1);
    }

    public static class pairVO {
        Node node;
        int vl = 0;

        pairVO(Node node, int vl) {
            this.node = node;
            this.vl = vl;
        }
    }

    public static ArrayList<ArrayList<Integer>> verticalOrder(Node node) {
        width(node, 0);
        int n = maxRightLevel - minLeftLevel + 1;

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            ans.add(new ArrayList<Integer>());
        }
        LinkedList<pairVO> que = new LinkedList<>();
        que.addLast(new pairVO(node, -minLeftLevel));

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                pairVO curr = que.getFirst();
                que.removeFirst();

                ans.get(curr.vl).add(curr.node.data);

                if (curr.node.left != null)
                    que.addLast(new pairVO(curr.node.left, curr.vl - 1));
                if (curr.node.right != null)
                    que.addLast(new pairVO(curr.node.right, curr.vl + 1));
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.print(i + "-> ");
            System.out.println(ans.get(i));
        }
        System.out.println();
        return ans;
    }

    public static void topView(Node node) {
        ArrayList<ArrayList<Integer>> vo = verticalOrder(node);

        for (ArrayList<Integer> col : vo) {
            System.out.print(col.get(0) + " ");
        }
    }

    public static void bottomView(Node node) {
        ArrayList<ArrayList<Integer>> vo = verticalOrder(node);

        for (ArrayList<Integer> col : vo) {
            System.out.print(col.get(col.size() - 1) + " ");
        }
    }

    public static void levelOrderSet() {
        // levelOrder(root);
        // leftView(root);
        // rightView(root);

        topView(root);
        bottomView(root);

        // Diagonal view, VO sum, Diagonal VO
    }

    public static void pathSumSet() {

        maxPathSumLeaves(root);
        System.out.println(maxPSL);

        maxPathSumNodes_(root);
        System.out.println(maxPSN);
    }

    public static void set2() {
        // lets code for allNodesKaway
        // System.out.println();
        // allNodesKaway01(root, 50, 5);

        // System.out.println();

        // allNodesKaway02(root, 50, 5);

        // System.out.println(diameter01(root));
        // System.out.println(diameter02(root).dia);
        // System.out.println(diameter02(root).hei);

    }

    public static void set1() {
        // int size = size(root);
        // int height = height(root);
        // System.out.println(size +" "+height);

        // System.out.println(Maximum(root));
        // System.out.println(Minimum(root));

        // System.out.println(Find(root,2001));

        // preorder(root);

        // ArrayList<Node> path = new ArrayList<>();
        // rootToNodePath01(root, path, 120);
        // // System.out.println(path);

        // for (Node node : path) {
        // System.out.print(node.data + " ");
        // }
        // System.out.println();
    }

    // Set of questions :
    // https://www.pepcoding.com/most-important-interview-questions-list-for-product-based-companies

    public static void solve() {
        constructTree();
        // set1();
        // set2();
        // pathSumSet();
        levelOrderSet();
    }

    public static void main(String[] args) {
        solve();
    }

}
