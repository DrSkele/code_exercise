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
	static int[][] cost;
	static int[][] visited;
	static int full;
	public static void init(BufferedReader in) throws IOException{
		N = Integer.parseInt(in.readLine());
		cost = new int[N][N];
		visited = new int[N][1 << N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < 1 << N; j++) {
				visited[i][j] = -1;
			}
		}
		
		full = (1 << N) - 1;
		StringTokenizer tokens;
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(in.readLine());
			for(int j = 0; j < N; j++) {
				cost[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
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
	
	public static int traverse(int idx, int visit) {
		if(visit == full) {
			return cost[idx][0] == 0? 1_000_001 : cost[idx][0];
		}
		if(visited[idx][visit] == -1) {
			int min = Integer.MAX_VALUE;
			for(int i = 0; i < N; i++) {
				if(idx == i) continue;
				
				int curBit = 1 << i;
				if((visit & curBit) > 0) continue;
				
				int curCost = cost[idx][i] == 0? 1_000_001 : cost[idx][i];
				
				min = Math.min(min, traverse(i, (visit | curBit)) + curCost); 
			}
			visited[idx][visit] = min;
		}
		return visited[idx][visit];
	}
}
