import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static int nPart;
	static int heal;
	static int[] points;
	static int[] costs;
	static void input(BufferedReader in) throws IOException {		
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		nPart = Integer.parseInt(tokens.nextToken());
		heal = Integer.parseInt(tokens.nextToken());
		
		points = new int[nPart];
		costs = new int[nPart];
		
		tokens = new StringTokenizer(in.readLine());
		for(int i = 0; i < nPart; i++) {
			points[i] = Integer.parseInt(tokens.nextToken());
		}
		tokens = new StringTokenizer(in.readLine());
		for(int i = 0; i < nPart; i++) {
			costs[i] = Integer.parseInt(tokens.nextToken());
		}
	}
	
	static void solve(){
		int[] maxPoints = new int[101];
		
		for(int i = 0; i < nPart; i++) {
			for(int h = 100; h >= costs[i]; h--) {
				maxPoints[h] = Math.max(maxPoints[h], maxPoints[h-costs[i]] + points[i]);
			}
			
			for(int h = 0; h <= 100 - heal; h++) {
				maxPoints[h] = maxPoints[h+heal];
			}
			for(int h = 100 - heal + 1; h <= 100; h++) {
				maxPoints[h] = maxPoints[100 - heal];
			}
		}
		
		int max = 0;
		
		for(int i = 0; i <= 100; i++) {
			max = Math.max(max, maxPoints[i]);
		}
		
		System.out.println(max);
	}
	
}