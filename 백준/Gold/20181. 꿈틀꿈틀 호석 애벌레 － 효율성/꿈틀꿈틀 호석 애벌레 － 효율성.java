import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    input(in);
    solve();
  }
  
  static int length;
  static int limit;
  static int[] food;
  public static void input(BufferedReader in) throws IOException {
    StringTokenizer tokens = new StringTokenizer(in.readLine());
    
    length = Integer.parseInt(tokens.nextToken());
    limit = Integer.parseInt(tokens.nextToken());
    
    food = new int[length];
    
    tokens = new StringTokenizer(in.readLine());
    for(int i = 0; i < length; i++) {
      food[i] = Integer.parseInt(tokens.nextToken());
    }
  }
  
  public static void solve() {
    
    int[] value = new int[length+1];
    int[] size = new int[length+1];
    
    int left = 0;
    int right = 0;
    int sum = food[0];
    
    while(left <= right) {
      if(sum >= limit || right == length-1) {
        size[left] = right - left + 1;
        value[left] = Math.max(0, sum - limit);
        
        if(size[left] == 1 && right != length-1) sum += food[++right];
        sum -= food[left++];
      } else {
        sum += food[++right];
      }
      //System.out.println(left + " " + right);
    }
    
    // for(int val : value) {
    //   System.out.print(val + " ");
    // }
    // System.out.println();
    // for(int s : size) {
    //   System.out.print(s + " ");
    // }
    
    int[] dp = new int[length+1];
    
    for(int i = length-1; i >= 0; i--) {
      dp[i] = Math.max(dp[i+1], dp[i+size[i]] + value[i]);
    }
    
    System.out.println(dp[0]);
  }
}