import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {

    public static class Edge {
        int v = 0;
        int w = 0;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    static int N = 7;
    // only array is initialized here -- need to inititalize AL for each node in
    // construct graph
    static ArrayList<Edge>[] graph = new ArrayList[N];

    public static void addEdge(int u, int v, int w) {
        // Bidirectional Graph
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(u, w));

    }

    public static void display() {
        for (int i = 0; i < N; i++) {
            System.out.print(i + " -> ");
            for (Edge e : graph[i]) {
                System.out.print(" (" + e.v + "," + e.w + ")");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int findEdge(int u, int v) {

        for (int i = 0; i < graph[u].size(); i++) {
            Edge e = graph[u].get(i);
            if (e.v == v) {
                return i;
            }
        }

        return -1;
    }

    public static void removeEdge(int u, int v) {
        int idx1 = findEdge(u, v);
        graph[u].remove(idx1);
        int idx2 = findEdge(v, u);
        graph[v].remove(idx2);
    }

    public static void removeVtx(int u) {
        for (int i = graph[u].size() - 1; i >= 0; i--) {
            Edge e = graph[u].get(i);
            removeEdge(u, e.v);
        }
    }





    // BFS
    // ======================================================================================================================


    public static class pair {
        int vtx;
        String psf;
        int level;

        pair(int vtx, String psf) {
            this.vtx = vtx;
            this.psf = psf;
        }

        // For BFS02 -- level storing
        pair(int vtx, String psf, int level) {
            this.vtx = vtx;
            this.psf = psf;
            this.level = level;
        }

    }




    private static void BSF(int i, boolean[] vis) {
        LinkedList<pair> que = new LinkedList<>();
        int des = 6;
        int cycle = 0;

        que.addLast(new pair(i, "" + i));

        while (que.size() != 0) {
            pair p = que.getFirst();
            que.removeFirst();

            if (vis[p.vtx]) {
                cycle++;
                continue;
            }

            if (p.vtx == des) {
                System.out.println("Shortest Path : " + p.psf);
            }

            vis[p.vtx] = true;

            for (Edge e : graph[p.vtx]) {
                if (!vis[e.v]) {
                    que.addLast(new pair(e.v, p.psf + e.v));
                }
            }
        }
        System.out.println("Total Cycles : " + cycle);
    }





    // Delimeter method  -- using null to keep an account of LEVELS 

    private static void BSF02(int i, boolean[] vis) {
        LinkedList<pair> que = new LinkedList<>();
        int des = 6;
        int cycle = 0;
        int level = 0;
        que.addLast(new pair(i, "" + i));
        que.addLast(null);
        while (que.size() != 1) {
            pair p = que.getFirst();
            que.removeFirst();

            if(p==null){
                level++;
                que.addLast(null);
                continue;
            }

            if (vis[p.vtx]) {
                cycle++;
                System.out.println("Cycle @"+p.vtx+"->"+level);
                continue;
            }

            if (p.vtx == des) {
                System.out.println("Shortest Path : " + p.psf + "->" + level);
            }

            vis[p.vtx] = true;

            for (Edge e : graph[p.vtx]) {
                if (!vis[e.v]) {
                    que.addLast(new pair(e.v, p.psf + e.v));
                }
            }
        }

        System.out.println("Total Cycles : " + cycle);
    }







    
    // Storing level in the pair class itself 

    private static void BSF03(int i, boolean[] vis) {
        LinkedList<pair> que = new LinkedList<>();
        int des = 6;
        int cycle = 0;

        que.addLast(new pair(i, "" + i,0));

        while (que.size() != 0) {
            pair p = que.getFirst();
            que.removeFirst();

            if (vis[p.vtx]) {
                cycle++;
                System.out.println("Cycle @"+p.vtx+"->"+p.level);
                continue;
            }

            if (p.vtx == des) {
                System.out.println("Shortest Path : " + p.psf + "->" + p.level);
            }

            vis[p.vtx] = true;

            for (Edge e : graph[p.vtx]) {
                if (!vis[e.v]) {
                    que.addLast(new pair(e.v, p.psf + e.v,p.level+1));
                }
            }

            if(que.size()==0){
                System.out.println(p.level);
            }
        }
        System.out.println("Total Cycles : " + cycle);
    }



    private static void BSF04(int i, boolean[] vis) {
        LinkedList<pair> que = new LinkedList<>();
        int des = 6;
        int level= 0;
        int count = 0;
        int cycle = 0;

        que.addLast(new pair(i, "" + i));

        while (que.size() != 0) {
            pair p = que.getFirst();
            que.removeFirst();

            if (vis[p.vtx]) {
                cycle++;
                continue;
            }

            if (p.vtx == des) {
                System.out.println("Shortest Path : " + p.psf);
            }

            vis[p.vtx] = true;

            for (Edge e : graph[p.vtx]) {
                if (!vis[e.v]) {
                    que.addLast(new pair(e.v, p.psf + e.v));
                    count++;
                }
            }
            count--;
            if(count == 0){
                
            }
        }
        System.out.println("Total Cycles : " + cycle);
    }





    public static void constructGraph() {
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<Edge>();
        }

        addEdge(0, 1, 10);
        addEdge(0, 3, 10);
        addEdge(1, 2, 10);
        addEdge(2, 3, 40);
        addEdge(3, 4, 2);
        addEdge(4, 5, 2);
        addEdge(4, 6, 3);
        addEdge(5, 6, 8);

        // removeEdge(3,4);
        // display();
        // removeVtx(2);
        // display();
    }

    public static void set1() {
        boolean[] vis = new boolean[N];
        //BSF(0, vis);

        BSF02(0 , vis);
    }

    public static void main(String[] args) {
        constructGraph();

        display();

        set1();
    }
}