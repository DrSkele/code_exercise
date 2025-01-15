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
  public static void input(BufferedReader in) throws IOException {
    first = in.readLine();
    second = in.readLine();
  }
  
  public static void solve() {
    boolean pivotAtBack = true;
    ArrayDeque<Character> deque = new ArrayDeque<>();
    for(char ch : second.toCharArray()) {
      deque.add(ch);
    }
    while(deque.size() > first.length()) {
      char last = pivotAtBack ? deque.pollLast() : deque.poll();
      //System.out.println(last);
      if(last == 'B') pivotAtBack = !pivotAtBack;
    }
    
    boolean isValid = true;
    for(int i = 0; i < first.length(); i++) {
      char ch = pivotAtBack ? deque.poll() : deque.pollLast();
      if(ch != first.charAt(i)) {
        isValid = false;
        break;
      }
    }
    
    System.out.println(isValid ? 1 : 0);
  }
}