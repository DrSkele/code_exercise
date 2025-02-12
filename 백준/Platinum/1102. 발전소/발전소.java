import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    input(in);
    solve();
  }
  
  static int nGenerator;
  static int[][] cost;
  static int init;
  static int cnt;
  static int min;
  static int[] dp;
  public static void input(BufferedReader in) throws IOException {
    nGenerator = Integer.parseInt(in.readLine());
    cost = new int[nGenerator][nGenerator];
    for(int i = 0; i < nGenerator; i++) {
      StringTokenizer tokens = new StringTokenizer(in.readLine());
      for(int j = 0; j < nGenerator; j++) {
        cost[i][j] = Integer.parseInt(tokens.nextToken());
      }
    }
    
    init = 0;
    cnt = 0;
    
    char[] line = in.readLine().toCharArray();
    for(int i = 0; i < nGenerator; i++) {
      if(line[i] == 'Y') {
        init |= (1 << i);
        cnt++;
      }
    }
    
    min = Integer.parseInt(in.readLine());
    
    dp = new int[1 << nGenerator];
    Arrays.fill(dp, -1);
  }
  
  public static void solve() {
    int result = dfs(cnt, init);
    System.out.println(result == Integer.MAX_VALUE ? -1 : result);
  }
  
  static int dfs(int cnt, int state) {
    if(cnt >= min) return 0;
    if(dp[state] != -1) return dp[state];
    
    dp[state] = Integer.MAX_VALUE;
    
    for(int i = 0; i < nGenerator; i++) {
      if((state & (1 << i)) != 0) {
        for(int j = 0; j < nGenerator; j++) {
          if((state & (1 << j)) == 0) {
            dp[state] = Math.min(dp[state], dfs(cnt+1, state | (1 << j)) + cost[i][j]);
          }
        }
      }
    }
    return dp[state];
  }
}