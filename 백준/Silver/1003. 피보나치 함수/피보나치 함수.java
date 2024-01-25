import java.io.*;
import java.util.*;

class Main{
    static int[] zero = new int[41];
    static int[] one = new int[41];
    static int fibonacciZero(int n){
        if(zero[n] != 0) return zero[n];
        if(n == 0){
            return 1;
        } else if (n == 1) {
            return 0;
        } else {
            int result = fibonacciZero(n-1) + fibonacciZero(n-2);
            zero[n] = result;
            return result;
        }
    }
    static int fibonacciOne(int n){
        if(one[n] != 0) return one[n];
        if(n == 0){
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            int result = fibonacciOne(n-1) + fibonacciOne(n-2);
            one[n] = result;
            return result;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(in.readLine());
        
        
        zero[0] = 1;
        one[1] = 1;
        
        fibonacciZero(40);
        fibonacciOne(40);
        
        StringBuilder str = new StringBuilder();
        for(int i = 0; i< testCase; i++){
            int n = Integer.parseInt(in.readLine());
            str.append(zero[n]).append(" ").append(one[n]).append("\n");
        }
        System.out.print(str.toString());
    }
}