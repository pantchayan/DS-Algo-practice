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

       public static void main(String[] args) {
              Set1();

       }
}
