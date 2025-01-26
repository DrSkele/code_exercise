import java.io.*;
import java.util.*;

public class Main {

	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());
		for(int t = 0; t < T; t++) {
			init(in);
			
			solve(in);			
		}
		
	}
	
	static int nBuilding;
	static int nOrder;
	static int[] buildTime;
	static ArrayList<Integer>[] buildOrder;
	static int lastBuild;
	static int[] minTime;
	public static void init(BufferedReader in) throws IOException{
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		nBuilding = Integer.parseInt(tokens.nextToken());
		nOrder = Integer.parseInt(tokens.nextToken());
		buildTime = new int[nBuilding];
		buildOrder = new ArrayList[nBuilding];
		tokens = new StringTokenizer(in.readLine());
		for(int i = 0; i < nBuilding; i++) {
			buildTime[i] = Integer.parseInt(tokens.nextToken());
			buildOrder[i] = new ArrayList<>();
		}
		for(int i = 0; i < nOrder; i++) {
			tokens = new StringTokenizer(in.readLine());
			int prev = Integer.parseInt(tokens.nextToken())-1;
			int build = Integer.parseInt(tokens.nextToken())-1;
			buildOrder[build].add(prev);
		}
		lastBuild = Integer.parseInt(in.readLine())-1;
		
		minTime = new int[nBuilding];
		for(int i = 0; i < nBuilding; i++) {
			minTime[i] = -1;
		}
	}
	
	public static void solve(BufferedReader in) throws IOException {
		System.out.println(findMinTime(lastBuild));
	}
	
	// build 까지 도달하는데 걸리는 최소시간
	public static int findMinTime(int build) {
		if(minTime[build] < 0) {
			int min = buildTime[build];
			for(int next : buildOrder[build]) {
				min = Math.max(min, buildTime[build] + findMinTime(next));
			}
			minTime[build] = min;
		}
		return minTime[build];
	}
}
