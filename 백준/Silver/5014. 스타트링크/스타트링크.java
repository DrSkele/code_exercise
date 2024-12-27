import java.io.*;
import java.util.*;

class Main{
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();		
	}
	
	static int height;
	static int start;
	static int goal;
	static int up;
	static int down;
	static int[] building;
	static boolean[] visited;
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		height = Integer.parseInt(tokens.nextToken());
		start = Integer.parseInt(tokens.nextToken())-1;
		goal = Integer.parseInt(tokens.nextToken())-1;
		up = Integer.parseInt(tokens.nextToken());
		down = Integer.parseInt(tokens.nextToken());
		building = new int[height];
		visited = new boolean[height];
	}
	
	static void solve() {
		navigate();
		
		if(start == goal) System.out.println(0);
		else if(building[goal] == 0) System.out.println("use the stairs");
		else System.out.println(building[goal]);
	}
	
	static int[] move;
	static void navigate() {
		
		ArrayDeque<Integer> q = new ArrayDeque<>();
		move = new int[] {up, -down};
		
		q.add(start);
		visited[start] = true;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			int cnt = building[cur];
			
			for(int i = 0; i < move.length; i++) {
				int next = cur + move[i];
				
				if(next < 0 || next >= height) continue;
				if(visited[next]) continue;
				
				q.add(next);
				building[next] = cnt + 1;
				visited[next] = true;
			}
		}
	}
}