import java.io.*;
import java.util.*;

public class Main {

	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		init(in);
		
		solve(in);			
	}
	
	static final int INF = 30_000;
	static int nPoint;
	static int range;
	static int nRoad;
	static int[] items;
	static int[][] roads;
	public static void init(BufferedReader in) throws IOException{
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		
		nPoint = Integer.parseInt(tokens.nextToken());
		range = Integer.parseInt(tokens.nextToken());
		nRoad = Integer.parseInt(tokens.nextToken());
		
		items = new int[nPoint];
		tokens = new StringTokenizer(in.readLine());
		for(int i = 0; i < nPoint; i++) {
			items[i] = Integer.parseInt(tokens.nextToken());
		}
		
		roads = new int[nPoint][nPoint];
		for(int i = 0; i < nPoint; i++) {
			for(int j = 0; j < nPoint; j++) {
				roads[i][j] = i == j ? 0 : INF;
			}
		}
		
		for(int i = 0; i < nRoad; i++) {
			tokens = new StringTokenizer(in.readLine());
			
			int end1 = Integer.parseInt(tokens.nextToken())-1;
			int end2 = Integer.parseInt(tokens.nextToken())-1;
			int length = Integer.parseInt(tokens.nextToken());
			
			roads[end1][end2] = length;
			roads[end2][end1] = length;
		}
	}
	
	public static void solve(BufferedReader in) throws IOException {
		
		for(int n = 0; n < nPoint; n++) {
			for(int i = 0; i < nPoint; i++) {
				for(int j = 0; j < nPoint; j++) {
					roads[i][j] = Math.min(roads[i][j], roads[i][n] + roads[n][j]);
				}
			}
		}
		
		int max = 0;
		for(int i = 0; i < nPoint; i++) {
			int sum = 0;
			for(int j = 0; j < nPoint; j++) {
				if(roads[i][j] <= range) sum += items[j];
			}
			max = Math.max(max, sum);
		}
		
		System.out.println(max);
	}
}
