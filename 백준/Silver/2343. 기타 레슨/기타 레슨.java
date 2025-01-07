import java.io.*;
import java.util.*;

class Main{
	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();		
	}
	
	static int cnt;
	static int goal;
	static int[] course;
	static int longest; // longest course
	static int sum; // sum of all course
	static void input(BufferedReader in) throws IOException {	
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		cnt = Integer.parseInt(tokens.nextToken());
		goal = Integer.parseInt(tokens.nextToken());
		course = new int[cnt];
		longest = 0;
		sum = 0;
		tokens = new StringTokenizer(in.readLine());
		for(int i = 0; i < cnt; i++) {
			int val = Integer.parseInt(tokens.nextToken());
			course[i] = val;
			longest = Math.max(longest, val);
			sum += val;
		}
	}
	
	static void solve() {
		System.out.println(findMinimum());
	}
	
	static int findMinimum() {
		int result = 0;
		int low = longest;
		int high = sum;
		
		while(low <= high) {
			int mid = low + (high - low)/2;
			
			int disk = countRequiredDisk(mid);
			
			//System.out.println(low + " " + high + " " + disk);
			
			if(disk <= goal) {
				high = mid - 1;
				result = mid;
			} else {
				low = mid + 1;
			}
		}
		return result;
	}
	
	static int countRequiredDisk(int length) {
		int disk = 1;
		int totalLength = 0;
		
		for(int i = 0; i < cnt; i++) {
			int cur = course[i];
			if(totalLength + cur <= length) {
				totalLength += cur;
			} else {
				disk++;
				totalLength = cur;
			}
		}
		
		return disk;
	}
}