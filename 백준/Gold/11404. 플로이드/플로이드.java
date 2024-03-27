import java.io.*;
import java.util.*;

public class Main {

	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		init(in);
		
		solve();	
	}
	
	static final int max = 100_000_000;
	static int city;
	static int bus;
	static int[][] route;
	public static void init(BufferedReader in) throws IOException{
		city = Integer.parseInt(in.readLine());
		bus = Integer.parseInt(in.readLine());
		route = new int[city][city];
		for(int i = 0; i < city; i++) {
			for(int j = 0; j < city; j++) {
				route[i][j] = (i == j) ? 0 : max;
			}
		}
		for(int i = 0; i < bus; i++) {
			StringTokenizer tokens = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(tokens.nextToken())-1;
			int to = Integer.parseInt(tokens.nextToken())-1;
			int cost = Integer.parseInt(tokens.nextToken());
			route[from][to] = Math.min(route[from][to], cost);
		}
	}
	
	public static void solve() {
		
		for(int n = 0; n < city; n++) {
			for(int i = 0; i < city; i++) {
				for(int j = 0; j < city; j++) {
					route[i][j] = Math.min(route[i][j], route[i][n] + route[n][j]);
				}  
			}
		}
		
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < city; i++) {
			for(int j = 0; j < city; j++) {
				int path = route[i][j] == max ? 0 : route[i][j];
				str.append(path).append(" ");
			}
			str.append("\n");
		}
		
		System.out.println(str.toString());
	}
	
}

