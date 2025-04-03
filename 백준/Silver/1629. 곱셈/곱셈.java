import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static int N;
	static int P;
	static int MOD;
	static void input(BufferedReader in) throws IOException {		
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		N = Integer.parseInt(tokens.nextToken());
		P = Integer.parseInt(tokens.nextToken());
		MOD = Integer.parseInt(tokens.nextToken());
	}
	
	static void solve(){
		System.out.println(pow(N, P) % MOD);
	}
	
	static long pow(int n, int p) {
		if(p == 1) return n % MOD;
		
		long half = pow(n, p/2) % MOD;
		
		if(p % 2 == 0) {
			return (half * half) % MOD;
		} else {
			return (half * half) % MOD * n % MOD;
		}
	}
}