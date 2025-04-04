import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static final int MOD = 1_000_000_007;
	static int nDice;
	static int[] side;
	static int[] sum;
	static void input(BufferedReader in) throws IOException {		
		nDice = Integer.parseInt(in.readLine());
		
		side = new int[nDice];
		sum = new int[nDice];
		
		for(int i = 0; i < nDice; i++) {
			StringTokenizer tokens = new StringTokenizer(in.readLine());
			side[i] = Integer.parseInt(tokens.nextToken());
			sum[i] = Integer.parseInt(tokens.nextToken());
		}
	}
	
	static void solve(){
		long bottom = 1;
		long top = 0;
		
		for(int i = 0; i < nDice; i++) {
			top = top * side[i] + bottom * sum[i];
			bottom *= side[i];
			bottom %= MOD;
			top %= MOD;
		}
		
		long result = 0;
		if(top % bottom == 0) result = top/bottom;
		else result = (top * pow(bottom, MOD-2)) % MOD;
		
		System.out.println(result);
	}
	
	static long pow(long n, int p) {
		if(p == 1) return n;
		
		long half = pow(n, p/2);
		if(p % 2 == 0) return (half * half) % MOD;
		else return (((half * half) % MOD) * n) % MOD;
	}
}