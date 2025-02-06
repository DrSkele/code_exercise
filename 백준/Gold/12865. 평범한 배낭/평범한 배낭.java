import java.io.*;
import java.util.*;

public class Main {

	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		init(in);
		
		solve(in);			
	}
	
	static int nThings;
	static int maxWeight;
	static int[] weight;
	static int[] value;
	public static void init(BufferedReader in) throws IOException{
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		
		nThings = Integer.parseInt(tokens.nextToken());
		maxWeight = Integer.parseInt(tokens.nextToken());
		
		weight = new int[nThings];
		value = new int[nThings];
		
		for(int i = 0; i < nThings; i++) {
			tokens = new StringTokenizer(in.readLine());
			weight[i] = Integer.parseInt(tokens.nextToken());
			value[i] = Integer.parseInt(tokens.nextToken());
		}
	}
	
	public static void solve(BufferedReader in) throws IOException {
		
		int[] dp = new int[maxWeight+1];
		
		for(int i = 0; i < nThings; i++) {
			int curWeight = weight[i];
			int curVal = value[i];
			for(int j = maxWeight; j >= curWeight; j--) {
				dp[j] = Math.max(dp[j], dp[j-curWeight] + curVal);									
			}
		}
		
		System.out.println(dp[maxWeight]);
	}
}
