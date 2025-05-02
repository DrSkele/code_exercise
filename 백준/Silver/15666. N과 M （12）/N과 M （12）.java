import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        input(in);
        solve();      
    }
    
    static int length;
    static int maxLength;
    static int[] arr;
    static int[] temp;
    static StringBuilder str;
    static void input(BufferedReader in) throws IOException {
    	StringTokenizer tokens = new StringTokenizer(in.readLine());
        length = Integer.parseInt(tokens.nextToken());
        maxLength = Integer.parseInt(tokens.nextToken());
        arr = new int[length];
        tokens = new StringTokenizer(in.readLine());
        for(int i = 0; i < length; i++) {
        	arr[i] = Integer.parseInt(tokens.nextToken());
        }
        Arrays.sort(arr);
        temp = new int[maxLength];
        str = new StringBuilder();
    }
    
    static void solve() {
    	traverse(0, 0);
    	System.out.println(str.toString());
    }
    
    static void traverse(int idx, int cnt) {
    	if(cnt == maxLength) {
    		for(int num : temp) {
    			str.append(num).append(' ');
    		}
    		str.append('\n');
    		return;
    	}
    	
    	int prev = -1;
    	for(int i = idx; i < length; i++) {
    		if(arr[i] == prev) continue;
    		
    		temp[cnt] = arr[i];
    		prev = arr[i];
    		traverse(i, cnt+1);
    	}
    }
}