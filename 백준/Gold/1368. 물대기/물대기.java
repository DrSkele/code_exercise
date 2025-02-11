import java.io.*;
import java.util.*;

public class Main {

	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		init(in);
		
		solve(in);			
	}
	
	static int nField;
	static int[] digCost;
	static int[] group;
	static ArrayList<Path> paths;
	public static void init(BufferedReader in) throws IOException{
		
		nField = Integer.parseInt(in.readLine());
		
		digCost = new int[nField+1];
		group = new int[nField+1];
		for(int i = 1; i < nField+1; i++) {
			digCost[i] = Integer.parseInt(in.readLine());
			group[i] = i;
		}
		
		paths = new ArrayList<>();
		for(int i = 1; i < nField+1; i++) {
			StringTokenizer tokens = new StringTokenizer(in.readLine());
			for(int j = 1; j < nField+1; j++) {
				if(i == j) {
					paths.add(new Path(0, i, digCost[i])); tokens.nextToken();
				} else {
					paths.add(new Path(i, j, Integer.parseInt(tokens.nextToken())));
				}
			}
		}
		
		Collections.sort(paths);
	}
	
	public static void solve(BufferedReader in) throws IOException {
		
		int cost = 0;
		
		for(Path path : paths) {
			if(find(path.end1) == find(path.end2)) continue;
			
			union(path.end1, path.end2);		
			cost += path.cost;
		}
		
		System.out.println(cost);
	}
	
	static class Path implements Comparable<Path>{
		int end1;
		int end2;
		int cost;
		
		public Path(int e1, int e2, int c) {
			end1 = e1;
			end2 = e2;
			cost = c;
		}
		
		public int compareTo(Path p) {
			return this.cost - p.cost;
		}
	}
	
	static void union(int n1, int n2) {
		n1 = find(n1);
		n2 = find(n2);
		
		group[n2] = n1;
	}
	
	static int find(int node) {
		if(group[node] == node) return node;
		
		return group[node] = find(group[node]);
	}
}
