import java.io.*;
import java.util.*;

class Main{
    static int length;
    static int[] stair;
    static int[] dp;
    static int[] dpRow;
    static int dp(int node, boolean inRow){
        if(node < 0){
            return 0;
        }
        if(inRow){
            if(dpRow[node] > 0) return dpRow[node];
            dpRow[node] = dp(node-2, false) + stair[node];
            return dpRow[node];
        } else {
            if(dp[node] > 0) return dp[node];
            dp[node] = Math.max(dp(node-1, true) + stair[node], 
                                dp(node-2, false) + stair[node]);
            return dp[node];
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        length = Integer.parseInt(in.readLine());
        stair = new int[length];
        dp = new int[length];
        dpRow = new int[length];
        
        for(int i = 0; i < length; i++){
            stair[i] = Integer.parseInt(in.readLine());
        }
        
        int result = dp(length-1, false);
        
        System.out.print(result);
    }
}