
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		init(in);
		
		solve();
	}
	
	static int N;
	static long[] arr;
	static long[] sum;
	static void init(BufferedReader in) throws IOException{
		N = Integer.parseInt(in.readLine());
		arr = new long[N];
		for(int i = 0; i < N; i++) {
			long value = Long.parseLong(in.readLine()); 
			arr[i] = value;
		}
		Arrays.sort(arr);
		sum = new long[N*N];
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				sum[cnt] = arr[i] + arr[j];
				//System.out.print(sum[cnt]+ " ");
				cnt++;
			}
		}
		Arrays.sort(sum);
	}
	
	static void solve() {
		for(int i = N-1; i >= 0; i--) {
			for(int j = i; j >= 0; j--) {
				long sub = arr[i] - arr[j];
				int idx = search(0, sum.length-1, sub);
				//System.out.println(sub + " " + sum[idx]);
				if(sum[idx] == sub) {
					System.out.println(arr[i]);
					return;
				}
			}
		}
	}
	
	static int search(int left, int right, long goal) {
		while(left != right) {
			int mid = left + (right-left)/2;
			if(sum[mid] >= goal) right = mid;
			else left = mid+1;
		}
		return left;
	}
}


