import java.io.*;
import java.util.*;

public class Main {

	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		init(in);
		
		solve();
	}
	static int N;
	static int[] cards;
	static int M;
	static int[] search;
	public static void init(BufferedReader in) throws IOException{
		StringTokenizer tokens;
		
		N = Integer.parseInt(in.readLine());
		cards = new int[N];
		tokens = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) cards[i] = Integer.parseInt(tokens.nextToken());
		Arrays.sort(cards);
		
		M = Integer.parseInt(in.readLine());
		search = new int[M];
		tokens = new StringTokenizer(in.readLine());
		for(int i = 0; i < M; i++) search[i] = Integer.parseInt(tokens.nextToken());
	}
	
	public static void solve() {
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < M; i++) {
			int cur = search[i];
			int upper = upperbound(cur);
			int lower = lowerbound(cur);
			int cnt = (cards[upper] == cur && cards[lower] == cur) ? upper - lower + 1 : 0;
			str.append(cnt).append(" ");
		}
		System.out.println(str.toString());
	}
	
	public static int upperbound(int goal) {
		int left = 0;
		int right = N - 1;
		int mid = 0;
		while(left < right) {
			mid = (int) Math.ceil((left + right) / 2f);
			//System.out.print(mid + " ");
			if(cards[mid] <= goal) {
				left = mid;
			} else {
				right = mid - 1;
			}
		}
		return left;
	}
	
	public static int lowerbound(int goal) {
		int left = 0;
		int right = N - 1;
		int mid = 0;
		while(left < right) {
			mid = (left + right) / 2;
			//System.out.print(mid + " ");
			if(goal <= cards[mid]) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return right;
	}

}
