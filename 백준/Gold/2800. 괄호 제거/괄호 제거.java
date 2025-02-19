import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    input(in);
    solve();
  }
  
  static char[] line;
  static ArrayList<Bracket> brackets;
  public static void input(BufferedReader in) throws IOException {
	  line = in.readLine().toCharArray();
	  
	  brackets = new ArrayList<>();
	  
	  Stack<Integer> stack = new Stack<>();
	  
	  for(int i = 0; i < line.length; i++) {
		  if(line[i] == '(') {
			  stack.push(i);
		  } else if(line[i] == ')') {
			  brackets.add(new Bracket(stack.pop(), i));
		  }
	  }
  }
  
  static Set<String> result;
  public static void solve() {
	  
	  result = new HashSet<>();
	  
	  combination(0, 0);
	  
	  ArrayList<String> resultList = new ArrayList<>(result);
	  Collections.sort(resultList);
	  
	  StringBuilder str = new StringBuilder();
	  for(String string : resultList) {
		  str.append(string);
	  }
	  System.out.println(str.toString());
  }
  
  static void combination(int idx, int mask) {
	  if(idx >= brackets.size()) {
		  if(mask == 0) return;
		  
		  StringBuilder str = new StringBuilder();
		  for(char ch : line) {
			  if(ch == ' ') continue;
			  str.append(ch);
		  }
		  str.append("\n");
		  result.add(str.toString());
		  return;
	  }
	  
	  Bracket br = brackets.get(idx);
	  combination(idx+1, mask);
	  line[br.start] = ' ';
	  line[br.end] = ' ';
	  combination(idx+1, mask | (1 << idx));
	  line[br.start] = '(';
	  line[br.end] = ')';
	  
  }
  
  static class Bracket{
	  int start;
	  int end;
	  public Bracket(int s, int e) {
		  start = s;
		  end = e;
	  }
  }
}