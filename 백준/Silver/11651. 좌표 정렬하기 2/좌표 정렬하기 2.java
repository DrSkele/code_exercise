import java.io.*;
import java.util.*;
import java.util.Map.Entry;

class Main{
	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static int num;
	static PriorityQueue<int[]> pq;
	static void input(BufferedReader in) throws IOException {
		num = Integer.parseInt(in.readLine());
		pq = new PriorityQueue<>(new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				if(a[1] < b[1]) return -1;
				else if(a[1] > b[1]) return 1;
				else {
					if(a[0] < b[0]) return -1;
					else if(a[0] > b[0]) return 1;
					else return 0;
				}
			}
		});
		
		for(int i = 0; i < num; i++) {
			StringTokenizer tokens = new StringTokenizer(in.readLine());
			pq.add(new int[] {Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())});
		}
	}
	
	static void solve() {
		StringBuilder str = new StringBuilder();
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			str.append(cur[0]).append(" ").append(cur[1]).append("\n");
		}
		System.out.println(str.toString());
	}
}