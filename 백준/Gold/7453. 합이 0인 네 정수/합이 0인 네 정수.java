import java.io.*;
import java.util.*;

class Main{
	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();		
	}
	
	static int length;
	static long[][] arr;
	static void input(BufferedReader in) throws IOException {
		length = Integer.parseInt(in.readLine());
		
		arr = new long[4][length];
		for(int i = 0; i < length; i++) {
			StringTokenizer tokens = new StringTokenizer(in.readLine());
			for(int j = 0; j < 4; j++) {
				arr[j][i] = Long.parseLong(tokens.nextToken());
			}
		}
	}
	
	static void solve() {
		
		long[] arrAB = new long[length * length];
		long[] arrCD = new long[length * length];
		int idxAB = 0;
		int idxCD = 0;
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < length; j++) {
				arrAB[idxAB++] = arr[0][i] + arr[1][j];
				arrCD[idxCD++] = arr[2][i] + arr[3][j];
			}
		}
		
		Arrays.sort(arrCD);
		
		long cnt = 0;
		
		for(int i = 0; i < length * length; i++) {
			long fixed = arrAB[i];
			
			int lowerBound = 0;
			int left = 0; 
			int right = length * length - 1;
			
			while(left <= right) {
				int mid = left + (right - left) / 2;
				
				if(arrCD[mid] + fixed >= 0) {
					lowerBound = mid;
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			}
			
			if(fixed + arrCD[lowerBound] != 0) continue;
			
			int upperBound = length * length - 1;
			left = 0;
			right = length * length - 1;
			
			while(left <= right) {
				int mid = left + (right - left) / 2;
				
				if(arrCD[mid] + fixed <= 0) {
					upperBound = mid;
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}
			
			cnt += upperBound - lowerBound + 1;
		}
		
		System.out.println(cnt);
	}
}