import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        input(in);
        solve();   
    }
    
    static int height;
    static int digit;
    static int limit;
    static int floor;
    static int[][] cost;
    static int[] num = {
    	0b1110111,
    	0b0010010,
    	0b1011101,
    	0b1011011,
    	0b0111010,
    	0b1101011,
    	0b1101111,
    	0b1010010,
    	0b1111111,
    	0b1111011,
    };
    static void input(BufferedReader in) throws IOException {
    	StringTokenizer tokens = new StringTokenizer(in.readLine());
    	height = Integer.parseInt(tokens.nextToken());
    	digit = Integer.parseInt(tokens.nextToken());
    	limit = Integer.parseInt(tokens.nextToken());
    	floor = Integer.parseInt(tokens.nextToken());
    	
    	cost = new int[10][10];
    	
    	for(int i = 0; i < 10; i++) {
    		for(int j = 0; j < 10; j++) {
    			int from = num[i];
    			int to = num[j];
    			
    			int diff =  Integer.bitCount(from ^ to);
    			//System.out.print(diff);
    			cost[i][j] = diff;
    		}
    		//System.out.println();
    	}
    }
    
    static void solve() {
    	int cnt = 0;
    	
    	for(int i = 1; i <= height; i++) {
    		if(floor != i && countRequiredLED(floor, i) <= limit) cnt++;
    	}
    	
    	System.out.println(cnt);
    }
    
    static int countRequiredLED(int from, int to) {
    	int sum = 0;
    	//System.out.print(from + " " + to + " ");
    	for(int i = 0; i < digit; i++) {
    		int digitFrom = from % 10;
    		int digitTo = to % 10;
    		sum += cost[digitFrom][digitTo];
    		from /= 10;
    		to /= 10;
    	}
    	//System.out.println(sum);
    	return sum;
    }
}