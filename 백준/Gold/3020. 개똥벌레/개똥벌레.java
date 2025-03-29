import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static int length;
	static int height;
	static int[] top;
	static int[] bottom;
	static void input(BufferedReader in) throws IOException {		
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		length = Integer.parseInt(tokens.nextToken());
		height = Integer.parseInt(tokens.nextToken());
		
		top = new int[length/2];
		bottom = new int[(length % 2 == 0) ? length/2 : length/2 + 1];
		
		for(int i = 0; i < length; i++) {
			if(i % 2 == 0) {
				bottom[i/2] = Integer.parseInt(in.readLine());
			} else {
				top[i/2] = Integer.parseInt(in.readLine());
			}
		}
		Arrays.sort(top);
		Arrays.sort(bottom);
		
	}
	
	static void solve(){
		int min = Integer.MAX_VALUE;
		int cnt = 0;
		
		for(int h = 0; h < height; h++) {
			int obstacles = obstacleOnTop(h) + obstacleOnBottom(h);
			//System.out.println(h + " " + obstacleOnTop(h) + " " + obstacleOnBottom(h));
			
			if(min > obstacles) {
				min = obstacles;
				cnt = 1;
			} else if(min == obstacles) {
				cnt++;
			}
		}
		
		System.out.println(min + " " + cnt);
	}
	
	static int obstacleOnTop(int h) {
		int left = 0;
		int right = top.length-1;
		int idx = -1;
		
		while(left <= right) {
			int mid = left + (right-left)/2;
			
			int curHeight = top[mid];
			
			if(height - h <= curHeight) {
				idx = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		
		return idx >= 0 ? top.length - idx : 0;
	}
	
	static int obstacleOnBottom(int h) {
		int left = 0;
		int right = bottom.length-1;
		int idx = -1;
		
		while(left <= right) {
			int mid = left + (right-left)/2;
			
			int curHeight = bottom[mid];
			
			if(h < curHeight) {
				idx = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		
		return idx >= 0 ? bottom.length - idx : 0;
	}
}