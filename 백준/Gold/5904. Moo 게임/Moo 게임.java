import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();		
	}
	
	static int idx;
	static void input(BufferedReader in) throws IOException {
		idx = Integer.parseInt(in.readLine());
	}
	
	static void solve(){
		int k = 0;
		ArrayList<Integer> moo = new ArrayList<>();
		moo.add(3);
		
		while(moo.get(moo.size()-1) < idx) {
			k++;
			moo.add(moo.get(moo.size()-1) * 2 + k + 3);
		}
		
		char answer = 'm';
		
		while(k >= 0) {
			if(idx == 1) {
				answer = 'm';
				break;
			} else if(idx <= 3) {
				answer = 'o';
				break;
			}
			
			if(idx <= moo.get(k-1)) k--;
			else if(idx <= moo.get(k-1) + k + 3) {
				idx -= moo.get(k-1);
				if(idx == 1) {
					answer = 'm';
				} else {
					answer = 'o';
				}
				break;
			} else {
				idx -= moo.get(k-1) + k + 3;
				k--;
			}
		}
		
		System.out.println(answer);
	}
}