import java.io.*;
import java.util.*;

public class Main {
	
	static int height;
	static int width;
	static boolean[][] prev;
	static boolean[][] cur;
	
	static int[] dx = { -1, 0, +1, +1, +1, 0, -1, -1 };
	static int[] dy = { -1, -1, -1, 0, +1, +1, +1, 0 };
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		height = Integer.parseInt(tokens.nextToken());
		width = Integer.parseInt(tokens.nextToken());
		
		prev = new boolean[height][width];
		cur = new boolean[height][width];
		boolean isSame = true;
		int cnt = 0;
		
		for(int i = 0; i < height; i++) {
			String line = in.readLine();
			for(int j = 0; j < width; j++) {
				char ch = line.charAt(j);
				prev[i][j] = ch == '1';
			}
		}
		
		for(int i = 0; i < height; i++) {
			String line = in.readLine();
			for(int j = 0; j < width; j++) {
				char ch = line.charAt(j);
				cur[i][j] = ch == '1';
			}
		}
		
		if(height >= 3 && width >= 3) {
			for(int i = 1; i < height-1; i++) {
				for(int j = 1; j < width-1; j++) {
					boolean changed = prev[i-1][j-1] ^ cur[i-1][j-1];
					if(changed) {
						cnt++;
						prev[i][j] = !prev[i][j];
						for(int k = 0; k < 8; k++) {
							int nx = j + dx[k];
							int ny = i + dy[k];
							prev[ny][nx] = !prev[ny][nx];
						}
					}
				}
			}
		}
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if(prev[i][j] != cur[i][j]) {
					isSame = false;
					break;
				}
			}
		}
		System.out.println(isSame ? cnt : -1);
	}
}