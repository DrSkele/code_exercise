import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		
		for(int t = 1; t <= T; t++) {
			init(in);
			
			solve(t);
		}
	}
	
	static int size;
	static ArrayList<Row>[] rows;
	static ArrayList<Block> blocks;
	static void init(BufferedReader in) throws IOException {
		size = Integer.parseInt(in.readLine());
		rows = new ArrayList[size];
		for(int i = 0; i < size; i++) {
			rows[i] = new ArrayList<>();
			StringTokenizer tokens = new StringTokenizer(in.readLine());
			int prev = 0;
			int start = 0;
			for(int j = 0; j < size; j++) {
				int val = Integer.parseInt(tokens.nextToken());
				if(prev == 0 && val > 0) {
					start = j;
				} else if(prev > 0) {
					if(val == 0) {
						rows[i].add(new Row(start, j-1));
					} else if(j == size-1) {
						rows[i].add(new Row(start, j));
					}
				}
				prev = val;
			}
		}
		blocks = new ArrayList<>();
	}
	
	static void solve(int t) throws IOException {
		
		
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < rows[i].size(); j++) {
				Row cur = rows[i].get(j);
				if(cur.isUsed) continue;
				int length = cur.length();
				int cnt = 1;
				for(int k = i+1; k < size; k++) {
					ArrayList<Row> next = rows[k];
					
					if(!hasRow(next, cur)) break;
					cnt++;
				}
				blocks.add(new Block(cur.length(), cnt));
			}
		}
		Collections.sort(blocks);
		
		StringBuilder str = new StringBuilder();
		for(Block b : blocks) {
			str.append(b.row).append(" ").append(b.column).append(" ");
		}
		
		System.out.println(String.format("#%d %d %s", t, blocks.size(), str.toString()));
		
	}
	
	static boolean hasRow(ArrayList<Row> arr, Row target) {
		for(int i = 0; i < arr.size(); i++) {
			Row cur = arr.get(i);
			if(cur.left == target.left && cur.right == target.right) {
				cur.isUsed = true;
				return true;
			}
		}
		return false;
	}
	
	static class Row{
		int left;
		int right;
		boolean isUsed;
		public Row(int left, int right) {
			this.left = left;
			this.right = right;
			isUsed = false;
		}
		public int length() {
			return right-left+1;
		}
	}
	
	static class Block implements Comparable<Block>{
		int column;
		int row;
		public Block(int column, int row) {
			this.column = column;
			this.row = row;
		}
		
		public int size() {
			return column * row;
		}
		
		@Override
		public int compareTo(Block b) {
			if(size() < b.size()) return -1;
			else if(size() > b.size()) return 1;
			else {
				if(row < b.row) return -1;
				else if(row > b.row) return 1;
				else return 0;
			}
		}
	}
}
