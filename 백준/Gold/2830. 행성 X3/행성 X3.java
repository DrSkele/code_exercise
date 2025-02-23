import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		input(in);
		solve();
	}
	
	static int cnt;
	static long[] bit;
	static void input(BufferedReader in) throws IOException {
		cnt = Integer.parseInt(in.readLine());
		bit = new long[20];
		
		for(int i = 0; i < cnt; i++) {
			int citizen = Integer.parseInt(in.readLine());
			for(int j = 0; j < 20; j++) {
				if((citizen & (1 << j)) == (1 << j)) bit[j]++;
			}
		}
	}
	
	static void solve() {
		long sum = 0;
		
		for(int i = 0; i < 20; i++) {
			sum += bit[i] * ((cnt - bit[i]) * (1 << i));
		}
		
		System.out.println(sum);
	}
}