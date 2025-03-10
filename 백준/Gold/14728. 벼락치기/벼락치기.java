import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();		
	}
	
	static int nChapter;
	static int totalTime;
	static int[] time;
	static int[] score;
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		nChapter = Integer.parseInt(tokens.nextToken());
		totalTime = Integer.parseInt(tokens.nextToken());
		
		time = new int[nChapter];
		score = new int[nChapter];
		
		for(int i = 0; i < nChapter; i++) {
			tokens = new StringTokenizer(in.readLine());
			time[i] = Integer.parseInt(tokens.nextToken());
			score[i] = Integer.parseInt(tokens.nextToken());
		}
	}
	
	static void solve(){
		
		int[] maxScore = new int[totalTime+1];
		
		for(int i = 0; i < nChapter; i++) {
			for(int t = totalTime; t >= time[i]; t--) {
				maxScore[t] = Math.max(maxScore[t], maxScore[t - time[i]] + score[i]);
			}
		}
		System.out.println(maxScore[totalTime]);
	}
	
}