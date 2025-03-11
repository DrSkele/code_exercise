import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static int nQuiz;
	static int totalTime;
	static int[] time;
	static int[] fine;
	static int totalFine;
	static void input(BufferedReader in) throws IOException {		
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		
		nQuiz = Integer.parseInt(tokens.nextToken());
		totalTime = Integer.parseInt(tokens.nextToken());
		
		time = new int[nQuiz];
		fine = new int[nQuiz];
		totalFine = 0;
		
		for(int i = 0; i < nQuiz; i++) {
			tokens = new StringTokenizer(in.readLine());
			
			time[i] = Integer.parseInt(tokens.nextToken());
			fine[i] = Integer.parseInt(tokens.nextToken());
			totalFine += fine[i];
		}
	}
	
	static void solve(){
		int[] exempt = new int[totalTime+1];
		
		for(int i = 0; i < nQuiz; i++) {
			for(int t = totalTime; t >= time[i]; t--) {
				exempt[t] = Math.max(exempt[t], exempt[t-time[i]] + fine[i]);
			}
		}
		
		System.out.println(totalFine - exempt[totalTime]);
	}
	
}