import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		init(in);
		
		solve();
	}
	
	static int width;
	static int height;
	static int[][] matrix;
	static int[][] max;
	static void init(BufferedReader in) throws IOException{
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		height = Integer.parseInt(tokens.nextToken());
		width = Integer.parseInt(tokens.nextToken());
		matrix = new int[height][width];
		max = new int[height][width];
		for(int y = 0; y < height; y++) {
			tokens = new StringTokenizer(in.readLine());
			for(int x = 0; x < width; x++) {
				matrix[y][x] = Integer.parseInt(tokens.nextToken());
			}
		}
		max[0][0] = matrix[0][0];
	}
	
	static void solve() {
		for(int x = 0; x < width; x++) {
			if(x+1 < width)
				max[0][x+1] = max[0][x] + matrix[0][x+1];
			if(height > 1)
				max[1][x] = max[0][x] + matrix[1][x];
		}
		
		for(int y = 1; y < height; y++) {
			int[] temp = new int[width];
			temp[0] = max[y][0];
			for(int x = 0; x < width-1; x++) {
				temp[x+1] = Math.max(max[y][x+1], temp[x] + matrix[y][x+1]);
			}
			
			int[] temp2 = new int[width];
			temp2[width-1] = max[y][width-1];
			for(int x = width-1; x >= 1; x--) {
				temp2[x-1] = Math.max(max[y][x-1], temp2[x] + matrix[y][x-1]);
			}
			
			for(int x = 0; x < width; x++) {
				max[y][x] = Math.max(temp[x], temp2[x]);
				if(y + 1 < height)
					max[y+1][x] = max[y][x] + matrix[y+1][x];
			}
		}
//		for(int y = 0; y < height; y++) {
//			for(int x = 0; x < width; x++) {
//				System.out.print(max[y][x] + " ");
//			}
//			System.out.println();
//		}
		
		System.out.println(max[height-1][width-1]);
	}
	
}


