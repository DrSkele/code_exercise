import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();		
	}
	
	static long nth;
	static void input(BufferedReader in) throws IOException {
		nth = Long.parseLong(in.readLine());
	}
	
	static void solve(){
		System.out.println(fibonacci(nth-1)[0][0]);
	}
	
	static long[][] matrix = {{1L, 1L}, {1L, 0L}};
	static long[][] fibonacci(long n) {
		if(n <= 1) return matrix;
		
		long[][] half = fibonacci(n/2L);
		long[][] even = multiplyMatrix(half, half);
		if(n % 2L == 0) {
			return even;
		} else {
			return multiplyMatrix(even, matrix);
		}
	}
	
	static final long mod = 1_000_000;
	static long[][] multiplyMatrix(long[][] a, long[][] b) {
		long[][] temp = new long[2][2];
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				for(int k = 0; k < 2; k++) {
					temp[i][j] += a[i][k] * b[k][j];
					temp[i][j] %= mod;
				}
			}
		}
		return temp;
	}
}