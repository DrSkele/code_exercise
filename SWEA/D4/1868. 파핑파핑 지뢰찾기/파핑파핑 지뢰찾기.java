import java.util.*;
import java.io.*;

class Solution
{
	
	static int size;
	static char[][] matrix;
	static int[] dx = { +1, +1, 0, -1, -1, -1, 0, +1 };
	static int[] dy = { 0, +1, +1, +1, 0, -1, -1, -1 };
	
	public static void main(String args[]) throws Exception
	{
		
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T=Integer.parseInt(in.readLine());
		for(int test_case = 1; test_case <= T; test_case++)
		{
			size = Integer.parseInt(in.readLine());
			matrix = new char[size][size];
			for(int i = 0; i < size; i++) {
				String line = in.readLine();
				for (int j = 0; j < size; j++) {
					matrix[i][j] = line.charAt(j);
				}
			}
			
			for(int y = 0; y < size; y++) {
				for (int x = 0; x < size; x++) {
					if(matrix[y][x] == '.') {
						boolean isNear = false;
						for(int i = 0; i < 8; i++) {
							int nx = x + dx[i];
							int ny = y + dy[i];
							
							if(nx < 0 || nx >= size || ny < 0 || ny >= size) continue;
							
							if(matrix[ny][nx] == '*') {
								isNear = true;
								break;
							}
						}
						if(!isNear) {
							matrix[y][x] = '0';
						}
					}
				}
			}
			
			int cnt = 0;
			for(int y = 0; y < size; y++) {
				for (int x = 0; x < size; x++) {
					if(matrix[y][x] == '0') {
						cnt++;
						bfs(x, y);
					}
				}
			}
			
			for(int y = 0; y < size; y++) {
				for (int x = 0; x < size; x++) {
					if(matrix[y][x] == '.') {
						cnt++;
					}
				}
			}
			
			System.out.println(String.format("#%d %d", test_case, cnt));
		}
	}
	
	static void bfs(int cx, int cy) {
		
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {cx, cy});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			
			for(int i = 0; i < 8; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= size || ny < 0 || ny >= size) continue;
				
				if(matrix[ny][nx] == '0') {
					q.add(new int[]{nx, ny});					
				}
				matrix[ny][nx] = '1';
			}
		}
	}
}