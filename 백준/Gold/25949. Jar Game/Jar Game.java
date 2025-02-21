import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		input(in);
		solve();
	}
	
	static int[] jars;
	static int[][][][] dp; // min max
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		
		jars = new int[3];
		
		jars[0] = Integer.parseInt(tokens.nextToken());
		jars[1] = Integer.parseInt(tokens.nextToken());
		jars[2] = Integer.parseInt(tokens.nextToken());
		
		dp = new int[jars[0]+1][jars[1]+1][jars[2]+1][30];
		for(int i = 0; i < jars[0] + 1; i++) {
			for(int j = 0; j < jars[1] + 1; j++) {
				for(int k = 0; k < jars[2] + 1; k++) {
					for(int l = 0; l < 30; l++) {
						dp[i][j][k][l] = -1;
					}
				}
			}
		}
	}
	
	static void solve() {
		int fMax = playGame(1);
		int sMax = jars[0] + jars[1] + jars[2] - fMax;
		
		if(fMax > sMax) System.out.println('F');
		else if(fMax < sMax) System.out.println('S');
		else System.out.println('D');
	}
	
	static int playGame(int turn) {
		
		int result = dp[jars[0]][jars[1]][jars[2]][turn];
		
		if(result != -1) return result;
		else if(turn % 2 == 1) result = 0;
		else result = Integer.MAX_VALUE;
		
		boolean emptyJar = true;
		
		for(int i = 0; i < 3; i++) {
			if(jars[i] == 0) continue;
			
			int amount = jars[i] >= turn ? turn : jars[i];
			jars[i] -= amount;
			int played = playGame(turn+1);
			if(turn % 2 == 1) {
				result = Math.max(result, played + amount);
			} else {
				result = Math.min(result, played);
			}
			jars[i] += amount;
			
			emptyJar = false;
		}
		
		if(emptyJar) result = 0;
		
		dp[jars[0]][jars[1]][jars[2]][turn] = result;
		
		return result;
	}
	
	static class Pair{
		int first;
		int second;
		public Pair(int f, int s) {
			first = f;
			second = s;
		}
	}
}