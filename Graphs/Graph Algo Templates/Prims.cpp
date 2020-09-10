// Prims Algorithm is used to find M.S.T. (Minimum Spanning Tree) of a graph
// 1. BFS approach -- BFS + priority_pque
#include <iostream>
#include <vector>
#include <queue>

using namespace std;

class Edge
{
public:
    int v = 0;
    int w = 0;
    Edge(int v, int w)
    {
        this->v = v;
        this->w = w;
    }
};

int N = 7;
vector<vector<Edge>> graph(N, vector<Edge>());
// vector<vector<pair<int,int>>> graph(N,vector<pair<int,int>>());

void addEdge(vector<vector<Edge>> &gp, int u, int v, int w)
{
    gp[u].push_back(Edge(v, w));
    gp[v].push_back(Edge(u, w));
}

void display(vector<vector<Edge>> &gp)
{

    for (int i = 0; i < gp.size(); i++)
    {
        cout << i << " -> ";

        for (Edge e : gp[i])
        {
            cout << "(" << e.v << ", " << e.w << "), ";
        }
        cout << endl;
    }

    cout << endl;
}

// Prims Algorithm ===========================================================================================================================

class ppair{
    public:
        int src = 0;
        int par = 0;
        int w = 0;

        ppair(int src,int par,int w){
            this->src = src;
            this->par = par;
            this->w = w;
        }
};

struct primsComp
{
public:
    bool operator()(ppair &p1, ppair &p2)
    {
        return p1.w > p2.w; // default min PQ.
        //   return p2.w > p1.w   // max PQ.
    }
};

int Prims()
{
    // BFS algo
    // primsComp Comparator instructs the PQ to refer (w) from ppair  
    priority_queue<ppair , vector<ppair>, primsComp> pq; // default min PQ

    vector<vector<Edge>> primsGraph(N,vector<Edge>());
 
    pq.push(ppair(0,-1,0));

    vector<bool> vis(N, false);

    int cost = 0;

    while (pq.size() != 0)
    {
        int size = pq.size();
        while (size-- > 0)
        {
            ppair vtx = pq.top();
            pq.pop();

            // cycle check
            if(vis[vtx.src]) continue;

            if(vtx.par != -1){
                addEdge(primsGraph,vtx.par,vtx.src,vtx.w);
                cost+=vtx.w;
            }

            vis[vtx.src] = true;

            for (Edge e : graph[vtx.src])
            {
                if (!vis[e.v])
                {
                    pq.push(ppair(e.v,vtx.src,e.w));
                }
            }
        }
    }

    display(primsGraph);
    return cost;
}

void set1()
{
    //Prims();
   cout << Prims() << endl;
}

void constructGraph()
{
    addEdge(graph, 0, 1, 10);
    addEdge(graph, 0, 3, 10);
    addEdge(graph, 1, 2, 10);
    addEdge(graph, 2, 3, 40);
    addEdge(graph, 3, 4, 2);
    addEdge(graph, 4, 5, 2);
    addEdge(graph, 4, 6, 3);
    addEdge(graph, 5, 6, 8);
    display(graph);
}

int main()
{
    constructGraph();
    set1();

    return 0;
}
