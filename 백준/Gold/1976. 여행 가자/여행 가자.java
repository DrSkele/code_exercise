import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static int nCity;
	static int nVisit;
	static int[] group;
	static int[] planned;
	static void input(BufferedReader in) throws IOException {		
		nCity = Integer.parseInt(in.readLine());
		nVisit = Integer.parseInt(in.readLine());
		
		group = new int[nCity+1];
		for(int i = 1; i <= nCity; i++) {
			group[i] = i;
		}
		
		for(int i = 1; i <= nCity; i++) {
			StringTokenizer tokens = new StringTokenizer(in.readLine());
			for(int j = 1; j <= nCity; j++) {
				boolean connected = tokens.nextToken().equals("1");
				
				if(connected) {
					union(i, j);
				}
			}
		}
		
		planned = new int[nVisit];
		
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		for(int i = 0; i < nVisit; i++) {
			planned[i] = Integer.parseInt(tokens.nextToken());
		}
	}
	
	static void solve(){
		boolean possible = true;
		int idx = find(planned[0]);
		for(int i = 1; i < nVisit; i++) {
			if(find(planned[i]) != idx) {
				possible = false;
				break;
			}
		}
		
		System.out.println(possible ? "YES" : "NO");
	}
	
	static int find(int n) {
		if(group[n] == n) return n;
		
		return group[n] = find(group[n]);
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a == b) return;
		
		if(a < b) {
			group[b] = a;
		} else {
			group[a] = b;
		}
	}
}