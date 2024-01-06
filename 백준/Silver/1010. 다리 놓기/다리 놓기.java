import java.io.*;
import java.util.*;

class Main{
    static int[][] combinations;
    
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();
        combinations = new int[31][31];
        
        for(int t = 0; t < T; t++){
            int R = scan.nextInt();
            int N = scan.nextInt();
            
            System.out.println(Combination(N, R));
        }
    }
    
    //nCr = n-1Cr + n-1Cr-1
    static int Combination(int n, int r){
        if(r == 0 || n == r) return 1;
        
        if(combinations[n][r] == 0)        
            combinations[n][r] = Combination(n-1, r-1) + Combination(n-1, r);
        
        return combinations[n][r];
    }
}