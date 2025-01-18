import java.io.*;
import java.util.*;

class Main{
	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static String first;
	static String second;
	static void input(BufferedReader in) throws IOException {
		first = in.readLine();
		second = in.readLine();
	}
	
	
	static void solve() {
		
		char[] arrFirst = first.toCharArray();
		char[] arrSecond = second.toCharArray();
		
		Arrays.sort(arrFirst);
		Arrays.sort(arrSecond);
		
		char[] arr = new char[arrFirst.length];
		
		int firstLeft = 0;
		int firstRight = arrFirst.length / 2 + arrFirst.length % 2 - 1;
		int secondLeft = arrSecond.length / 2 + arrSecond.length % 2;
		int secondRight = arrSecond.length - 1;
		
		boolean isFirst = true;
		int left = 0;
		int right = arr.length - 1;
		while(left <= right) {
			if(isFirst) {
				if(arrFirst[firstLeft] < arrSecond[secondRight]) {
					arr[left++] = arrFirst[firstLeft++];
				} else {
					arr[right--] = arrFirst[firstRight--];
				}
			} else {
				if(arrSecond[secondRight] > arrFirst[firstLeft]) {
					arr[left++] = arrSecond[secondRight--];
				} else {
					arr[right--] = arrSecond[secondLeft++];
				}
			}
			isFirst = !isFirst;
		}
		
		StringBuilder str = new StringBuilder();
		for(char ch : arr) {
			str.append(ch);
		}
		
		System.out.println(str.toString());
	}
}