import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        input(in);
        solve(in);
    }
    
    static int length;
    static int cnt;
    static int[] nums;
    static void input(BufferedReader in) throws IOException {
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        length = Integer.parseInt(tokens.nextToken());
        cnt = Integer.parseInt(tokens.nextToken());
        nums = new int[length];
        
        tokens = new StringTokenizer(in.readLine());
        for(int i = 0; i < length; i++) {
        	nums[i] = Integer.parseInt(tokens.nextToken());
        }
    }
    
    static void solve(BufferedReader in) throws IOException {
        int maxLength = 0;
        
        int left = 0;
        int right = 0;
        int oddCnt = 0;
        
        while(right < length) {
        	int cur = nums[right++];
        	
        	if(cur % 2 == 1) oddCnt++;
        	
        	while(oddCnt > cnt) {
        		int num = nums[left++];
        		if(num % 2 == 1) oddCnt--;
        	}
        	
        	maxLength = Math.max(maxLength, (right - left) - oddCnt);
        }
        
        System.out.println(maxLength);
    }
}