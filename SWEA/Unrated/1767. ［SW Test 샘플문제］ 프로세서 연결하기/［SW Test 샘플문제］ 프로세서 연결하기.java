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
	
	static int size;
	static boolean[][] matrix;
	static ArrayList<Core> cores;
	static int maxCore;
	static int minLength;
	static void init(BufferedReader in) throws IOException {
		size = Integer.parseInt(in.readLine());
		matrix = new boolean[size][size];
		cores = new ArrayList<>();
		maxCore = 0;
		minLength = Integer.MAX_VALUE;
		for(int y = 0; y < size; y++) {
			StringTokenizer tokens = new StringTokenizer(in.readLine());
			for(int x = 0; x < size; x++) {
				boolean isCore = (tokens.nextToken().charAt(0) == '1');
				matrix[y][x] = isCore;
				if(isCore) cores.add(new Core(x, y));
			}
		}
		//System.out.println("cores : " + cores.size());
	}
	
	static void solve(int t) {
		dfs(0, 0, 0);
		System.out.println(String.format("#%d %d", t, minLength));
	}
	
	static void dfs(int idx, int cnt, int length) {
		if(idx == cores.size()) {
			if(maxCore < cnt) {
				maxCore = cnt;
				minLength = length;
			} else if(maxCore == cnt && length < minLength) {
				minLength = length;
			}
			return;
		}
		
		Core core = cores.get(idx);
		
		if(core.x == 0 || core.x == size-1 || core.y == 0 || core.y == size-1) {
			dfs(idx+1, cnt+1, length);
		} else {
			//up
			boolean isAbove = false;
			boolean isRight = false;
			boolean isUnder = false;
			boolean isLeft = false;
			for(int i = 0; i < cores.size(); i++) {
				if(i == idx) continue;
				
				Core other = cores.get(i);
				
				if(other.y < core.y && !isAbove) {
					if((other.x == core.x) 
							||(other.x < core.x && other.cable[1]) 
							|| (other.x > core.x && other.cable[3])) {
						isAbove = true;
					}
				}
				if(other.x > core.x && !isRight) {
					if((other.y == core.y) 
							||(other.y < core.y && other.cable[2]) 
							|| (other.y > core.y && other.cable[0])) {
						isRight = true;
					}
				}
				if(other.y > core.y && !isUnder) {
					if((other.x == core.x) 
							||(other.x < core.x && other.cable[1]) 
							|| (other.x > core.x && other.cable[3])) {
						isUnder = true;
					}
				}
				if(other.x < core.x && !isLeft) {
					if((other.y == core.y) 
							||(other.y < core.y && other.cable[2]) 
							|| (other.y > core.y && other.cable[0])) {
						isLeft = true;
					}
				}
			}
			if(!isAbove) {
				int cableLength = core.y;
				core.cable[0] = true;
				dfs(idx+1, cnt+1, length+cableLength);
				core.cable[0] = false;
			}
			
			if(!isRight) {
				int cableLength = size - 1 - core.x;
				core.cable[1] = true;
				dfs(idx+1, cnt+1, length+cableLength);
				core.cable[1] = false;
			}
			
			if(!isUnder) {
				int cableLength = size - 1 - core.y;
				core.cable[2] = true;
				dfs(idx+1, cnt+1, length+cableLength);
				core.cable[2] = false;
			}
			
			if(!isLeft) {
				int cableLength = core.x;
				core.cable[3] = true;
				dfs(idx+1, cnt+1, length+cableLength);
				core.cable[3] = false;
			}
			
			dfs(idx+1, cnt, length);
		}
	}
	
	static class Core{
		int x;
		int y;
		
		boolean[] cable = new boolean[4]; // 0: up, 1: right, 2: down, 3: left
		
		public Core(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
