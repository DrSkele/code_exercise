import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		arr = new int[N];
		
		tokens = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			String cur = tokens.nextToken();
			arr[i] = Integer.parseInt(cur);
		}
		
		arr = nextPermutation(arr);
		if(arr != null) {
			StringBuilder str = new StringBuilder();
			for(int n : arr) {
				str.append(n).append(" ");
			}
			System.out.println(str.toString());
		} else {
			System.out.println(-1);
		}
	}
	
	static int[] nextPermutation(int[] arr) {
		if(arr.length == 1) return null;
		
		int lastIdx = arr.length-1;
		
		int swapPoint;
		for(swapPoint = lastIdx-1; swapPoint >= 0; swapPoint--) {
			if(arr[swapPoint] < arr[swapPoint+1]) break;
		}
		
		if(swapPoint < 0) return null;
		
		int subSequence;
		for(subSequence = lastIdx; subSequence >= swapPoint; subSequence--) {
			if(arr[subSequence] > arr[swapPoint]) {
				int temp = arr[swapPoint];
				arr[swapPoint] = arr[subSequence];
				arr[subSequence] = temp;
				break;
			}
		}
		
		//swap
		int swapCnt = (lastIdx - swapPoint) / 2;
		for(int i = 0; i < swapCnt; i++) {
			int headIdx = swapPoint+1+i;
			int tailIdx = lastIdx-i;
			
			int temp = arr[headIdx];
			arr[headIdx] = arr[tailIdx];
			arr[tailIdx] = temp;
		}
		return arr;
	}
}




