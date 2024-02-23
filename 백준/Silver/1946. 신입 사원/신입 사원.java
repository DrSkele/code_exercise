import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		StringTokenizer tokens;
        StringBuilder str = new StringBuilder();
		
		for(int t = 1; t < T+1; t++) {
			int n = Integer.parseInt(in.readLine()); 
			
			int[] scores = new int[n+1];
			int[] ranks = new int[n+1];
			
			for(int i = 0; i < n; i++) {
				tokens = new StringTokenizer(in.readLine());
				
				int score = Integer.parseInt(tokens.nextToken());
				int rank = Integer.parseInt(tokens.nextToken());
				
				scores[score] = rank;
				ranks[rank] = score;
			}
			
			HashSet<Integer> set = new HashSet<>();

			set.add(scores[1]);
			int prevScore = scores[1];
			
			for(int i = 2; i < n+1; i++) {
				if(scores[i] > prevScore) continue;
				
				set.add(scores[i]);
				prevScore = scores[i];
			}
			
            str.append(set.size()).append("\n");
			//System.out.println(set.size());
		}
        System.out.println(str.toString());
	}
}
