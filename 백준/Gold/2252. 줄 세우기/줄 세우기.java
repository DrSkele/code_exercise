import java.io.*;
import java.util.*;

public class Main {

	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		init(in);
		
		solve(in);
	}
	
	static int nStudents;
	static int nCompare;
	static ArrayList<Integer>[] order;
	static int[] degree;
	public static void init(BufferedReader in) throws IOException{
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		nStudents = Integer.parseInt(tokens.nextToken());
		nCompare = Integer.parseInt(tokens.nextToken());
		
		order = new ArrayList[nStudents+1];
		degree = new int[nStudents+1];
		for(int i = 0; i < order.length; i++) {
			order[i] = new ArrayList<>();
		}
		for(int i = 0; i < nCompare; i++) {
			tokens = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(tokens.nextToken());
			int to = Integer.parseInt(tokens.nextToken());
			order[from].add(to);
			degree[to]++;
		}
	}
	
	public static void solve(BufferedReader in) throws IOException {
		
		ArrayDeque<Integer> q = new ArrayDeque<>();
		
		for(int i = 1; i < degree.length; i++) {
			if(degree[i] == 0) q.add(i);
		}
		
		StringBuilder str = new StringBuilder();
		while(!q.isEmpty()) {
			int cur = q.poll();
			str.append(cur).append(" ");
			
			for(int next : order[cur]) {
				degree[next]--;
				
				if(degree[next] == 0) q.add(next);
			}
		}
		
		System.out.println(str.toString());
	}
}
