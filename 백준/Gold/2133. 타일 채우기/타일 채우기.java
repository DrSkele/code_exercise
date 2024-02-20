import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(tokens.nextToken());
		
		if(N % 2 == 1) {
			System.out.print(0);
			return;
		}
		
		N = N/2+1;
		
		int dp[] = new int[N];
		
		dp[0] = 1;
		dp[1] = 3;
		
		for(int i = 2; i < N; i++) {
			dp[i] = dp[i-1] * dp[1];
			for(int j = i-2; j >= 0; j--) {
				dp[i] += dp[j] * 2;
			}
		}
		
		System.out.print(dp[N-1]);
	}
}




