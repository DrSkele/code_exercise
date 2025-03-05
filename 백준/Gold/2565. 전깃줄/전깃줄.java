import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();		
	}
	
	static int nCable;
	static Cable[] cables;
	static int[] maxCable;
	static void input(BufferedReader in) throws IOException {
		nCable = Integer.parseInt(in.readLine());
		
		cables = new Cable[nCable];
		
		for(int i = 0; i < nCable; i++) {
			StringTokenizer tokens = new StringTokenizer(in.readLine());
			cables[i] = new Cable(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
		}
		
		Arrays.sort(cables, new Comparator<Cable>() {
			public int compare(Cable a, Cable b) {
				return a.from - b.from;
			}
		});
		maxCable = new int[nCable];
		Arrays.fill(maxCable, -1);
	}
	
	static void solve(){
		int max = 0;
		
		for(int i = 0; i < nCable; i++) {
			max = Math.max(max, connect(i));
		}
		
		System.out.println(nCable - max);
	}
	
	static int connect(int idx) {
		int max = maxCable[idx];
		if(max == -1) {
			max = 1;
			for(int i = idx+1; i < nCable; i++) {
				if(cables[idx].to < cables[i].to) {
					max = Math.max(max, connect(i) + 1);					
				}
			}
		}
		return maxCable[idx] = max;
	}
	
	static class Cable {
		int from;
		int to;
		public Cable(int f, int t) {
			from = f;
			to = t;
		}
	}
}