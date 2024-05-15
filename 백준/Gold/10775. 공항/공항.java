import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		init();
		solve();
	}
	
	static int num;
	static int planes;
	static int cnt;
	static int[] gates;
	static void init() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		num = Integer.parseInt(in.readLine());
		planes = Integer.parseInt(in.readLine());
		cnt = 0;
		gates = new int[num+1];
		for(int i = 1; i <= num; i++) {
			gates[i] = i;
		}
		for(int i = 0; i < planes; i++) {
			int cur = Integer.parseInt(in.readLine());
			
			int con = find(cur);
			if(con == 0) break;
			union(con-1, con);
			cnt++;
		}
	}
	
	static void solve() {
		System.out.println(cnt);
	}
	
	static int find(int val) {
		if(gates[val] == val) return val;
		
		return gates[val] = find(gates[val]);
	}
	
	static void union(int g1, int g2) {
		int p1 = find(g1);
		int p2 = find(g2);
		
		gates[p2] = p1;
	}
}
