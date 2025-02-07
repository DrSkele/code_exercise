import java.io.*;
import java.util.*;

public class Main {

	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		init(in);
		
		solve(in);			
	}
	
	static int nHouse;
	static int nRoad;
	static int[] connection;
	static ArrayList<Path> paths;
	public static void init(BufferedReader in) throws IOException{
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		nHouse = Integer.parseInt(tokens.nextToken());
		nRoad = Integer.parseInt(tokens.nextToken());
		
		connection = new int[nHouse+1];
		for(int i = 1; i < nHouse+1; i++) {
			connection[i] = i;
		}
		
		paths = new ArrayList<>();
		
		for(int i = 0; i < nRoad; i++) {
			tokens = new StringTokenizer(in.readLine());
			paths.add(new Path(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())));
		}
		
		Collections.sort(paths, Comparator.comparing(Path::getCost));
	}
	
	public static void solve(BufferedReader in) throws IOException {
		
		int sum = 0;
		int max = 0;
		
		for(Path path : paths) {
			if(union(path.start, path.dest)) {
				sum += path.cost;
				max = path.cost;
			}
		}
		
		System.out.println(sum - max);
	}
	
	static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a == b) return false;
		
		connection[a] = b;
		return true;
	}
	
	static int find(int a) {
		if(connection[a] == a) return a;
		
		return connection[a] = find(connection[a]);
	}
	
	static class Path{
		int start;
		int dest;
		int cost;
		public Path(int s, int d, int c) {
			start = s;
			dest = d;
			cost = c;
		}
		public int getCost() {return cost;}
	}
}
