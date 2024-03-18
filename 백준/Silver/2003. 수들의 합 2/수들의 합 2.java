import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		init(in);
		
		solve(in);
		
		System.out.println(cnt);
	}
	
	static int size;
	static int goal;
	static int[] arr;
	static int cnt;
	static void init(BufferedReader in) throws IOException{
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		size = Integer.parseInt(tokens.nextToken());
		goal = Integer.parseInt(tokens.nextToken());
		arr = new int[size];
		cnt = 0;
		tokens = new StringTokenizer(in.readLine());
		for(int i =0; i < size; i++) {
			int value = Integer.parseInt(tokens.nextToken());
			arr[i] = value;
		}
	}
	
	static void solve(BufferedReader in) throws IOException {
		int start = 0;
		int end = 0;
		int val = arr[0];
		while(start <= end && end < size) {
			if(val == goal) {
				cnt++;
				val -= arr[start];
				start++;
				end++;
				if(end < size) {
					val += arr[end];					
				}
			} else if(val < goal) {
				end++;
				if(end < size) {
					val += arr[end];					
				}
			} else {
				if(start == end) {
					val -= arr[start];
					start++;
					end++;
					if(end < size) {
						val += arr[end];					
					}
				} else {
					val -= arr[start];
					start++;					
				}
			}
		}
	}
	
}


