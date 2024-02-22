import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(in.readLine());
		String curState = in.readLine();
		String nextState = in.readLine();
		int length = curState.length();
		
		boolean[] state;
		int cnt = 0;
		boolean impossible = false;
		
		for(int t = 0; t < 2; t++) {	
			state = new boolean[length];
			state[0] = (t % 2) != 0;
			cnt = t;
			for(int i = 1; i < length; i++) {
				boolean hasChanged = curState.charAt(i-1) != nextState.charAt(i-1);
				boolean switchChanged = i > 1 ? state[i-1] ^ state[i-2] : state[i-1];
				
				state[i] = hasChanged ^ switchChanged;
				if(state[i]) cnt++;
			}
			impossible = (curState.charAt(length-1) != nextState.charAt(length-1)) ^ (state[length-1] ^ state[length-2]);
			
			if(!impossible) break;
		}
		
		System.out.println(impossible ? -1 : cnt);
	}
	
}