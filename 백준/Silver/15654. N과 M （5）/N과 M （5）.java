import java.io.*;
import java.util.*;

public class Main {

	// 행렬 제곱 = 곱하는 
	
    public static void main(String[] args) throws IOException {
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    	
    	init(in);
    	
    	solve();  
    }

    static int N;
    static int M;
    static int[] arr;
    static boolean[] used;
    static int[] ans;
    static StringBuilder str;
    static void init(BufferedReader in) throws IOException {
    	StringTokenizer tokens = new StringTokenizer(in.readLine());
    	N = Integer.parseInt(tokens.nextToken());
    	M = Integer.parseInt(tokens.nextToken());
    	arr = new int[N];
    	used = new boolean[N];
    	ans = new int[M];
    	str = new StringBuilder();
    	tokens = new StringTokenizer(in.readLine());
    	for(int i = 0; i < N; i++) {
    		arr[i] = Integer.parseInt(tokens.nextToken());
    	}
    	Arrays.sort(arr);
    }

    static void solve(){
    	combination(0);
    	
    	System.out.println(str.toString());
    }
    
    static void combination(int depth) {
    	if(depth == M) {
    		for(int i : ans) {
    			str.append(i).append(" ");
    		}
    		str.append("\n");
    		return;
    	}
    	
    	for(int i = 0; i < N; i++) {
    		if(used[i]) continue;
    		
    		used[i] = true;
    		ans[depth] = arr[i];
    		combination(depth+1);
    		used[i] = false;
    	}
    }
}
