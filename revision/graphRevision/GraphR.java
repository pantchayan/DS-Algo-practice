import java.util.ArrayList;

public class GraphR {

    public static class Edge {
        int v = 0;
        int w = 0;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    static int N = 7;
    static ArrayList<Edge>[] graph = new ArrayList[N];

    // Basics ====================================================================================================

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
        // bidirectional graph
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(u, w));

    }

    public static void display(ArrayList<Edge>[] graph) {

        for (int i = 0; i < N; i++) {
            System.out.print(i + " -> ");
            for (Edge e : graph[i]) {
                System.out.print("(" + e.v + ", " + e.w + ") ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void removeEdge(int u, int v) {
        for (Edge e : graph[u]) {
            if (e.v == v) {
                graph[u].remove(e);
                break;
            }
        }
        for (Edge e : graph[v]) {
            if (e.v == u) {
                graph[v].remove(e);
                break;
            }
        }
    }

    public static void removeVtx(int u) {
        for (Edge e1 : graph[u]) {
            int v = e1.v;
            for (Edge e2 : graph[v]) {
                if (e2.v == u) {
                    graph[v].remove(e2);
                    break;
                }
            }
        }
        for (int i = graph[u].size() - 1; i >= 0; i--) {
            graph[u].remove(i);
        }
    }

    // =====================================================================================================
    
    
    // DFS =================================================================================================

    public static boolean hasPath(int src,int dest,boolean[] vis){
        if(src==dest) return true;

        vis[src] = true;
        boolean res = false;
        for(Edge e:graph[src]){
            if(!vis[e.v]){
                res = res || hasPath(e.v, dest, vis);
            }
        }
        return res;
    } 

    public static void allPath(int src, int dest,boolean[] vis,int pw, String psf){
        if(src==dest) System.out.println(psf+"@"+pw);

        vis[src] = true;
        for(Edge e:graph[src]){
            if(!vis[e.v]){
                allPath(e.v, dest, vis, pw+e.w, psf+e.v);
            }
        }
        vis[src] = false;
    }

    // Hamiltonian Path ==================================================================================================================

    public static boolean hasHamiltonian(int src,int count,boolean[] vis){
        if(count+1==N) return true;
        vis[src] = true;
        boolean res = false;
        for(Edge e:graph[src]){
            if(!vis[e.v]){
               res = res || hasHamiltonian(e.v, count+1, vis);
            }
        }
        return res;
    }

    
    public static void allHamiltoninan(int src,int count, boolean[] vis,int pw, String psf){
        if(count+1==N) System.out.println(psf+"@"+pw);

        vis[src] = true;
        for(Edge e:graph[src]){
            if(!vis[e.v]){
                allHamiltoninan(e.v,count+1, vis, pw+e.w, psf+e.v);
            }
        }
        vis[src] = false;
    }

    // GCC -- Get Connected Componenets ======================================================================================================

    public static int dfsGCC(int src,int size,boolean[] vis){
        vis[src] = true;
        for(Edge e:graph[src]){
            if(!vis[e.v]){
                dfsGCC(e.v,size++,vis);
            }
        }
        return size+1;
    }


    public static void GCC(){
        boolean[] vis = new boolean[N];
        int count = 0;
        int maxSize = 0;
        for(int i=0;i<N;i++){
            if(!vis[i]){
                maxSize = Math.max(dfsGCC(i,0,vis),maxSize);
                count++;
            }
        }
        System.out.println("Number of Components : "+ count);
        
        System.out.println("Maximum size of a component : "+ maxSize);
    }










    public static void setDFS() {
        boolean[] vis = new boolean[N];
        System.out.println(hasPath(0, 5, vis));
        //System.out.println(hasPath(0, 7, vis));
        
        vis = new boolean[N];
        allPath(0, 6, vis, 0, "0");
        System.out.println("\nHamilton : ");
        System.out.println(hasHamiltonian(0, 0, vis));
        vis = new boolean[N];
        allHamiltoninan(0, 0, vis, 0, "0");

        System.out.println("GCC : ");

        GCC();
    }

    public static void constructGraph() {
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<Edge>();
        }

        addEdge(graph, 0, 1, 10);
        addEdge(graph, 0, 3, 10);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 10);

        addEdge(graph, 3, 4, 6);

        addEdge(graph, 4, 5, 5);
        addEdge(graph, 4, 6, 8);
        addEdge(graph, 5, 6, 1);

        display(graph);

        // removeEdge(5, 6);

        // removeVtx(2);

        // System.out.println();

        // display(graph);
    }

    public static void main(String args[]) {
        constructGraph();
        setDFS();
    }
}