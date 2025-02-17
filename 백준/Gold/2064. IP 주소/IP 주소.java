import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    input(in);
    solve();
  }
  
  static int length;
  static int[][] addresses;
  public static void input(BufferedReader in) throws IOException {
	  length = Integer.parseInt(in.readLine());
	  addresses = new int[4][length];
	  for(int i = 0; i < length; i++) {
		  StringTokenizer tokens = new StringTokenizer(in.readLine(), ".");
		  
		  addresses[0][i] = Integer.parseInt(tokens.nextToken());
		  addresses[1][i] = Integer.parseInt(tokens.nextToken());
		  addresses[2][i] = Integer.parseInt(tokens.nextToken());
		  addresses[3][i] = Integer.parseInt(tokens.nextToken());
	  }
  }
  
  public static void solve() {
	  
	  int[] mask = new int[4];
	  
	  Loop : for(int i = 0; i < 4; i++) {
		  for(int j = 7; j >= 0; j--) {
			  
			  boolean isValid = true;
			  int curBit = 1 << j;
			  
			  for(int k = 1; k < length; k++) {
				  if((addresses[i][0] & curBit) != (addresses[i][k] & curBit)) {
					  isValid = false;
					  break;
				  }
			  }
			  
			  if(isValid) {
				  mask[i] |= curBit;
			  } else {
				  break Loop;
			  }
		  }
	  }
	  
	  int[] address = new int[4];
	  for(int i = 0; i < 4; i++) {
		  address[i] = (mask[i] & addresses[i][0]);
	  }
	  
	  System.out.println(address[0] + "." + address[1] + "." + address[2] + "." + address[3]);
	  System.out.println(mask[0] + "." + mask[1] + "." + mask[2] + "." + mask[3]);
  }
  
}