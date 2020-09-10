// Dijkstra's algorithm is a single source minimum cost path finding algorithm.

// 1. Different from creating a M.S.T.
// 2. Source dependent.
// 3. BFS approach -- BFS + priority pque
// 4. Important to create the resultant graph

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

// Dijkstra's Algorithm ========================================================================================================

class dpair
{
public:
    int src = 0;
    int par = 0;
    int w = 0;
    int wsf = 0;

    dpair(int src, int par, int w, int wsf)
    {
        this->src = src;
        this->par = par;
        this->w = w;
        this->wsf = wsf;
    }
};


struct dijikstraComp
{
public:
    bool operator()(dpair &p1, dpair &p2)
    {
        return p1.wsf > p2.wsf; // default min PQ.
        //   return p2.wsf > p1.wsf   // max PQ.
    }
};



void Dijkstra(int src)
{
    // BFS algo


    // the comparator instructs the PQ to refer to wsf in dpair class
    priority_queue<dpair, vector<dpair>, dijikstraComp> pq; // default min in cpp

    vector<vector<Edge>> dijkstraGraph(N,vector<Edge>());

    pq.push(dpair(src, -1, 0, 0));

    vector<bool> vis(N, false);

    while (pq.size() != 0)
    {
        int size = pq.size();
        while (size-- > 0)
        {
            dpair vtx = pq.top();
            pq.pop();

            // cycle check
            if(vis[vtx.src]) continue;

            if(vtx.par!=-1){
                //dijkstraGraph.push_back(Edge(vtx.par,vtx.src,vtx.w));
                addEdge(dijkstraGraph,vtx.par,vtx.src,vtx.w);
            }

            vis[vtx.src] = true;

            for (Edge e : graph[vtx.src])
            {
                if (!vis[e.v])
                {
                    pq.push(dpair(e.v, vtx.src, e.w, vtx.wsf + e.w));
                }
            }
        }
    }

    display(dijkstraGraph);

}

void set1()
{
    Dijkstra(0);
    Dijkstra(2);
}

void constructGraph()
{
    addEdge(graph, 0, 1, 20);
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
