import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static int N;
	static long[] x;
	static long[] y;
	static void input(BufferedReader in) throws IOException {		
		N = Integer.parseInt(in.readLine());
		x = new long[N+1];
		y = new long[N+1];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer tokens = new StringTokenizer(in.readLine());
			x[i] = Long.parseLong(tokens.nextToken());
			y[i] = Long.parseLong(tokens.nextToken());
		}
		
		x[N] = x[0];
		y[N] = y[0];
	}
	
	static void solve(){
		
		long sumX = 0;
		long sumY = 0;	
		for(int i = 0; i < N; i++) {
			sumX += x[i] * y[i+1];
			sumY += y[i] * x[i+1];
		}
		
		long sum = Math.abs(sumX - sumY);
		double result = sum / 2.0;
		
		System.out.println(String.format("%.1f", result));
	}
}