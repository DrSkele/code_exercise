import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int totalDist;
	static int curGas;
	static PriorityQueue<GasStation> stations;
	static PriorityQueue<Integer> gas; // get highest gas value
	static int cnt; // number of gas fill

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		init(in);
		
		solve();
		
	}

	static void init(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		N = Integer.parseInt(tokens.nextToken());
		stations = new PriorityQueue<>();
		gas = new PriorityQueue<>(Collections.reverseOrder()); // poll highest gas value
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(in.readLine());
			GasStation gasStation = new GasStation(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
			stations.add(gasStation);
		}
		tokens = new StringTokenizer(in.readLine());
		totalDist = Integer.parseInt(tokens.nextToken());
		curGas = Integer.parseInt(tokens.nextToken());
	}
	
	static void solve() {
		while(curGas < totalDist) {
			if(!stations.isEmpty() && stations.peek().dist <= curGas) { // gas station within range
				GasStation station = stations.poll();
				gas.add(station.gas); // record available gas
			} else { // gas not enough to reach next station or no more stations to visit
				if(!gas.isEmpty()) { // more gas to fill
					cnt++; // increase count
					curGas += gas.poll();					
				} else { // no gas to fill
					// cannot reach next station
					break;
				}
			}
		}
		
		System.out.println(curGas >= totalDist ? cnt : -1);
	}
	
	static class GasStation implements Comparable<GasStation>{
		public int dist;
		public int gas;
		
		public GasStation(int dist, int gas) {
			this.dist = dist;
			this.gas = gas;
		}
		@Override
		public int compareTo(GasStation g) {
			int result = 0;
			if(dist < g.dist) result = -1;
			else if(dist > g.dist) result = 1;
			return result;
		}
	}
}

