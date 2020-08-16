#include <iostream>
#include <vector>
#include <string>
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

int findEdge(int v1, int v2)
{
    int vtx = -1;
    for (int i = 0; i < graph[v1].size(); i++)
    {
        Edge e = graph[v1][i];
        if (e.v == v2)
        {
            vtx = i;
            break;
        }
    }

    return vtx;
}

void removeEdge(int u, int v)
{

    int idx1 = findEdge(u, v);
    int idx2 = findEdge(v, u);

    graph[u].erase(graph[u].begin() + idx1);
    graph[v].erase(graph[v].begin() + idx2);
}

void removeVtx(int vtx)
{

    while (graph[vtx].size() != 0)
    {
        Edge e = graph[vtx].back();
        removeEdge(vtx, e.v);
    }
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

// Traversal -- DFS questions =====================================================================================

bool hasPath(int src, int dest, vector<bool> &vis)
{
    if (src == dest)
    {
        return true;
    }

    bool res = false;
    // Normal DFS algo
    //1. mark the current node (src)
    vis[src] = true;
    //2. visit all the unmarked neighbours.
    for (Edge e : graph[src])
    {
        if (!vis[e.v])
        {
            //3. call for all the un. neighbours

            res = res || hasPath(e.v, dest, vis);
        }
    }
    return res;
}



int allPath(int src, int dest, vector<bool> &vis, int weight, string ans)
{
    if (src == dest)
    {
        cout << ans << src << "@" << weight << endl;
        return 1;
    }
    // DFS ALGO
    vis[src] = true;
    int count = 0;
    for (Edge e : graph[src])
    {
        if (!vis[e.v])
        {
            count += allPath(e.v, dest, vis, weight + e.w, ans+to_string(src));
        }
    }
    vis[src] = false;
    return count;
}


class allSolutionsPair{
    public:
        int lightW = 1e7;
        string lightPath = "";
        int heavyW = 0;
        string heavyPath = "";

        int ceil = 1e7;
        string ceilPath = "";
        int floor = 0;
        string floorPath = "";

}; 


void allSolution(int src, int dest,vector<bool>& vis, int data, allSolutionsPair& pair,int weight,string path){
    if(src == dest){
        if(weight < pair.lightW){
            pair.lightW = weight;
            pair.lightPath = path+to_string(dest);
        }
        if(weight > pair.heavyW){
            pair.heavyW = weight;
            pair.heavyPath = path+to_string(dest);
        }
        if(weight < data && weight > pair.floor){
            pair.floor = weight;
            pair.floorPath = path+to_string(dest);
        }
        if(weight > data && weight < pair.ceil){
            pair.ceil = weight;
            pair.ceilPath = path+to_string(dest);
        }
    }



    //DFS Algo
    vis[src] = true;
    for(Edge e:graph[src]){
        if(!vis[e.v]){
            allSolution(e.v, dest,vis,data,pair,weight + e.w, path+to_string(src));
        }
    }
    vis[src] = false;

}



void constructGraph()
{
    // for(int i=0;i<N;i++){
    //     vector<Edge*> a;
    //     graph.push_back(a);
    // }
    addEdge(graph, 0, 1, 10);
    addEdge(graph, 0, 3, 10);
    addEdge(graph, 1, 2, 10);
    addEdge(graph, 2, 3, 40);
    addEdge(graph, 3, 4, 2);
    addEdge(graph, 4, 5, 2);
    addEdge(graph, 4, 6, 3);
    addEdge(graph, 5, 6, 8);

    // addEdge(graph, 2, 5, 2);

    display(graph);
    cout << endl;
}

void set1()
{

    vector<bool> vis(N, false);

    //removeEdge(3, 4);

    // bool ans = hasPath(0, 6, vis);

    int ans = allPath(0,6,vis,0,"");

    allSolutionsPair pair = allSolutionsPair();
    allSolution(0,6,vis,30,pair,0,"");
    cout << endl;
    cout <<  pair.lightPath + "@" << pair.lightW << endl ; 
    cout <<  pair.heavyPath + "@" << pair.heavyW <<endl; 
    cout <<  pair.floorPath + "@" << pair.floor <<endl; 
    cout <<  pair.ceilPath + "@" << pair.ceil <<endl; 
    
    //cout << ans << endl;
}

void solve()
{
    constructGraph();
    set1();
}

int main()
{
    solve();
    return 0;
}