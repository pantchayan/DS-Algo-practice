import java.util.ArrayList;

public class l001 {

    public static void display2D(int[][] arr){
        for(int[] ar:arr){
            for(int a: ar){
                System.out.print(a+" ");
            }
            System.out.println();
        }
    }

    public static void display1D(int[] arr){
        for(int a:arr){
            System.out.print(a+" ");
        }
        System.out.println();
    }

    public static int fibonacci(int n, int[] dp){
        if(n<=1){
            return dp[n] = n;
        }
        if(dp[n]!=0){
            return dp[n];
        }

        dp[n] = fibonacci(n-1, dp)+fibonacci(n-2, dp);

        return dp[n];
    }

// MazePath series ======================================================================

    public static int mazePath(int sr, int sc, int er, int ec, int[][] dp){
        if(sr==er && sc==ec){
            return dp[sr][sc] = 1;
        }
        if(dp[sr][sc]!=0){
            return dp[sr][sc];
        }
        int count = 0;
        if(sr+1<=er){
            count+= mazePath(sr+1,sc,er,ec,dp);
        }
        if(sc+1<=ec){
            count+= mazePath(sr, sc+1, er, ec, dp);
        }
        if(sc+1<=ec && sr+1<=er){
            count+= mazePath(sr+1, sc+1, er, ec, dp);
        }
        return dp[sr][sc] = count;
    } 

    public static int mazePathTab(int sr, int sc, int er, int ec, int[][] dp){
        for(sr=er;sr>=0;sr--){
            for(sc=ec;sc>=0;sc--){
                if(sr==er && sc==ec){
                    dp[sr][sc] = 1;
                    continue;
                }
                int count = 0;
                if(sr+1<=er){
                    count+= dp[sr+1][sc];
                }
                if(sc+1<=ec){
                    count+= dp[sr][sc+1];
                }
                if(sc+1<=ec && sr+1<=er){
                    count+= dp[sr+1][sc+1];
                }
                dp[sr][sc] = count;
            }
        }
        return dp[0][0];
    }


    public static int mazePathMulti(int sr, int sc, int er, int ec, String ans){
        if(sr==er && sc==ec){
            System.out.println(ans);
            return 1;
        }
        int count = 0;

        for(int jump=1;sr+jump<=er;jump++){
            count+= mazePathMulti(sr+jump, sc, er, ec, ans+"V"+jump);
        }
        for(int jump=1;sc+jump<=ec;jump++){
            count+= mazePathMulti(sr, sc+jump, er, ec, ans+"H"+jump);
        }
        for(int jump=1;sr+jump<=er && sc+jump<=ec;jump++){
            count+= mazePathMulti(sr+jump, sc+jump, er, ec, ans+"D"+jump);
        }

        return count;
    }


    public static int mazePathMultiMem(int sr, int sc, int er, int ec, int[][] dp){
        if(sr==er && sc==ec){
            return dp[sr][sc] = 1;
        }
        if(dp[sr][sc]!=0){
            return dp[sr][sc];
        }

        int count = 0;

        for(int jump=1;sr+jump<=er;jump++){
            count+= mazePathMultiMem(sr+jump, sc, er, ec, dp);
        }
        for(int jump=1;sc+jump<=ec;jump++){
            count+= mazePathMultiMem(sr, sc+jump, er, ec, dp);
        }
        for(int jump=1;sr+jump<=er && sc+jump<=ec;jump++){
            count+= mazePathMultiMem(sr+jump, sc+jump, er, ec, dp);
        }

        return dp[sr][sc]=count;
    }


    // BOARD PATH ===================================================================================


    // memoization =================================================================================
    public static int boardPath(int start, int end, int[] dp){
        if(start==end){
            return dp[start] = 1;
        }
        if(dp[start]!=0) return dp[start];
        int count=0;
        for(int jump=1;jump<=6 && start+jump<=end;jump++){
            count+=boardPath(start+jump, end, dp);
        }
        return dp[start] = count;
    }

    // tabular ========================================================================
    public static int boardPathTab(int start, int end, int[] dp){
        for(start=end;start>=0;start--){
            if(start==end){
                dp[start] = 1;
                continue;
            }
            int count=0;
            for(int jump=1;jump<=6 && start+jump<=end;jump++){
                count+=dp[start+jump];
            }
            dp[start] = count;
        }
        return dp[0];
    }

    // BOARD PATH with variable jumps ================================================


    // memoization =========================================================
    public static int boardPath2(int start, int end, int[] jumps, int[] dp){
        if(start==end){
            return dp[start] = 1;
        }
        if(dp[start]!=0) return dp[start];
        int count=0;
        for(int jump:jumps){
            if(start+jump<=end){
                count+=boardPath2(start+jump, end, jumps, dp);
            }
        }
        return dp[start] = count;
    }

    // tabulation =========================================================






    // CLIMB STAIRS =========================================================
    public static int climbStairs(int start, int end, int[] dp){
        if(start==end) return dp[start]=1;
        
        if(dp[start]!=0) return dp[start];
        int count=0;
        
        if(start+1<=end){
            count+=climbStairs(start+1,end,dp);
        }
        if(start+2<=end){
            count+=climbStairs(start+2,end,dp);
        }
        return dp[start]=count;
    }


    public static void pathSet(){
        // System.out.println("Hey");
        // int n = 3;
        // int[][] dp = new int[n][n];
        // // System.out.println(mazePathTab(0, 0, 2, 2, dp));
        // // display2D(dp);

        // System.out.println(mazePathMultiMem(0, 0, 2, 2, dp));
        // display2D(dp);


        int[] jumps = {2,3,5,7};
        int[] dp = new int[11];
        System.out.println(boardPath2(0, 10, jumps, dp));
        display1D(dp);
    }

    public static void set2(){
        
        int[] dp = new int[11];
        climbStairs(0,10,dp);
        display1D(dp);
    }

    public static void main(String args[]){

        // pathSet();

        set2();
    }
}
