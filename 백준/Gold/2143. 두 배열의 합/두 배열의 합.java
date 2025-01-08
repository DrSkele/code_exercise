import java.io.*;
import java.util.*;

class Main{
	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();		
	}
	
	static int goal;
	static int lengthA;
	static int[] arrA;
	static ArrayList<Integer> sumA;
	static int lengthB;
	static int[] arrB;
	static ArrayList<Integer> sumB;
	static void input(BufferedReader in) throws IOException {	
		goal = Integer.parseInt(in.readLine());
		lengthA = Integer.parseInt(in.readLine());
		arrA = new int[lengthA];
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		for(int i = 0; i < lengthA; i++) {
			arrA[i] = Integer.parseInt(tokens.nextToken());
		}
		sumA = new ArrayList<>();
		for(int i = 0; i < lengthA; i++) {
			int sum = arrA[i];
			sumA.add(sum);
			for(int j = i+1; j < lengthA; j++) {
				sum += arrA[j];
				sumA.add(sum);
			}
		}
		Collections.sort(sumA);
		
		lengthB = Integer.parseInt(in.readLine());
		arrB = new int[lengthB];
		tokens = new StringTokenizer(in.readLine());
		for(int i = 0; i < lengthB; i++) {
			arrB[i] = Integer.parseInt(tokens.nextToken());
		}
		sumB = new ArrayList<>();
		for(int i = 0; i < lengthB; i++) {
			int sum = arrB[i];
			sumB.add(sum);
			for(int j = i+1; j < lengthB; j++) {
				sum += arrB[j];
				sumB.add(sum);
			}
		}
		Collections.sort(sumB);
	}
	
	static void solve() {
		long cnt = 0;

		for(int rangeA : sumA) {
			cnt += findRangeFromB(rangeA);
		}
		
		System.out.println(cnt);
	}
	
	static long findRangeFromB(int pair) {
		int lowerBound = -1;
		int left = 0;
		int right = sumB.size() - 1;
		
		while(left <= right) {
			int mid = left + (right - left)/2;
			
			if(pair + sumB.get(mid) >= goal) {
				lowerBound = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		
		int upperBound = -1;
		left = 0;
		right = sumB.size() - 1;
		
		while(left <= right) {
			int mid = left + (right - left)/2;
			
			if(pair + sumB.get(mid) <= goal) {
				upperBound = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		
		return lowerBound >= 0 && upperBound >= 0 ? upperBound - lowerBound + 1 : 0;
	}
}