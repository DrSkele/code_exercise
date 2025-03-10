import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();		
	}
	
	static int goal;
	static int nCity;
	static int[] cost;
	static int[] ad;
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		goal = Integer.parseInt(tokens.nextToken());
		nCity = Integer.parseInt(tokens.nextToken());
		cost = new int[nCity];
		ad = new int[nCity];
		for(int i = 0; i < nCity; i++) {
			tokens = new StringTokenizer(in.readLine());
			cost[i] = Integer.parseInt(tokens.nextToken());
			ad[i] = Integer.parseInt(tokens.nextToken());
		}
	}
	
	static final int max = 20_000_000;
	static void solve(){
		int[] minCost = new int[goal+101];
		
		Arrays.fill(minCost, max);
		minCost[0] = 0;
		
		for(int city = 0; city < nCity; city++) {
			for(int client = ad[city]; client <= goal + 100; client++) {
				minCost[client] = Math.min(minCost[client], minCost[client- ad[city]] + cost[city]);
				//System.out.print(minCost[client] + " ");
			}
			//System.out.println();
		}
		int min = Integer.MAX_VALUE;
		for(int i = goal; i <= goal+100; i++) {
			min = Math.min(min, minCost[i]);
		}
		
		System.out.print(min);
	}
	
}