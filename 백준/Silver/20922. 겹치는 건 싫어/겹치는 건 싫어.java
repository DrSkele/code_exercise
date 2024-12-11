import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    	init(in);
    	solve(in);    		
    }
    
    static int length;
    static int cnt;
    static int[] arr;
    static int[] nums;
    static void init(BufferedReader in) throws IOException {
    	StringTokenizer tokens = new StringTokenizer(in.readLine());
    	length = Integer.parseInt(tokens.nextToken());
    	cnt = Integer.parseInt(tokens.nextToken());
    	arr = new int[length];
    	nums = new int[100_001];
    	
    	tokens = new StringTokenizer(in.readLine());
    	for(int i= 0; i < length; i++) {
    		arr[i] = Integer.parseInt(tokens.nextToken());
    	}
    }

    static void solve(BufferedReader in) throws IOException {
    	
    	ArrayDeque<Integer> idxQ = new ArrayDeque<Integer>();
    	
    	int maxLength = 1;
    	int left = 0;
    	int right = 0;
    	
    	while(right < arr.length) {
    		if(nums[arr[right]] < cnt) {
    			nums[arr[right]]++;
    			
    			if(maxLength < right - left + 1) maxLength = right - left + 1;
				right++;
    		} else {
    			nums[arr[left]]--;
    			left++;
    		}
    	}
    	
    	System.out.println(maxLength);
    }
}


