import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        input(in);
        solve();   
    }
    
    static int length;
    static int[] arr;
    static void input(BufferedReader in) throws IOException {
    	length = Integer.parseInt(in.readLine());
    	arr = new int[length];
    	for(int i = 0; i < length; i++) {
    		arr[i] = Integer.parseInt(in.readLine());
    	}
    }
    
    static void solve() {
    	System.out.println(length - findLIS(arr));
    }
    
    static int findLIS(int[] arr) {
    	
    	int max = 1;
    	
    	int[] cnt = new int[arr.length];
    	
    	cnt[0] = 1;
    	
    	for(int i = 1; i < length; i++) {
    		int cur = arr[i];
    		cnt[i] = 1;
    		for(int j = i-1; j >= 0; j--) {
    			if(arr[j] < cur) cnt[i] = Math.max(cnt[i], cnt[j]+1);
    		}
    		
    		max = Math.max(max, cnt[i]);
    	}
    	
    	return max;
    }
}