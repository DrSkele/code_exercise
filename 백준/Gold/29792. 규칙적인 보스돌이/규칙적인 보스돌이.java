import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static int nChar;
	static int nUse;
	static int nBose;
	static long[] damage;
	static long[] hp;
	static long[] drop;
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		nChar = Integer.parseInt(tokens.nextToken());
		nUse = Integer.parseInt(tokens.nextToken());
		nBose = Integer.parseInt(tokens.nextToken());
		damage = new long[nChar];
		for(int i = 0; i < nChar; i++) {
			damage[i] = Long.parseLong(in.readLine());
		}
		hp = new long[nBose];
		drop = new long[nBose];
		for(int i = 0; i < nBose; i++) {
			tokens = new StringTokenizer(in.readLine());
			hp[i] = Long.parseLong(tokens.nextToken());
			drop[i] = Long.parseLong(tokens.nextToken());
		}
	}
	
	static void solve(){
		long[] maxDrop = new long[nChar];
		for(int i = 0; i < nChar; i++) {
			maxDrop[i] = maxDrop(i, 0, 900, 0);
		}
		
		long[] total = new long[nUse+1];
		Arrays.fill(total, -1);
		total[0] = 0;
		
//		for(long n : maxDrop) {
//			System.out.println(n);
//		}
		
		for(int i = 0; i < nChar; i++) {
			for(int j = nUse; j >= 1; j--) {
				if(total[j-1] >= 0) total[j] = Math.max(total[j], total[j-1] + maxDrop[i]);
			}
		}
		
		System.out.println(total[nUse]);
	}
	
	static long maxDrop(int character, int idx, long time, long sum) {
		if(idx >= nBose) {
			return sum;
		}
		
		long max = 0;
		if(time * damage[character] >= hp[idx]) {
			max = Math.max(max, maxDrop(character, idx+1, time - (hp[idx]/damage[character] + (hp[idx]%damage[character] == 0? 0 : 1)), sum + drop[idx]));
		}
		max = Math.max(max, maxDrop(character, idx+1, time, sum));
		
		return max;
	}
}