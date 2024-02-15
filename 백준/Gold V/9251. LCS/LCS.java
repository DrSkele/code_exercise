import java.io.*;
import java.util.*;

public class Main {
	
	static String str1;
	static String str2;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		str1 = in.readLine();
		str2 = in.readLine();
		
		dp = new int[str1.length()+1][str2.length()+1];
		
		for(int i = 0; i < str1.length(); i++) {
			dp[i][0] = 0;
		}
		for(int i = 0; i < str2.length(); i++) {
			dp[0][i] = 0;
		}
		
		for(int i = 0; i < str1.length(); i++) {
			for(int j = 0; j < str2.length(); j++) {
				if(str1.charAt(i) == str2.charAt(j)) dp[i+1][j+1] = dp[i][j]+1;
				else dp[i+1][j+1] = Math.max(dp[i+1][j], dp[i][j+1]);
			}
		}
		System.out.print(dp[str1.length()][str2.length()]);
	}
}
