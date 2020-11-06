import java.util.ArrayList;

public class TreeR {

    public static class Node {
        int data = 0;
        Node left = null;
        Node right = null;

        Node(int data) {
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

    static int idx = 0;

    public static Node preOrderToTree(int[] arr) {
        if (arr[idx] == -1 || idx >= arr.length) {
            idx++;
            return null;
        }
        Node node = new Node(arr[idx++]);
        node.left = preOrderToTree(arr);
        node.right = preOrderToTree(arr);

        return node;
    }

    public static void constructTree() {
        int[] preOrdArr = { 1, 2, 4, -1, -1, 5, 8, -1, -1, 9, -1, -1, 3, 6, 10, -1, -1, -1, 7, 11, -1, -1, -1 };
        root = preOrderToTree(preOrdArr);

        display(root);
    }

    // BASIC FUNCTIONS ============================================================

    public static void inorder(Node node) {
        if (node == null)
            return;

        inorder(node.left);
        System.out.print(node.data + " ");
        inorder(node.right);
    }

    public static void preorder(Node node) {
        if (node == null) {
            System.out.print("-1 ");
            return;
        }

        System.out.print(node.data + " ");
        preorder(node.left);
        preorder(node.right);
    }

    public static void postorder(Node node) {
        if (node == null)
            return;

        postorder(node.left);
        postorder(node.right);
        System.out.print(node.data + " ");
    }

    public static boolean find(Node node, int data) {
        if (node == null)
            return false;
        if (data == node.data)
            return true;

        return find(node.left, data) || find(node.right, data);

    }

    public static int minimum(Node node) {
        if (node == null)
            return 100000;
        if (node.left == null && node.right == null)
            return node.data;

        return Math.min(Math.min(minimum(node.left), minimum(node.right)), node.data);
    }

    public static int maximum(Node node) {
        if (node == null)
            return Integer.MIN_VALUE;

        return Math.max(Math.max(maximum(node.left), maximum(node.right)), node.data);
    }

    public static int height(Node node) {
        if (node == null)
            return -1;

        int leftH = height(node.left);
        int rightH = height(node.right);

        return Math.max(leftH, rightH) + 1;
    }

    public static boolean rootToNode01(Node node, int data, ArrayList<Integer> ans) {
        if (node == null)
            return false;
        if (node.data == data) {
            ans.add(node.data);
            return true;
        }

        boolean res = false;
        res = res || rootToNode01(node.left, data, ans) || rootToNode01(node.right, data, ans);
        if (res)
            ans.add(node.data);

        return res;
    }

    public static int LCA01(Node node, int child1, int child2) {
        ArrayList<Integer> path1 = new ArrayList<Integer>();
        ArrayList<Integer> path2 = new ArrayList<Integer>();

        rootToNode01(node, child1, path1);
        rootToNode01(node, child2, path2);

        int LCA = path1.get(path1.size() - 1);

        int i = path1.size() - 1;
        int j = path2.size() - 1;

        while (i >= 0 && j >= 0) {
            if (path1.get(i) != path2.get(j))
                break;

            LCA = path1.get(i);

            i--;
            j--;
        }
        return LCA;
    }

    public static boolean LCA02(Node node, int child1, int child2) {
        if (node == null)
            return false;
        if (node.data == child1 || node.data == child2)
            return true;

        boolean left = LCA02(node.left, child1, child2);
        boolean right = LCA02(node.right, child1, child2);

        if (left && right)
            System.out.println(node.data);
        else if (left || right)
            return true;

        return false;
    }

    // LCA -- RAJNEESH SIR
    // =============================================================================

    static Node LCANode = null;

    public static boolean lowestCommonAncestor_02(Node root, int p, int q) {
        if (root == null)
            return false;

        boolean selfDone = false;
        if (root.data == p || root.data == q) {
            selfDone = true;
        }

        boolean leftDone = lowestCommonAncestor_02(root.left, p, q);
        if (LCANode != null)
            return true;

        boolean rightDone = lowestCommonAncestor_02(root.right, p, q);
        if (LCANode != null)
            return true;

        if ((selfDone && leftDone) || (selfDone && rightDone) || (leftDone && rightDone))
            LCANode = root;

        return selfDone || leftDone || rightDone;
    }

    // K down
    // ============================================================================================
    static boolean flag = false;

    public static void kDown(Node node, int target, int K, int blocked) {
        if (node == null || node.data == blocked)
            return;

        if (flag)
            K--;
        if (K == 0) {
            System.out.print(node.data + " ");
        }
        if (node.data == target) {
            flag = true;
        }

        kDown(node.right, target, K, blocked);
        kDown(node.left, target, K, blocked);
    }

    public static void allNodesKAway(Node node, int target, int K) {
        ArrayList<Integer> ans = new ArrayList<>();

        rootToNode01(node, target, ans);

        flag = false;
        kDown(node, target, K, 0);

        for (int i = 1; i < ans.size(); i++) {
            if (K - i < 0)
                break;

            flag = false;
            kDown(node, ans.get(i), K - i, ans.get(i - 1));

        }

    }

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

    public static class pairDH {
        int hei = 0;
        int dia = 0;

        pairDH(int hei, int dia) {
            this.hei = hei;
            this.dia = dia;
        }
    }
    public static pairDH diameter02(Node node) {
        if (node == null) {
            return new pairDH(-1, 0);
        }

        pairDH left = diameter02(node.left);
        pairDH right = diameter02(node.right);

        int hei = Math.max(left.hei, right.hei) + 1;
        int dia = Math.max(Math.max(left.dia, right.dia), left.hei + right.hei + 2);

        return new pairDH(hei, dia);

    }

    public static void set2() {
        // ArrayList<Integer> ans = new ArrayList<Integer>();
        // rootToNode01(root, 8, ans);
        // System.out.println(ans);
        // System.out.println(LCA01(root,10,6));

        // LCA02(root, 6, 10);

        // kDown(root, 2, 2, 0);
        // System.out.println();
        // allNodesKAway(root, 3, 2);

        System.out.println(diameter01(root));

        pairDH ans = diameter02(root);
        System.out.println(ans.hei + " " + ans.dia);
    }

    public static void set1() {
        inorder(root);
        System.out.println();
        preorder(root);
        System.out.println();
        postorder(root);
        System.out.println();

        System.out.println(find(root, 11));
        System.out.println(minimum(root));
        System.out.println(height(root));
    }

    public static void main(String args[]) {
        constructTree();
        // set1();
        set2();
    }

}
