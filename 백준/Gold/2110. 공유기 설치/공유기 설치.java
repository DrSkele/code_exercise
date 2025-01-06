import java.io.*;
import java.util.*;

class Main{
	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();		
	}
	
	static int house;
	static int wifi;
	static int[] houses;
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		house = Integer.parseInt(tokens.nextToken());
		wifi = Integer.parseInt(tokens.nextToken());
		
		houses = new int[house];
		
		for(int i = 0; i < house; i++) {
			int dist = Integer.parseInt(in.readLine());
			houses[i] = dist;
		}
		
		Arrays.sort(houses);
	}
	
	static void solve() {
		System.out.println(findLongest());
	}
	
	static int findLongest() {
		int result = 0;
		int left = 0;
		int right = houses[houses.length - 1] - houses[0];
		
		while(left <= right) {
			int mid = left + (right - left)/2;
			
			int cnt = 1;
			int prevWifiIdx = 0;
			for(int i = 1; i < houses.length; i++) {
				int distBetween = houses[i] - houses[prevWifiIdx];
				if(distBetween >= mid) {
					cnt++;
					prevWifiIdx = i;
				}
			}
			
			if(cnt >= wifi) {
				left = mid + 1;
				result = mid;
			} else {
				right = mid - 1;
			}
		}
		
		return result;
	}
}