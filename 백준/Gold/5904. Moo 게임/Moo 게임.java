
import java.io.*;
import java.util.*;

// boolean array로 moo 값 저장
// k번째 moo를 배열을 순회하면서 저장

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		init(in);
		
		solve();
	}
	
	static int N;
	static int[] moo;
	static void init(BufferedReader in) throws IOException{
		N = Integer.parseInt(in.readLine());
		moo = new int[28];
	}
	
	static void solve() {
		int length = 3;
		int cnt = 0;
		int sum = length;
		moo[0] = length;
		while(sum < N) {
			cnt++;
			length++;
			sum += length + sum;
			moo[cnt] = sum;
		}
		//System.out.println(cnt);
		System.out.print(find(N, cnt));
	}
	
	static String find(int val, int cnt) {
		if(cnt == 0) {
			if(val == 1) return "m";
			else return "o";
		}
		if(val <= moo[cnt-1] + cnt) {
			int newVal = val-moo[cnt-1];
			if(newVal == 1) return "m";
			else return "o";
		} else {
			int newVal = val - moo[cnt-1] - (cnt+3);
			int n = 0;
			for(; n < cnt; n++) {
				if(newVal <= moo[n]) break;
			}
			//System.out.println(newVal);
			return find(newVal, n);
		}
	}
}


