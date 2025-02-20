import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		input(in);
		solve();
	}
	
	static int nPlanet;
	static int start;
	static int[][] matrix;
	static int min;
	static int FIN;
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		nPlanet = Integer.parseInt(tokens.nextToken());
		start = Integer.parseInt(tokens.nextToken());
		
		matrix = new int[nPlanet][nPlanet];
		
		for(int i = 0; i < nPlanet; i++) {
			tokens = new StringTokenizer(in.readLine());
			for(int j = 0; j < nPlanet; j++) {
				matrix[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		for(int k = 0; k < nPlanet; k++) {
			for(int i = 0; i < nPlanet; i++) {
				if(i == k) continue;
				for(int j = 0; j < nPlanet; j++) {
					if(j == k) continue;
					matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
				}
			}
		}
		
//		for(int i = 0; i < nPlanet; i++) {
//			for(int j = 0; j < nPlanet; j++) {
//				System.out.print(matrix[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		min = Integer.MAX_VALUE;
		FIN = (1 << (nPlanet)) - 1;
	}
	
	static void solve() {
		travel(start, 1 << start, 0);
		
		System.out.println(min);
	}
	
	static void travel(int cur, int visited, int time) {
		if(visited == FIN) {
			min = Math.min(min, time);
			return;
		}
		
		for(int i = 0; i < nPlanet; i++) {
			if((visited & (1 << i)) == 0) {
				travel(i, visited | (1 << i), time + matrix[cur][i]);
			}
		}
	}
}