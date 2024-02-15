import java.io.*;
import java.util.*;

public class Main {
	
	static int[] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(in.readLine());
		
		int[] time = new int[n];
		long[] pay = new long[n];
		
		for(int i = 0; i < n; i++) {
			String[] line = in.readLine().split(" ");
			time[i] = Integer.parseInt(line[0]);
			pay[i] = Integer.parseInt(line[1]);
		}
		
		long[] salary = new long[n+1];
		for(int i = n-1; i >= 0; i--) {
			int need = time[i];
			if(need > (n - i)) {
				salary[i] = salary[i+1];
			}
			else {
				salary[i] = Math.max(salary[i+need] + pay[i], salary[i+1]);
			}
		}
		System.out.println(salary[0]);
	}
}