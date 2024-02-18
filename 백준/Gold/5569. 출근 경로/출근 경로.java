import java.io.*;
import java.util.*;

public class Main {
	
	static CrossRoad[][] matrix;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		
		int width = Integer.parseInt(tokens.nextToken());
		int height = Integer.parseInt(tokens.nextToken());
		
		matrix = new CrossRoad[height][width]; // 1 : from south, 0 : from west
		
		matrix[0][0] =  new CrossRoad();
		matrix[0][1] = new CrossRoad();
		matrix[1][0] = new CrossRoad();
		
		matrix[0][1].fromWest[1] = 1;
		matrix[1][0].fromSouth[1] = 1;
		
		CrossRoad result = dp(width-1, height-1);
		
//		for(int i = 0; i < height; i++) {
//			for(int j = 0; j < width; j++) {
//				System.out.print(String.format("{(%d ,%d) (%d, %d)}", matrix[i][j].fromWest[0], matrix[i][j].fromWest[1]
//						, matrix[i][j].fromSouth[0], matrix[i][j].fromSouth[1]));
//			}
//			System.out.println();
//		}
		
		System.out.println(result.all());
	}
	
	static CrossRoad noVal = new CrossRoad();
	static CrossRoad dp(int x, int y) {
		if(x < 0 || y < 0) return noVal;
		if(matrix[y][x] == null) {
			if(x == 0) {
				matrix[y][x] = dp(x, y-1);
			} else if(y== 0) {
				matrix[y][x] = dp(x-1, y);
			} else {
				CrossRoad cross = new CrossRoad();
				CrossRoad south = dp(x, y-1);
				CrossRoad west = dp(x-1, y);
				cross.fromSouth[0] = south.fromWest[1];
				cross.fromSouth[1] = (south.fromSouth[0] + south.fromSouth[1]) % 100_000;
				cross.fromWest[0] = west.fromSouth[1];
				cross.fromWest[1] = (west.fromWest[0] + west.fromWest[1]) % 100_000;
				matrix[y][x] = cross;
			}
		}
		return matrix[y][x];
	}
	
	static class CrossRoad{
		public int[] fromWest = new int[2]; //0 : 한 칸 전 , 1: 두 칸 전
		public int[] fromSouth = new int[2];
		
		public int all() {
			return (fromWest[0] + fromWest[1] + fromSouth[0] + fromSouth[1]) % 100_000;
		}
	}
}


