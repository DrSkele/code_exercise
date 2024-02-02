import java.util.*;
import java.io.*;

class Main{
	
	static ArrayList<ArrayList<Integer>> map;
	static int[] visited;
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		
		int testCase = Integer.parseInt(tokens.nextToken());
		
		for(int t = 0; t < testCase; t++) {
			tokens = new StringTokenizer(in.readLine());
			
			int vertex = Integer.parseInt(tokens.nextToken());
			int line = Integer.parseInt(tokens.nextToken());
			
			visited = new int[vertex];
			map = new ArrayList<>(vertex);
			
			for(int i = 0; i < vertex; i++) {
				map.add(new ArrayList<>());
			}
			
			for(int i = 0; i < line; i++) {
				tokens = new StringTokenizer(in.readLine());
				
				int v1 = Integer.parseInt(tokens.nextToken())-1;
				int v2 = Integer.parseInt(tokens.nextToken())-1;
				
				map.get(v1).add(v2);
				map.get(v2).add(v1);
			}
			
			boolean isBinary = true;
			int cnt = 0;
			
			for(int i = 0; i < vertex; i++) {
				if(visited[i] > 0) continue;
				
				isBinary = bfs(i);
				
				if(!isBinary) break;
			}
			
			String result = !isBinary || cnt > 1 ? "NO" : "YES";
			
			System.out.println(result);
		}
	}
	
	static boolean bfs(int start) {
		
		Queue<Integer> q = new LinkedList<>();
		
		q.add(start);
		visited[start] = 1;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			int curTeam = visited[cur];
			
			for(int next : map.get(cur)) {
				if(visited[next] == curTeam) {
					return false;
				} else if(visited[next] > 0) continue;
				
				q.add(next);
				visited[next] = curTeam == 1 ? 2 : 1;
			}
		}
		return true;
	}
}