import java.util.ArrayList;
import java.util.LinkedList;

public class directedGraph {

    static int N = 6;

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
            System.out.print(ans.get(i) + "->");
        }
        System.out.println();

    }

    // Kahn's algorithm ======================================================================================================

    public static void kahnAlgo(){

        int[] indegree = new int[N];
        
        for(int i=0;i<N;i++){
            for(int v : graph[i]){
                indegree[v]++;
            }   
        }   

        LinkedList<Integer> que = new LinkedList<>();

        for(int i=0;i<N;i++){
            if(indegree[i]==0){
                que.addLast(i);
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();

        while(que.size()!=0){
            int size = que.size();
            while(size -- >0){
                int rvtx = que.removeLast();
                
                ans.add(rvtx);
                for(int v : graph[rvtx]){
                    indegree[v]--;
                    if(indegree[v]==0){
                        que.addLast(v);
                    }
                }
            }
        }


        if(ans.size()==N){
            System.out.println("Topological Order");
            
            System.out.println(ans);

        }
        else{
            System.out.println("Cycle found -- Deadlock present -- No correct topological order possible");
        }

    }


    // DFS topological Cycle detection ================================================================================


    private static boolean topologicalCycleDFS(int src, int[] vis,ArrayList<Integer> ans) {
        vis[src] = 1;
        boolean res = false;
        for(Integer v : graph[src]){
            if(vis[v]==0){
                res = res||topologicalCycleDFS(v, vis,ans);
            }
            else if(vis[v]==1){
                return true;
            }
        }

        vis[src]=2;
        ans.add(src);
        return res;
    }


    public static void topologicalCycle(){
        int[] vis = new int[N]; // 0 : unvisited , 1 : same path vis(cycle) , 2 : other path vis

        ArrayList<Integer> ans = new ArrayList<>();
        boolean res = false;
        for(int i=0;i<N;i++){
            if(vis[i]==0){
                res = res || topologicalCycleDFS(i,vis,ans);
            }
        }

        if(res){
            System.out.println("Cycle found.");
        }
        else{
            for (int i = ans.size() - 1; i >= 0; i--) {
                System.out.print(ans.get(i) + "->");
            }
            System.out.println();
        }

    }



    // ================================================================================================================

    

    public static void set1() {
        topologicalSort();

        kahnAlgo();

        topologicalCycle();

    }

    public static void constructGraph() {
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        graph[0].add(1);
        graph[1].add(2);

        graph[2].add(3);
        graph[3].add(4);
        graph[5].add(4);
        graph[4].add(1);
        // graph[2].add(1);
        // graph[3].add(1);
        // graph[1].add(0);

        display();

    }

    public static void main(String[] args) {

        constructGraph();

        set1();

    }
}