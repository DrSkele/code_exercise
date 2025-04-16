import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static String first;
	static String second;
	static String third;
	static void input(BufferedReader in) throws IOException {
		first = in.readLine();
		second = in.readLine();
		third = in.readLine();
	}
	
	static void solve(){
		int[][][] dp = new int[first.length()+1][second.length()+1][third.length()+1];
		
		
		for(int i = 0; i < first.length(); i++) {
			for(int j = 0; j < second.length(); j++) {
				for(int k = 0; k < third.length(); k++) {
					if(first.charAt(i) == second.charAt(j) && second.charAt(j) == third.charAt(k)) {
						dp[i+1][j+1][k+1] = dp[i][j][k]+1;
					} else {
						dp[i+1][j+1][k+1] = Math.max(dp[i][j+1][k+1], Math.max(dp[i+1][j][k+1], dp[i+1][j+1][k]));
					}
				}
			}
		}
		
		System.out.println(dp[first.length()][second.length()][third.length()]);
	}
	
}