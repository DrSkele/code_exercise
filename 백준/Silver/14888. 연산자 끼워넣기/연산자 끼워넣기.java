import java.io.*;
import java.util.*;

public class Main {
	
	static int n;
	static int[] num;
	static int[] op;
	static long min;
	static long max;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens;
		n = Integer.parseInt(in.readLine());
		
		num = new int[n];
		tokens = new StringTokenizer(in.readLine());
		for(int i = 0; i < n; i++) {
			num[i] = Integer.parseInt(tokens.nextToken());
		}
		
		op = new int[4];
		tokens = new StringTokenizer(in.readLine());
		for(int i = 0; i < 4; i++) {
			op[i] = Integer.parseInt(tokens.nextToken());
		}
		
		min = Long.MAX_VALUE;
		max = Long.MIN_VALUE;
		
		dfs(1, num[0]);
		
		System.out.println(max);
		System.out.println(min);
	}
	
	static void dfs(int depth, long value) {
		if(n <= depth) {
			//System.out.println(depth + ":" + value);
			if(value < min) min = value;
			if(max < value) max = value;
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			if(op[i] > 0) {
				op[i]--;
				dfs(depth+1, calculate(i, value, num[depth]));
				op[i]++;
			}
		}
	}
	static long calculate(int idx, long a, long b) {
		switch(idx) {
		case 0 : return a+b;
		case 1 : return a-b;
		case 2 : return a*b;
		default : return a/b;
		}
	}
}