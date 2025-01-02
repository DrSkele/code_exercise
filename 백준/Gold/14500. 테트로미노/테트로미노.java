import java.io.*;
import java.util.*;

class Main{
	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();		
	}
	
	static int height;
	static int width;
	static int[][] matrix;
	static int max;
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		height = Integer.parseInt(tokens.nextToken());
		width = Integer.parseInt(tokens.nextToken());
		matrix = new int[height][width];
		for(int y = 0; y < height; y++) {
			tokens = new StringTokenizer(in.readLine());
			for(int x = 0; x < width; x++) {
				matrix[y][x] = Integer.parseInt(tokens.nextToken());
			}
		}
		max = 0;
	}
	
	static void solve() {
		placeTetromino();
		rotateMatrix();
		placeTetromino();
		rotateMatrix();
		placeTetromino();
		rotateMatrix();
		placeTetromino();
		rotateMatrix();
		mirrorMatrix();
		placeTetromino();
		rotateMatrix();
		placeTetromino();
		rotateMatrix();
		placeTetromino();
		rotateMatrix();
		placeTetromino();
		
		System.out.println(max);
	}
	
	static void placeTetromino() {
		int curHeight = matrix.length;
		int curWidth = matrix[0].length;
		
		for(int y = 0; y < curHeight; y++) {
			for(int x = 0; x < curWidth; x++) {
				// stick shape
				if(x + 3 < curWidth) {
					max = Math.max(max, matrix[y][x] + matrix[y][x+1] + matrix[y][x+2] + matrix[y][x+3]);
				}
				
				// box shape
				if(x + 1 < curWidth && y + 1 < curHeight) {
					max = Math.max(max, matrix[y][x] + matrix[y][x+1] + matrix[y+1][x] + matrix[y+1][x+1]);
				}
				
				if(x + 1 < curWidth && y + 2 < curHeight) {
					// L shape
					max = Math.max(max, matrix[y][x] + matrix[y+1][x] + matrix[y+2][x] + matrix[y+2][x+1]);
					// lightning shape
					max = Math.max(max, matrix[y][x] + matrix[y+1][x] + matrix[y+1][x+1] + matrix[y+2][x+1]);
					// ㅏ shape
					max = Math.max(max, matrix[y][x] + matrix[y+1][x] + matrix[y+1][x+1] + matrix[y+2][x]);
				}	
			}
		}
	}
	
	static void rotateMatrix() {
		int newWidth = matrix.length;
		int newHeight = matrix[0].length;
		
		int[][] newMatrix = new int[newHeight][newWidth];
		
		for(int y = 0; y < newHeight; y++) {
			for(int x = 0; x < newWidth; x++) {
				newMatrix[y][x] = matrix[newWidth - 1 - x][y];
			}
		}
		
		matrix = newMatrix;
	}
	
	static void mirrorMatrix() {
		int newWidth = matrix[0].length;
		int newHeight = matrix.length;
		
		int[][] newMatrix = new int[newHeight][newWidth];
		
		for(int y = 0; y < newHeight; y++) {
			for(int x = 0; x < newWidth; x++) {
				newMatrix[y][x] = matrix[y][newWidth - 1 - x];
			}
		}
		
		matrix = newMatrix;
	}
}