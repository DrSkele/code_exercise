import java.io.*;
import java.util.*;
public class Main {

	public static void main(String[] args) throws IOException {
		init();
		solve();
	}

	static int size;
	static int[][] matrix;
	static int result;
	public static void init() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		size = Integer.parseInt(in.readLine());
		matrix = new int[size][size];
		
		for(int i = 0; i < size; i++) {
			StringTokenizer tokens = new StringTokenizer(in.readLine());
			for(int j = 0; j < size; j++) {
				matrix[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		result = 0;
	}
	
	public static void solve() {
		int direction = 0;
		int cnt = 0;
		int move = 0;
		int step = 1;
		int x = size/2-1;
		int y = size/2;
		while(x >= 0 && x < size && y >= 0 && y < size) {
			int sand = matrix[y][x];
			if(direction == 0) {
				int blown = blow(x, y-2, sand, 0.02)
						+blow(x-1, y-1, sand, 0.1)
						+blow(x, y-1, sand, 0.07)
						+blow(x+1, y-1, sand, 0.01)
						+blow(x-2, y, sand, 0.05)
						+blow(x-1, y+1, sand, 0.1)
						+blow(x, y+1, sand, 0.07)
						+blow(x+1, y+1, sand, 0.01)
						+blow(x, y+2, sand, 0.02);
				leftOver(x-1, y, sand - blown);
			}
			else if(direction == 1) {
				int blown = blow(x-2, y, sand, 0.02)
						+blow(x-1, y+1, sand, 0.1)
						+blow(x-1, y, sand, 0.07)
						+blow(x-1, y-1, sand, 0.01)
						+blow(x, y+2, sand, 0.05)
						+blow(x+1, y+1, sand, 0.1)
						+blow(x+1, y, sand, 0.07)
						+blow(x+1, y-1, sand, 0.01)
						+blow(x+2, y, sand, 0.02);
				leftOver(x, y+1, sand - blown);
			}
			else if(direction == 2) {
				int blown = blow(x, y-2, sand, 0.02)
						+blow(x+1, y-1, sand, 0.1)
						+blow(x, y-1, sand, 0.07)
						+blow(x-1, y-1, sand, 0.01)
						+blow(x+2, y, sand, 0.05)
						+blow(x+1, y+1, sand, 0.1)
						+blow(x, y+1, sand, 0.07)
						+blow(x-1, y+1, sand, 0.01)
						+blow(x, y+2, sand, 0.02);
				leftOver(x+1, y, sand - blown);
			}
			else if(direction == 3) {
				int blown = blow(x-2, y, sand, 0.02)
						+blow(x-1, y-1, sand, 0.1)
						+blow(x-1, y, sand, 0.07)
						+blow(x-1, y+1, sand, 0.01)
						+blow(x, y-2, sand, 0.05)
						+blow(x+1, y-1, sand, 0.1)
						+blow(x+1, y, sand, 0.07)
						+blow(x+1, y+1, sand, 0.01)
						+blow(x+2, y, sand, 0.02);
				leftOver(x, y-1, sand - blown);
			}
			
			move++;
			if(move == step) { // 꺽는 지점
				cnt++;
				move = 0;
				direction = (direction+1) % 4;
			}
			if(cnt == 2) { // 두번 꺽었다면
				step++;
				cnt = 0;
			}
			
			switch(direction) {
			case 0 : x -= 1; break;
			case 1 : y += 1; break;
			case 2 : x += 1; break;
			case 3 : y -= 1; break;
			}
		}
		System.out.println(result);
	}
	static void leftOver(int x, int y, int sand) {
		if(x >= 0 && x < size && y >= 0 && y < size) {
			matrix[y][x] += sand;
		} else {
			result += sand;
		}
	}
	
	static int blow(int x, int y, int sand, double percent) {
		int blown = (int)Math.floor(sand * percent);
		if(x >= 0 && x < size && y >= 0 && y < size) {
			matrix[y][x] += blown;
		} else {
			result += blown;
		}
		return blown;
	}
}
