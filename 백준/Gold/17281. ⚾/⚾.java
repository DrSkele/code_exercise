import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static int inning;
	static int[][] strikes;
	static int[] order;
	static int max;
	static void input(BufferedReader in) throws IOException {
		inning = Integer.parseInt(in.readLine());
		
		strikes = new int[inning][9];
		
		for(int i = 0; i < inning; i++) {
			StringTokenizer tokens = new StringTokenizer(in.readLine());
			
			for(int j = 0; j < 9; j++) {
				strikes[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		order = new int[9];
		max = 0;
	}
	
	static void solve(){
		dfs(0, 0);
		
		System.out.println(max);
	}
	
	static final int full = (1 << 9) - 1;
	static void dfs(int cnt, int used) {
		if(used == full) {
			playGame();
			return;
		}
		if(cnt == 3) {
			order[cnt] = 0;
			dfs(cnt+1, used | 1);
		} else {
			for(int i = 1; i < 9; i++) {
				if((used & (1 << i)) > 0) continue;
				
				order[cnt] = i;
				dfs(cnt+1, used | (1 << i));
			}			
		}
		
	}
	
	static void playGame() {
		int player = 0;
		int score = 0;
		
		for(int i = 0; i < inning; i++) {
			int out = 0;
			int field = 0;
			while(out < 3) {
				int strike = strikes[i][order[player]];
				
				if(strike == 0) {
					out++;
				} else {
					field <<= 1;
					field |= 1;
					score += (field & (1 << 3)) > 0 ? 1 : 0;
					
					for(int n = 1; n < strike; n++) {
						field <<= 1;
						score += (field & (1 << 3)) > 0 ? 1 : 0;
					}					
				}
				
				player = (player+1)%9;
			}
		}
		
		max = Math.max(max, score);
	}
}