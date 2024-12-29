import java.io.*;
import java.util.*;

class Main{
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();		
	}
	
	static int N;
	static int M;
	static List<Integer> list;
	static int[] arr;
	static Set<String> set;
	static List<String> answer;
	
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		list = new ArrayList<>();
		arr = new int[M];
		set = new TreeSet<>();
		answer = new ArrayList<>();
		tokens = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			list.add(Integer.parseInt(tokens.nextToken()));
		}
		Collections.sort(list);
		
		
	}
	
	static void solve() {
		iterate(0, 0);
		StringBuilder str = new StringBuilder();
		for(String subArr : answer) {
			str.append(subArr).append("\n");
		}
		System.out.println(str.toString());
	}
	
	static void iterate(int idx, int length) {
		if(length == M) {
			StringBuilder str = new StringBuilder();
			for(int num : arr) {
				str.append(num).append(" ");
			}
			String subArr = str.toString();
			if(!set.contains(subArr)) {
				set.add(subArr);
				answer.add(subArr);
			}
			return;
		}
		if(idx >= list.size()) return;
		
		arr[length] = list.get(idx);
		iterate(idx+1, length+1);
		iterate(idx+1, length);			
	}
}