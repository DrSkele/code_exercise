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
	static char[][] matrix;
	static int[] redStart;
	static int[] blueStart;
	static int[] hole;
	static int minMove;
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		height = Integer.parseInt(tokens.nextToken());
		width = Integer.parseInt(tokens.nextToken());
		matrix = new char[height][width];
		
		for(int i = 0; i < height; i++) {
			char[] line = in.readLine().toCharArray();
			for(int j = 0; j < line.length; j++) {
				switch(line[j]) {
				case 'R': redStart = new int[] { i, j }; matrix[i][j] = '.'; break;
				case 'B': blueStart = new int[] { i, j }; matrix[i][j] = '.'; break;
				case 'O': hole = new int[] { i, j }; matrix[i][j] = '.'; break;
				default : matrix[i][j] = line[j]; break; 
				}
			}
		}
		
		minMove = Integer.MAX_VALUE;
	}
	
	static void solve() {
		
		move(0, redStart, blueStart, -1);
		
		if(minMove == Integer.MAX_VALUE) minMove = -1;
		
		System.out.println(minMove);
	}
	
	static int[] dx = { +1, 0, -1, 0 };
	static int[] dy = { 0, +1, 0, -1 };
	static void move(int cnt, int[] red, int[] blue, int lastDir) {
		if(cnt >= 10) {
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			if(i == lastDir) continue;
			
			boolean redFirst = true;
			
			switch(i) {
			case 0 : redFirst = red[1] >= blue[1]; break;
			case 1 : redFirst = red[0] >= blue[0]; break;
			case 2 : redFirst = red[1] <= blue[1]; break;
			case 3 : redFirst = red[0] <= blue[0]; break;
			}
			
			int[] newRed = new int[] {red[0], red[1]};
			int[] newBlue = new int[] {blue[0], blue[1]};
			
			boolean redInHole = false;
			boolean blueInHole = false;
			if(redFirst) {
				//red first
				while(matrix[newRed[0]+dy[i]][newRed[1]+dx[i]] == '.') {
					newRed[0] += dy[i];
					newRed[1] += dx[i];
					
					if(newRed[0] == hole[0] && newRed[1] == hole[1]) {
						redInHole = true;
					}
				}
				
				while(matrix[newBlue[0]+dy[i]][newBlue[1]+dx[i]] == '.' && (redInHole || !(newBlue[0] + dy[i] == newRed[0] && newBlue[1] + dx[i] == newRed[1]))) {
					newBlue[0] += dy[i];
					newBlue[1] += dx[i];
					
					if(newBlue[0] == hole[0] && newBlue[1] == hole[1]) {
						blueInHole = true;
					}
				}
			} else {
				//blue first
				while(matrix[newBlue[0]+dy[i]][newBlue[1]+dx[i]] == '.') {
					newBlue[0] += dy[i];
					newBlue[1] += dx[i];
					
					if(newBlue[0] == hole[0] && newBlue[1] == hole[1]) {
						blueInHole = true;
					}
				}
				
				while(matrix[newRed[0]+dy[i]][newRed[1]+dx[i]] == '.' 
						&& !(newRed[0] + dy[i] == newBlue[0] && newRed[1] + dx[i] == newBlue[1])) {
					newRed[0] += dy[i];
					newRed[1] += dx[i];
					
					if(newRed[0] == hole[0] && newRed[1] == hole[1]) {
						redInHole = true;
					}
				}
			}
			
			if(blueInHole) continue;
			else if(redInHole) {
				minMove = Math.min(minMove, cnt + 1);
				return;
			}
			
			// not moved
			if(red[0] == newRed[0] && red[1] == newRed[1] && blue[0] == newBlue[0] && blue[1] == newBlue[1]) continue;
			
			//System.out.println(newRed[0] + " " + newRed[1] + " " + newBlue[0] + " " + newBlue[1]);
			
			move(cnt + 1, newRed, newBlue, i);
		}
	}
}