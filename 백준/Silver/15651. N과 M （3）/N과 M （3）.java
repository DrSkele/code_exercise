import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int M;
	static int[] arr;
	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		arr = new int[M];
		str = new StringBuilder();
		
		permutation(0);
		
		System.out.println(str.toString());
	}
	
	static void permutation(int idx) {
		if(idx >= M) {
			for(int n : arr) {
				str.append(n).append(" ");
			}
			str.append("\n");
			return;
		}
		
		for(int i = 1; i <= N; i++) {
			arr[idx] = i;
			permutation(idx+1);
		}
	}
}