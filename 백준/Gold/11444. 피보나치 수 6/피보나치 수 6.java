import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    
        init(in);
        solve();
    }
    
    static long mod = 1_000_000_007;
    static long[][] matrix = {{1L, 1L}, {1L, 0L}}; 
    static long N;
    //static Map<Long, long[][]> map;
    static void init(BufferedReader in) throws IOException {
    	N = Long.parseLong(in.readLine());
    	//map = new HashMap<>();
    	//map.put(1L, matrix);
    }

    static void solve(){
    	//[ F(n+1), F(n) ] = matrix^n * [1, 0]
        System.out.println(powOf(N-1)[0][0]);
    }
    
    // 행렬 제곱
    static long[][] powOf(long n) {
    	if(n == 1 || n == 0) return matrix;
    	//if(map.containsKey(n)) return map.get(n);
    	
    	long[][] half = powOf(n/2L);
    	if(n % 2L == 0) {
    		return mul(n, half, half);
    	} else {
    		return mul(n, mul(n-1, half, half), matrix);
    	}
    }
    
    // 행렬 곱
    static long[][] mul(long n, long[][] a, long[][] b){
    	long[][] temp = new long[2][2];
    	for(int i = 0; i < 2; i++) {
    		for(int j = 0; j < 2; j++) {
    			for(int k = 0; k < 2; k++) {
    				temp[i][j] += a[i][k] * b[k][j];
    				temp[i][j] %= mod;
    			}
    		}
    	}
    	//map.put(n, temp);
    	return temp;
    }
}


