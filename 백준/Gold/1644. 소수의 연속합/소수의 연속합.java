import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static int N;
	static ArrayList<Integer> primes;
	static void input(BufferedReader in) throws IOException {		
		N = Integer.parseInt(in.readLine());
		primes = new ArrayList<>();
		
		boolean[] mark = new boolean[N+1];
		
		mark[0] = mark[1] = true;
		
		for(int i = 2; i <= (int)Math.sqrt(N); i++) {
			if(mark[i] == true) continue;
			
			for(int j = i * i; j < mark.length; j=j+i) {
				mark[j] = true;
			}
		}
		
		for(int i = 2; i < mark.length; i++) {
			if(mark[i] == false) primes.add(i);
		}
	}
	
	static void solve(){
		
		if(N == 1) {
			System.out.println(0);
			return;
		}
		
		int sum = primes.get(0);
		
		int left = 0;
		int right = 0;
		
		int cnt = 0;
		
		while(left <= right) {
			if(sum == N) cnt++;
			
			if(sum <= N) {
				if(right < primes.size()-1) {
					right++;
					sum += primes.get(right);					
				} else {
					break;
				}
			} else if(sum > N) {
				sum -= primes.get(left);
				left++;
			}
		}
		
		System.out.println(cnt);
	}
}