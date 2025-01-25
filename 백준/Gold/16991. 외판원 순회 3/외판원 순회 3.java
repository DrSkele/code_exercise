import java.io.*;
import java.util.*;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Main {

	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		init(in);
		
		solve(in);
	}
	
	static int N;
	static int[][] cities;
	static double[][] cost;
	static double[][] visited;
	static int full;
	public static void init(BufferedReader in) throws IOException{
		N = Integer.parseInt(in.readLine());
		visited = new double[N][1 << N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < 1 << N; j++) {
				visited[i][j] = -1;
			}
		}
		cities = new int[N][N];
		for(int i = 0; i < N; i++) {
			StringTokenizer tokens = new StringTokenizer(in.readLine());
			cities[i][0] = Integer.parseInt(tokens.nextToken());
			cities[i][1] = Integer.parseInt(tokens.nextToken());
		}
		cost = new double[N][N];
		for(int i = 0; i < N; i++) {
			int[] from = cities[i];
			for(int j = 0; j < N; j++) {
				int[] to = cities[j];
				
				cost[i][j] = Math.sqrt(Math.pow(from[0] - to[0], 2) + Math.pow(from[1] - to[1], 2));
			}
		}
		full = (1 << N) - 1;
	}
	
	static void fill() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < 1 << N; j++) {
				visited[i][j] = -1;
			}
		}
	}
	
	public static void solve(BufferedReader in) throws IOException {
		
		System.out.println(traverse(0, 1));
	}
	
	public static double traverse(int idx, int visit) {
		if(visit == full) {
			return cost[idx][0] <= 0? 3000 : cost[idx][0];
		}
		if(visited[idx][visit] == -1) {
			double min = Integer.MAX_VALUE;
			for(int i = 0; i < N; i++) {
				if(idx == i) continue;
				
				int curBit = 1 << i;
				if((visit & curBit) > 0) continue;
				
				double curCost = cost[idx][i] == 0? 3000 : cost[idx][i];
				
				min = Math.min(min, traverse(i, (visit | curBit)) + curCost); 
			}
			visited[idx][visit] = min;
		}
		return visited[idx][visit];
	}
}
