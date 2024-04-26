import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		
		init();
		
		solve();
	}
	
	static int N;
	static PriorityQueue<Integer> q;
	static void init() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		q = new PriorityQueue<>();
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < N; i++) {
			int cmd = Integer.parseInt(in.readLine());
			if(cmd == 0) {
				if(q.isEmpty()) str.append(0).append("\n");
				else str.append(q.poll()).append("\n");
			} else {
				q.add(cmd);
			}
		}
		System.out.println(str.toString());
	}
	static void solve() {
		
	}
}


