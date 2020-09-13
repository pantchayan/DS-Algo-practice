// Kahn's Algorithm :
// used for generating proper topological sort.. along with cycle detection in weighted graphs
// BFS approach with indegree calculations and no visited array


#include <iostream>
#include <vector>
#include <queue>

using namespace std;

void Kahns(int src,vector<vector<int>> graph){
    // let's assume that the graph is a 2d matrix with 1 placed in relation and 0 in no relation

    vector<int> indegree(graph.size(),0);
    // to calculate the indegree, we travel through each vertex

    for(int i=0;i<graph.size();i++){
        for(int j=0;j<graph.size();j++){
            if (graph[i][j]==1)
            {
                indegree[j]++;
            }
        }
    }   

    queue<int> que;
    // now we push all the elements with indegree == 0 in the queue
    for(int i=0;i<indegree.size();i++){
        if(indegree[i]==0) que.push(i);
    }

    // No need for visited array
   // vector<bool> vis(graph.size(),false);

    vector<int> ans;

    while(que.size()!=0){
        int size = 0;
        while(size-->0){
            int vtx = que.front();
            que.pop();
            
            ans.push_back(vtx);
            for(int j=0;j<graph.size();j++){
                if(graph[vtx][j]==1){
                    indegree[j]--;
                    if(indegree[j]==0) que.push(j);
                }
            }
        }
    }
     
    if(ans.size()==graph.size()) {
        cout << "Topological order is :" << endl;
        for(int v:ans){
            cout<< v <<"--> ";
        }
    } 
    else{
        cout<< "Cycle/DeadLock found" << endl;
    }


}