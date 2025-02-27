import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();		
	}
	
	static int N;
	static int M;
	static String[] nums;
	static boolean[] used;
	static String[] temp;
	static HashSet<String> set;
	static void input(BufferedReader in) throws IOException {
		N = Integer.parseInt(in.readLine());
		M = Integer.parseInt(in.readLine());
		
		nums = new String[N];
		
		for(int i = 0; i < N; i++) {
			nums[i] = in.readLine();
		}
		
		used = new boolean[N];
		temp = new String[M];
		set = new HashSet<>();
	}
	
	static void solve() {
		permutation(0, 0);
		System.out.println(set.size());
	}
	
	static void permutation(int idx, int length) {
		if(length == M) {
			StringBuilder str = new StringBuilder();
			for(String s : temp) {
				str.append(s);
			}
			set.add(str.toString());
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(used[i]) continue;
			temp[length] = nums[i];
			used[i] = true;
			permutation(i+1, length+1);
			used[i] = false;
		}
	}
}