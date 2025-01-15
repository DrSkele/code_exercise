import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    input(in);
    solve();
  }
  
  static String first;
  static String second;
  static int[][] lcs;
  public static void input(BufferedReader in) throws IOException {
    first = in.readLine();
    second = in.readLine();
    lcs = new int[first.length()+1][second.length()+1];
  }
  
  public static void solve() {
    
    for(int i = 0; i < first.length(); i++) {
      for(int j = 0; j < second.length(); j++) {
        if(first.charAt(i) == second.charAt(j)) {
          lcs[i+1][j+1] = lcs[i][j] + 1;
        } else {
          lcs[i+1][j+1] = Math.max(lcs[i][j+1], lcs[i+1][j]);
        }
      }
    }
    
    System.out.println(lcs[first.length()][second.length()]);
  }
}