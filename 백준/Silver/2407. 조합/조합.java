import java.io.*;
import java.math.BigInteger;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    
        init(in);
        solve(in);        	
    }
    
    static int N;
    static int R;
    static BigInteger[][] dp;
    static void init(BufferedReader in) throws IOException {
    	StringTokenizer tokens = new StringTokenizer(in.readLine());
    	N = Integer.parseInt(tokens.nextToken());
    	R = Integer.parseInt(tokens.nextToken());
    	dp = new BigInteger[101][101];
    }

    static void solve(BufferedReader in) throws IOException {
    	System.out.println(combination(N, R));
    }
    
    static BigInteger combination(int n, int r) {
    	for(int i = 1; i <= n; i++) {
    		for(int j = 0; j <= i; j++) {
    			if(j == 0 || j == i) dp[i][j] = new BigInteger("1");
    			else dp[i][j] = dp[i-1][j].add(dp[i-1][j-1]);
    		}
    	}
    	return dp[n][r];
    }
}


