import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		init(in);
		
		solve();
	}
	
	static int N;
	static Building[] building;
	static void init(BufferedReader in) throws IOException{
		N = Integer.parseInt(in.readLine());
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		building = new Building[N];
		for(int i = 0; i < N; i++) {
			building[i] = new Building(i+1, Integer.parseInt(tokens.nextToken()));
		}
	}
	
	static void solve() {
		
		Stack<Building> left = new Stack<>();
		Stack<Building> right = new Stack<>();
		
		for(int i = 0; i < N; i++) {
			
			if(!left.isEmpty()) {
				while(!left.isEmpty() && left.peek().height <= building[i].height) {
					left.pop();
				}
				if(!left.isEmpty()) building[i].closest = left.peek().idx;
				left.add(building[i]);
			} else {
				left.add(building[i]);
			}
			building[i].view = left.size()-1;
		}
		for(int i = N-1; i >= 0; i--) {
			
			if(!right.isEmpty()) {
				
				while(!right.isEmpty() && right.peek().height <= building[i].height) {
					right.pop();
				}
				if(!right.isEmpty()) {
					int oldVal = Math.abs(building[i].idx - building[i].closest);
					int newVal = Math.abs(building[i].idx - right.peek().idx);
					if(oldVal == newVal) {
						building[i].closest = Math.min(right.peek().idx, building[i].closest);
					} else if(newVal < oldVal) {
						building[i].closest = right.peek().idx;						
					}
				}
				right.add(building[i]);
			} else {
				right.add(building[i]);
			}
			building[i].view += right.size()-1;
		}
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < N; i++) {
			if(building[i].view == 0)
				str.append(0).append("\n");
			else {
				str.append(building[i].view).append(" ").append(building[i].closest).append("\n");
			}
		}
		System.out.println(str.toString());
	}
	
	static class Building{
		int idx = 0;
		int height = 0;
		int view = 0;
		int closest = 100_000;
		public Building(int idx, int height) {
			this.idx = idx;
			this.height = height;
		}
	}
}


