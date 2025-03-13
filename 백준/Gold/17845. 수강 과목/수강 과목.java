import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static int maxTime;
	static int nSubject;
	static int[] importance;
	static int[] time;
	static void input(BufferedReader in) throws IOException {		
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		maxTime = Integer.parseInt(tokens.nextToken());
		nSubject = Integer.parseInt(tokens.nextToken());
		
		importance = new int[nSubject];
		time = new int[nSubject];
		
		for(int i = 0; i < nSubject; i++) {
			tokens = new StringTokenizer(in.readLine());
			importance[i] = Integer.parseInt(tokens.nextToken());
			time[i] = Integer.parseInt(tokens.nextToken());
		}
	}
	
	static void solve(){
		int[] max = new int[maxTime+1];
		
		for(int i = 0; i < nSubject; i++) {
			for(int t = maxTime; t >= time[i]; t--) {
				max[t] = Math.max(max[t], max[t - time[i]] + importance[i]);
			}
		}
		
		System.out.println(max[maxTime]);
	}
	
}