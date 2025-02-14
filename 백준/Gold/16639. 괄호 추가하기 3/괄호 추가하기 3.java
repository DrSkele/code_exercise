import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    input(in);
    solve();
  }
  
  static int length;
  static int[] nums;
  static char[] ops;
  public static void input(BufferedReader in) throws IOException {
    length = Integer.parseInt(in.readLine());
    nums = new int[length/2+1];
    ops = new char[length/2];
    char[] line = in.readLine().toCharArray();
    for(int i = 0; i < length; i++) {
      if(i%2 == 0) {
        nums[i/2] = Character.getNumericValue(line[i]);
      } else {
        ops[i/2] = line[i];
      }
    }
  }
  
  public static void solve() {
    int[][] dp = new int[nums.length][nums.length];
    int[][] dpMin = new int[nums.length][nums.length];
    
    for(int i = 0; i < nums.length; i++) {
      for(int j = 0; j < nums.length; j++) {
        if(i == j) {
          dp[i][i] = nums[i];
          dpMin[i][j] = nums[i];
        } else {
          dp[i][j] = Integer.MIN_VALUE;
          dpMin[i][j] = Integer.MAX_VALUE;
        }
      }
    }
    
    for(int m = 1; m <= nums.length; m++) {
      for(int i = 0; i+m < nums.length; i++) {
        for(int j = i; j < i+m; j++) {
          char op = ops[j];
          int val = Integer.MIN_VALUE;
          int valMin = Integer.MAX_VALUE;
          switch(op) {
            case '+' : 
              val = Math.max(val, dp[i][j] + dp[j+1][i+m]);
              valMin = Math.min(valMin, dpMin[i][j] + dpMin[j+1][i+m]);
              break;
            case '-' : 
              val = Math.max(val, dp[i][j] - dpMin[j+1][i+m]);
              valMin = Math.min(valMin, dpMin[i][j] - dp[j+1][i+m]);
              break;
            default : 
              int[] arrMul = { dp[i][j] * dp[j+1][i+m], 
                dp[i][j] * dpMin[j+1][i+m], 
                dpMin[i][j] * dp[j+1][i+m], 
                dpMin[i][j] * dpMin[j+1][i+m] 
              };
              
              for(int k = 0; k < arrMul.length; k++) {
                val = Math.max(val, arrMul[k]);
                valMin = Math.min(valMin, arrMul[k]);
              }
              
              break;
          }
          
          dp[i][i+m] = Math.max(dp[i][i+m], val);
          dpMin[i][i+m] = Math.min(dpMin[i][i+m], valMin);
        }
      }
    }
    
//     for(int i = 0; i < nums.length; i++) {
//       for(int j = 0; j < nums.length; j++) {
//         System.out.print(dp[i][j] + " ");
//       }
//       System.out.println();
//     }
    
    System.out.println(dp[0][length/2]);
  }
}