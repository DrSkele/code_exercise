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
	static StringBuilder str;
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
		str = new StringBuilder();
	}
	
	static void solve() {
		Arrays.sort(arr);
		
		iterate(0, 0);
		
		System.out.println(str.toString());
	}
	
	static void iterate(int idx, int arrSize) {
		if(arrSize == length) {
			for(int i = 0; i < temp.length; i++) {
				str.append(temp[i]).append(" ");
			}
			str.append("\n");
			return;
		}
		if(idx >= size) return;
		
		for(int i = 0; i < size; i++) {
			if(arrSize > 0 && arr[i] < temp[arrSize-1]) continue;
			temp[arrSize] = arr[i];
			iterate(idx+1, arrSize+1);
		}
	}
}