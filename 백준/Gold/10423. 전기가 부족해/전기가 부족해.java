import java.io.*;
import java.util.*;

public class Main {

	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		init(in);
		
		solve(in);			
	}
	
	static int nCity;
	static int nCable;
	static int nGenerator;
	static int[] root;
	static Set<Integer> generators;
	static ArrayList<Cable> cables;
	public static void init(BufferedReader in) throws IOException{
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		nCity = Integer.parseInt(tokens.nextToken());
		nCable = Integer.parseInt(tokens.nextToken());
		nGenerator = Integer.parseInt(tokens.nextToken());
		
		root = new int[nCity+1];
		for(int i = 1; i < nCity+1; i++) {
			root[i] = i;
		}
		
		generators = new TreeSet<Integer>();
		tokens = new StringTokenizer(in.readLine());
		for(int i = 0; i < nGenerator; i++) {
			generators.add(Integer.parseInt(tokens.nextToken()));
		}
		
		cables = new ArrayList<>();
		for(int i = 0; i < nCable; i++) {
			tokens = new StringTokenizer(in.readLine());
			cables.add(new Cable(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())));
		}
	}
	
	public static void solve(BufferedReader in) throws IOException {
		
		int sum = 0;
		
		Collections.sort(cables);
		
		for(Cable cable : cables) {
			int root1 = find(cable.end1);
			int root2 = find(cable.end2);
			
			if(root1 == root2 || (generators.contains(root1) && generators.contains(root2))) continue;
			else if(generators.contains(root1)) {
				union(cable.end1, cable.end2);
			} else {
				union(cable.end2, cable.end1);
			}
			sum += cable.cost;
		}
		
		System.out.println(sum);
	}
	
	static void union(int node1, int node2) {
		node1 = find(node1);
		node2 = find(node2);
		
		root[node2] = node1;
	}
	
	static int find(int node) {
		if(root[node] == node) return node;
		
		return root[node] = find(root[node]);
	}
	
	static class Cable implements Comparable<Cable>{
		int end1;
		int end2;
		int cost;
		public Cable(int e1, int e2, int c) {
			end1 = e1;
			end2 = e2;
			cost = c;
		}
		
		public int compareTo(Cable c) {
			return this.cost - c.cost;
		}
	}
}
