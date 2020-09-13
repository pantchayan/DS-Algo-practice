// Topological sort gives relational sort of nodes in a weighted graph
// There are different approaches of performing topological sort:
// 1. DFS -- can't give proper answer when deadlock present(cycle). always gives an answer though.
// 2. Kahn's Algorithm -- BFS approach, works well as it detects deadlock and returns no answer when found.


#include <iostream>
#include <vector>
#include <queue>

using namespace std;

void TopologicalDFS(int src,vector<int> vis,vector<vector<int>> graph,vector<int> sort){
    // simple dfs algo along with answer generation @ backtracking
    vis[src] = true;
    for(int v:graph[src]){
        if(!vis[v]) TopologicalDFS(v,vis,graph,sort);
    }

    // backtracking
    sort.push_back(src);
}