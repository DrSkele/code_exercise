import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    	
    	init(in);
    	
    	solve();  
    }

    static int N;
    static int len;
    static char[] line;
    static void init(BufferedReader in) throws IOException {
    	N = Integer.parseInt(in.readLine());
    	len = Integer.parseInt(in.readLine());
    	line = in.readLine().toCharArray();
    }

    static void solve(){
    	int cnt = 0;
    	int[] streak = new int[line.length];
    	int i = 0;
    	while(i < line.length) {
    		if(line[i] == 'I') {
    			if(streak[i] >= N) cnt++;
    			if(i+2 < line.length && line[i+1] == 'O' && line[i+2] == 'I') {
    				streak[i+2] = streak[i] + 1;
    				i+=2;
    				continue;
    			}
    		} 
    		i++;
    	}
    	System.out.println(cnt);
    }
    
}
