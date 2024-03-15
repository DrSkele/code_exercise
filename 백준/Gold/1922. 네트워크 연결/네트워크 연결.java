import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		init(in);
		
		solve(in);
	}
	
	static int C;
	static int L;
	static int[] union;
	static ArrayList<Cable> arr;
	static void init(BufferedReader in) throws IOException{
		C = Integer.parseInt(in.readLine());
		L = Integer.parseInt(in.readLine());
		union = new int[C];
		for(int i = 0; i < C; i++) {
			union[i] = i;
		}
		
		StringTokenizer tokens;
		arr = new ArrayList<>();
		for(int i = 0; i < L; i++) {
			tokens = new StringTokenizer(in.readLine());
			int c1 = Integer.parseInt(tokens.nextToken())-1;
			int c2 = Integer.parseInt(tokens.nextToken())-1;
			int cost = Integer.parseInt(tokens.nextToken());
			
			if(c1 == c2) continue;
			
			arr.add(new Cable(c1, c2, cost));
		}
		Collections.sort(arr);
	}
	
	static void solve(BufferedReader in) throws IOException {
		
		int sum = 0;
		for(int i = 0; i < arr.size(); i++) {
			Cable cur = arr.get(i);
			if(find(cur.c1) == find(cur.c2)) continue;
			
			union(cur.c1, cur.c2);
			sum += cur.cost;
		}
		System.out.println(sum);
	}
	
	static int find(int c) {
		if(union[c] == c) return c;
		
		return union[c] = find(union[c]);
	}
	
	static void union(int a, int b) {
		int uA = find(a);
		int uB = find(b);
		
		union[uB] = uA;
	}
	
	static class Cable implements Comparable<Cable>{
		int c1;
		int c2;
		int cost;
		
		public Cable(int c1, int c2, int cost) {
			this.c1 = c1;
			this.c2 = c2;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Cable c) {
			int result = 0;
			if(cost < c.cost) result = -1;
			else if(cost > c.cost) result = 1;
			return result;
		}
	}
}


