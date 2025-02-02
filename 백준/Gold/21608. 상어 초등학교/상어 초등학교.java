import java.io.*;
import java.util.*;

public class Main {

	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		init(in);
		
		solve(in);
	}
	
	static int N;
	static int[][] seat;
	public static void init(BufferedReader in) throws IOException{
		N = Integer.parseInt(in.readLine());
		
		seat = new int[N][N];
	}
	
	static Set<Integer>[] likes;
	public static void solve(BufferedReader in) throws IOException {
		likes = new TreeSet[N*N+1];
		for(int i = 0; i < N * N; i++) {
			StringTokenizer tokens = new StringTokenizer(in.readLine());
			int student = Integer.parseInt(tokens.nextToken());
			likes[student] = new TreeSet<>();
			
			for(int j = 0; j < 4; j++) {
				likes[student].add(Integer.parseInt(tokens.nextToken()));
			}
			
			placeSeat(student, likes[student]);
		}
		
		System.out.println(sumPoints());
	}
	
	static int[] dx = { +1, 0, -1, 0 };
	static int[] dy = { 0, +1, 0, -1 };
	static void placeSeat(int student, Set<Integer> likes) {
		int placeX = 0;
		int placeY = 0;
		int friend = -1;
		int space = -1;
		
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				if(seat[y][x] != 0) continue;
				
				int near = 0;
				int empty = 0;
				
				for(int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					
					if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
					
					if(seat[ny][nx] == 0) empty++;
					else if(likes.contains(seat[ny][nx])) near++;
				}
				
				if(friend < near || (near == friend && space < empty)) {
					placeX = x;
					placeY = y;
					friend = near;
					space = empty;
				}
			}
		}
		
		seat[placeY][placeX] = student;
	}
	
	static int sumPoints() {
		int sum = 0;
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				int student = seat[y][x];
				int cnt = 0;
				
				//System.out.print(student + " ");
				if(student == 0) continue;
				
				for(int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					
					if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
					
					if(likes[student].contains(seat[ny][nx])) cnt++;
				}
				
				sum += (int)Math.pow(10, cnt) / 10;
			}
			//System.out.println();
		}
		return sum;
	}
}
