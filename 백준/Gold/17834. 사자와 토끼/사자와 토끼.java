import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();		
	}
	
	static int nBush;
	static int nPath;
	static ArrayList<Integer>[] paths;
	static int[] mark;
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		nBush = Integer.parseInt(tokens.nextToken());
		nPath = Integer.parseInt(tokens.nextToken());
		
		paths = new ArrayList[nBush+1];
		
		for(int i = 1; i < nBush+1; i++) {
			paths[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < nPath; i++) {
			tokens = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(tokens.nextToken());
			int to = Integer.parseInt(tokens.nextToken());
			
			paths[from].add(to);
			paths[to].add(from);
		}
		mark = new int[nBush+1];
	}
	
	static void solve(){
		System.out.println(checkCycle());
	}
	
	static int checkCycle() {
		
		boolean hasCycle = false;
		
		ArrayDeque<Integer> q = new ArrayDeque<>();
		
		q.add(1);
		mark[1] = 1;
		
		Loop : while(!q.isEmpty()) {
			int cur = q.poll();
			int curMark = mark[cur];
			
			for(int next : paths[cur]) {
				if(mark[next] == 0) {
					q.add(next);
					mark[next] = (curMark % 2) + 1;
				} else if(mark[next] == curMark) {
					hasCycle = true;
					break Loop;
				}
			}
		}
		
		if(hasCycle) return 0;
		
		int oddCnt = 0;
		
		for(int bush : mark) {
			if(bush == 1) oddCnt++;
		}
		
		int evenCnt = nBush - oddCnt;
		
		return oddCnt * evenCnt * 2;
	}
}