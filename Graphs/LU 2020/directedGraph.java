import java.util.ArrayList;
import java.util.LinkedList;

public class directedGraph {

    static int N = 12;

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
     
    // Strongly connected components ================================================================================ 

    public static void topologicalSortSCC(int src,boolean[] vis, ArrayList<Integer> ans){

        vis[src] = true;
        for(Integer ele:graph[src]){
            if(!vis[ele]){
                topologicalSortSCC(ele, vis, ans);
            }
        }

        ans.add(src);
    }

    public static int dfsSCC(int src,ArrayList<Integer>[] ngraph,boolean[] vis,ArrayList<Integer> ans){
        vis[src] = true;
        int count = 0;
        for(Integer ele:ngraph[src]){
            if(!vis[ele]){
                count+=dfsSCC(ele, ngraph, vis, ans);
            }
        }
        ans.add(src);

        return count+1;
    }


    public static void SCC(){
        
        // KOSARAJU ALGORITHM

        // Step 1 - find topological order of the the graph.
        boolean[] vis = new boolean[N];

        ArrayList<Integer> ans = new ArrayList<>();

        for(int i=0;i<N;i++){
            if(!vis[i]){
                topologicalSortSCC(i,vis,ans);
            }
        }

        // Step 2 - reverse the graph

        ArrayList<Integer>[] ngraph = new ArrayList[N];

        for(int i=0;i<N;i++){
            ngraph[i] = new ArrayList<Integer>();
        }

        for(int i=0;i<N;i++){
            for(Integer ele:graph[i]){
                ngraph[ele].add(i);
            }
        }

        // Step 3 - call DFS in topological order on the new reversed graph

        ArrayList<Integer> ansFinal = new ArrayList<>();
        vis = new boolean[N];
        for(int i=ans.size()-1;i>=0;i--){
            int src = ans.get(i);
            if(!vis[src]){
                int size = dfsSCC(src,ngraph,vis,ansFinal);

                System.out.print(ansFinal+"@"+size+" -> ");
            }
            ansFinal = new ArrayList<>();
        }
        System.out.println();
        
    }



    // ================================================================================================================

    

    public static void set1() {
        topologicalSort();

        kahnAlgo();

        topologicalCycle();

        SCC();
    }

    public static void constructGraph() {
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        graph[1].add(2);
        graph[3].add(1);
        graph[2].add(3);

        graph[3].add(4);

        graph[4].add(5);
        graph[5].add(6);
        graph[6].add(7);
        graph[7].add(4);

        graph[7].add(8);

        
        graph[8].add(9);
        graph[9].add(10);
        graph[10].add(8);

        graph[11].add(10);

        display();

    }

    public static void main(String[] args) {

        constructGraph();

        set1();

    }
}