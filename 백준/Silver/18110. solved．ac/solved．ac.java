import java.io.*;
import java.util.*;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Main {

	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		init(in);
		
		solve();
	}
	
	static int N;
	static int[] arr;
	public static void init(BufferedReader in) throws IOException{
		N = Integer.parseInt(in.readLine());
		arr = new int[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(in.readLine());
		}
		Arrays.sort(arr);
	}
	
	public static void solve() {
		int cut = Math.round(N * 0.15f);
		int sum = 0;
		for(int i = cut; i < N - cut; i++) {
			sum += arr[i];
		}
		System.out.println(Math.round(sum / (float)(N-2*cut)));
	}
	
}
