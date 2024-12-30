import java.io.*;
import java.util.*;

class Main{
	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();		
	}
	
	static int citys;
	static int roads;
	static int distance;
	static int start;
	static Map<Integer, Set<Integer>> path;
	static int[] minDist;
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		citys = Integer.parseInt(tokens.nextToken());
		roads = Integer.parseInt(tokens.nextToken());
		distance = Integer.parseInt(tokens.nextToken());
		start = Integer.parseInt(tokens.nextToken());
		
		path = new TreeMap<>();
		
		for(int i = 0; i < roads; i++) {
			tokens = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(tokens.nextToken());
			int to = Integer.parseInt(tokens.nextToken());
			
			if(!path.containsKey(from)) path.put(from, new TreeSet<>());
			
			path.get(from).add(to);
		}
		
		minDist = new int[citys+1];
		for(int i = 0; i < minDist.length; i++) {
			minDist[i] = Integer.MAX_VALUE;
		}
	}
	
	static void solve() {
		
		PriorityQueue<Journey> pq = new PriorityQueue<>();
		
		pq.add(new Journey(start, 0));
		minDist[start] = 0;
		
		while(!pq.isEmpty()) {
			Journey cur = pq.poll();
			
			if(!path.containsKey(cur.city)) continue;
			
			for(int otherCity : path.get(cur.city)) {
				int newDist = cur.dist + 1;
				
				if(minDist[otherCity] <= newDist) continue;
				
				pq.add(new Journey(otherCity, newDist));
				minDist[otherCity] = newDist;
			}
		}
		
		StringBuilder str = new StringBuilder();
		
		for(int i = 1; i < minDist.length; i++) {
			if(minDist[i] == distance) {
				str.append(i).append("\n");
			}
		}
		
		if(str.length() == 0) System.out.println(-1);
		else System.out.println(str.toString());
	}
	
	static class Journey implements Comparable<Journey>{
		int city;
		int dist;
		public Journey(int city, int dist) {
			this.city = city;
			this.dist = dist;
		}
		
		public int compareTo(Journey j) {
			if(this.dist < j.dist) return -1;
			else if(this.dist > j.dist) return 1;
			else return 0;
		}
	}
}