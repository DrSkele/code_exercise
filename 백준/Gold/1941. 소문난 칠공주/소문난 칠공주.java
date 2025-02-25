import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		input(in);
		solve();
	}
	
	static char[][] map;
	static boolean[] visited;
	static int cnt;
	static void input(BufferedReader in) throws IOException {
		map = new char[5][5];
		
		for(int i = 0; i < 5; i++) {
			map[i] = in.readLine().toCharArray();
		}
		
		visited = new boolean[1 << 25];
		cnt = 0;
	}
	
	static void solve() {
		for(int y = 0; y < 5; y++) {
			for(int x = 0; x < 5; x++) {
				boolean isSom = map[y][x] == 'S';
				chillPrincess((1 << (y * 5 + x)), isSom ? 1 : 0, isSom ? 0 : 1);
			}
		}
		
		System.out.println(cnt);
	}
	
	static int[] dx = { +1, 0, -1, 0 };
	static int[] dy = { 0, +1, 0, -1 };
	static void chillPrincess(int mark, int som, int yeon) {
		if(som + yeon == 7) {
			if(!visited[mark]) {
				visited[mark] = true;
				cnt++;
			}
			return;
		}
		
		for(int b = 0; b < 25; b++) {
			if((mark & (1 << b)) == 0) continue;
			
			int x = b % 5;
			int y = b / 5;
			
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;
				
				int nextVisit = (1 << (ny * 5 + nx));
				
				if((mark & (nextVisit)) == nextVisit) continue;
				
				boolean isSom = map[ny][nx] == 'S';
				
				if(!isSom && yeon >= 3) continue;
				
				chillPrincess(mark | nextVisit, isSom ? som + 1 : som, isSom ? yeon : yeon + 1);
			}
		}
	}
}