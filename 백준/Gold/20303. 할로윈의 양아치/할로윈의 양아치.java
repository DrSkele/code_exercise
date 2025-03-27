import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static int kids;
	static int relationships;
	static int threshold;
	static int[] candy;
	static ArrayList<Integer>[] relationship;
	static void input(BufferedReader in) throws IOException {		
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		
		kids = Integer.parseInt(tokens.nextToken());
		relationships = Integer.parseInt(tokens.nextToken());
		threshold = Integer.parseInt(tokens.nextToken());
		
		candy = new int[kids+1];
		relationship = new ArrayList[kids+1];
		
		for(int i = 1; i <= kids; i++) {
			relationship[i] = new ArrayList<>();
		}
		
		tokens = new StringTokenizer(in.readLine());
		
		for(int i = 1; i <= kids; i++) {
			candy[i] = Integer.parseInt(tokens.nextToken());
		}
		
		for(int i = 0; i < relationships; i++) {
			tokens = new StringTokenizer(in.readLine());
			
			int k1 = Integer.parseInt(tokens.nextToken());
			int k2 = Integer.parseInt(tokens.nextToken());
			
			relationship[k1].add(k2);
			relationship[k2].add(k1);
		}
	}
	
	static void solve(){
		setup();
		System.out.println(findMax());
	}
	
	static ArrayList<Group> groups;
	static void setup() {
		groups = new ArrayList<>();
		boolean[] visited = new boolean[kids+1];
		for(int i = 1; i <= kids; i++) {
			if(visited[i]) continue;
			
			ArrayDeque<Integer> q = new ArrayDeque<>();
			
			q.add(i);
			visited[i] = true;
			
			int cnt = 0;
			int sum = 0;
			
			while(!q.isEmpty()) {
				int cur = q.poll();
				
				cnt++;
				sum += candy[cur];
				
				for(int next : relationship[cur]) {
					if(visited[next]) continue;
					
					q.add(next);
					visited[next] = true;
				}
			}
			
			groups.add(new Group(cnt, sum));
		}
	}
	
	static long findMax() {
		
		long dp[] = new long[threshold];
		
		Arrays.fill(dp, -1);
		dp[0] = 0;
		
		for(Group group : groups) {
			int cnt = group.kids;
			int sum = group.candy;
			for(int i = threshold-1; i >= cnt; i--) {
				if(dp[i-cnt] != -1) {
					dp[i] = Math.max(dp[i], dp[i-cnt] + sum);					
				}
			}
		}
		
		long max = 0;
		
		for(long cnt : dp) {
			if(cnt > 0) max = Math.max(max, cnt);
		}
		
		return max;
	}
	
	static class Group{
		int kids;
		int candy;
		public Group(int kids, int candy) {
			this.kids = kids;
			this.candy = candy;
		}
	}
}