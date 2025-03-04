import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();		
	}
	
	static int size;
	static long pow;
	static long[][] matrix;
	static Map<Long, long[][]> memo;
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		size = Integer.parseInt(tokens.nextToken());
		pow = Long.parseLong(tokens.nextToken());
		
		matrix = new long[size][size];
		
		for(int i = 0; i < size; i++) {
			tokens = new StringTokenizer(in.readLine());
			for(int j = 0; j < size; j++) {
				matrix[i][j] = Long.parseLong(tokens.nextToken());
			}
		}
		
		memo = new HashMap<>();
		
		memo.put(0L, matrix);
		memo.put(1L, matrix);
	}
	
	static void solve(){
		long[][] answer = power(pow);
		
		StringBuilder str = new StringBuilder();
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				str.append(answer[i][j]%1_000).append(" ");
			}
			str.append("\n");
		}
		
		System.out.println(str.toString());
	}
	
	static long[][] power(long pow) {
		if(memo.containsKey(pow)) {
			return memo.get(pow);
		}
		
		long[][] result;
		if(pow % 2 == 1) {
			result = multiply(multiply(power(pow/2), power(pow/2)), power(1));
		} else {
			result = multiply(power(pow/2), power(pow/2));
		}
		
		memo.put(pow, result);
		return result;
	}
	
	static long[][] multiply(long[][] a, long[][] b) {
		long[][] temp = new long[size][size];
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				int sum = 0;
				for(int k = 0; k < size; k++) {
					sum += (a[i][k] * b[k][j]) % 1_000;
				}
				
				temp[i][j] = sum % 1_000;
			}
		}
		
		return temp;
	}
}