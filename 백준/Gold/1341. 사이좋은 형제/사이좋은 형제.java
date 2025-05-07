import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        input(in);
        solve();
    }
    
    static BigInteger num;
    static BigInteger deno;
    static void input(BufferedReader in) throws IOException {
    	StringTokenizer tokens = new StringTokenizer(in.readLine());
    	num = new BigInteger(tokens.nextToken());
    	deno = new BigInteger(tokens.nextToken());
    }
    
    static void solve() {
    	
    	for (int n = 1; n <= 60; n++) {
            BigInteger twoToTheN = BigInteger.ONE.shiftLeft(n);
            BigInteger numerator = num.multiply(twoToTheN.subtract(BigInteger.ONE));
            
            // a * (2^n - 1)이 b로 나누어 떨어지는지 확인
            if (numerator.mod(deno).equals(BigInteger.ZERO)) {
                BigInteger patternAsInteger = numerator.divide(deno);
                
                // 패턴이 n비트 내에 맞는지 확인
                if (patternAsInteger.compareTo(twoToTheN) < 0) {
                    // 패턴으로 변환
                    StringBuilder pattern = new StringBuilder();
                    for (int i = n - 1; i >= 0; i--) {
                        if (patternAsInteger.testBit(i)) {
                            pattern.append('*');
                        } else {
                            pattern.append('-');
                        }
                    }
                    
                    System.out.println(pattern.toString());
                    return;
                }
            }
        }
    	
    	System.out.println(-1);
    }
    
}