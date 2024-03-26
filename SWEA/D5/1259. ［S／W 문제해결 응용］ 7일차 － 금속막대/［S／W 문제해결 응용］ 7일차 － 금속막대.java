import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		
		for(int t = 1; t <= T; t++) {
			init(in);
			
			solve(t);
		}
	}
	
	static int N;
	static Pipe[] pipes;
	static boolean[] used;
	static ArrayDeque<Pipe> linked;
	static int max;
	static ArrayDeque<Pipe> connected;
	static void init(BufferedReader in) throws IOException {
		N = Integer.parseInt(in.readLine());
		pipes = new Pipe[N];
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			pipes[i] = new Pipe(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
		}
		used = new boolean[N];
		linked = new ArrayDeque<>();
		max = 0;
	}
	
	static void solve(int t) throws IOException {
		
		for(int i = 0; i < pipes.length; i++) {
			used[i] = true;
			linked.push(pipes[i]);
			dfs(pipes[i].nut);
			used[i] = false;
			linked.pop();
		}
		
		StringBuilder str = new StringBuilder();
		
		while(!connected.isEmpty()) {
			Pipe p = connected.pollLast();
			str.append(p.bolt).append(" ").append(p.nut).append(" ");
		}
		
		System.out.println(String.format("#%d %s", t, str));
	}
	
	static void dfs(int nut) {
		
		boolean last = true;
		for(int i = 0; i < pipes.length; i++) {
			if(used[i]) continue;
			
			if(nut == pipes[i].bolt) {
				last = false;
				
				used[i] = true;
				linked.push(pipes[i]);
				dfs(pipes[i].nut);
				used[i] = false;
				linked.pop();
			}
		}
		
		if(last) {
			if(linked.size() > max) {
				max = linked.size();
				connected = linked.clone();
			}
		}
	}
	
	static class Pipe{
		int bolt;
		int nut;
		public Pipe(int bolt, int nut) {
			this.bolt = bolt;
			this.nut = nut;
		}
	}
}
