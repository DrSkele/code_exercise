import java.io.*;
import java.util.*;

public class Main {

	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		init(in);
		
		solve(in);
	}
	
	static int nParts;
	static int nLines;
	static ArrayList<Integer>[] order;
	static int[] degree;
	static ArrayList<Part>[] req;
	public static void init(BufferedReader in) throws IOException{
		nParts = Integer.parseInt(in.readLine());
		nLines = Integer.parseInt(in.readLine());
		
		order = new ArrayList[nParts+1];
		degree = new int[nParts+1];
		req = new ArrayList[nParts+1];
		
		for(int i = 0; i < nParts+1; i++) {
			order[i] = new ArrayList<>();
			req[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < nLines; i++) {
			StringTokenizer tokens = new StringTokenizer(in.readLine());
			
			int assembled = Integer.parseInt(tokens.nextToken());
			int part = Integer.parseInt(tokens.nextToken());
			int amount = Integer.parseInt(tokens.nextToken());
			
			order[part].add(assembled);
			degree[assembled]++;
			req[assembled].add(new Part(part, amount));
		}
	}
	
	public static void solve(BufferedReader in) throws IOException {
		
		ArrayList<Integer> assemOrder = new ArrayList<>();
		
		ArrayDeque<Integer> q = new ArrayDeque<>();
		
		ArrayList<Integer> basicPart = new ArrayList<>();
		
		for(int i = 1; i < degree.length; i++) {
			if(degree[i] == 0) {
				q.add(i);
				basicPart.add(i);
			}
		}
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			assemOrder.add(cur);
			
			for(int next : order[cur]) {
				degree[next]--;
				if(degree[next] == 0) q.add(next);
			}
		}
		
		int[] required = new int[nParts+1];
		
		required[nParts] = 1;
		Collections.reverse(assemOrder);
		
		for(int idx : assemOrder) {
			int curAmount = required[idx];
			for(Part next : req[idx]) {
				required[next.idx] += curAmount * next.cnt;
			}
		}
		
		StringBuilder str = new StringBuilder();
		
		for(int idx : basicPart) {
			str.append(idx).append(" ").append(required[idx]).append("\n");
		}
		
		System.out.println(str.toString());
	}
	
	static class Part{
		int idx;
		int cnt;
		public Part(int i, int c) {
			idx = i;
			cnt = c;
		}
	}
}
