import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		
		for(int t = 0; t < T; t++) {
			input(in);
			solve();					
		}
	}
	
	static int nCoin;
	static int[] coins;
	static int goal;
	static void input(BufferedReader in) throws IOException {		
		nCoin = Integer.parseInt(in.readLine());
		coins = new int[nCoin];
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		for(int i = 0; i < nCoin; i++) {
			coins[i] = Integer.parseInt(tokens.nextToken());
		}
		goal = Integer.parseInt(in.readLine());
	}
	
	static void solve(){
		int[] money = new int[goal+1];
		
		money[0] = 1;
		
		for(int i = 0; i < nCoin; i++) {
			for(int j = coins[i]; j <= goal; j++) {
				money[j] += money[j - coins[i]];
			}
		}
		
		System.out.println(money[goal]);
	}
	
}