import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static int height;
	static int width;
	static int rotate;
	static int[][] matrix;
	static int[][] rotation;
	static int full;
	static int min;
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		
		height = Integer.parseInt(tokens.nextToken());
		width = Integer.parseInt(tokens.nextToken());
		rotate = Integer.parseInt(tokens.nextToken());
		
		matrix = new int[height][width];
		rotation = new int[rotate][3];
		
		for(int i = 0; i < height; i++) {
			tokens = new StringTokenizer(in.readLine());
			for(int j = 0; j < width; j++) {
				matrix[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		for(int i = 0; i < rotate; i++) {
			tokens = new StringTokenizer(in.readLine());
			for(int j = 0; j < 3; j++) {
				rotation[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		full = (1<<rotate)-1;
		min = Integer.MAX_VALUE;
	}
	
	static void solve(){
		rotate(0);
		System.out.println(min);
	}
	
	static void rotate(int check) {
		if(check == full) {
			findMin();
			return;
		}
		
		for(int i = 0; i < rotate; i++) {
			if((check & (1 << i)) > 0) continue;
			
			int row = rotation[i][0]-1;
			int col = rotation[i][1]-1;
			int size = rotation[i][2];
			
			rotateClockwise(row, col, size);
//			System.out.println(row + " " + col + " " + size);
//			printMatrix();
			rotate(check | (1 << i));
			rotateCounterClock(row, col, size);
		}
	}
	
	static void printMatrix() {
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	static void findMin() {
		for(int i = 0; i < height; i++) {
			int sum = 0;
			for(int j = 0; j < width; j++) {
				sum += matrix[i][j];
			}
			min = Math.min(min, sum);
		}
	}
	
	static void rotateClockwise(int row, int col, int size) {
		if(size == 0) return;
		
		int left = col-size;
		int right = col+size;
		int top = row-size;
		int bottom = row+size;
		
		int temp = matrix[top][left];
		
		for(int i = left+1; i <= right; i++) {
			int cur = matrix[top][i];
			matrix[top][i] = temp;
			temp = cur;
		}
		for(int i = top+1; i <= bottom; i++) {
			int cur = matrix[i][right];
			matrix[i][right] = temp;
			temp = cur;
		}
		for(int i = right-1; i >= left; i--) {
			int cur = matrix[bottom][i];
			matrix[bottom][i] = temp;
			temp = cur;
		}
		for(int i = bottom-1; i >= top; i--) {
			int cur = matrix[i][left];
			matrix[i][left] = temp;
			temp = cur;
		}
		rotateClockwise(row, col, size-1);
	}
	
	static void rotateCounterClock(int row, int col, int size) {
		if(size == 0) return;
		
		int left = col-size;
		int right = col+size;
		int top = row-size;
		int bottom = row+size;
		
		int temp = matrix[top][left];
		
		for(int i = top+1; i <= bottom; i++) {
			int cur = matrix[i][left];
			matrix[i][left] = temp;
			temp = cur;
		}
		for(int i = left+1; i <= right; i++) {
			int cur = matrix[bottom][i];
			matrix[bottom][i] = temp;
			temp = cur;
		}
		for(int i = bottom-1; i >= top; i--) {
			int cur = matrix[i][right];
			matrix[i][right] = temp;
			temp = cur;
		}
		for(int i = right-1; i >= left; i--) {
			int cur = matrix[top][i];
			matrix[top][i] = temp;
			temp = cur;
		}
		
		rotateCounterClock(row, col, size-1);
	}
}