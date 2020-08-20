#include <iostream>
#include <vector>
#include <string>
#include <queue>
using namespace std;

// Leetcode 130 =================================================================================================================================

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