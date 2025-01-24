import java.io.*;
import java.util.*;
import java.util.Map.Entry;

class Main{
	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static int length;
	static int[] arr;
	static void input(BufferedReader in) throws IOException {
		length = Integer.parseInt(in.readLine());
		arr = new int[length];
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		for(int i = 0; i < length; i++) {
			arr[i] = Integer.parseInt(tokens.nextToken());
		}
	}
	
	static void solve() {
		int[] asc = new int[length];
		
		for(int i  = 0; i < length; i++) {
			int max = 1;
			for(int j = 0; j < i; j++) {
				if(arr[j] < arr[i]) max = Math.max(asc[j]+1, max);
			}
			asc[i] = max;
		}
		
		int[] desc = new int[length];
		
		for(int i  = length-1; i >= 0; i--) {
			int max = 1;
			for(int j = length-1; j > i; j--) {
				if(arr[j] < arr[i]) max = Math.max(desc[j]+1, max);
			}
			desc[i] = max;
		}
		
		int max = 1;
		for(int i = 0; i < length; i++) {
			//System.out.println(asc[i] + " " + desc[i]);
			max = Math.max(asc[i] + desc[i] - 1, max);
		}
		
		System.out.println(max);
	}
	
}