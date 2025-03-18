import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static int money;
	static int nCorp;
	static ArrayList<Integer>[] profit;
	static void input(BufferedReader in) throws IOException {		
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		money = Integer.parseInt(tokens.nextToken());
		nCorp = Integer.parseInt(tokens.nextToken());
		profit = new ArrayList[nCorp];
		
		for(int i = 0; i < nCorp; i++) {
			profit[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < money; i++) {
			tokens = new StringTokenizer(in.readLine());
			int cost = Integer.parseInt(tokens.nextToken());
			
			for(int j = 0; j < nCorp; j++) {
				profit[j].add(Integer.parseInt(tokens.nextToken())); 
			}
		}
	}
	
	static void solve(){
		
		int[][] dp = new int[nCorp][money+1];
		
		int[][] invest = new int[nCorp][money+1];
		
		ArrayList<Integer> first = profit[0];
		for(int i = 0; i < money; i++) {
			dp[0][i+1] = first.get(i);
			//System.out.print(dp[0][i+1] + " ");
		}
		//System.out.println();
		
		for(int i = 1; i < nCorp; i++) {
			ArrayList<Integer> corp = profit[i];
			
			for(int m = money; m > 0; m--) {
				
				dp[i][m] = dp[i-1][m];
				invest[i][m] = m;
				
				for(int j = 0; j < m; j++) {
					int cost = j+1;
					int gain = corp.get(j);
					
					//System.out.println(dp[i-1][m] + "/" + dp[i-1][m - cost] + "/" + gain + "");
					
					if(dp[i][m] < dp[i-1][m - cost] + gain) {
						dp[i][m] = dp[i-1][m - cost] + gain;
						invest[i][m] = m - cost;
					}
				}
				
				//System.out.print(dp[i][m] + " ");
			}
			//System.out.println();
		}
		
		int idx = 0;
		int max = 0;
		
		for(int i = 1; i <= money; i++) {
			if(max < dp[nCorp-1][i]) {
				max = dp[nCorp-1][i];
				idx = i;
			}
		}
		System.out.println(max);
		
		StringBuilder str = new StringBuilder();
		
		Stack<Integer> stack = new Stack<>();
		
		int pivot = idx;
		
		stack.add(idx);
		for(int i = nCorp-1; i > 0; i--) {
			stack.add(invest[i][pivot]);
			pivot = invest[i][pivot];
		}
		
		int prev = stack.pop();
		str.append(prev).append(" ");
		while(!stack.isEmpty()) {
			int cur = stack.pop();
			str.append(cur - prev).append(" ");
			prev = cur;
		}
		
		System.out.println(str.toString());
	}
}