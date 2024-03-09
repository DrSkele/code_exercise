import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		init(in);
		
		solve();
	}
	
	static int time;
	static int maxCnt;
	static ArrayList<int[]> plums;
	static int[][] dp; //idx, move
	static void init(BufferedReader in) throws IOException{
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		time = Integer.parseInt(tokens.nextToken());
		maxCnt = Integer.parseInt(tokens.nextToken());
		plums = new ArrayList<>();
		//first
		int prev = Integer.parseInt(in.readLine());
		int cnt = 1;
		//after first
		for(int i = 1; i < time; i++) {
			int cur = Integer.parseInt(in.readLine());
			if(prev == cur) {
				cnt++;
			} else {
				plums.add(new int[] {prev, cnt}); // tree , number of plums
				prev = cur;
				cnt = 1;
			}
		}
		plums.add(new int[] {prev, cnt});
		dp = new int[plums.size()][maxCnt+1];
	}
	
	static void solve() {
		
		int state = 1;
		int[] first = plums.get(0);
		
		dp[0][0] = first[0] == state ? first[1] : 0;
		dp[0][1] = first[0] != state ? first[1] : 0;
		
		
		int max = first[1];
		for(int i = 1; i < plums.size(); i++) {
			int maxMove = i + 1 <= maxCnt ? i + 1 : maxCnt;
			int[] cur = plums.get(i);
			int curTree = cur[0];
			int curPlum = cur[1];
			for(int m = 0; m <= maxMove; m++) {
				if(m == 0) {
					if(i == 1) {
						dp[1][0] = state == curTree ? curPlum : 0;
					} else {
						dp[i][0] = dp[i-2][0] + curPlum;
					}
				} else {
					int curState = m % 2 == 0 ? 1 : 2;
					dp[i][m] = Math.max(dp[i-1][m-1], dp[i-1][m])+ (curState == curTree ? curPlum : 0);
				}
				
				max = Math.max(max, dp[i][m]);
			}
		}
		System.out.println(max);
	}
}


