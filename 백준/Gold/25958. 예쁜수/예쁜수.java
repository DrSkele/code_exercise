import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static int goal;
	static int mod;
	static ArrayList<Integer> nums;
	static void input(BufferedReader in) throws IOException {		
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		goal = Integer.parseInt(tokens.nextToken());
		mod = Integer.parseInt(tokens.nextToken());
		
		nums = new ArrayList<>();
		
		for(int i = 1; i <= goal; i++) {
			int one = i % 10;
			int ten = (i/10) % 10;
			int hund = (i/100) % 10;
			int thou = (i/1000) % 10;
			int sum = one + ten + hund + thou;
			
			if(i % sum == 0) nums.add(i);
		}
	}
	
	static void solve(){
		int[][] dp = new int[nums.size()+1][goal+1];
		
		for(int i = 0; i <= nums.size(); i++) {
			dp[i][0] = 1;
		}
		
		for(int i = 1; i <= nums.size(); i++) {
			int num = nums.get(i-1);
			//System.out.println(num);
			for(int j = 1; j <= goal; j++) {
				if(j < num) dp[i][j] = dp[i-1][j];
				else dp[i][j] = dp[i-1][j] +  dp[i][j - num];
				dp[i][j] %= mod;
				
				//System.out.print(dp[i][j] + " ");
			}
			//System.out.println();
		}
		
		System.out.println(dp[nums.size()][goal] % mod);
	}
	
}