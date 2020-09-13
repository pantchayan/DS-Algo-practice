// DFS is more of an approach than just an algorithm
// 1 - Recursion technique
// 2 - GCC is a major application along with noob topological sort

#include <iostream>
#include <vector>

using namespace std;

void dfs(int src,vector<int> vis,vector<vector<int>> graph){

    //mark the src
    vis[src] = true;

    //for all unvisited neighbours -- call dfs

    for(int v:graph[src]){
        if(!vis[v]){
            dfs(v,vis,graph);
        }
    }

}
