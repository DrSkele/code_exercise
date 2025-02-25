import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		input(in);
		solve();
	}
	
	static int nEgg;
	static Egg[] eggs;
	static int max;
	static void input(BufferedReader in) throws IOException {
		nEgg = Integer.parseInt(in.readLine());
		eggs = new Egg[nEgg];
		
		for(int i = 0; i < nEgg; i++) {
			StringTokenizer tokens = new StringTokenizer(in.readLine());
			eggs[i] = new Egg(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
		}
		max = 0;
	}
	
	static void solve() {
		crackEgg(0, 0);
		System.out.println(max);
	}
	
	static void crackEgg(int idx, int cnt) {
		if(idx == nEgg) {
			max = Math.max(max, cnt);
			
			return;
		}
		
		if(eggs[idx].shell <= 0) {
			crackEgg(idx+1, cnt);
		} else {
			for(int i = 0; i < nEgg; i++) {
				if(i == idx) continue;
				
				if(eggs[i].shell > 0) {
					eggs[i].shell -= eggs[idx].weight;
					eggs[idx].shell -= eggs[i].weight;
					crackEgg(idx+1, cnt + (eggs[i].shell <= 0 ? 1 : 0) + (eggs[idx].shell <= 0 ? 1 : 0));
					eggs[i].shell += eggs[idx].weight;
					eggs[idx].shell += eggs[i].weight;
				} else {
					crackEgg(idx+1, cnt);
				}
			}			
		}
		
	}
	
	static class Egg{
		int shell;
		int weight;
		public Egg(int s, int w) {
			shell = s;
			weight = w;
		}
	}
}