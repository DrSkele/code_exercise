import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();		
	}
	
	static int level;
	static long slice;
	static Burger[] burger;
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		level = Integer.parseInt(tokens.nextToken());
		slice = Long.parseLong(tokens.nextToken());
		
		burger = new Burger[level+1];
		burger[0] = new Burger(1, 1L);
		for(int i = 1; i < level+1; i++) {
			burger[i] = new Burger(burger[i-1].size*2 + 3, burger[i-1].patty*2 + 1);
		}
	}
	
	static void solve(){
		long sum  = 0;
		long num = slice;
		int cur = level;
		while(num > 0) {
			if(num == burger[cur].size) {
				sum += burger[cur].patty;
				break;
			} else if(num >= burger[cur-1].size + 2) {
				num -= burger[cur-1].size + 2;
				sum += burger[--cur].patty + 1;
			} else {
				cur--;
				num--;
			}
		}
		
		System.out.println(sum);
	}
	
	static class Burger{
		long size;
		long patty;
		
		public Burger(long s, long p) {
			size = s;
			patty = p;
		}
	}
}