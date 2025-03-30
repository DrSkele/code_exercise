import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static final int MAX = 5_000_000;
	static int V;
	static int E;
	static int[][] minPath;
	static void input(BufferedReader in) throws IOException {		
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		V = Integer.parseInt(tokens.nextToken());
		E = Integer.parseInt(tokens.nextToken());
		
		minPath = new int[V+1][V+1];
		
		for(int i = 0; i <= V; i++) {
			for(int j = 0; j <= V; j++) {
				minPath[i][j] = MAX;
			}
		}
		
		for(int i = 0; i < E; i++) {
			tokens = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(tokens.nextToken());
			int to = Integer.parseInt(tokens.nextToken());
			int dist = Integer.parseInt(tokens.nextToken());
			
			minPath[from][to] = dist;
		}
	}
	
	static void solve(){
		
		for(int k = 1; k <= V; k++) {
			for(int i = 1; i <= V; i++) {
				for(int j = 1; j <= V; j++) {
					minPath[i][j] = Math.min(minPath[i][j], minPath[i][k] + minPath[k][j]);
				}
			}
		}
		
		int min = MAX;
		
		for(int i = 1; i <= V; i++) {
			min = Math.min(min, minPath[i][i]);
		}
		
		System.out.println(min == MAX ? -1 : min);
	}
}