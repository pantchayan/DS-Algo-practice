// Kosaraju Algorithm is used to determine the number of Strongly Connected Components(SCC) in a graph.

// 3 steps :
// Topological sort on normal graph (DFS + backtrack)
// Compute (G)**-1 .. -- > inverse the edges of the graph
// Run DFS along the topological sort and count SCC. (similar to GCC)



#include <iostream>
#include <vector>

using namespace std;


void topo(int src,vector<bool> vis,vector<int> ans,vector<vector<int>> graph){
    vis[src] = true;
    for(int v: graph[src]){
        if(graph[src][v]==1 && !vis[v]){
            topo(v,vis,ans,graph);
        }
    }
    ans.push_back(src);
}

void dfs(int src,vector<bool> vis,vector<vector<int>> graph){
    vis[src] = true;
    for(int v: graph[src]){
        if(graph[src][v]==1 && !vis[v]){
            dfs(v,vis,graph);
        }
    }
}

int SCC(vector<vector<int>> &graph){
    // step 1: Finding topological sort

    int N = graph.size();
    vector<bool> vis(N,false);
    vector<int> ans;

    for(int i=0;i<N;i++){
            if(!vis[i]){
                topo(i,vis,ans,graph);
            }
    }

    // step 2: Inversing the graph

    for(int i=0;i<N;i++){
        for(int j=0;j<N;j++)
        if(graph[i][j]==1){
            graph[j][i] = 1;
            graph[i][j] = 0;
        }
    }


    // step 3: Calling dfs on Topo sort (ans)
    vector<bool> vis(N,false);
    int count = 0;
    for(int i=0;i<ans.size();i++){
        if(!vis[i]){
            count++;
            dfs(i,vis,graph);
        }
    } 

    return count;

}

