import java.io.*;
import java.util.*;

class Main{
	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();		
	}
	
	static int length;
	static int goal;
	static int[] bowls;
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		length = Integer.parseInt(tokens.nextToken());
		goal = Integer.parseInt(tokens.nextToken());
		
		bowls = new int[length];
		tokens = new StringTokenizer(in.readLine());
		for(int i = 0; i < length; i++) {
			bowls[i] = Integer.parseInt(tokens.nextToken());
		}
	}
	
	static int[] dx = { -1, 0, +1, 0 };
	static int[] dy = { 0, -1, 0, +1 };
	
	static void solve() {
		int idx = 0;
		int num = 0; // max size of one side that makes cube.
		int inBoxLength = 0; // max number of index that makes cube.
		while(true) {		
			int tempNum = num;
			if(idx % 2 == 0) {
				tempNum += 1;
			}
			if(inBoxLength + tempNum > length) {
				break;
			} else {
				num = tempNum;
				inBoxLength += num;
			}
			idx++;
		}
		
		int BoxHeight = idx % 2 == 0 ? num + 1 : num;
		int BoxWidth = num;
		
		int cnt = 0;
		
		while(!checkDiff(goal)) {
			addFish();
//			for(int fish : bowls) {
//				System.out.print(fish + " ");
//			}
//			System.out.println();
			
			circularPlace(BoxHeight, BoxWidth, inBoxLength);
			
//			for(int fish : bowls) {
//				System.out.print(fish + " ");
//			}
//			System.out.println();
			
			foldPlace();
			
//			for(int fish : bowls) {
//				System.out.print(fish + " ");
//			}
//			System.out.println();
			
			cnt++;
		}
		
		System.out.println(cnt);
	}
	
	static void addFish() {
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < length; i++) {
			min = Math.min(min, bowls[i]);
		}
		for(int i = 0; i < length; i++) {
			if(bowls[i] == min) bowls[i]++;
		}
	}
	
	static void circularPlace(int height, int width, int inBoxLength) {
		int y = height - 1;
		int x = width - 1;
		int dir = 0;
		
		int[][] matrix = new int[height][width];
		
		int idx = inBoxLength - 1;
		//System.out.println("coord : " + y + " " + x + " " + idx);
		matrix[y][x] = bowls[idx--];

		
		while(idx >= 0) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			
			if(nx < 0 || nx >= width || ny < 0 || ny >= height || matrix[ny][nx] != 0) {
				dir = (dir + 1) % 4;
				continue;
			}
			
			x = nx;
			y = ny;
			
			//System.out.println("coord : " + y + " " + x + " " + idx);
			matrix[y][x] = bowls[idx--];
		}
		
//		for(int i = 0; i < height; i++) {
//			for(int j = 0; j < width; j++) {
//				System.out.print(matrix[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		int[][] temp = new int[height][width];
		for(y = 0; y < height; y++) {
			for(x = 0; x < width; x++) {
				int val = matrix[y][x];
				temp[y][x] += val;
				
				for(int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					
					if(nx < 0 || nx >= width || ny < 0 || ny >= height) continue;
					
					temp[y][x] += (matrix[ny][nx] - val) / 5;
				}
			}
		}
		
//		for(int i = 0; i < height; i++) {
//			for(int j = 0; j < width; j++) {
//				System.out.print(temp[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		int leftOver = length - inBoxLength;
		int[] tempArr = new int[leftOver];
		
		if(leftOver > 0) {
			int first = bowls[inBoxLength];
			tempArr[0] = first;
			temp[height-1][width-1] += (first - matrix[height-1][width-1]) / 5;
			tempArr[0] += (matrix[height-1][width-1] - first) / 5;
			if(inBoxLength + 1 < length) {
				tempArr[0] += (bowls[inBoxLength + 1] - first) / 5;
			}
					
			for(int i = 1; i < leftOver; i++) {
				int val = bowls[inBoxLength + i];
				tempArr[i] += val;
				
				if(inBoxLength + i - 1 >= 0) {
					tempArr[i] += (bowls[inBoxLength + i - 1] - val) / 5;
				}
				if(inBoxLength + i + 1 < length) {
					tempArr[i] += (bowls[inBoxLength + i + 1] - val) / 5;
				}
			}
			
			for(int i = 0; i < leftOver; i++) {
				bowls[inBoxLength + i] = tempArr[i];
			}
		}
		
		idx = 0;
		for(x = 0; x < width; x++) {
			for(y = height-1; y >= 0; y--) {
				bowls[idx++] = temp[y][x];
			}
		}
	}
	
	static void foldPlace() {
		int halfLength = bowls.length/2;
		int[][] firstMatrix = new int[2][halfLength];
		
		for(int x = 0; x < halfLength; x++) {
			firstMatrix[0][x] = bowls[halfLength-1-x];
			firstMatrix[1][x] = bowls[halfLength + x];
		}
		
		int quarterLength = bowls.length/4;
		int[][] secondMatrix = new int[4][quarterLength];
		
		for(int x = 0; x < quarterLength; x++) {
			secondMatrix[0][x] = firstMatrix[1][quarterLength-1-x];
			secondMatrix[1][x] = firstMatrix[0][quarterLength-1-x];
			secondMatrix[2][x] = firstMatrix[0][quarterLength + x];
			secondMatrix[3][x] = firstMatrix[1][quarterLength + x];
		}
		
		int[][] temp = new int[4][quarterLength];
		for(int y = 0; y < 4; y++) {
			for(int x = 0; x < quarterLength; x++) {
				int val = secondMatrix[y][x];
				temp[y][x] += val;
				
				for(int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					
					if(nx < 0 || nx >= quarterLength || ny < 0 || ny >= 4) continue;
					
					temp[y][x] += (secondMatrix[ny][nx] - val) / 5;
				}
			}
		}
		
		int idx = 0;
		
		for(int x = 0; x < quarterLength; x++) {
			for(int y = 3; y >= 0; y--) {
				bowls[idx++] = temp[y][x];
			}
		}
	}
	
	static boolean checkDiff(int goal) {
		int max = 0;
		int min = Integer.MAX_VALUE;
		for(int fish : bowls) {
			max = Math.max(max, fish);
			min = Math.min(min, fish);
		}
		
		return max - min <= goal;
	}
}