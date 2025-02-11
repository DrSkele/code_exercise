import java.io.*;
import java.util.*;

public class Main {

	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		init(in);
		
		solve(in);			
	}
	
	static int height;
	static int width;
	static char[][] mine;
	static int[][] left;
	static int[][] right;
	public static void init(BufferedReader in) throws IOException{
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		height = Integer.parseInt(tokens.nextToken());
		width = Integer.parseInt(tokens.nextToken());
		
		mine = new char[height][width];
		
		for(int y = 0; y < height; y++) {
			mine[y] = in.readLine().toCharArray();
		}
		
		left = new int[height][width];
		right = new int[height][width];
		
		for(int y = height - 1; y >= 0; y--) {
			for(int x = width - 1; x >= 0; x--) {
				if(mine[y][x] == '0') left[y][x] = 0;
				else {
					if(y == height - 1 || x == 0) left[y][x] = 1;
					else left[y][x] = left[y+1][x-1] + 1;
				}
			}
		}
		
		for(int y = height - 1; y >= 0; y--) {
			for(int x = 0; x < width; x++) {
				if(mine[y][x] == '0') right[y][x] = 0;
				else {
					if(y == height - 1 || x == width - 1) right[y][x] = 1;
					else right[y][x] = right[y+1][x+1] + 1;
				}
			}
		}
		
//		for(int y = 0; y < height; y++) {
//			for(int x = 0; x < width; x++) {
//				System.out.print(right[y][x]);
//			}
//			System.out.println();
//		}
	}
	
	public static void solve(BufferedReader in) throws IOException {
		int max = 0;
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				int size = Math.min(left[y][x], right[y][x]);
				
				while(size > 0) {
					int leftCheck = right[y+(size-1)][x-(size-1)];
					int rightCheck = left[y+(size-1)][x+(size-1)];
					
					if(size <= leftCheck && size <= rightCheck) {
						break;
					}
					
					size--;
				}
				
				max = Math.max(max, size);
			}
		}
		
		System.out.println(max);
	}
}
