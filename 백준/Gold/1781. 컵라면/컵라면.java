import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		init();
		solve();
	}
	
	static int N;
	static ArrayList<Integer>[] prob;
	static PriorityQueue<Integer> pq;
	static void init() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		prob = new ArrayList[N+1];
		pq = new PriorityQueue<Integer>(Comparator.reverseOrder());
		for(int i = 0; i <= N; i++) {
			prob[i] = new ArrayList<>();
		}
		for(int i = 0; i < N; i++) {
			StringTokenizer tokens = new StringTokenizer(in.readLine());
			int deadline = Integer.parseInt(tokens.nextToken());
			int ramen = Integer.parseInt(tokens.nextToken());
			prob[deadline].add(ramen);
		}
	}
	
	static void solve() {
		int sum = 0;
		for(int i = N; i >= 1; i--) {
			for(int ramen : prob[i]) {
				pq.add(ramen);
			}
			if(!pq.isEmpty()) {
				sum += pq.poll();
			}
		}
		System.out.println(sum);
	}
}
