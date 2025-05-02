import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        input(in);
        solve();      
    }
    
    static int origin;
    static int result;
    static int min;
    static void input(BufferedReader in) throws IOException {
    	StringTokenizer tokens = new StringTokenizer(in.readLine());
        origin = Integer.parseInt(tokens.nextToken());
        result = Integer.parseInt(tokens.nextToken());
    }
    
    static void solve() {
    	System.out.println(changable(result, 1));
    }
    
    static int changable(int num, int cnt) {
    	if(num <= 0) return -1;
    	
    	if(num == origin) {
    		return cnt;
    	}
    	
    	int minCnt = -1;
    	
    	if(num % 2 == 0) minCnt = changable(num/2, cnt+1);
    	if(num % 10 == 1) minCnt = changable(num/10, cnt+1);
    	
    	return minCnt;
    }
}