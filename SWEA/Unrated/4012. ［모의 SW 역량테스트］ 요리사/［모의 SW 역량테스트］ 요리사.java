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
	static int[][] ingredients;
	
	static boolean[] recipe;
	
	static Map<String, Integer> synergy;
	
	static int min;
	static void init(BufferedReader in) throws IOException {
		size = Integer.parseInt(in.readLine());
		ingredients = new int[size][size];
		
		recipe = new boolean[size];
		
		synergy = new HashMap<String, Integer>();
		min = Integer.MAX_VALUE;
		
		for(int i = 0; i < size; i++) {
			StringTokenizer tokens = new StringTokenizer(in.readLine());
			for(int j = 0; j < size; j++) {
				ingredients[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
	}
	
	static void solve(int t) throws IOException {
		combination(0, size/2);
		System.out.println(String.format("#%d %d", t, min));
	}
	
	static void combination(int idx, int cnt) {
		if(cnt == 0) {
			int pick = 0;
			int[] picked = new int[size/2];
			int left = 0;
			int[] leftOver = new int[size/2];
			for(int i = 0; i < size; i++) {
				if(recipe[i]) picked[pick++] = i;
				else leftOver[left++] = i;
			}
			
			int cooked = getSynergy(picked);
			int other = getSynergy(leftOver);
			
			int diff = (int)Math.abs(cooked - other);
			//System.out.println(cooked + " " + other + " " + diff);
			if(diff < min) {
				min = diff;
			}
			return;
		}
		if(idx == size) {
			return;
		}
		
		recipe[idx] = true;
		combination(idx+1, cnt-1);
		recipe[idx] = false;
		
		combination(idx+1, cnt);
	}
	
	static int getSynergy(int[] arr) {
		int sum = 0;
		for(int i = 0; i < arr.length; i++) {
			int first = arr[i];
			for(int j = i+1; j < arr.length; j++) {
				int second = arr[j];
				sum += ingredients[first][second] + ingredients[second][first];
			}
		}
		return sum;
	}
}
