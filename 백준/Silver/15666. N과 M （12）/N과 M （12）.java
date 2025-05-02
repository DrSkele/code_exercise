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
    static Set<String> set;
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
        set = new HashSet<>();
    }
    
    static void solve() {
    	traverse(0, 0);
    }
    
    static void traverse(int idx, int cnt) {
    	if(cnt == maxLength) {
    		StringBuilder str = new StringBuilder();
    		for(int num : temp) {
    			str.append(num).append(' ');
    		}
    		String seq = str.toString();
    		if(!set.contains(seq)) {
    			set.add(seq);
    			System.out.println(str.toString());
    		}
    		return;
    	}
    	
    	for(int i = idx; i < length; i++) {
    		temp[cnt] = arr[i];
    		traverse(i, cnt+1);
    	}
    }
}