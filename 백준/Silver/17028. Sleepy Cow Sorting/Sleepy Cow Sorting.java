import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    
        init(in);
        solve(in);        	
    }
    
    static int N;
    static int[] arr;
    static void init(BufferedReader in) throws IOException {
    	N = Integer.parseInt(in.readLine());
    	arr = new int[N];
    	int check = 0;
    	StringTokenizer tokens = new StringTokenizer(in.readLine());
    	arr[0] = Integer.parseInt(tokens.nextToken());
    	for(int i = 1; i < N; i++) {
    		arr[i] = Integer.parseInt(tokens.nextToken());
    		
    		if(arr[i-1] < arr[i]) continue;
    		else check = i;
    	}
    	System.out.println(check);
    }

    static void solve(BufferedReader in) throws IOException {
    	
    }
    
}


