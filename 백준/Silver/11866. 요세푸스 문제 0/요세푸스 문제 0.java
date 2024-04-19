import java.io.*;
import java.util.*;
public class Main {

	public static void main(String[] args) throws IOException {
		init();
		solve();
	}

	static int K;
	static int N;
	static ArrayDeque<Integer> q;
	static StringBuilder str;
	public static void init() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		q = new ArrayDeque<>();
		K = Integer.parseInt(tokens.nextToken());
		N = Integer.parseInt(tokens.nextToken());
		str = new StringBuilder();
		for(int i = 1; i <= K; i++) {
			q.add(i);
		}
	}
	
	public static void solve() {
		int idx = 0;
		while(q.size() > 1) {
			int cur = q.pop();
			if(idx == N-1) {
				str.append(cur).append(", ");
			} else {
				q.add(cur);
			}
			idx = (idx+1)%N;
		}
		System.out.println("<" + str.toString() + q.pop()+ ">");
	}
}
