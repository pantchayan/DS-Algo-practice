#include <iostream>
#include <vector>
#include <string>
#include <queue>
using namespace std;

#define lli long long int
// Leetcode 130  Surrounded Regions =================================================================================================================================

//DFS helper function for the program
void surroundedRegDFS(int r, int c, int tr, int tc, vector<vector<char>> &board)
{
    if (board[r][c] != 'O')
    {
        return;
    }

    board[r][c] = '#';

    if (c + 1 < tc)
        surroundedRegDFS(r, c + 1, tr, tc, board);
    if (r + 1 < tr)
        surroundedRegDFS(r + 1, c, tr, tc, board);
    if (c - 1 >= 0)
        surroundedRegDFS(r, c - 1, tr, tc, board);
    if (r - 1 >= 0)
        surroundedRegDFS(r - 1, c, tr, tc, board);
}

void surroundedRegions(vector<vector<char>> &board)
{

    int tr = board.size();
    int tc = board[0].size();

    for (vector<char> v : board)
    {
        for (char c : v)
            cout << c << " ";

        cout << endl;
    }

    for (int i = 0; i < tr; i++)
    {
        if (board[i][0] == 'O')
            surroundedRegDFS(i, 0, tr, tc, board);

        if (board[i][tc - 1] == 'O')
            surroundedRegDFS(i, tc - 1, tr, tc, board);
    }

    for (int i = 0; i < tc; i++)
    {
        if (board[0][i] == 'O')
            surroundedRegDFS(0, i, tr, tc, board);

        if (board[tr - 1][i] == 'O')
            surroundedRegDFS(tr - 1, i, tr, tc, board);
    }

    for (int i = 0; i < tr; i++)
    {
        for (int j = 0; j < tc; j++)
        {
            if (board[i][j] == 'O')
                board[i][j] = 'X';

            if (board[i][j] == '#')
                board[i][j] = 'O';
        }
    }

    for (vector<char> v : board)
    {
        for (char c : v)
            cout << c << " ";

        cout << endl;
    }
}

// Leetcode 200 ======================================================================================
// Leetcode 695 ======================================================================================
// Leetcode 463 ======================================================================================

// Number of distinct Islands ============================================================================================================

int numberOfDistinctIslands(vector<vector<int>> &grid)
{

    int n = grid.size();
    int m = grid[0].size();

    for (int i = 0; i < n; i++)
        for (int j = 0; j < m; j++)
            if (grid[i][j] == 1)
                string island = dfs_NumOfDistinctIslands(i, j, n, m, grid);
}

string dfs_NumOfDistinctIslands(int r, int c, int n, int m, vector<vector<int>> &grid)
{
}

// BFS =====================================================================================================================================

// Leetcode 1091 Shortest Path in Binary Matrix ===========================================================================================

int shortestPathBinaryMatrix(vector<vector<int>> &grid)
{
    if (grid.size() == 0)
    {
        return 0;
    }
    if (grid[0][0] == 1)
    {
        return -1;
    }

    queue<pair<int, int>> que;

    int N = grid.size();

    int level = 1;

    que.push({0, 0});

    grid[0][0] = 1;

    int dir[8][2] = {{-1, -1}, {-1, 0}, {0, -1}, {-1, 1}, {1, -1}, {0, 1}, {1, 0}, {1, 1}};

    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            pair rvtx = que.front();
            que.pop();
            int i = rvtx.first;
            int j = rvtx.second;
            if (i == N - 1 && j == N - 1)
            {
                return level;
            }
            for (int k = 0; k < 8; k++)
            {

                int l = i + dir[k][0];
                int m = j + dir[k][1];

                if (l >= 0 && m >= 0 && l < N && m < N)
                {
                    if (grid[l][m] == 0)
                    {
                        que.push({l, m});
                        grid[l][m] = 1;
                    }
                }
            }
        }
        level++;
    }

    return -1;
}

// Leetcode 286 (Lintcode) Walls and gates in room 2D ======================================================================================================================

// Leetcode 994 Rotten Oranges =======================================================================================================================
int orangesRotting(vector<vector<int>> &grid)
{

    int N = grid.size();
    int M = grid[0].size();

    if (N == 0)
    {
        return 0;
    }
    if (N == 1 && M == 1)
    {
        if (grid[0][0] == 0)
        {
            return 0;
        }
    }

    int level = 0;

    queue<int> que;

    int dir[4][2] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < M; j++)
        {
            if (grid[i][j] == 2)
            {
                que.push(i * M + j);
            }
        }
    }

    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {

            int vtx = que.front();
            que.pop();

            int i = vtx / M;
            int j = vtx % M;

            for (int k = 0; k < 4; k++)
            {
                int r = i + dir[k][0];
                int c = j + dir[k][1];

                if (r >= 0 && c >= 0 && r < N && c < M)
                {
                    if (grid[r][c] == 1)
                    {
                        grid[r][c] = 2;

                        que.push(r * M + c);
                    }
                }
            }
        }
        level++;
    }

    for (vector<int> arr : grid)
    {
        for (int o : arr)
        {
            if (o == 1)
            {
                return -1;
            }
        }
    }

    return level - 1;
}
// Leetcode 684 (Union Find) Redundant connection =========================================================================================

vector<int> findRedundantConnection(vector<vector<int>> &edges)
{

    int N = edges.size();
    vector<int> size(N + 1, 1);
    vector<int> par(N + 1, 0);

    vector<int> ans;
    for (int i = 1; i <= N; i++)
    {
        par[i] = i;
        //size[i] = 1;
    }

    for (vector<int> pair : edges)
    {
        cout << pair[0] << " " << pair[1];
        int l1 = findPar(pair[0], par);
        int l2 = findPar(pair[1], par);

        if (l1 == l2)
        {
            return pair;
        }
        else
        {
            mergeSet(l1, l2, size, par);
        }
    }

    return ans;
}

int findPar(int vtx, vector<int> &par)
{
    if (par[vtx] == vtx)
        return vtx;
    vtx = findPar(par[vtx], par);
    return vtx;
}

void mergeSet(int l1, int l2, vector<int> &size, vector<int> &par)
{

    if (size[l1] < size[l2])
    {
        par[l1] = l2;
        size[l2] += size[l1];
    }
    else
    {
        par[l2] = l1;
        size[l1] += size[l2];
    }
}
// Leetcode 547 UnionFind for GCC in Matrix -- Friend circles ==========================================================================
int findCircleNum(vector<vector<int>> &M)
{
    int n = M.size();

    vector<int> par;
    for (int i = 0; i < n; i++)
    {
        par.push_back(i);
    }

    int count = 0;
    for (int i = 0; i < n; i++)
    {
        for (int j = i; j < n; j++)
        {
            if (i != j && M[i][j] == 1)
            {
                int l1 = findPar(i, par);
                int l2 = findPar(j, par);
                if (l1 == l2)
                {
                    continue;
                }
                else
                {
                    count++;
                    par[l1] = l2;
                }
            }
        }
    }

    return n - count;
}

// Leetcode 200 - numsIsland using UNION FIND (Superior) ================================================================================

int numIslands(vector<vector<char>> &grid)
{
    // Union Find method

    if (grid.size() == 0)
        return 0;

    int N = grid.size();
    int M = grid[0].size();

    vector<int> par;

    for (int i = 0; i < N * M; i++)
    {
        par.push_back(i);
    }

    int numOfOnes = 0;
    int mergeCount = 0;
    int dir[2][2] = {{1, 0}, {0, 1}};
    int count = 0;
    for (int i = 0; i < N * M; i++)
    {
        int r = i / M;
        int c = i % M;

        if (grid[r][c] == '1')
        {
            numOfOnes++;
            for (int k = 0; k < 2; k++)
            {
                int x = r + dir[k][0];
                int y = c + dir[k][1];

                if (x >= 0 && y >= 0 && x < N && y < M && grid[x][y] == '1')
                {
                    int j = x * M + y;
                    // normal findPar method
                    int l1 = findPar(i, par);
                    int l2 = findPar(j, par);

                    if (l1 != l2)
                    {
                        par[l2] = l1;
                        mergeCount++;
                    }
                }
            }
        }
    }

    // for(int i=0;i<par.size();i++){
    //     if(par[i]==i && grid[i/M][i%M]=='1'){
    //         count++;
    //     }
    // }

    return numOfOnes - mergeCount;
}


// leetcode 839 ======================================================================================================================


    vector<int> par;
    bool isSimilar(string &A, string &B){
        
        //if(A==B) return true;
        int count = 0;
        for(int i=0;i<A.size();i++){
            if(A[i]!=B[i] && ++count>2)
                return false;
        }
        return true;
    }
    
    int numSimilarGroups(vector<string>& A) {
        int n = A.size();
        
        for(int i=0;i<n;i++){
            par.push_back(i);
        }
        
        int groups = n;
        
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(isSimilar(A[i],A[j])){
                    int l1 = findPar(i,par);
                    int l2 = findPar(j,par);
                    if(l1!=l2){
                        par[l1] = l2;
                        groups--;
                    }
                }
            }
        }
        return groups;
    }

// Leetcode  - Water Distribution in a village ==========================================================================================


int findPar(int vtx,vector<int>& par){
    if(par[vtx] == vtx) return vtx;

    return par[vtx] = findPar(par[vtx],par);
}

int waterDistribution(int n,vector<int>& wells,vector<vector<int>>& pipes){
    //pipes -- > (u,v,w) 

    for(int i=0;i<n;i++){
        vector<int> newEdge = {0,i,wells[i]};
        pipes.push_back(newEdge);
    }

    // sorting the 2d array pipes w.r.t. weights coloumn =========================================================
    std::sort(pipes.begin(),
          pipes.end(),
          [] (const std::vector<double> &a, const std::vector<double> &b)
          {
              return a[2] < b[2];
          });

    vector<int> par;

    for(int i=0;i<n;i++){
        par.push_back(i);
    }

    int cost = 0;
    for(vector<int> edge:pipes){
        int p1 = findPar(edge[0],par);
        int p2 = findPar(edge[1],par);
         
        if(p1!=p2){
            par[p2] = p1;
            cost = cost + edge[2];
        }
    }

    return cost;
}


// Mr. President HackerEarth ===========================================================================================================
// https://www.hackerearth.com/practice/algorithms/graphs/minimum-spanning-tree/practice-problems/algorithm/mr-president/submissions/


int mrPresident()
{
    lli n, m, k;
    cin >> n >> m >> k;

    vector<vector<int>> graph, kruskalGraph;
    while (m--)
    {
        int u, v, w;
        cin >> u >> v >> w;
        vector<int> ar = {u, v, w};
        graph.push_back(ar);
    }

    sort(graph.begin(), graph.end(), [](vector<int> &a, vector<int> &b) {
        return a[2] < b[2];
    });

    for (int i = 0; i <= n; i++)
        par.push_back(i);

    lli MSTCost = 0;
    for (vector<int> g : graph)
    {
        int p1 = findPar(g[0],par);
        int p2 = findPar(g[1],par);
        if (p1 != p2)
        {
            par[p1] = p2;
            kruskalGraph.push_back(g);
            MSTCost += g[2];
        }
    }

    int componentCount = 0;
    for (int i = 1; i <= n; i++)
        if (par[i] == i && ++componentCount > 1)
            return -1;

    int superRoad = 0;
    for (int i = kruskalGraph.size() - 1; i >= 0; i--)
    {
        if (MSTCost <= k)
            break;
        MSTCost = MSTCost - kruskalGraph[i][2] + 1;
        superRoad++;
    }

    return MSTCost <= k ? superRoad : -1;
}

auto SpeedUp = []() {
    std::ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    return 0;
}();


//=======================================================================================================================================

void setDFS()
{
}

void solve()
{
    setDFS();
}

int main()
{
    solve();
    return 0;
}


//question link: https://leetcode.com/problems/minimize-malware-spread/discuss/614031/C++-:-Union-Find-(pepcoding.com)-reframe-the-question-on-"CORONA"-with-relatable-explanation

