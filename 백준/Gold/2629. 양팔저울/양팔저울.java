
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		init(in);
		
		solve();
	}
	
	static int N;
	static int[] weight;
	static int M;
	static int[] orb;
	static Set<Integer> set;
	static void init(BufferedReader in) throws IOException{
		N = Integer.parseInt(in.readLine());
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		weight = new int[N];
		for(int i = 0; i < N; i++) {
			weight[i] = Integer.parseInt(tokens.nextToken());
		}
		M = Integer.parseInt(in.readLine());
		tokens = new StringTokenizer(in.readLine());
		orb = new int[M];
		for(int i = 0; i < M; i++) {
			orb[i] = Integer.parseInt(tokens.nextToken());
		}
		set = new HashSet<Integer>();
	}
	
	static void solve() {
		for(int i = 0; i < N; i++) {
			int cur = weight[i];
			Integer[] arr = set.toArray(new Integer[0]);
			
			for(int j = 0; j < arr.length; j++) {
				int prev = arr[j];
				if(cur + prev <= 40_000) set.add(cur + prev);
				if(0 < cur - prev) set.add(cur - prev);
				if(0 < prev - cur) set.add(prev - cur);
			}
			set.add(cur);
		}
		
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < M; i++) {
			str.append(set.contains(orb[i]) ? "Y" : "N").append(" ");
		}
		System.out.println(str.toString());
	}
	
}


