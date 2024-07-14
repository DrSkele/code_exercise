import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    
        init(in);
        solve(in);        	
    }
    
    static int num;
    static int[] dp;
    static void init(BufferedReader in) throws IOException {
    	num = Integer.parseInt(in.readLine());
    	dp = new int[num+1];
    }

    static void solve(BufferedReader in) throws IOException {
    	dp[0] = 0;
    	for(int i = 1; i <= num; i++) {
    		int min = Integer.MAX_VALUE;
    		for(int j = 1; j * j <= i; j++) {
    			min = Math.min(min, dp[i - j*j]);
    		}
    		dp[i] = min+1;
    	}
    	System.out.println(dp[num]);
    }
    
}


