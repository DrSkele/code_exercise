import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		init(in);
	}
	static int length;
	static int cnt;
	static int[] arr;
	static int[] sums;
	static void init(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		length = Integer.parseInt(tokens.nextToken());
		cnt = Integer.parseInt(tokens.nextToken());
		arr = new int[length];
		sums = new int[length];
		int sum = 0;
		tokens = new StringTokenizer(in.readLine());
		for(int i = 0; i < length; i++) {
			arr[i] = Integer.parseInt(tokens.nextToken());
			sum += arr[i];
			sums[i] = sum;
		}
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < cnt; i++) {
			tokens = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(tokens.nextToken())-1;
			int end = Integer.parseInt(tokens.nextToken())-1;
			str.append(sums[end] - sums[start] + arr[start]).append("\n");
		}
		System.out.println(str.toString());
	}
}
