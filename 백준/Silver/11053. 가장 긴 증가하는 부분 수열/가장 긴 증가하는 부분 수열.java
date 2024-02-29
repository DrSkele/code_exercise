import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] arr;
	static int[] asc;
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		init(in);
		
		System.out.println(max);

	}

	static void init(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		N = Integer.parseInt(tokens.nextToken());
		arr = new int[N];
		asc = new int[N];
		max = 0;
		
		tokens = new StringTokenizer(in.readLine());
		
		for(int i = 0; i < N; i++) {
			int cur =Integer.parseInt(tokens.nextToken());
			asc[i] = 1;
			arr[i] = cur;
			for(int j = 0; j < i; j++) {
				if(arr[j] < arr[i] && asc[i] < asc[j]+1) {
					asc[i] = asc[j]+1;
				}
			}
			max = Math.max(max, asc[i]);
		}
	}
}

