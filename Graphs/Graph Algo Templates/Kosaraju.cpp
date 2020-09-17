// Kosaraju Algorithm is used to determine the number of Strongly Connected Components(SCC) in a graph.

// 3 steps :
// Topological sort on normal graph (DFS + backtrack)
// Compute (G)**-1 .. -- > inverse the edges of the graph
// Run DFS along the topological sort and count SCC. (similar to GCC)



#include <iostream>
#include <vector>

using namespace std;




