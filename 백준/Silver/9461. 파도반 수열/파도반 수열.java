import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    
        init(in);
        solve(in);        	
    }
    
    static int N;
    static long[] dp;
    static void init(BufferedReader in) throws IOException {
    	N = Integer.parseInt(in.readLine());
    	dp = new long[101];
    	
    	dp[0] = 1;
    	dp[1] = 1;
    	dp[2] = 1;
    	dp[3] = 1;
    	dp[4] = 2;
    	dp[5] = 2;
    	for(int i = 6; i < dp.length; i++) {
    		dp[i] = dp[i-1] + dp[i-5];
    	}
    	
    	
    	StringBuilder str = new StringBuilder();
    	for(int i = 0; i < N; i++) {
    		int cur = Integer.parseInt(in.readLine());
    		str.append(dp[cur]).append("\n");
    	}
    	System.out.println(str.toString());
    }

    static void solve(BufferedReader in) throws IOException {
    	
    }
    
}


