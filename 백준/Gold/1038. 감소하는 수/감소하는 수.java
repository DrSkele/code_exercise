import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static int nth;
	static ArrayList<Long> arr;
	static void input(BufferedReader in) throws IOException {
		nth = Integer.parseInt(in.readLine());
		arr = new ArrayList<>();
	}
	
	static void solve(){
		for(int i = 0; i <= 9; i++) {
			desc(i, i, 1);
		}
		Collections.sort(arr);
		if(nth < arr.size()) {
			System.out.println(arr.get(nth));
		} else {
			System.out.println(-1);
		}
	}
	
	static void desc(int n, long num, int length) {
		arr.add(num);
		long mul = (long)Math.pow(10, length);
		for(int i = n+1; i <= 9; i++) {
			desc(i, num + i * mul, length+1);
		}
	}
}