import java.io.*;
import java.util.*;

public class Main {

	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		init(in);
		
		solve();	
	}
	
	static int[] num;
	static int cnt;
	static int max;
	static boolean[][] visited;
	public static void init(BufferedReader in) throws IOException{
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		String line = tokens.nextToken();
		num = new int[line.length()];
		for(int i = 0; i < num.length; i++) {
			num[i] = line.charAt(i) - '0';
		}
		cnt = Integer.parseInt(tokens.nextToken());
		max = -1;
		visited = new boolean[cnt][1_000_001];
	}
	
	public static void solve() {
		
		int val = toNum(num);
		dfs(0, val);
		
		System.out.println(max);
	}
	
	static void dfs(int depth, int asNum) {
		if(depth == cnt) {
			if(asNum > max)	max = asNum;
			return;
		}
		
		for(int i = 0; i < num.length; i++) {
			int first = num[i];
			for(int j = i+1; j < num.length; j++) {
				int second = num[j];
				if(i == 0 && second == 0) continue;
				
				num[i] = second;
				num[j] = first;
				
				int next = toNum(num);
				
				if(!visited[depth][next]) {
					dfs(depth+1, next);
					visited[depth][next] = true;
				}
				
				num[i] = first;
				num[j] = second;
			}
		}
	}
	
	static int toNum(int[] arr) {
		
		int sum = 0;
		for(int i = 0; i < arr.length; i++) {
			sum += arr[i] * Math.pow(10, arr.length-1-i);
		}
		return sum;
	}
}

