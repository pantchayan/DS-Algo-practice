// BFS is more of a technique and approach than just an algorithm 
// Base for many different algorithms .. including Dijkstra and Prims
// Iterative unlike dfs which is recursive
// Many types of BFS .. MAJOR ONES being :
// -- simple 1 while loop
// -- 2 while loops (Level is important)

#include <iostream>
#include <vector>
#include <queue>

using namespace std;


void BFS(int src,vector<vector<int>> graph){

    queue<int> que;

    que.push(src);
    int level = 0;

    vector<bool> vis(graph.size(),false);

    while(que.size()!=0){
        int size = que.size();
        while(size-->0){
            int vtx = que.front();
            que.pop();
            // cycle check is placed here
            if(vis[vtx]) continue;

            // vtx is marked here is cycle detection is important
            vis[vtx] = true;

            // for all unvisited neighbours push in que
            for(int v:graph[vtx]){
                if(!vis[v]) que.push(v);
            }   
        }
        level++;
    }
    
}