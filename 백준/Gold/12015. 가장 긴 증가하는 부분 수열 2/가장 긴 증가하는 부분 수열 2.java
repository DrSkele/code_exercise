import java.io.*;
import java.util.*;

class Main{
	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();		
	}
	
	static int length;
	static int[] nums;
	static List<Integer> asc;
	static void input(BufferedReader in) throws IOException {	
		length = Integer.parseInt(in.readLine());
		nums = new int[length];
		
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		for(int i = 0; i < length; i++) {
			nums[i] = Integer.parseInt(tokens.nextToken());
		}
		
		asc = new ArrayList<>();
	}
	
	static void solve() {
		asc.add(nums[0]);
		
		for(int i = 1; i < length; i++) {
			int cur = nums[i];
			
			if(cur > asc.get(asc.size() - 1)) {
				asc.add(cur);
			} else {
				int result = 0;
				int left = 0;
				int right = asc.size() - 1;
				
				while(left <= right) {
					int mid = left + (right - left)/2;
					
					if(asc.get(mid) >= cur) {
						result = mid;
						right = mid - 1;
					} else {
						left = mid + 1;
					}
				}				
				
				asc.set(result, cur);
			}
			
		}
		
		System.out.println(asc.size());
	}
}