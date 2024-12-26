import java.io.*;
import java.util.*;

class Main{
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();
	}
	
	static int size;
	static int length;
	static int[] arr;
	static int[] temp;
	static List<String> answer;
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		size = Integer.parseInt(tokens.nextToken());
		length = Integer.parseInt(tokens.nextToken());
		arr = new int[size];
		tokens = new StringTokenizer(in.readLine());
		for(int i = 0; i < size; i++) {
			arr[i] = Integer.parseInt(tokens.nextToken());
		}
		temp = new int[length];
		answer = new ArrayList<>();
	}
	
	static void solve() {
		Arrays.sort(arr);
		
		iterate(0, 0);
		
		StringBuilder str = new StringBuilder();
		for(String next : answer) {
			str.append(next).append("\n");
		}
		System.out.println(str.toString());
	}
	
	static void iterate(int idx, int arrSize) {
		if(arrSize == length) {
			StringBuilder str = new StringBuilder();
			for(int i = 0; i < temp.length; i++) {
				str.append(temp[i]).append(" ");
			}
			answer.add(str.toString());
			return;
		}
		if(idx >= size) return;
		
		temp[arrSize] = arr[idx];
		iterate(idx+1, arrSize+1);
		iterate(idx+1, arrSize);
	}
}