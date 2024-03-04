import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		init(in);
	}
	
	static int N;
	static int[] cards;
	static int M;
	static StringTokenizer tokens;
	static void init(BufferedReader in) throws IOException{
		N = Integer.parseInt(in.readLine());
		tokens = new StringTokenizer(in.readLine());
		cards = new int[N];
		for(int i = 0; i < N; i++) {
			cards[i] = Integer.parseInt(tokens.nextToken());
		}
		Arrays.sort(cards);
		M = Integer.parseInt(in.readLine());
		tokens = new StringTokenizer(in.readLine());
		
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < M; i++) {
			str.append(binarySearch(Integer.parseInt(tokens.nextToken())) ? 1 : 0).append(" ");
		}
		
		System.out.println(str.toString());
	}
	
	static boolean binarySearch(int goal) {
		
		int l = 0;
		int r = N-1;
		while(l <= r) {
			int mid = (l+r)/2;
			if(cards[mid] == goal) return true;
			else if(cards[mid] < goal) l = mid+1;
			else r = mid-1;
		}
		return false;
	}
}


