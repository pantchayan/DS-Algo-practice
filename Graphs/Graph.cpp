#include <iostream>
#include <vector>
#include <string>
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

// Traversal -- DFS questions =============================================================================================

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
            count += allPath(e.v, dest, vis, weight + e.w, ans + to_string(src));
        }
    }
    vis[src] = false;
    return count;
}

class allSolutionsPair
{
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

void allSolution(int src, int dest, vector<bool> &vis, int data, allSolutionsPair &pair, int weight, string path)
{
    if (src == dest)
    {
        if (weight < pair.lightW)
        {
            pair.lightW = weight;
            pair.lightPath = path + to_string(dest);
        }
        if (weight > pair.heavyW)
        {
            pair.heavyW = weight;
            pair.heavyPath = path + to_string(dest);
        }
        if (weight < data && weight > pair.floor)
        {
            pair.floor = weight;
            pair.floorPath = path + to_string(dest);
        }
        if (weight > data && weight < pair.ceil)
        {
            pair.ceil = weight;
            pair.ceilPath = path + to_string(dest);
        }
    }

    //DFS Algo
    vis[src] = true;
    for (Edge e : graph[src])
    {
        if (!vis[e.v])
        {
            allSolution(e.v, dest, vis, data, pair, weight + e.w, path + to_string(src));
        }
    }
    vis[src] = false;
}

// Hamiltonian path ===============================================================================================================

bool hasHamiltonianPath(int src, int curr, string path, vector<bool> &vis, int count)
{
    if (count == 1)
    {
        cout << path << endl;
        return true;
    }

    vis[curr] = true;
    bool res;
    for (Edge e : graph[curr])
    {
        if (!vis[e.v])
        {

            res = res || hasHamiltonianPath(src, e.v, path + to_string(e.v), vis, count - 1);
        }
    }

    vis[curr] = false;
    return res;
}

int allHamiltonianPaths(int src, int curr, string path, vector<bool> &vis, int count)
{
    if (count == 1)
    {
        cout << path << endl;
        return 1;
    }

    int num = 0;
    vis[curr] = true;
    for (Edge e : graph[curr])
    {
        if (!vis[e.v])
        {
            count--;
            num += allHamiltonianPaths(src, e.v, path + to_string(e.v), vis, count);
            count++;
        }
    }
    vis[curr] = false;
    return num;
}

int allHamiltonianCycles(int src, int curr, string path, vector<bool> &vis, int count)
{
    if (count == 1)
    {

        for (Edge e : graph[curr])
        {
            if (e.v == src)
            {
                cout << path + to_string(src) << endl;
                return 1;
            }
        }
    }
    int num = 0;
    vis[curr] = true;
    for (Edge e : graph[curr])
    {
        if (!vis[e.v])
        {
            count--;
            num += allHamiltonianCycles(src, e.v, path + to_string(e.v), vis, count);
            count++;
        }
    }
    vis[curr] = false;
    return num;
}

// GCC (distinct components in a graph) ==================================================================================================

int dfsGCC(int src, vector<bool> &vis, vector<bool> &final)
{
    if (final[src] == false)
    {
        final[src] = true;
    }

    vis[src] = true;
    int count = 0;
    for (Edge e : graph[src])
    {
        if (!vis[e.v])
        {
            count += dfsGCC(e.v, vis, final);
        }
    }
    //vis[src] = false;
    return count + 1;
}

int GCC()
{
    vector<bool> final(N, false);
    vector<bool> vis(N, false);
    int count = 0;
    int maxSize = 0;
    for (int i = 0; i < N; i++)
    {
        if (!final[i])
        {
            count++;
            int size = dfsGCC(i, vis, final);
            if (maxSize < size)
            {
                maxSize = size;
            }

            //maxSize = max(maxSize,size);
        }
    }
    cout << "Max size of a component : " << maxSize << endl;
    return count;
}
// BFS =============================================================================================================================

void BFS(int src, int des)
{

    //Shortest path
    int cycle = 0;
    queue<pair<int, string>> que;

    int level = 0;

    que.push({src, to_string(src) + ""});
    
    //delimeter method to keep account of level
    que.push({-1,"null"});
        //que.push(nullptr);

    vector<bool> vis(N, false);
    while (que.size() != 1)
    {
        pair<int, string> vtx = que.front();
        que.pop();

        if(vtx.second == "null"){
            level++;
            que.push({-1,"null"});
            continue;
        }

        if (vis[vtx.first])
        {
            cycle++;
            cout << vtx.first << "@" << level << endl; 
            continue;
        }

        if (vtx.first == des)
            cout << "Shortest path to " << des << " : " << vtx.second << "@" <<  level << endl;
        
        vis[vtx.first] = true;

        for (Edge e : graph[vtx.first])
        {
            if (!vis[e.v])
                que.push({e.v, vtx.second + to_string(e.v)});
        }
    }

    cout << "Number of cycles : " << cycle << endl;
    cout << "Number of levels : " << level << endl;
}

// =================================================================================================================================






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

    // for GCC ::
    // component 2
    // addEdge(graph, 7, 8, 10);
    // addEdge(graph, 7, 10, 10);
    // addEdge(graph, 8, 9, 10);
    // addEdge(graph, 9, 10, 10);
    // //component 3
    // addEdge(graph, 11, 12, 10);
    // addEdge(graph, 12, 15, 10);
    // addEdge(graph, 12, 14, 10);
    // addEdge(graph, 12, 13, 10);

    //addEdge(graph, 2, 5, 10);
    // addEdge(graph, 2, 5, 2);

    display(graph);
    cout << endl;
}

void set1()
{

    //vector<bool> vis(N, false);

    //removeEdge(3, 4);

    // bool ans = hasPath(0, 6, vis);

    // int ans = allPath(0,6,vis,0,"");

    // allSolutionsPair pair = allSolutionsPair();
    // allSolution(0,6,vis,30,pair,0,"");
    // cout << endl;
    // cout <<  pair.lightPath + "@" << pair.lightW << endl ;
    // cout <<  pair.heavyPath + "@" << pair.heavyW <<endl;
    // cout <<  pair.floorPath + "@" << pair.floor <<endl;
    // cout <<  pair.ceilPath + "@" << pair.ceil <<endl;

    //cout << ans << endl;
}

void set2()
{

    // Hamiltonian path

    vector<bool> vis(N, false);

    hasHamiltonianPath(2, 2, "2", vis, N);

    cout << " =============================== " << endl;
    int num = allHamiltonianPaths(2, 2, "2", vis, N);

    cout << "Number of Hamilton paths : " << num << endl;

    int num2 = allHamiltonianCycles(2, 2, "2", vis, N);
    cout << "Number of Hamilton cycles : " << num2 << endl;

    int n = GCC();
    cout << "Total number of components : " << n << endl;
}

void set3()
{
    BFS(0, 6);
}

void solve()
{
    constructGraph();
    //set1();
    //set2();

    set3();
}

int main()
{
    solve();
    return 0;
}