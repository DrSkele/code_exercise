import java.io.*;
import java.util.*;

class Main{
	static TreeMap<Integer, TreeSet<Integer>> map;
	static boolean[] visited;
	
	static StringBuilder str;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		str = new StringBuilder();
		
		int vertex = sc.nextInt();
		int line = sc.nextInt();
		int start = sc.nextInt();
		
		map = new TreeMap<>();
		visited = new boolean[vertex+1];
		
		for(int i = 0; i < line; i++) {
			int v1 = sc.nextInt();
			int v2 = sc.nextInt();
			
			put(v1, v2);
			put(v2, v1);
		}
		if(map.containsKey(start)) {
			dfs(start);
			for(int i = 0; i < vertex+1; i++) {
				visited[i] = false;
			}
			str.append("\n");
			bfs(start);
		} else {
			str.append(start).append("\n").append(start);
		}
		System.out.println(str.toString());
	}
	
	static void put(int first, int second) {
		if(map.containsKey(first)) {
			TreeSet<Integer> set = map.get(first);
			set.add(second);
			map.put(first, set);
		} else {
			TreeSet<Integer> set = new TreeSet<>();
			set.add(second);
			map.put(first, set);
		}
	}
	
	static void bfs(int start) {
		
		Queue<Integer> q = new LinkedList<>();
		
		q.add(start);
		visited[start] = true;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			str.append(cur).append(" ");
			
			for(int next : map.get(cur)) {
				if(visited[next]) continue;
				q.add(next);
				visited[next] = true;
			}
		}
	}
	
	static void dfs(int node) {
		visited[node] = true;
		str.append(node).append(" ");
		
		for(int next : map.get(node)) {
			if(visited[next]) continue;
			dfs(next);
		}
	}
}