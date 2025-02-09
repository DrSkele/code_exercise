import java.io.*;
import java.util.*;

public class Main {

	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		init(in);
		
		solve(in);			
	}
	
	static int nStorage;
	static int nPath;
	static int[][] paths;
	static int[][] firstStop;
	public static void init(BufferedReader in) throws IOException{
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		nStorage = Integer.parseInt(tokens.nextToken());
		nPath = Integer.parseInt(tokens.nextToken());
		
		paths = new int[nStorage][nStorage];
		firstStop = new int[nStorage][nStorage];
		
		for(int i = 0; i < nStorage; i++) {
			for(int j = 0; j < nStorage; j++) {
				paths[i][j] = i == j ? 0 : 2_000_000;
				firstStop[i][j] = j;
			}
		}
		
		for(int i = 0; i < nPath; i++) {
			tokens = new StringTokenizer(in.readLine());
			int end1 = Integer.parseInt(tokens.nextToken())-1;
			int end2 = Integer.parseInt(tokens.nextToken())-1;
			int cost = Integer.parseInt(tokens.nextToken());
			paths[end1][end2] = cost;
			firstStop[end1][end2] = end2;
			paths[end2][end1] = cost;
			firstStop[end2][end1] = end1;
		}
	}
	
	public static void solve(BufferedReader in) throws IOException {
		
		for(int m = 0; m < nStorage; m++) {
			for(int i = 0; i < nStorage; i++) {
				for(int j = 0; j < nStorage; j++) {
					if(paths[i][j] > paths[i][m] + paths[m][j]) {
						paths[i][j] = paths[i][m] + paths[m][j];
						firstStop[i][j] = firstStop[i][m];						
					}
				}
			}
		}
		
		StringBuilder str = new StringBuilder();
		
		for(int i = 0; i < nStorage; i++) {
			for(int j = 0; j < nStorage; j++) {
				str.append(i == j ? "-" : firstStop[i][j]+1).append(" ");
			}
			str.append("\n");
		}
		
		System.out.println(str.toString());
	}
}
