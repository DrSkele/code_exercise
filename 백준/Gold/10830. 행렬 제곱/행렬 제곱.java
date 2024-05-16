import java.io.*;
import java.util.*;

public class Main {

	// 행렬 제곱 = A*A*A = (A*A)*A
	
    public static void main(String[] args) throws IOException {
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    	
    	init(in);
    	
    	solve();  
    }

    static int size;
    static long pow;
    static Map<Long, int[][]> arr;
    static void init(BufferedReader in) throws IOException {
    	StringTokenizer tokens = new StringTokenizer(in.readLine());
    	size = Integer.parseInt(tokens.nextToken());
    	pow = Long.parseLong(tokens.nextToken());
    	arr = new HashMap<>();
    	arr.put(1L, new int[size][size]);
    	for(int i = 0; i < size; i++) {
    		tokens = new StringTokenizer(in.readLine());
    		for(int j = 0; j < size; j++) {
    			arr.get(1L)[i][j] = Integer.parseInt(tokens.nextToken());
    		}
    	}
    }

    static void solve(){
    	int[][] ans = powOf(pow);
    	
    	StringBuilder str = new StringBuilder();
    	for(int i = 0; i < size; i++) {
    		for(int j = 0; j < size; j++) {
    			str.append(ans[i][j]%1000).append(" ");
    		}
    		str.append("\n");
    	}
    	
    	System.out.println(str.toString());
    }
    
    static int[][] powOf(long num) {
    	if(arr.containsKey(num)) return arr.get(num);
    	
    	if(num % 2 == 0) {
    		return mul(num, powOf(num/2), powOf(num/2));
    	} else {
    		return mul(num, mul(num-1, powOf(num/2), powOf(num/2)), arr.get(1L));
    	}
    }
    
    static int[][] mul(long num, int[][] arr1, int[][] arr2) {
    	arr.put(num, new int[size][size]);
    	for(int i = 0; i < size; i++) {
    		for(int j = 0; j < size; j++) {
    			int sum = 0;
    			for(int k = 0; k < size; k++) {
    				sum += (arr1[i][k] * arr2[k][j]) % 1000;
    			}
    			arr.get(num)[i][j] = sum % 1000;
    		}
    	}
    	return arr.get(num);
    }
}
