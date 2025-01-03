import java.io.*;
import java.util.*;

class Main{
	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();		
	}
	
	static int size;
	static int cnt;
	static List<int[]> chicken;
	static List<int[]> house;
	static int[][] dist;
	static int[] temp;
	static int min;
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		size = Integer.parseInt(tokens.nextToken());
		cnt = Integer.parseInt(tokens.nextToken());
		
		chicken = new ArrayList<>();
		house = new ArrayList<>();
		
		for(int y = 0; y < size; y++) {
			tokens = new StringTokenizer(in.readLine());
			for(int x = 0; x < size; x++) {
				String cur = tokens.nextToken();
				switch(cur) {
					case "1" : 
						house.add(new int[] {y, x});
						break;
					case "2" : 
						chicken.add(new int[] {y, x});
						break;
					default: break;
				}
			}
		}
		min = Integer.MAX_VALUE;
		
		dist = new int[house.size()][chicken.size()];
		for(int i = 0; i < house.size(); i++) {
			int[] from = house.get(i);
			
			for(int j = 0; j < chicken.size(); j++) {
				int[] to = chicken.get(j);
				
				dist[i][j] = Math.abs(from[0] - to[0]) + Math.abs(from[1] - to[1]);
			}
		}
		
		temp = new int[cnt];
	}
	
	static void solve() {
		comb(-1, 0);
		System.out.println(min);
	}
	
	static void comb(int idx, int picked) {
		if(picked == cnt) {
			int sum = 0;
			
			for(int i = 0; i < house.size(); i++) {
				int minDist = Integer.MAX_VALUE;
				for(int j : temp) {
					minDist = Math.min(minDist, dist[i][j]);
				}
				sum += minDist;
			}
			
			min = Math.min(min, sum);
			return;
		}
		
		for(int i = idx + 1; i < chicken.size(); i++) {
			temp[picked] = i;
			comb(i, picked+1);
		}
	}
}