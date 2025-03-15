import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static int nStudent;
	static int nBlock;
	static int goal;
	static ArrayList<Integer>[] students;
	static void input(BufferedReader in) throws IOException {		
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		nStudent = Integer.parseInt(tokens.nextToken());
		nBlock = Integer.parseInt(tokens.nextToken());
		goal = Integer.parseInt(tokens.nextToken());
		
		students = new ArrayList[nStudent];
		
		for(int i = 0; i < nStudent; i++) {
			students[i] = new ArrayList<>();
			tokens = new StringTokenizer(in.readLine());
			while(tokens.hasMoreTokens()) {
				students[i].add(Integer.parseInt(tokens.nextToken()));
			}
		}
	}
	
	static void solve(){
		int[] dp = new int[goal+1];
		
		dp[0] = 1;
		
		for(int i = 0; i < nStudent; i++) {
			for(int h = goal; h >= 0; h--) {
				for(int block : students[i]) {
					if(h >= block) {
						dp[h] += dp[h - block] % 10_007;						
					}					
				}			
			}
		}
		
		System.out.println(dp[goal] % 10_007);
	}
	
}