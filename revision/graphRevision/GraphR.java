import java.util.ArrayList;
public class GraphR{

    public static class Edge{
        int v = 0;
        int w = 0;

        Edge(int v,int w){
            this.v = v;
            this.w = w;
        }
    }
    static int N = 7;
    static ArrayList<Edge>[] graph = new ArrayList[N];

    public static void addEdge(ArrayList<Edge>[] graph,int u,int v,int w){
        // bidirectional graph 
        graph[u].add(new Edge(v,w));
        graph[v].add(new Edge(u,w));
        
    }

    public static void display(ArrayList<Edge>[] graph){

        for(int i=0;i<N;i++){
            System.out.print(i +" -> ");
            for(Edge e:graph[i]){
                System.out.print("("+e.v+", "+e.w+") ");
            }
            System.out.println();
        }
        System.out.println();
    }





    public static void setDFS(){

    }

    public static void constructGraph(){
        for(int i=0;i<N;i++){
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

    }

    public static void main(String args[]){
        constructGraph();
        setDFS();
    }
}