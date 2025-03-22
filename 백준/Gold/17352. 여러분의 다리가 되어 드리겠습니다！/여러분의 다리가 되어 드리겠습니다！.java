import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static int nIslands;
	static int[] group;
	static void input(BufferedReader in) throws IOException {		
		nIslands = Integer.parseInt(in.readLine());
		
		group = new int[nIslands+1];
		
		for(int i = 0; i <= nIslands; i++) {
			group[i] = i;
		}
		
		for(int i = 0; i < nIslands-2; i++) {
			StringTokenizer tokens = new StringTokenizer(in.readLine());
			
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			
			union(a, b);
		}
	}
	
	static void solve(){
		int a = find(1);
		
		for(int i = 2; i <= nIslands; i++) {
			if(find(i) != a) {
				System.out.println(a + " " + i);
				break;
			}
		}
	}
	
	static int find(int n) {
		if(group[n] == n) return n;
		
		return group[n] = find(group[n]);
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a < b)
			group[b] = a;
		else
			group[a] = b;
	}
}