import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int weight;
	static Item[] items;
	static int[] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		init(in);
		
		solve();
		
	}

	static void init(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		N = Integer.parseInt(tokens.nextToken());
		weight = Integer.parseInt(tokens.nextToken());
		items = new Item[N];
		dp = new int[weight+1];
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(in.readLine());
			items[i] = new Item(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
		}
		Arrays.sort(items);
	}
	
	static void solve() {
		dp();
		System.out.println(dp[weight]);
	}
	
	static void dp() {
		
		for(int n = 0; n < N; n++) {
			Item item = items[n];
			for(int w = weight; w >= item.weight; w--) {
				dp[w] = Math.max(dp[w], dp[w-item.weight] + item.value);
			}
		}
	}
	
	static class Item implements Comparable<Item>{
		public int weight;
		public int value;
		public Item(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}
		
		@Override
		public int compareTo(Item i) {
			int result = 0;
			if(weight < i.weight) result = -1;
			else if(weight > i.weight) result = 1;
			else if(value > i.value) result = -1;
			else if(value < i.value) result = 1;
			return result;
		}
	}
}