import java.io.*;
import java.util.*;

public class Main {
	
	static int n;
	static ArrayList<Integer>[] graph;
	static int[] population;
	static boolean[] ally;
	static int total;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(in.readLine());
		population = new int[n+1];
		ally = new boolean[n+1];
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		total = 0;
		for(int i = 1; i <= n; i++) {
			population[i] = Integer.parseInt(tokens.nextToken());
			total += population[i];
		}
		
		graph = new ArrayList[n+1];
		for(int i = 0; i < n+1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		int alone = 0;
		for(int area = 1; area <= n; area++) {
			tokens = new StringTokenizer(in.readLine());
			int con = Integer.parseInt(tokens.nextToken());
			for(int j = 0; j < con; j++) {
				int connected = Integer.parseInt(tokens.nextToken());
				graph[area].add(connected);
			}
			if(con == 0) alone++;
		}
		
		// 동떨어진 구역이 2개 이상이고 총 구역의 수가 3 이상일때
		if(alone >= 2 && n >= 3) {
			System.out.println(-1);
			return;
		}
		
		dfs(1);
		
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}
	
	static void dfs(int idx) {
		if(idx == n+1) {
			int allyCnt = 0;
			int allyPopulation = 0;
			
			int enemyCnt = 0;
			int enemyPopulation = 0;
			
			HashSet<Integer> allyGroup = new HashSet<>();
			HashSet<Integer> enemyGroup = new HashSet<>();
			for(int i =1; i < n+1; i++) {
				if(ally[i]) {
					allyCnt++;
					allyPopulation += population[i];
					allyGroup.add(i);
				} else {
					enemyCnt++;
					enemyPopulation += population[i];
					enemyGroup.add(i);
				}
			}
			if(allyCnt > 0 && enemyCnt > 0) {
				int leftOver = bfs(allyGroup) + bfs(enemyGroup);
				if(leftOver == 0) {
					min = Math.min(min, Math.abs(allyPopulation - enemyPopulation));
				}
			}
			return;
		}
		
		ally[idx] = true;
		dfs(idx+1);
		
		ally[idx] = false;
		dfs(idx+1);
	}
	
	static int bfs(HashSet<Integer> group) {
		
		int first = (int)group.toArray()[0];
		
		Queue<Integer> q = new LinkedList<>();
		q.add(first);
		group.remove(first);
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(int next : graph[cur]) {
				if(group.contains(next)) {
					q.add(next);
					group.remove(next);
				}
			}
		}
		
		return group.size();
	}
}