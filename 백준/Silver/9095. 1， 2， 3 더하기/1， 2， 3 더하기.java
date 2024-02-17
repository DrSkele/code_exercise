import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int[] values;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		values = new int[11];
		
		values[0] = 1;
		for(int i = 1; i <= 10; i++) {
			get(0, i);
		}
		
		for(int t = 0; t < T; t++) {
			N = Integer.parseInt(in.readLine());
			
			System.out.println(values[N]);
		}
	}
	
	 static void get(int value, int goal) {
		 if(value == goal) {
			 values[goal]++;
			 return;
		 }
		 
		 for(int i = 1; i <= 3; i++) {
			 if(value + i <= goal)
				 get(value + i, goal);
		 }
	 }
}