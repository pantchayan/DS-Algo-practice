import java.util.HashMap;

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

       public static void printArr(Integer[] arr) {
              for (Integer a : arr) {
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
              Integer[] dp = new Integer[n + 1];
              dp[n] = 0;

              // Direction
              for (int i = n - 1; i >= 0; i--) {
                     // Calculate Value
                     int smallAns = Integer.MAX_VALUE;
                     for (int jump = 1; jump <= jumps[i]; jump++) {
                            if (i + jump <= n) {
                                   if (dp[i + jump] != null)
                                          smallAns = Math.min(smallAns, dp[i + jump]);
                            }
                     }
                     if (smallAns == Integer.MAX_VALUE)
                            continue;
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
              printArr(jumps);

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
              // System.out.println(climbStairsVariableMin(0, n, jumps));
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

       static int recurSteps;

       public static int getMaxGoldRecur(int[][] mine) {
              int max = Integer.MIN_VALUE;
              for (int i = 0; i < mine.length; i++) {
                     int rowMax = dfsHelper(i, 0, mine);
                     max = Math.max(rowMax, max);
              }
              System.out.println("Recursion evaluations : " + recurSteps);
              return max;
       }

       public static int dfsHelper(int r, int c, int[][] mine) {
              if (c == mine[0].length - 1) {
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

       static int memoSteps;

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

              if (qb[r][c] != 0)
                     return qb[r][c];

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

       public static int getMaxGoldTab(int[][] mine) {
              int n = mine.length;
              int m = mine[0].length;

              // Assign Storage
              int[][] dp = new int[n][m];

              dp[n - 1][m - 1] = mine[n - 1][m - 1];

              // Direction
              for (int c = m - 1; c >= 0; c--) {
                     for (int r = n - 1; r >= 0; r--) {
                            // Value - formulae
                            if (r == n - 1 && c == m - 1)
                                   continue;

                            int gold = 0;
                            // right-up
                            if (c + 1 < mine[0].length && r - 1 >= 0) {
                                   gold = Math.max(gold, dp[r - 1][c + 1]);
                            }

                            // right
                            if (c + 1 < mine[0].length) {
                                   gold = Math.max(gold, dp[r][c + 1]);
                            }

                            // right-down
                            if (c + 1 < mine[0].length && r + 1 < mine.length) {
                                   gold = Math.max(gold, dp[r + 1][c + 1]);
                            }

                            dp[r][c] = gold + mine[r][c];
                     }
              }
              print2DArr(dp);
              int maxRow = Integer.MIN_VALUE;
              for (int i = 0; i < n; i++) {
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

       public static boolean TargetSumRecur(int currSum, int target, int idx, int[] arr) {
              if (currSum > target) {
                     return false;
              }
              if (currSum == target) {
                     return true;
              }
              boolean ans = false;
              recurSteps++;
              for (int i = idx; i < arr.length; i++) {
                     ans = ans || TargetSumRecur(currSum, target, i + 1, arr)
                                   || TargetSumRecur(currSum + arr[i], target, i + 1, arr);
              }

              return ans;
       }

       public static void printBoolean2D(boolean[][] arr) {
              for (boolean[] a : arr) {
                     for (boolean x : a) {
                            System.out.print(x + " ");
                     }
                     System.out.println();
              }
       }

       public static boolean TargetSumTabular(int[] arr, int target) {
              // Assign Storage
              boolean[][] dp = new boolean[arr.length + 1][target + 1];
              // Meaning -> each cell i,j -> if subset of arr till i-1 can create a sum of j
              // -> true/false -> 0,1.
              // example -> 0,0 -> means {} empty subset of arr can make a sum of 0 -> true ->
              // 1.
              // -> 3,3 -> means {4,2,7} subset of arr can make a sum of 3 -> false -> 0.

              // trivial case -> first coloumn will be entirely 1 -> as every set has an empty
              // subset which corresponds to sum == 0 (j).
              // -> first row will entirely be 0 apart from 0,0.
              for (int i = 0; i < arr.length + 1; i++) {
                     dp[i][0] = true;
              }

              for (int i = 1; i < arr.length + 1; i++) {
                     for (int j = 1; j < target + 1; j++) {
                            boolean smallAns = false;

                            smallAns = smallAns || dp[i - 1][j];
                            if (j >= arr[i - 1]) {
                                   smallAns = smallAns || dp[i - 1][j - arr[i - 1]];
                            }

                            dp[i][j] = smallAns;
                     }
              }

              printBoolean2D(dp);
              return dp[arr.length][target];

       }

       public static int coinChangeCombinationRecur(int target, String psf, int idx, int[] coins) {
              if (target < 0) {
                     return 0;
              }
              if (target == 0) {
                     // System.out.println(psf);
                     return 1;
              }

              recurSteps++;
              int ans = 0;
              for (int i = idx; i < coins.length; i++) {
                     ans += coinChangeCombinationRecur(target - coins[i], psf + coins[i], i, coins);
              }

              return ans;
       }

       public static int coinChangeCombinationMemo(int target, int idx, int[] coins, int[][] qb) {
              if (target < 0) {
                     return 0;
              }
              if (target == 0) {
                     qb[idx][target] = 1;
                     return 1;
              }

              if (qb[idx][target] != -1)
                     return qb[idx][target];

              memoSteps++;
              int ans = 0;
              for (int i = idx; i < coins.length; i++) {
                     ans += coinChangeCombinationMemo(target - coins[i], i, coins, qb);

              }

              qb[idx][target] = ans;
              memoMatrix = qb;
              return ans;
       }

       public static int coinChangeCombinationTabular(int target, int[] coins) {
              // Assign Storage and meaning
              int[] dp = new int[target + 1];
              dp[0] = 1;
              // meaning -> cell i represents, number of possible combinations leading to sum
              // i
              // for current iteration

              // direction

              // for combinations : 1. iterate through the choices
              // 2. iretate through the dp
              // -> we pick the choice and then iterate through the dp and fill in answers
              for (int i = 0; i < coins.length; i++) {
                     int currentCoin = coins[i];
                     for (int j = 1; j <= target; j++) {
                            if (j - currentCoin >= 0)
                                   dp[j] += dp[j - currentCoin];
                     }
              }

              return dp[target];
       }

       public static int coinChangePermutationsRecur(int target, String psf, int[] coins) {
              if (target < 0) {
                     return 0;
              }
              if (target == 0) {
                     // System.out.println(psf);
                     return 1;
              }
              recurSteps++;
              int ans = 0;
              for (int i = 0; i < coins.length; i++) {
                     ans += coinChangePermutationsRecur(target - coins[i], psf + coins[i], coins);
              }
              return ans;
       }

       public static int coinChangePermutationsMemo(int target, int[] coins, int[] qb) {
              if (target < 0) {
                     return 0;
              }
              if (target == 0) {
                     qb[target] = 1;
                     return 1;
              }
              if (qb[target] != 0)
                     return qb[target];

              memoSteps++;
              int ans = 0;
              for (int i = 0; i < coins.length; i++) {
                     ans += coinChangePermutationsMemo(target - coins[i], coins, qb);
              }

              qb[target] = ans;
              memoArr = qb;
              return ans;
       }

       public static int coinChangePermutationTabular(int target, int[] coins) {
              // Assign Storage and meaning
              int[] dp = new int[target + 1];
              dp[0] = 1;
              // meaning -> cell i represents, number of possible combinations leading to sum
              // i
              // for current iteration

              // direction
              // for permutations : evaluate answer for a sum using every choice available
              for (int j = 1; j <= target; j++) {
                     for (int i = 0; i < coins.length; i++) {
                            int currentCoin = coins[i];
                            if (j - currentCoin >= 0)
                                   dp[j] += dp[j - currentCoin];
                     }
              }
              printArr(dp);
              return dp[target];
       }

       public static int zeroOneKnapsackRecur(int bag, int idx, int[] values, int[] weights) {
              if (bag == 0 || idx == values.length) {
                     return 0;
              }

              if (weights[idx] > bag) {
                     return zeroOneKnapsackRecur(bag, idx + 1, values, weights);
              } else {
                     int case1 = values[idx] + zeroOneKnapsackRecur(bag - weights[idx], idx + 1, values, weights);
                     int case2 = zeroOneKnapsackRecur(bag, idx + 1, values, weights);

                     return Math.max(case1, case2);
              }
       }

       // public static int zeroOneKnapsackMemo(int bag, int idx, int[] values, int[]
       // weights, HashMap<String, Integer> qb){
       // if(bag==0 || idx==values.length){
       // return 0;
       // }
       // String key = idx+"-"+bag;
       // if(qb.containsKey(key)) return qb.get(key);
       // if(weights[idx]>bag){
       // int ans = zeroOneKnapsackMemo(bag, idx+1, values, weights, qb);
       // qb.put(key, ans);
       // return ans;
       // }
       // else{
       // int case1 = values[idx]+zeroOneKnapsackMemo(bag-weights[idx], idx+1, values,
       // weights, qb);
       // int case2 = zeroOneKnapsackMemo(bag, idx+1, values, weights, qb);

       // int ans = Math.max(case1, case2);
       // qb.put(key, ans);
       // return ans;
       // }
       // }

       public static int zeroOneKnapsackMemo(int bag, int idx, int[] values, int[] weights, int[][] qb) {
              if (bag == 0 || idx == values.length) {
                     return 0;
              }
              // String key = idx+"-"+bag;
              if (qb[idx][bag] != 0)
                     return qb[idx][bag];
              if (weights[idx] > bag) {
                     int ans = zeroOneKnapsackMemo(bag, idx + 1, values, weights, qb);
                     qb[idx][bag] = ans;
                     return ans;
              } else {
                     int case1 = values[idx] + zeroOneKnapsackMemo(bag - weights[idx], idx + 1, values, weights, qb);
                     int case2 = zeroOneKnapsackMemo(bag, idx + 1, values, weights, qb);

                     int ans = Math.max(case1, case2);
                     qb[idx][bag] = ans;
                     return ans;
              }
       }

       public static int zeroOneKnapsackTabular(int bag, int[] values, int[] weights) {
              // Assign Storage and Meaning
              int[][] dp = new int[values.length + 1][bag + 1];

              // trivial cases
              for (int i = 0; i <= bag; i++) {
                     dp[0][i] = 0;
              }
              for (int i = 0; i <= values.length; i++) {
                     dp[i][0] = 0;
              }

              for (int i = 1; i <= values.length; i++) {
                     for (int j = 1; j <= bag; j++) {
                            int ans = 0;
                            // it doesn't come
                            ans = Math.max(ans, dp[i - 1][j]);
                            // it comes
                            if (j >= weights[i - 1]) {
                                   ans = Math.max(ans, values[i - 1] + dp[i - 1][j - weights[i - 1]]);
                            }

                            dp[i][j] = ans;

                     }
              }
              return dp[values.length][bag];
       }

       public static int unboundedZeroOneKnapsackMemo(int bag, int idx, int[] values, int[] weights, int[][] qb) {
              if (bag == 0 || idx == values.length) {
                     return 0;
              }
              // String key = idx+"-"+bag;
              if (qb[idx][bag] != 0)
                     return qb[idx][bag];
              if (weights[idx] > bag) {
                     int ans = unboundedZeroOneKnapsackMemo(bag, idx + 1, values, weights, qb);
                     qb[idx][bag] = ans;
                     return ans;
              } else {
                     int case1 = values[idx]
                                   + unboundedZeroOneKnapsackMemo(bag - weights[idx], idx, values, weights, qb);
                     int case2 = unboundedZeroOneKnapsackMemo(bag, idx + 1, values, weights, qb);

                     int ans = Math.max(case1, case2);
                     qb[idx][bag] = ans;
                     return ans;
              }
       }

       public static int unboundedZeroOneKnapsackTabular(int bag, int[] values, int[] weights) {
              // Assign Storage and Meaning
              int[] dp = new int[bag + 1];

              for (int i = 1; i <= bag; i++) {
                     for (int j = 0; j < values.length; j++) {
                            int currValue = values[j];
                            int currWeight = weights[j];
                            if (i - currWeight >= 0)
                                   dp[i] = Math.max(dp[i], currValue + dp[i - currWeight]);
                     }
              }

              return dp[bag];
       }

       public static double fractionalKnapsack(int bag, int[] values, int[] weights) {
              double[] frac = new double[values.length];

              for (int i = 0; i < values.length; i++) {
                     frac[i] = ((double) values[i]) / ((double) weights[i]);
              }

              double totalValue = 0;
              int totalWeight = 0;
              int idx = -1;
              while (totalWeight <= bag) {
                     double max = Double.MIN_VALUE;
                     idx = -1;
                     for (int i = 0; i < frac.length; i++) {
                            if (frac[i] > max) {
                                   max = frac[i];
                                   idx = i;
                            }
                     }

                     if (idx == -1)
                            return totalValue;

                     frac[idx] = Double.MIN_VALUE;
                     totalValue += (double) values[idx];
                     totalWeight += weights[idx];
              }

              double remainingBag = (double) bag - ((double) totalWeight - (double) weights[idx]);
              double lastFrac = remainingBag / (double) weights[idx];

              totalValue -= (double) values[idx];

              totalValue += ((double) values[idx]) * lastFrac;

              return totalValue;
       }

       public static void Set3() {
              // int target = 10;
              // int[] arr = { 4, 2, 7, 1, 3 };
              // System.out.println(TargetSumRecur(0, target, 0, arr));
              // System.out.println(recurSteps);

              // System.out.println(TargetSumTabular(arr, target));

              // int[] coins = { 2, 3, 5, 6};
              // int target = 9;
              // recurSteps = 0;
              // System.out.println(coinChangeCombinationRecur(target, "", 0, coins));

              // memoSteps = 0;
              // int[][] qb = new int[coins.length][target+1];
              // for(int i=0;i<qb.length;i++){
              // for(int j=0;j<qb[0].length;j++){
              // qb[i][j] = -1;
              // }
              // }
              // System.out.println(coinChangeCombinationMemo(target, 0, coins, qb));

              // System.out.println(recurSteps+" vs "+memoSteps);

              // print2DArr(memoMatrix);

              // System.out.println(coinChangeCombinationTabular(target, coins));
              recurSteps = 0;
              memoSteps = 0;
              // System.out.println("Total number of permutations
              // "+coinChangePermutationsRecur(target,"", coins));

              // System.out.println("Total number of permutations
              // "+coinChangePermutationsMemo(target, coins, new int[target+1]));

              // System.out.println(recurSteps+" vs "+memoSteps);

              // printArr(memoArr);

              // System.out.println(coinChangePermutationTabular(target, coins));

              int[] values = { 2, 2, 1, 3 };
              int[] weights = { 6, 4, 5, 1 };

              int bag = 4;
              System.out.println(zeroOneKnapsackRecur(bag, 0, values, weights));

              System.out.println(zeroOneKnapsackMemo(bag, 0, values, weights, new int[values.length + 1][bag + 1]));
              // System.out.println(recurSteps + " vs " + memoSteps);
              System.out.println(zeroOneKnapsackTabular(bag, values, weights));

              System.out.println(fractionalKnapsack(bag, values, weights));

       }

       public static int countBinaryStringsRecur(int prev, String psf, int n) {
              if (n < 0) {
                     return 0;
              }
              if (n == 0) {
                     System.out.println(psf);
                     return 1;
              }

              recurSteps++;
              if (prev == 0) {
                     return countBinaryStringsRecur(1, psf + 1, n - 1);
              } else {
                     int ans = 0;
                     ans += countBinaryStringsRecur(0, psf + 0, n - 1);
                     ans += countBinaryStringsRecur(1, psf + 1, n - 1);

                     return ans;
              }
       }

       public static int countBinaryStringsMemo(int prev, int n, int[][] qb) {
              if (n < 0) {
                     return 0;
              }
              if (n == 0) {
                     return 1;
              }

              if (prev != -1 && qb[prev][n] != -1)
                     return qb[prev][n];

              memoSteps++;
              if (prev == 0) {
                     qb[prev][n] = countBinaryStringsMemo(1, n - 1, qb);
                     memoMatrix = qb;
                     return qb[prev][n];
              } else {

                     int ans = 0;
                     ans += countBinaryStringsMemo(0, n - 1, qb);
                     ans += countBinaryStringsMemo(1, n - 1, qb);
                     qb[prev][n] = ans;
                     memoMatrix = qb;
                     return ans;
              }
       }

       public static int countBinaryStringsTab(int n) {
              int oc0 = 1;
              int oc1 = 1;

              for (int i = 2; i <= n; i++) {
                     int nc0 = oc1;
                     int nc1 = oc0 + oc1;

                     oc1 = nc1;
                     oc0 = nc0;
              }

              return (oc0 + oc1);
       }

       public static int countEncodingsRecur(String asf, String num) {

              if (num.length() == 0) {
                     System.out.println(asf + " " + num);
                     return 1;
              }
              if (num.charAt(0) == '0') {
                     return 0;
              }

              int count = 0;
              char ans = (char) (num.charAt(0) - '0' + 96);

              count += countEncodingsRecur(asf + ans, num.substring(1));
              recurSteps++;
              if (num.length() > 1) {
                     char tens = num.charAt(0);
                     char ones = num.charAt(1);

                     int asc = (tens - '0') * 10 + (ones - '0');
                     if (asc <= 26 && asc >= 1) {
                            char a = (char) (asc + 96);
                            count += countEncodingsRecur(asf + a, num.substring(2));
                     }
              }
              return count;
       }

       public static int countEncodingsMemo(String asf, String num, int[] qb) {

              if (num.length() == 0) {
                     qb[num.length()] = 1;
                     return 1;
              }
              if (num.charAt(0) == '0') {
                     return 0;
              }

              if (qb[num.length()] != 0)
                     return qb[num.length()];
              int count = 0;
              char ans = (char) (num.charAt(0) - '0' + 96);

              count += countEncodingsMemo(asf + ans, num.substring(1), qb);
              memoSteps++;
              if (num.length() > 1) {
                     char tens = num.charAt(0);
                     char ones = num.charAt(1);

                     int asc = (tens - '0') * 10 + (ones - '0');
                     if (asc <= 26 && asc >= 1) {
                            char a = (char) (asc + 96);
                            count += countEncodingsMemo(asf + a, num.substring(2), qb);
                     }
              }

              qb[num.length()] = count;

              memoArr = qb;
              return count;
       }

       public static int abcSubsequences(String s) {
              int aCount = 0;
              int bCount = 0;
              int cCount = 0;

              for (int i = 0; i < s.length(); i++) {
                     char c = s.charAt(i);
                     if (c == 'a') {
                            aCount = 1 + 2 * aCount;
                     }
                     if (c == 'b') {
                            bCount = aCount + 2 * bCount;
                     }
                     if (c == 'c') {
                            cCount = bCount + 2 * cCount;
                     }
              }

              return cCount;
       }

       public static void Set4() {
              // int n = 15;
              // System.out.println(countBinaryStringsRecur(1,"",n));

              // int[][] qb = new int[2][n+1];
              // for(int i=0;i<2;i++){
              // for(int j=0;j<=n;j++){
              // qb[i][j] = -1;
              // }
              // }
              // System.out.println(countBinaryStringsMemo(1, n, qb));

              // System.out.println(recurSteps+" vs "+memoSteps);
              // print2DArr(memoMatrix);

              // System.out.println(countBinaryStringsTab(n));
              String ques = "21123";
              System.out.println(countEncodingsRecur("", ques));

              System.out.println(countEncodingsMemo("", ques, new int[ques.length() + 1]));
              System.out.println(recurSteps + " vs " + memoSteps);

              if (memoArr != null) {

                     printArr(memoArr);
              }
       }

       public static int maxSumNonAdjacent(int ans, int idx, boolean prev, int[] arr){
              if(idx==arr.length){
                     return ans;
              }

              int a = 0;

              recurSteps++;
              if(prev==true){
                     return maxSumNonAdjacent(ans, idx+1, false, arr);
              }
              else{
                     a = Math.max(a,maxSumNonAdjacent(ans+arr[idx], idx + 1, true, arr));
                     a = Math.max(a,maxSumNonAdjacent(ans, idx + 1, false, arr));
              }

              return a;
       }

       public static int maxSumNonAdjacentTab(int[] arr){
              // Assign storage and meaning
              int n = arr.length;
              int[][] dp = new int[n][2];

              dp[0][0] = 0;
              dp[0][1] = arr[0];
              // Direction
              for(int i=1;i<n;i++){
                     dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
                     dp[i][1] = dp[i-1][0] + arr[i];
              }

              return Math.max(dp[n-1][0], dp[n-1][1]);
       }


       public static int paintHouse(int n, int[][] costs){
              // Assign storage and meaning
              int[][] dp = new int[n][3];

              dp[0][0] = costs[0][0];
              dp[0][1] = costs[0][1];
              dp[0][2] = costs[0][2];

              for(int i=1;i<n;i++){
                     dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + costs[i][0];
                     dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + costs[i][1];
                     dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + costs[i][2];
              }

              return Math.min(Math.min(dp[n-1][0], dp[n-1][1]), dp[n-1][2]);

       }
       public static void Set5() {
              int[] arr = { 5, 10, 10, 100, 5,6 };
              int ans = maxSumNonAdjacent(0, 0, false, arr);
              System.out.println(ans);

              System.out.println("Iterations : " +recurSteps);

              ans = maxSumNonAdjacentTab(arr);
              System.out.println(ans);

       }

       public static void main(String[] args) {
              // Set1();
              // Set2();

              // P&C
              // Set3();

              // Set4();
              Set5();

       }
}
