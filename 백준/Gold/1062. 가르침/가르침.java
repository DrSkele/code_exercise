import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    input(in);
    solve();
  }
  
  static int nWord;
  static int nChar;
  static int[] chars;
  static int base;
  static boolean[] visited;
  static int max;
  public static void input(BufferedReader in) throws IOException {
	  StringTokenizer tokens = new StringTokenizer(in.readLine());
	  nWord = Integer.parseInt(tokens.nextToken());
	  nChar = Integer.parseInt(tokens.nextToken());
	  
	  chars = new int[nWord];
	  
	  for(int i = 0; i < nWord; i++) {
		  char[] line = in.readLine().toCharArray();
		  
		  int mark = 0;
		  
		  for(int j = 0; j < line.length; j++) {
			  mark |= (1 << (line[j] - 'a'));
		  }
		  
		  chars[i] = mark;
	  }
	  
	  base = 0;
	  base |= (1 << ('a' - 'a'));
	  base |= (1 << ('c' - 'a'));
	  base |= (1 << ('i' - 'a'));
	  base |= (1 << ('n' - 'a'));
	  base |= (1 << ('t' - 'a'));
	  
	  visited = new boolean[1 << 26];
	  
	  max = 0;
  }
  
  public static void solve() {
	  if(nChar < 5) {
		  System.out.println(0);
		  return;
	  }
	  
	  dfs(base, 5);
	  
	  System.out.println(max);
  }
  
  static void dfs(int mask, int cnt) {
	  
	  visited[mask] = true;

	  if(cnt == nChar) {
		  
		  int word = 0;
		  
		  for(int i = 0; i < nWord; i++) {
			  if((chars[i] & mask) == chars[i]) word++;
		  }
		  
		  max = Math.max(max, word);
		  
		  return;
	  }
	  
	  
	  for(int i = 0; i < 26; i++) {
		  if((mask & (1 << i)) > 0) continue;
		  
		  int next = mask | (1 << i);
		  if(visited[next]) continue;
		  
		  dfs(next, cnt+1);
	  }
  }
}