import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();		
	}
	
	static int size;
	static int red;
	static int green;
	static int blue;
	static long dp[][][][];
	static long max;
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		size = Integer.parseInt(tokens.nextToken());
		red = Integer.parseInt(tokens.nextToken());
		green = Integer.parseInt(tokens.nextToken());
		blue = Integer.parseInt(tokens.nextToken());
		
		dp = new long[size+1][red+1][green+1][blue+1];
		
		for(int i = 0; i <= size; i++) {
			for(int j = 0; j <= red; j++) {
				for(int k = 0; k <= green; k++) {
					for(int l = 0; l <= blue; l++) {
						dp[i][j][k][l] = -1;
					}
				}
			}
		}
		
		max = 0;
	}
	
	static void solve(){
		System.out.println(tree(1, red, green, blue));
	}
	
	static long tree(int level, int red, int green, int blue) {
		if(level > size) return 1;
		
		long val = dp[level][red][green][blue];
		
		if(val == -1) {
			val = 0;
			
			if(level % 3 == 0) { // three type
				int req = level / 3;
				if(red >= req && green >= req && blue >= req) {
					val += tree(level+1, red - req, green - req, blue - req) * combination(level, req) * combination(level - req, req);
				}
			} 
			
			if(level % 2 == 0) { // two type
				int req = level / 2;
				
				if(red >= req && green >= req) {
					val += tree(level+1, red - req, green - req, blue) * combination(level, req);
				}
				if(green >= req && blue >= req) {
					val += tree(level+1, red, green - req, blue - req) * combination(level, req);
				}
				if(blue >= req && red >= req) {
					val += tree(level+1, red - req, green, blue - req) * combination(level, req);
				}
			} 
			
			// single type
			if(red >= level) {
				val += tree(level+1, red - level, green, blue);
			}
			
			if(green >= level) {
				val += tree(level+1, red, green - level, blue);
			}
			
			if(blue >= level) {
				val += tree(level+1, red, green, blue - level);
			}
			
			dp[level][red][green][blue] = val;
			
			//System.out.println(level + " " + red + " " + green + " " + blue + " " + val);
		}
		
		return dp[level][red][green][blue];
		
	}
	
	public static int combination(int n, int r) {
		return factorial(n)/(factorial(r)*factorial(n-r));
	}
	public static int factorial(int x) {
		if(x == 1) return 1;
		return x*factorial(x-1);
	}
}