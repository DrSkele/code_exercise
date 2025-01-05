import java.io.*;
import java.util.*;

class Main{
	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();		
	}
	
	static final int WHITE = 0;
	static final int RED = 1;
	static final int BLUE = 2;
	
	static int size;
	static int cnt;
	static int[][] board;
	static Stack<Piece>[][] stacks;
	static Piece[] pieces;
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		size = Integer.parseInt(tokens.nextToken());
		cnt = Integer.parseInt(tokens.nextToken());
		
		board = new int[size][size];
		stacks = new Stack[size][size];
		
		for(int y = 0; y < size; y++) {
			tokens = new StringTokenizer(in.readLine());
			for(int x = 0; x < size; x++) {
				board[y][x] = Integer.parseInt(tokens.nextToken());
				stacks[y][x] = new Stack<>();
			}
		}
		
		pieces = new Piece[cnt];
		
		for(int i = 0; i < cnt; i++) {
			tokens = new StringTokenizer(in.readLine());
			int y = Integer.parseInt(tokens.nextToken())-1;
			int x = Integer.parseInt(tokens.nextToken())-1;
			int dir = Integer.parseInt(tokens.nextToken());
			
			Piece newPiece = new Piece(y, x, dir);
			pieces[i] = newPiece;
			stacks[y][x].add(newPiece);
		}
	}
	
	static int[] dx = { +1, 0, -1, 0 }; // right, down, left, up
	static int[] dy = { 0, +1, 0, -1,};
	static void solve() {
		int turn = 0;
		
		while(turn <= 1000) {
			turn++;
			movePieces();
			
			if(isOver()) break;
		}
		
		System.out.println(turn > 1000 ? -1 : turn);
	}
	
	static void movePieces() {
		
		for(int i = 0; i < cnt; i++) {
			Piece cur = pieces[i];
			int y = cur.y;
			int x = cur.x;
			int dir = cur.dir;
			
			int ny = y + dy[dir];
			int nx = x + dx[dir];
			
			int color = onBoard(ny, nx) ? board[ny][nx] : BLUE;
			switch(color) {
			case WHITE : moveToWhite(cur, y, x, ny, nx); break;
			case RED : moveToRed(cur, y, x, ny, nx); break;
			case BLUE : 
				cur.changeDir();
				dir = cur.dir;
				ny = y + dy[dir];
				nx = x + dx[dir];
				color = onBoard(ny, nx) ? board[ny][nx] : BLUE;
				
				switch(color) {
				case WHITE : moveToWhite(cur, y, x, ny, nx); break;
				case RED : moveToRed(cur, y, x, ny, nx); break;
				case BLUE : break;
				}
				
				break;
			}
			
			if(isOver()) return;
		}
	}
	
	static void moveToWhite(Piece top, int fromY, int fromX, int toY, int toX) {
		Stack<Piece> temp = new Stack<>();
		while(temp.isEmpty() || temp.peek() != top) {
			temp.push(stacks[fromY][fromX].pop());
		}
		while(!temp.isEmpty()) {
			Piece toMove = temp.pop();
			toMove.move(toY, toX);
			stacks[toY][toX].push(toMove);
		}
	}
	
	static void moveToRed(Piece top, int fromY, int fromX, int toY, int toX) {
		while(stacks[toY][toX].isEmpty() || stacks[toY][toX].peek() != top) {
			Piece toMove = stacks[fromY][fromX].pop();
			toMove.move(toY, toX);
			stacks[toY][toX].push(toMove);
		}
	}
	
	static boolean isOver() {
		for(int y = 0; y < size; y++) {
			for(int x = 0; x < size; x++) {
				int stack = stacks[y][x].size();
				
				if(stack >= 4) return true;
			}
		}
		return false;
	}
	
	static boolean onBoard(int y, int x) {
		return 0 <= y && y < size && 0 <= x && x < size;
	}
	
	static void printStack() {
		for(int y = 0; y < size; y++) {
			for(int x = 0; x < size; x++) {
				int stack = stacks[y][x].size();
				
				System.out.print(stack + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	static class Piece{
		int y;
		int x;
		int dir;
		public Piece(int y, int x, int dir) {
			this.y = y;
			this.x = x;
			switch(dir) {
			case 1: this.dir = 0; break;
			case 2: this.dir = 2; break;
			case 3: this.dir = 3; break;
			case 4: this.dir = 1; break;
			}
		}
		
		public void move(int y, int x) {
			this.y = y;
			this.x = x;
		}
		
		public void changeDir() {
			dir = (dir+2) % 4;
		}
	}
}