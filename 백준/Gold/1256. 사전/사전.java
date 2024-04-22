import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		
		init();
		
		solve();
	}
	
	static int N;
	static int M;
	static int K;
	static void init() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
	}
	static long[][] comb;
	static void solve() {
		comb = new long[N+M+1][N+M+1];
		StringBuilder str = new StringBuilder();
	
		int a = N;
		int z = M;
		
		if(combination(a+z, a) < 0 || combination(a+z, a) >= 1_000_000_000) {
			while(combination(a+z-1, a-1) < 0 || combination(a+z-1, a-1) >= 1_000_000_000) {
				str.append("a");
				a--;
			}
		} else {
			if(K > combination(a+z, a)) {
				System.out.println(-1);
				return;
			}
		}
		
		//System.out.println(combination(a+z-1, a-1));
		while(K >= 1) {
			if(a > 0 && z > 0) {
				//System.out.println(K + ", " + a +", " + z + ", " + combination(a+z-1, a-1));
				if(K < combination(a+z-1, a-1)) {
					str.append("a");
					a--;
				} else if(K == combination(a+z-1, a-1)) {
					str.append("a");
					for(int i = 0; i < z; i++) {
						str.append("z");
					}
					for(int i = 0; i < a-1; i++) {
						str.append("a");
					}
					break;
				} else {
					str.append("z");
					K -= combination(a+z-1, a-1);
					z--;
				}
			} else {
				if(a == 0) {
					for(int i = 0; i < z; i++) {
						str.append("z");
					}
					break;
				}
				if(z == 0) {
					for(int i = 0; i < a; i++) {
						str.append("a");
					}
					break;
				}
			}
		}
		System.out.println(str.toString());
		//System.out.println(str.toString().length());
	}
	
	static long combination(int n, int r) {
		if(r < 1) return 1;
		if(r == 1) return n;
		if(n == r) return 1;
		
		if(comb[n][r] == 0) {
			if(n-r < r) r = n-r;
			comb[n][r] = combination(n-1, r) + combination(n-1, r-1);
		}
		return comb[n][r];
	}
}


