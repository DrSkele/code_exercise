
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		init(in);
		
		solve();
	}
	
	static int N;
	static int[] cost;
	static int[] gain;
	static int health;
	static int[] mood;
	static void init(BufferedReader in) throws IOException{
		N = Integer.parseInt(in.readLine());
		cost = new int[N];
		gain = new int[N];
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			cost[i] = Integer.parseInt(tokens.nextToken());
		}
		tokens = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			gain[i]	= Integer.parseInt(tokens.nextToken());
		}
		health = 99;
		mood = new int[health+1];
	}
	
	static void solve() {
		
		for(int i = 0; i < N; i++) {
			int curCost = cost[i];
			for(int j = health; j >= 0; j--) {
				if(curCost > j) continue;
				mood[j] = Math.max(mood[j], mood[j-curCost] + gain[i]);
			}
		}
		System.out.println(mood[health]);
	}
	
}


