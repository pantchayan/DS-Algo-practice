import java.util.ArrayList;

public class directedGraph {

    static int N = 8;

    static ArrayList<Integer>[] graph = new ArrayList[N];

    public static void display() {

        for (int i = 0; i < N; i++) {
            System.out.print(i + "-> ");
            for (Integer e : graph[i]) {
                System.out.print(e + ", ");
            }
            System.out.println();
        }

        System.out.println();

    }

    public static void constructGraph() {
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        graph[7].add(5);
        graph[7].add(6);

        graph[5].add(4);
        graph[5].add(2);
        graph[6].add(4);
        graph[6].add(3);
        graph[2].add(1);
        graph[3].add(1);
        graph[1].add(0);

        display();

    }

    // Topological Sort <Dependency>
    // ====================================================================================================

    // using DFS
    // ========================================================================================================================

    public static void topologicalSortDFS(int src, boolean[] vis, ArrayList<Integer> ans) {
        vis[src] = true;
        for (Integer e : graph[src]) {
            if (!vis[e]) {
                topologicalSortDFS(e, vis, ans);
            }
        }
        ans.add(src);
    }

    public static void topologicalSort() {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        boolean[] vis = new boolean[N];

        for (int i = 0; i < N; i++) {
            if (!vis[i]) {
                topologicalSortDFS(i, vis, ans);
            }
        }

        for (int i = ans.size() - 1; i >= 0; i--) {
            System.out.print(i + "->");
        }
        System.out.println();

    }

    public static void set1() {
        topologicalSort();
    }

    public static void main(String[] args) {

        constructGraph();

        set1();

    }
}