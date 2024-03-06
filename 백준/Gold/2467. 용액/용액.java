import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		init(in);
		
		solve();
	}
	
	static int N;
	static int[] liquid;
	static StringTokenizer tokens;
	
	static int min;
	static int minAcid;
	static int minAlkali;
	static void init(BufferedReader in) throws IOException{
		N = Integer.parseInt(in.readLine());
		liquid = new int[N];
		tokens = new StringTokenizer(in.readLine());
		min = Integer.MAX_VALUE;
		for(int i = 0; i < N; i++) {
			liquid[i] = Integer.parseInt(tokens.nextToken());
		}
	}
	
	static void solve() {
		int left = 0;
		int right = N-1;
		while(left < right) {
			int onLeft = liquid[left];
			int onRight = liquid[right];
			int mix = Math.abs(onRight + onLeft);
			
			if(mix < min) {
				min = mix;
				minAcid = onLeft;
				minAlkali = onRight;
			}
			
			if(left + 1 < right && left < right - 1 ) {
				int mixLeft = Math.abs(onRight + liquid[left+1]);
				int mixRight = Math.abs(onLeft + liquid[right-1]);
				
				if(mixLeft < mixRight) {
					left = left + 1;
				} else if(mixRight < mixLeft) {
					right = right -1;
				} else {
					left = left + 1;
					right = right -1;
				}
			} else break;
		}
		
		System.out.println(minAcid + " " + minAlkali);
	}
	
}


