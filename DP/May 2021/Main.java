public class Main {
       // Recursive
       public static int fibonacciR(int n) {
              if (n == 1 || n == 0) {
                     return n;
              }
              System.out.println("Evaluating : " + n);
              return fibonacciR(n - 1) + fibonacciR(n - 2);
       }

       // Memoization
       public static int fiboMemo(int n, int[] qb) {
              if (n == 0 || n == 1)
                     return n;
              if (qb[n] != 0)
                     return qb[n];

              System.out.println("Evaluating : " + n);
              int a = fiboMemo(n - 1, qb) + fiboMemo(n - 2, qb);

              qb[n] = a;
              return a;

       }

       // Recursive
       public static int climbStairsRecur(int n, String psf) {
              if (n < 0)
                     return 0;
              if (n == 0) {
                     // System.out.println(psf);
                     return 1;
              }

              System.out.println("Evaluating : " + n);

              int a = climbStairsRecur(n - 1, psf + 1);
              int b = climbStairsRecur(n - 2, psf + 2);
              int c = climbStairsRecur(n - 3, psf + 3);

              return a + b + c;

       }

       // Memoization
       public static int climbStairsMemo(int n, int[] qb) {
              if (n < 0)
                     return 0;
              if (n == 0) {
                     return 1;
              }

              if (qb[n] != 0) {
                     return qb[n];
              }

              System.out.println("Evaluating : " + n);

              int a = climbStairsMemo(n - 1, qb);
              int b = climbStairsMemo(n - 2, qb);
              int c = climbStairsMemo(n - 3, qb);

              qb[n] = a + b + c;
              return a + b + c;

       }

       // Tabulation
       public static int climbStairsTab(int n) {
              // Assign storage
              int[] dp = new int[n + 1];
              // base case
              dp[0] = 1;

              for (int i = 1; i <= n; i++) {
                     if (i == 1) {
                            dp[i] = dp[i - 1];
                     } else if (i == 2) {
                            dp[i] = dp[i - 1] + dp[i - 2];
                     } else {
                            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
                     }
              }

              return dp[n];
       }

       // bottom-up recusrion
       public static int climbStairsVariable(int currStep, int n, String psf, int[] jumps) {
              if (currStep > n) {
                     return 0;
              }
              if (currStep == n) {
                     System.out.println(psf + currStep);
                     return 1;
              }

              System.out.println("Evaluating : " + currStep);
              int jump = jumps[currStep];
              int smallAns = 0;
              for (int i = 1; i <= jump; i++) {
                     smallAns += climbStairsVariable(currStep + i, n, psf + currStep + ",", jumps);
              }

              return smallAns;
       }

       public static void printArr(int[] arr) {
              for (int a : arr) {
                     System.out.print(a + " ");
              }
              System.out.println();
       }

       public static void print2DArr(int[][] arr) {
              for (int[] a : arr) {
                     for (int x : a) {
                            System.out.print(x + " ");
                     }
                     System.out.println();
              }
       }

       // Memoization
       static int[] memoArr;

       public static int climbStairsVariableMemo(int currStep, int n, int[] jumps, int[] qb) {
              if (currStep > n) {
                     return 0;
              }
              if (currStep == n) {
                     qb[currStep] = 1;
                     return 1;
              }

              if (qb[currStep] > 0)
                     return qb[currStep];

              // System.out.println("Evaluating : " + currStep);
              int jump = jumps[currStep];
              int smallAns = 0;
              for (int i = 1; i <= jump; i++) {
                     smallAns += climbStairsVariableMemo(currStep + i, n, jumps, qb);
              }

              qb[currStep] = smallAns;

              memoArr = qb;
              return smallAns;
       }

       // Tabular
       public static int climbStairsVariableTab(int n, int[] jumps) {
              // Assign storage
              int[] dp = new int[n + 1];
              dp[n] = 1;

              for (int i = n - 1; i >= 0; i--) {
                     for (int jump = 1; jump <= jumps[i]; jump++) {
                            if (i + jump <= n) {
                                   dp[i] += dp[i + jump];
                            }
                     }
              }

              printArr(dp);
              return dp[0];
       }

       // bottom-up recusrion
       public static int climbStairsVariableMin(int currStep, int n, int[] jumps) {
              if (currStep > n) {
                     return n + 1;
              }

              if (currStep == n) {
                     return 0;
              }

              int smallAns = n + 1;

              for (int i = 1; i <= jumps[currStep]; i++) {
                     smallAns = Math.min(smallAns, climbStairsVariableMin(currStep + i, n, jumps));
              }

              return smallAns + 1;
       }

       // memoization
       public static int climbStairsVariableMemoMin(int currStep, int n, int[] jumps, int[] qb) {
              if (currStep > n) {
                     return n + 1;
              }

              if (currStep == n) {
                     return 0;
              }

              if (qb[currStep] > 0)
                     return qb[currStep];

              int smallAns = n + 1;

              for (int i = 1; i <= jumps[currStep]; i++) {
                     smallAns = Math.min(smallAns, climbStairsVariableMemoMin(currStep + i, n, jumps, qb));
              }

              qb[currStep] = smallAns + 1;
              memoArr = qb;
              return smallAns + 1;
       }

       // Tabular
       public static int climbStairsVariableMinTab(int n, int[] jumps) {
              // Assign Storage
              int[] dp = new int[n + 1];
              dp[n] = 0;

              // Direction
              for (int i = n - 1; i >= 0; i--) {
                     // Calculate Value
                     int smallAns = n + 1;
                     for (int jump = 1; jump <= jumps[i]; jump++) {
                            if (i + jump <= n) {
                                   smallAns = Math.min(smallAns, dp[i + jump]);
                            }
                     }
                     dp[i] = smallAns + 1;
              }
              printArr(dp);
              return dp[0];
       }

       public static void Set1() {
              // Fibonacci
              // System.out.println(fibonacciR(10));
              // System.out.println(fiboMemo(10, new int[11]));

              // Climb stairs
              int n = 10;
              int[] jumps = { 3, 3, 0, 2, 1, 2, 4, 2, 0, 0 };
              // System.out.println(climbStairsRecur(n, ""));
              // System.out.println();
              // System.out.println(climbStairsMemo(n, new int[n + 1]));
              // System.out.println();
              // System.out.println(climbStairsTab(n));

              // Climb stairs with variable jumps
              // System.out.println(climbStairsVariable(0, n, "", jumps));
              // System.out.println(climbStairsVariableMemo(0, n, jumps, new int[n+1]));
              // printArr(memoArr);

              // System.out.println(climbStairsVariableTab(n, jumps));

              // MINIMUM Climb stairs with variable jumps
              printArr(jumps);
              System.out.println(climbStairsVariableMin(0, n, jumps));
              System.out.println(climbStairsVariableMemoMin(0, n, jumps, new int[n + 1]));
              printArr(memoArr);
              System.out.println(climbStairsVariableMinTab(n, jumps));
       }

       public static int countAllPaths(int r, int c, int[][] maze) {
              if (r == maze.length - 1 && c == maze.length - 1) {
                     return 1;
              }
              int count = 0;

              if (c + 1 < maze.length) {
                     count += countAllPaths(r, c + 1, maze);
              }

              if (r + 1 < maze.length) {
                     count += countAllPaths(r + 1, c, maze);
              }

              return count;
       }

       // Recursive
       public static int getMinPathRec(int r, int c, int[][] maze) {
              if (r == maze.length - 1 && c == maze[0].length - 1) {
                     return maze[r][c];
              }
              int cost = Integer.MAX_VALUE;

              if (c + 1 < maze[0].length) {
                     cost = Math.min(cost, getMinPathRec(r, c + 1, maze));
              }

              if (r + 1 < maze.length) {
                     cost = Math.min(cost, getMinPathRec(r + 1, c, maze));
              }

              return cost + maze[r][c];
       }

       static int[][] memoMatrix;

       public static int getMinPathMemo(int r, int c, int[][] maze, int[][] qb) {
              if (r == maze.length - 1 && c == maze[0].length - 1) {
                     qb[r][c] = maze[r][c];
                     return maze[r][c];
              }

              if (qb[r][c] != 0)
                     return qb[r][c];

              int cost = Integer.MAX_VALUE;

              if (c + 1 < maze[0].length) {
                     cost = Math.min(cost, getMinPathMemo(r, c + 1, maze, qb));
              }

              if (r + 1 < maze.length) {
                     cost = Math.min(cost, getMinPathMemo(r + 1, c, maze, qb));
              }

              qb[r][c] = cost + maze[r][c];
              memoMatrix = qb;
              return cost + maze[r][c];
       }

       public static int getMinPathTab(int[][] maze) {
              int n = maze.length;
              int m = maze[0].length;
              // Assign Storage
              int[][] dp = new int[n][m];
              dp[n - 1][m - 1] = maze[n - 1][m - 1];

              // Direction
              for (int r = n - 1; r >= 0; r--) {
                     for (int c = m - 1; c >= 0; c--) {
                            if (r == n - 1 && c == m - 1)
                                   continue;
                            // Value - Formula
                            int cost = Integer.MAX_VALUE;
                            if (r + 1 < n) {
                                   cost = Math.min(cost, dp[r + 1][c]);
                            }

                            if (c + 1 < m) {
                                   cost = Math.min(cost, dp[r][c + 1]);
                            }

                            dp[r][c] = cost + maze[r][c];
                     }
              }

              print2DArr(dp);
              return dp[0][0];
       }

       static int recurSteps = 0;
       public static int getMaxGoldRecur(int[][] mine) {
              int max = Integer.MIN_VALUE;
              for (int i = 0; i < mine.length; i++) {
                     int rowMax = dfsHelper(i, 0, mine);
                     max = Math.max(rowMax, max);
              }
              System.out.println("Recursion evaluations : "+recurSteps);
              return max;
       }
       
       public static int dfsHelper(int r, int c, int[][] mine) {
              if(c==mine[0].length-1){
                     // right wall
                     return mine[r][c];
              }

              recurSteps++;
              int gold = Integer.MIN_VALUE;
              // right-up
              if (c + 1 < mine[0].length && r - 1 >= 0) {
                     gold = Math.max(gold, dfsHelper(r - 1, c + 1, mine));
              }

              // right
              if (c + 1 < mine[0].length) {
                     gold = Math.max(gold, dfsHelper(r, c + 1, mine));
              }

              // right-down
              if (c + 1 < mine[0].length && r + 1 < mine.length) {
                     gold = Math.max(gold, dfsHelper(r + 1, c + 1, mine));
              }

              return gold + mine[r][c];

       }

       static int memoSteps = 0;
       public static int getMaxGoldMemo(int[][] mine) {
              int max = Integer.MIN_VALUE;
              int[][] qb = new int[mine.length][mine[0].length];

              for (int i = 0; i < mine.length; i++) {
                     int rowMax = dfsHelperMemo(i, 0, mine, qb);
                     max = Math.max(rowMax, max);
              }

              print2DArr(memoMatrix);

              System.out.println("Memoization evaluations : " + memoSteps);
              return max;
       }

       public static int dfsHelperMemo(int r, int c, int[][] mine, int[][] qb) {
              if (c == mine[0].length - 1) {
                     // right wall
                     qb[r][c] = mine[r][c];
                     return mine[r][c];
              }

              if(qb[r][c]!=0) return qb[r][c];

              memoSteps++;
              int gold = Integer.MIN_VALUE;
              // right-up
              if (c + 1 < mine[0].length && r - 1 >= 0) {
                     gold = Math.max(gold, dfsHelperMemo(r - 1, c + 1, mine, qb));
              }

              // right
              if (c + 1 < mine[0].length) {
                     gold = Math.max(gold, dfsHelperMemo(r, c + 1, mine, qb));
              }

              // right-down
              if (c + 1 < mine[0].length && r + 1 < mine.length) {
                     gold = Math.max(gold, dfsHelperMemo(r + 1, c + 1, mine, qb));
              }

              qb[r][c] = gold + mine[r][c];
              memoMatrix = qb;
              return gold + mine[r][c];

       }


       public static int getMaxGoldTab(int[][] mine){
              int n = mine.length;
              int m = mine[0].length;

              // Assign Storage
              int[][] dp = new int[n][m];

              dp[n-1][m-1] = mine[n-1][m-1];

              // Direction
              for (int c = m - 1; c >= 0; c--){
                     for (int r = n - 1; r >= 0; r--){
                            // Value - formulae
                            if(r==n-1 && c==m-1) continue;

                            int gold = 0;
                            // right-up
                            if (c + 1 < mine[0].length && r - 1 >= 0) {
                                   gold = Math.max(gold, dp[r-1][c+1]);
                            }

                            // right
                            if (c + 1 < mine[0].length) {
                                   gold = Math.max(gold, dp[r][c+1]);
                            }

                            // right-down
                            if (c + 1 < mine[0].length && r + 1 < mine.length) {
                                   gold = Math.max(gold, dp[r+1][c+1]);
                            }

                            dp[r][c] = gold + mine[r][c];
                     }
              }
              print2DArr(dp);
              int maxRow = Integer.MIN_VALUE;
              for(int i=0;i<n;i++){
                     maxRow = Math.max(maxRow, dp[i][0]);
              }
              return maxRow;
       }      


       public static void Set2() {
              // mazepath
              int[][] maze = { { 0, 1, 4, 2, 8, 2 }, { 4, 3, 6, 5, 0, 4 }, { 1, 2, 4, 1, 4, 6 }, { 2, 0, 7, 3, 2, 2 },
                            { 3, 1, 5, 9, 2, 4 }, { 2, 7, 0, 8, 5, 1 } };

              // int[][] maze2 = { { 1, 4, 5, 4 }, { 3, 1, 5, 3 }, { 4, 3, 1, 1 }, { 4, 3, 5,
              // 2 } };
              // System.out.println(countAllPaths(0, 0, maze));
              // System.out.println(getMinPathRec(0, 0, maze));
              // System.out.println(getMinPathMemo(0, 0, maze, new int[6][6]));
              // print2DArr(memoMatrix);
              // System.out.println(getMinPathTab(maze));

              int[][] goldMine = maze;
              print2DArr(goldMine);

              System.out.println(getMaxGoldRecur(goldMine));
              System.out.println(getMaxGoldMemo(goldMine));
              System.out.println(getMaxGoldTab(goldMine));

       }

       public static void main(String[] args) {
              // Set1();
              Set2();

       }
}
