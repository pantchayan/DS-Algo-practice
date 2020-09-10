// Kruskal's Algorithm is used to find M.S.T. (Minimum Spanning Tree) of a graph.
// It is source independent
// Union-Find approach

#include <iostream>
#include <vector>

using namespace std;

int findPar(int vtx,vector<int> &par){
    if(par[vtx]==vtx) return vtx;
    return par[vtx] = findPar(par[vtx],par);
}

int KruskalsAlgo(int N, vector<vector<int>> graph){
    // {u,v,w} === > form of internal vector 
    int cost = 0;
    vector<int> par;

    for(int i=0;i<N;i++){
        par.push_back(i);
    }

    for(vector<int> edge : graph){
        //{u,v,w}
        int p1 = findPar(edge[0],par); // u parent
        int p2 = findPar(edge[1],par); // v parent

        if(p1!=p2){
            par[p2] = p1;
            cost = cost + edge[2]; 
        }
    }

    return cost;
}