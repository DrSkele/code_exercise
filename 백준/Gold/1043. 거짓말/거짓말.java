import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();		
	}
	
	static int nPeople;
	static int nParty;
	static int[] knows;
	static ArrayList<Integer>[] parties;
	static int[] group;
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		nPeople = Integer.parseInt(tokens.nextToken());
		nParty = Integer.parseInt(tokens.nextToken());
		
		tokens = new StringTokenizer(in.readLine());
		int num = Integer.parseInt(tokens.nextToken());
		knows = new int[num];
		
		for(int i = 0; i < num; i++) {
			knows[i] = Integer.parseInt(tokens.nextToken());
		}
		
		parties = new ArrayList[nParty];
		group = new int[nPeople+1];
		
		for(int i = 1; i < nPeople+1; i++) {
			group[i] = i;
		}
		
		for(int i = 0; i < nParty; i++) {
			tokens = new StringTokenizer(in.readLine());
			parties[i] = new ArrayList<>();
			
			int people = Integer.parseInt(tokens.nextToken());
			
			int prev = Integer.parseInt(tokens.nextToken());
			parties[i].add(prev);
			for(int j = 1; j < people; j++) {
				int person = Integer.parseInt(tokens.nextToken());
				parties[i].add(person);
				union(prev, person);
			}
		}
		if(num > 0) union(0, knows[0]);
		for(int i = 1; i < num; i++) {
			union(knows[i-1], knows[i]);
		}
	}
	
	static void solve() {
		int cnt = 0;
		
		for(ArrayList<Integer> party : parties) {
			boolean truth = false;
			for(int person : party) {
				if(find(person) == 0) {
					truth = true;
					break;
				}
			}
			if(!truth) cnt++;
		}
		
		System.out.println(cnt);
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		group[b] = a;
	}
	
	static int find(int a) {
		if(group[a] == a) return a;
		
		return group[a] = find(group[a]);
	}
	
}