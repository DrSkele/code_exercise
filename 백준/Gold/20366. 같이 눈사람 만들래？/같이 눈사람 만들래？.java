import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    input(in);
    solve();
  }
  
  static int length;
  static int[] diameter;
  public static void input(BufferedReader in) throws IOException {
	length = Integer.parseInt(in.readLine());
	diameter = new int[length];
	StringTokenizer tokens = new StringTokenizer(in.readLine());
	for(int i = 0; i< length; i++) {
		diameter[i] = Integer.parseInt(tokens.nextToken());
	}
	Arrays.sort(diameter);
  }
  
  public static void solve() {
	  
	  int min = Integer.MAX_VALUE;
	  
	  for(int i = 0; i < length; i++) {
		  for(int j = i+1; j < length; j++) {
			  int elsa = diameter[i] + diameter[j];
			  
			  int head = 0;
			  int body = length-1;
			  
			  while(head < body) {
				  if(head == i || head == j) {
					  head++;
					  continue;
				  }
				  if(body == i || body == j) {
					  body--;
					  continue;
				  }
				  
				  int anna = diameter[head] + diameter[body];
				  min = Math.min(min, Math.abs(anna - elsa));
				  
				  if(elsa < anna) body--;
				  else if(elsa > anna) head++;
				  else break;
			  }
		  }
	  }
	  
	  System.out.println(min);
  }
  
}