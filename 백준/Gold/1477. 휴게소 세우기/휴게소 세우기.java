import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		init(in);
		
		solve();
	}
	
	static int curStation;
	static int build;
	static int length;
	static int[] stations;
	static PriorityQueue<DistBetween> pq;
	static StringTokenizer tokens;
	
	static void init(BufferedReader in) throws IOException{
		tokens = new StringTokenizer(in.readLine());
		curStation = Integer.parseInt(tokens.nextToken());
		build = Integer.parseInt(tokens.nextToken());
		length = Integer.parseInt(tokens.nextToken());
		pq = new PriorityQueue<>();
		
		if(curStation > 0) {
			stations = new int[curStation];
			
			tokens = new StringTokenizer(in.readLine());
			for(int i = 0; i < curStation; i++) {
				stations[i] = Integer.parseInt(tokens.nextToken());
			}
			Arrays.sort(stations);
			
			for(int i = 0; i < curStation - 1; i++) {
				int first = stations[i];
				int second = stations[i+1];
				pq.add(new DistBetween(first, second));
			}
			pq.add(new DistBetween(0, stations[0]));
			pq.add(new DistBetween(stations[curStation-1], length));
		} else {
			pq.add(new DistBetween(0, length));
		}
	}
	
	static void solve() {
		while(build > 0) {
			DistBetween twoStation = pq.poll();
			int next = pq.isEmpty() ? 0 : pq.peek().dist();
			int n = 0;
			while(next <= twoStation.dist() && n < build) {
				n++;
				twoStation.stationBetween += 1;
			}
			build -= n;
			pq.add(twoStation);
		}
		
		System.out.println(pq.peek().dist());
	}
	
	static class DistBetween implements Comparable<DistBetween>{
		public int first;
		public int second;
		public int stationBetween = 0;
		public DistBetween(int first, int second) {
			this.first = first;
			this.second = second;
		}
		public int dist(){
			int between = second-first;
			return (int) Math.ceil((double) between / (stationBetween + 1));
		}
		@Override
		public int compareTo(DistBetween d) {
			int result = 0;
			if(dist() > d.dist()) result = -1;
			else if(dist() < d.dist()) result = 1;
			return result;
		}
	}
}


