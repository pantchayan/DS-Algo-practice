import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

       public static class Edge {
              int src;
              int nbr;
              int wt;

              Edge(int src, int nbr, int wt) {
                     this.src = src;
                     this.nbr = nbr;
                     this.wt = wt;
              }
       }

       private static void display(ArrayList<Edge>[] graph) {
              for (int i = 0; i < graph.length; i++) {
                     System.out.print(i + " -> ");

                     for (Edge e : graph[i]) {
                            System.out.print(e.nbr + "@" + e.wt + " , ");
                     }
                     System.out.println();
              }

       }

       public static ArrayList<Edge>[] constructFromInput() {
              Scanner sc = new Scanner(System.in);
              int V = 0;
              int E = 0;
              ArrayList<Edge>[] Graph;
              V = sc.nextInt();
              E = sc.nextInt();
              Graph = new ArrayList[V];
              // 7
              // 9
              // 0 1 10
              // 1 2 10
              // 2 3 10
              // 0 3 10
              // 3 4 10
              // 4 5 10
              // 5 6 10
              // 4 6 10
              // 2 5 10
              for (int i = 0; i < V; i++) {
                     Graph[i] = new ArrayList<>();
              }

              for (int i = 0; i < E; i++) {
                     int src = sc.nextInt();
                     int nbr = sc.nextInt();
                     int wt = sc.nextInt();
                     Graph[src].add(new Edge(src, nbr, wt));
                     Graph[nbr].add(new Edge(nbr, src, wt));
              }

              sc.close();

              return Graph;
       }

       public static ArrayList<Edge>[] construct() {
              ArrayList<Edge>[] graph = constructFromInput();
              display(graph);
              return graph;
       }

       public static void hamiltonianPath(ArrayList<Edge>[] graph, int src) {
              int V = graph.length;

              hamiltonianDFS(src, src, V, "" + src, new boolean[V], graph);

       }

       public static void hamiltonianDFS(int source, int currSrc, int V, String ans, boolean[] vis,
                     ArrayList<Edge>[] graph) {
              if (V < 0) {
                     return;
              }

              if (V == 1) {
                     // check if cycle
                     boolean flag = false;
                     for (Edge e : graph[currSrc]) {
                            if (e.nbr == source) {
                                   ans += "*";
                                   flag = true;
                                   break;
                            }
                     }
                     if (!flag)
                            ans += ".";
                     System.out.println(ans);
                     return;
              }

              // DFS algorithm
              vis[currSrc] = true;
              for (Edge e : graph[currSrc]) {
                     if (vis[e.nbr] == false) {
                            hamiltonianDFS(source, e.nbr, V - 1, ans + e.nbr, vis, graph);
                     }
              }
              vis[currSrc] = false;
       }

       public static void Set1(ArrayList<Edge>[] graph) {
              int src = 0;
              hamiltonianPath(graph, src);
       }

       public static class Pair {
              int vtx;
              String path;

              Pair(int vtx, String path) {
                     this.vtx = vtx;
                     this.path = path;
              }
       }

       public static void bfsPath(int src, ArrayList<Edge>[] graph) {
              Queue<Pair> que = new ArrayDeque<>();
              que.add(new Pair(src, "" + src));

              boolean[] vis = new boolean[graph.length];

              while (que.size() != 0) {
                     int size = que.size();
                     while (size-- > 0) {
                            Pair curr = que.remove();

                            if (vis[curr.vtx])
                                   continue;

                            String ans = curr.vtx + "@" + curr.path;
                            System.out.println(ans);

                            vis[curr.vtx] = true;

                            for (Edge e : graph[curr.vtx]) {
                                   if (vis[e.nbr] == false) {
                                          que.add(new Pair(e.nbr, curr.path + e.nbr));
                                          // vis[e.nbr] = true;
                                   }
                            }
                     }
              }
       }

       public static boolean isComponentCyclic(int src, boolean[] vis, ArrayList<Edge>[] graph) {
              Queue<Integer> que = new ArrayDeque<>();

              que.add(src);

              while (que.size() != 0) {
                     int size = que.size();
                     while (size-- > 0) {
                            int cvtx = que.remove();
                            if (vis[cvtx]) {
                                   return true;
                            }

                            vis[cvtx] = true;

                            for (Edge e : graph[cvtx]) {
                                   if (vis[e.nbr] == false) {
                                          que.add(e.nbr);
                                          // vis[e.nbr] = true;
                                   }
                            }
                     }
              }

              return false;
       }

       public static boolean isGraphCyclic(ArrayList<Edge>[] graph) {

              boolean[] vis = new boolean[graph.length];
              int GCC = 0;
              for (int src = 0; src < graph.length; src++) {
                     if (vis[src] == false) {
                            GCC++;
                            if (isComponentCyclic(src, vis, graph)) {
                                   System.out.println("GCC : " + GCC);
                                   return true;
                            }

                     }
              }

              System.out.println("GCC : " + GCC);

              return false;
       }

       public static class biPair {
              int vtx;
              int color;

              // color -> -1 (Nothing), 0 (Red), 1(Green)
              biPair(int vtx, int color) {
                     this.vtx = vtx;
                     this.color = color;
              }
       }

       public static boolean isGraphBipartite(ArrayList<Edge>[] graph) {

              int[] vis = new int[graph.length];

              for (int i = 0; i < graph.length; i++) {
                     vis[i] = -1;
              }

              int GCC = 0;
              for (int src = 0; src < graph.length; src++) {
                     if (vis[src] == -1) {
                            GCC++;
                            if (!isComponentBipartite(src, vis, graph)) {
                                   System.out.println("GCC : " + GCC);
                                   return false;
                            }

                     }
              }

              System.out.println("GCC : " + GCC);

              return true;
       }

       public static boolean isComponentBipartite(int src, int[] vis, ArrayList<Edge>[] graph) {
              Queue<biPair> que = new ArrayDeque<>();

              que.add(new biPair(src, 0));
              // System.out.println(src);

              while (que.size() != 0) {
                     int size = que.size();
                     while (size-- > 0) {
                            biPair curr = que.remove();

                            if (vis[curr.vtx] != -1) {
                                   if (vis[curr.vtx] != curr.color) {
                                          return false;
                                   }
                                   continue;
                            }

                            vis[curr.vtx] = curr.color;
                            for (Edge e : graph[curr.vtx]) {
                                   if (vis[e.nbr] == -1) {
                                          que.add(new biPair(e.nbr, (vis[curr.vtx] + 1) % 2));
                                   }
                            }
                     }
              }

              return true;
       }

       public static int isInfected(int src, int time, ArrayList<Edge>[] graph){
              Queue<Integer> que = new ArrayDeque<>();

              que.add(src);
              boolean[] vis = new boolean[graph.length];
              int count = 0;
              while(que.size()!=0){
                     int size = que.size();
                     while(size-->0){
                            int cvtx = que.remove();
                            if(vis[cvtx]){
                                   continue;
                            } 
                            count++;
                            vis[cvtx] = true;
                            for(Edge e: graph[cvtx]){
                                   if(vis[e.nbr]==false){
                                          que.add(e.nbr);
                                   }
                            }
                     }
                     time--;
                     if(time==0) break;
              }
              return count;
       }


       public static class dijkstraClass{
              int vtx;
              int wsf;
              String psf;

              dijkstraClass(int vtx, int wsf, String psf){
                     this.vtx = vtx;
                     this.wsf = wsf;
                     this.psf = psf;
              }
       }
       public static void shortestPathWeights(int src, ArrayList<Edge>[] graph){
              PriorityQueue<dijkstraClass> que = new PriorityQueue<>((a, b)-> a.wsf-b.wsf);

              que.add(new dijkstraClass(src, 0, ""+src));
              boolean[] vis = new boolean[graph.length];

              while(que.size()!=0){
                     int size = que.size();
                     while(size-->0){
                            dijkstraClass curr = que.remove();

                            if(vis[curr.vtx]) continue;

                            String ans = curr.vtx + " via " + curr.psf + " @ " + curr.wsf;
                            System.out.println(ans);
                            vis[curr.vtx] = true;
                            for(Edge e:graph[curr.vtx]){
                                   if(vis[e.nbr]==false){
                                          que.add(new dijkstraClass(e.nbr, curr.wsf+e.wt, curr.psf+e.nbr));
                                   }
                            }
                     }
              }

       }

       public static class primsClass {
              int vtx;
              int parent;
              int wt;

              primsClass(int vtx, int parent, int wt) {
                     this.vtx = vtx;
                     this.parent = parent;
                     this.wt = wt;
              }
       }

       public static void minimumSpanningTree(int src, ArrayList<Edge>[] graph) {
              PriorityQueue<primsClass> que = new PriorityQueue<>((a, b) -> a.wt - b.wt);

              que.add(new primsClass(src, -1, 0));
              boolean[] vis = new boolean[graph.length];
              int totalCost = 0;
              while (que.size() != 0) {
                     int size = que.size();
                     while (size-- > 0) {
                            primsClass curr = que.remove();

                            if (vis[curr.vtx])
                                   continue;

                            if(curr.parent!=-1){
                                   // [1-0@10]
                                   String ans = "["+curr.vtx+"-"+curr.parent+"@"+curr.wt+"]";
                                   System.out.println(ans);
                                   totalCost += curr.wt;
                            }
                            
                            vis[curr.vtx] = true;
                            for (Edge e : graph[curr.vtx]) {
                                   if (vis[e.nbr] == false) {
                                          que.add(new primsClass(e.nbr, curr.vtx, e.wt));
                                   }
                            }
                     }
              }
              System.out.println(totalCost);

       }


       public static void topologicalSort(ArrayList<Edge>[] graph){
              ArrayList<Integer> ans = new ArrayList<>();

              boolean[] vis = new boolean[graph.length];
              for (int i = 0; i < graph.length; i++) {
                     if (vis[i] == false) {
                            topologicalSortDFS(i, ans, vis, graph);
                     }
              }

              for (int i = ans.size() - 1; i >= 0; i--) {
                     System.out.println(ans.get(i));
              }
       }

       public static void topologicalSortDFS(int src, ArrayList<Integer> ans, boolean[] vis, ArrayList<Edge>[] graph ){
              vis[src] = true;
              for(Edge e:graph[src]){
                     if(vis[e.nbr] == false){
                            topologicalSortDFS(e.nbr, ans, vis, graph);
                     }
              }


              ans.add(src);
       }



       public static void iterativeDFS(int src, ArrayList<Edge>[] graph){
              Stack<Pair> st = new Stack<>();

              st.push(new Pair(src, ""+src));
              boolean[] vis = new boolean[graph.length];

              while(st.size()!=0){
                     Pair curr = st.pop();

                     if(vis[curr.vtx]) continue;

                     String ans = curr.vtx+"@"+curr.path;

                     System.out.println(ans);
                     vis[curr.vtx] = true;

                     for(Edge e:graph[curr.vtx]){
                            if(vis[e.nbr]==false){
                                   st.push(new Pair(e.nbr, curr.path+e.nbr));
                            }
                     }
              }

       }





       
       public static void Set2(ArrayList<Edge>[] graph) {
              int src = 6;
              // bfsPath(src, graph);
              // boolean res;
              // res = isGraphCyclic(graph);
              // System.out.println(res);

              // res = isGraphBipartite(graph);
              // System.out.println(res);

              int t = 3;     
              System.out.println(isInfected(src, t, graph));  
       }

       public static void main(String[] args) {
              ArrayList<Edge>[] graph = construct();
              // Set1(graph);
              Set2(graph);
       }
}