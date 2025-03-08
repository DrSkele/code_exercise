import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();		
	}
	
	static int time;
	static int size;
	static int middle;
	static int startY;
	static int endY;
	static int startX;
	static int endX;
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		time = Integer.parseInt(tokens.nextToken());
		size = Integer.parseInt(tokens.nextToken());
		middle = Integer.parseInt(tokens.nextToken());
		startY = Integer.parseInt(tokens.nextToken());
		endY = Integer.parseInt(tokens.nextToken());
		startX = Integer.parseInt(tokens.nextToken());
		endX = Integer.parseInt(tokens.nextToken());
	}
	
	static void solve(){
		StringBuilder str = new StringBuilder();
		
		for(int y = startY; y <= endY; y++) {
			for(int x = startX; x <= endX; x++) {
				if(time > 0) str.append(findValue(time, y, x));
				else str.append(0);
			}
			str.append("\n");
		}
		
		System.out.println(str.toString());
	}
	
	static int findValue(int t, int y, int x) {
		if(t == 1) {
			int midStart = size/2 - middle/2;
			int midEnd = midStart + middle;
			if(midStart <= y && y < midEnd && midStart <= x && x < midEnd) return 1;
			else return 0;
		} else {
			int curMiddle = middle * (int)Math.pow(size, t-1);
			int curSize = size * (int)Math.pow(size, t-1);
			
			int midStart = curSize/2 - curMiddle/2;
			int midEnd = midStart + curMiddle;
			if(midStart <= y && y < midEnd && midStart <= x && x < midEnd) return 1;
			else {
				y %= curSize/size;
				x %= curSize/size;
				
				return findValue(t-1, y, x);
			}
		}
	}
}