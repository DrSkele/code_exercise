import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			StringTokenizer tokens = new StringTokenizer(in.readLine());
			nCandy = Integer.parseInt(tokens.nextToken());
			budget = (int)(Float.parseFloat(tokens.nextToken()) * 100 + 0.05f);
			
			if(nCandy == 0) break;
			
			calories = new int[nCandy];
			prices = new int[nCandy];
			
			for(int i = 0;i < nCandy; i++) {
				tokens = new StringTokenizer(in.readLine());
				
				calories[i] = Integer.parseInt(tokens.nextToken());
				prices[i] = (int)(Float.parseFloat(tokens.nextToken()) * 100 + 0.05f);
			}
			solve();					
		}
	}
	
	static int nCandy;
	static int budget;
	static int[] calories;
	static int[] prices;
	static void input(BufferedReader in) throws IOException {		
		
	}
	
	static void solve(){
		int[] maxCalorie = new int[budget+1];
		
		for(int i = 0; i < nCandy; i++) {
			for(int j = prices[i]; j <= budget; j++) {
				maxCalorie[j] = Math.max(maxCalorie[j], maxCalorie[j-prices[i]] + calories[i]);
			}
		}
		
		System.out.println(maxCalorie[budget]);
	}
	
}