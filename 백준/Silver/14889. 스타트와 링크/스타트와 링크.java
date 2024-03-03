import java.io.*;
import java.util.*;

public class Main {

	static int size;
	static int[][] matrix;
	static HashSet<Integer> team;
	static int min;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		init(in);
		
		solve();
	}
	
	static void init(BufferedReader in) throws IOException{
		StringTokenizer tokens;
		
		size = Integer.parseInt(in.readLine());
		matrix = new int[size][size];
		team = new HashSet<>();
		min = Integer.MAX_VALUE;
		for(int i = 0; i < size; i++) {
			tokens = new StringTokenizer(in.readLine());
			for(int j = 0; j < size; j++) {
				matrix[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
	}
	
	static void solve() {
		combination(0, 0);
		
		System.out.println(min);
	}
	
	static void combination(int idx, int cnt) {
		if(idx > size) return;
		if(cnt >= size/2) {
			min = Math.min(min, evaluate());
			return;
		}
		
		team.add(idx);
		combination(idx+1, cnt+1);
		team.remove(idx);
		
		combination(idx+1, cnt);
	}
	
	static int evaluate() {
		int teamA = 0;
		int teamB = 0;
		for(int i = 0; i < size; i++) {
			if(team.contains(i)) {
				for(int j = 0; j < size; j++) {
					if(i == j) continue;
					if(team.contains(j)) teamA += matrix[i][j];
				}
			} else {
				for(int j = 0; j < size; j++) {
					if(i == j) continue;
					if(!team.contains(j)) teamB += matrix[i][j];
				}
			}
		}
		return Math.abs(teamA-teamB);
	}
}
