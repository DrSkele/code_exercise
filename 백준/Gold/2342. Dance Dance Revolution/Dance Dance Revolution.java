import java.io.*;
import java.util.*;
import java.math.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        ArrayList<Integer> arr = new ArrayList<>();
        
        while(tokens.hasMoreTokens()) {
            int cur = Integer.parseInt(tokens.nextToken());
            if(cur == 0) break;
            arr.add(cur);
        }
        
        int size = arr.size() + 1;
        
        int[][][] dp = new int[size][5][5];
        
        for(int j = 0; j < 5; j++) {
            for(int k = 0; k < 5; k++) {
                dp[0][j][k] = Integer.MAX_VALUE;
            }
        }
        dp[0][0][0] = 0;
        
        for(int i = 0; i < arr.size(); i++) {
            int step = arr.get(i);
            
            
            for(int j = 0; j < 5; j++) {
                for(int k = 0; k < 5; k++) {
                    dp[i+1][j][k] = Integer.MAX_VALUE;
                }
            }
            
            for(int j = 0; j < 5; j++) {
                for(int k = 0; k < 5; k++) {
                    int prev = dp[i][j][k];
                    if(prev == Integer.MAX_VALUE) continue;
                    if(step == k) continue;
                    
                    int add = 0;
                    if(j == 0) add = 2;
                    else if(step == j) add = 1;
                    else if(step == (j+2)%4 || (step == 4 && ((j+2)%4 == 0))) add = 4;
                    else add = 3;
                    
                    int sum = prev + add;
                    
                    if(dp[i+1][step][k] > sum) dp[i+1][step][k] = sum;
                }
            }
            
            for(int j = 0; j < 5; j++) {
                for(int k = 0; k < 5; k++) {
                    int prev = dp[i][j][k];
                    if(prev == Integer.MAX_VALUE) continue;
                    if(step == j) continue;
                    
                    int add = 0;
                    if(k == 0) add = 2;
                    else if(step == k) add = 1;
                    else if(step == (k+2)%4 || (step == 4 && ((k+2)%4 == 0))) add = 4;
                    else add = 3;
                    
                    int sum = prev + add;
                    
                    if(dp[i+1][j][step] > sum) dp[i+1][j][step] = sum;
                }
            }
            
            // System.out.println(step);
            // for(int m = 0; m < 5; m++) {
            //     for(int n = 0; n < 5; n++) {
            //       System.out.print(dp[i+1][m][n]);
            //       System.out.print(" ");
            //     }
            //     System.out.println();
            // }
        }
        
        int min = Integer.MAX_VALUE;
        
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                if(min > dp[size-1][i][j]) min = dp[size-1][i][j];
            }
        }
        
        System.out.println(min);
    }
}