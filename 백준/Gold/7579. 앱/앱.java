import java.io.*;
import java.util.*;

public class Main {
	
	static App[] apps;
	static int goal;
	static long[] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(tokens.nextToken());
		goal = Integer.parseInt(tokens.nextToken());
		
		apps = new App[N];
		StringTokenizer bites = new StringTokenizer(in.readLine());
		StringTokenizer costs = new StringTokenizer(in.readLine());
		
		int total = 0;
		for(int i = 0; i < N; i++) {
			int cost = Integer.parseInt(costs.nextToken());
			apps[i] = new App(Integer.parseInt(bites.nextToken()), cost);
			total += cost;
		}
		
		dp = new long[total+1];
		
		Arrays.sort(apps);
		
		for(int i = 0; i < apps.length; i++) {
			int curCost = apps[i].cost;
			int curByte = apps[i].bytes;
				
			for(int cost = total; cost >= curCost; cost--) {
				dp[cost] = Math.max(dp[cost - curCost] + curByte, dp[cost]);	
			}
		}
		
		int totalCost = 0;
		
		for(int i = 0; i < dp.length; i++) {
			if(dp[i] >= goal) {
				totalCost = i;
				break;
			}
			//System.out.print(dp[i] + ", ");
		}
		
		System.out.println(totalCost);
	}
	
	static class App implements Comparable<App>{
		public int bytes;
		public int cost;
		
		public App(int bytes, int cost) {
			this.bytes = bytes;
			this.cost = cost;
		}

		@Override
		public int compareTo(App app) {
			int result = 0;
			if(this.cost < app.cost) result = -1;
			else if(app.cost < this.cost) result = 1;
			else {
				if(this.bytes < app.bytes) result = -1;
				else if(app.bytes < this.bytes) result = 1;
			}
			// TODO Auto-generated method stub
			return result;
		}
	}
}