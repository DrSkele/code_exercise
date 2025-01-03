import java.io.*;
import java.util.*;

class Main{
	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();		
	}
	
	
	static int size;
	static int cnt;
	static int[][] matrix;
	static int[][] magic;
	static int[] exploded; // number of exploded beads
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		size = Integer.parseInt(tokens.nextToken());
		cnt = Integer.parseInt(tokens.nextToken());
		matrix = new int[size][size];
		for(int y = 0; y < size; y++) {
			tokens = new StringTokenizer(in.readLine());
			for(int x = 0; x < size; x++) {
				matrix[y][x] = Integer.parseInt(tokens.nextToken());
			}
		}
		magic = new int[cnt][2];
		for(int i = 0; i < cnt; i++) {
			tokens = new StringTokenizer(in.readLine());
			magic[i][0] = Integer.parseInt(tokens.nextToken()) - 1;
			magic[i][1] = Integer.parseInt(tokens.nextToken());
		}
		exploded = new int[4];
	}
	
	static void solve() {
		setup();
		blizzard();
	}
	
	static int[] dx = { -1, 0, +1, 0 };
	static int[] dy = { 0, +1, 0, -1 };
	static int[][] range;
	static int length;
	static int[][] index;
	static void setup() {
		range = new int[4][size/2];
		length = size * size - 1;
		index = new int[length][2];
		
		int idx = 0;
		int dir = 0;
		int n = 1;
		int cnt = 0;
		
		int x = size/2;
		int y = size/2;
		
		while(x + dx[dir] >= 0 && x + dx[dir] < size && y + dy[dir] >= 0 && y + dy[dir] < size) {
			x += dx[dir];
			y += dy[dir];
			
			index[idx] = new int[] {y, x};
			
			if(x == size/2 && y < size/2) range[0][size/2-1-y] = idx;
			else if(x == size/2 && y > size/2) range[1][y - 1 - size/2] = idx;
			else if(x < size/2 && y == size/2) range[2][size/2-1-x] = idx;
			else if(x > size/2 && y == size/2) range[3][x - 1 - size/2] = idx;
			
			idx++;
			cnt++;
			if(cnt == n) {
				dir = (dir + 1) % 4;
				cnt = 0;
				if(dir == 0 || dir == 2) n++;
			}
		}
		
//		for(int num : arr) {
//			System.out.print(num + " ");
//		}
//		System.out.println();
	}
	
	static void blizzard() {
		
		for(int i = 0; i < cnt; i++) {
			int dir = magic[i][0];
			int area = magic[i][1];
			
			for(int a = 0; a < area; a++) {
				int idx = range[dir][a];
				
				int[] coord = index[idx];
				matrix[coord[0]][coord[1]] = 0;
			}
			
//			for(int[] num : index) {
//				System.out.print(matrix[num[0]][num[1]] + " ");
//			}
//			System.out.println();
			
			removeZero();
			
			while(popBeads()) {
				removeZero();
			}
			
			changeBeads();
			
		}
		
		int answer = 0;
		for(int i = 1; i < exploded.length; i++) {
			answer += exploded[i] * i;
		}
		System.out.println(answer);
	}
	
	static boolean popBeads() {
		boolean hasContinuous = false;
		
		int idx = 0;
		int prev = -1;
		int inRow = 1;
		while(idx < index.length) {
			int[] coord = index[idx];
			int cur = matrix[coord[0]][coord[1]];
			
			if(cur == 0) break;
			
			if(prev == cur) inRow++;
			else inRow = 1;
			
			if(inRow >= 4 && (idx + 1 >= index.length || matrix[index[idx+1][0]][index[idx+1][1]] != cur)) {
				exploded[cur] += inRow;
				for(int i = 0; i < inRow; i++) {
					int[] toRemove = index[idx-i];
					matrix[toRemove[0]][toRemove[1]] = 0;
				}
				prev = -1;
				inRow = 1;
				hasContinuous = true; 
			} else {
				prev = cur;
			}
			idx++;
		}
		
//		for(int[] num : index) {
//			System.out.print(matrix[num[0]][num[1]] + " ");
//		}
//		System.out.println();
		
		return hasContinuous;
	}
	
	static void changeBeads() {
		int[][] subMatrix = new int[size][size];
		
		int subIdx = 0;
		int idx = 0;
		int prev = -1;
		int inRow = 1;
		while(idx < index.length && subIdx < index.length) {
			int[] coord = index[idx];
			int cur = matrix[coord[0]][coord[1]];
			
			if(cur == 0) break;
			
			if(prev == cur) inRow++;
			else inRow = 1;
			
			if(idx + 1 >= index.length || matrix[index[idx+1][0]][index[idx+1][1]] != cur) {
				int[] subCoord = index[subIdx++];
				subMatrix[subCoord[0]][subCoord[1]] = inRow;
				subCoord = index[subIdx++];
				subMatrix[subCoord[0]][subCoord[1]] = cur;
				
				prev = -1;
				inRow = 1;
			} else {
				prev = cur;
			}
			idx++;
		}
		
		matrix = subMatrix;
		
//		for(int[] num : index) {
//			System.out.print(matrix[num[0]][num[1]] + " ");
//		}
//		System.out.println();
	}
	
	static void removeZero() {
		int idx = 0;
		int dir = 0;
		int n = 1;
		int cnt = 0;
		
		int x = size/2;
		int y = size/2;
		int forward = 0;
		
		while(x + dx[dir] >= 0 && x + dx[dir] < size && y + dy[dir] >= 0 && y + dy[dir] < size) {
			x += dx[dir];
			y += dy[dir];
			
			int cur = matrix[y][x];
			
			if(cur == 0 && (idx + forward < index.length && matrix[index[idx + forward][0]][index[idx + forward][1]] == 0)) {
				// length of zeros
				for(int i = idx + forward; i < index.length; i++) {
					int[] coord = index[i];
					if(matrix[coord[0]][coord[1]] != 0) break;
					forward++;
				}
				//System.out.println(forward);
			}
			
			if(idx + forward >= index.length) break;
			if(forward > 0) {
				int[] nextIdx = index[idx + forward];
				matrix[y][x] = matrix[nextIdx[0]][nextIdx[1]];
				matrix[nextIdx[0]][nextIdx[1]] = 0;				
			}
			
			idx++;
			cnt++;
			if(cnt == n) {
				dir = (dir + 1) % 4;
				cnt = 0;
				if(dir == 0 || dir == 2) n++;
			}
		}
		
//		for(int[] num : index) {
//			System.out.print(matrix[num[0]][num[1]] + " ");
//		}
//		System.out.println();
	}
}