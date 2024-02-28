import java.io.*;

public class Main {

  static final long div = 1_000_000_009;
  static final int N = 100000;
  static long[][] dp = new long[3][N+1];
  static int M;
  static long result;
  static StringBuilder str;
  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(in.readLine());
    str = new StringBuilder();

    preSolve();
    
    for(int t = 0; t < T; t++) {
      init(in);
      
      solve();
    }
    System.out.println(str.toString());
  }

  static void init(BufferedReader in) throws IOException {
    M = Integer.parseInt(in.readLine());
    result = 0;
  }
  
  static void preSolve() {
    dp[0][1] = 1;
    dp[1][2] = 1;
    dp[0][3] = 1;
    dp[1][3] = 1;
    dp[2][3] = 1;
    
    for(int val = 4; val <= N; val++) {
      dp[0][val] = dp[1][val-1] % div + dp[2][val-1] % div;
      dp[1][val] = dp[0][val-2] % div + dp[2][val-2] % div;
      dp[2][val] = dp[0][val-3] % div + dp[1][val-3] % div;
    }
    
//    for(int val = 1; val <= N; val++) {
//      System.out.println("val : " + val + " (" + dp[0][val] + "," + dp[1][val] + "," + dp[2][val] + ")");
//    }
    
    
  }
  
  static void solve() {
    for(int i = 0; i <= 2; i++) {
      result += dp[i][M];
    }
    result %= div;
    str.append(result).append("\n");
  }
}
