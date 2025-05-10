import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        input(in);
        solve();
    }
    
    static int height;
    static int width;
    static int nString;
    static char[][] matrix;
    static String[] strings;
    static void input(BufferedReader in) throws IOException {
    	StringTokenizer tokens = new StringTokenizer(in.readLine());
    	height = Integer.parseInt(tokens.nextToken());
    	width = Integer.parseInt(tokens.nextToken());
    	nString = Integer.parseInt(tokens.nextToken());
    	matrix = new char[height][width];
    	strings = new String[nString];
    	
    	for(int i = 0; i < height; i++) {
    		char[] line = in.readLine().toCharArray();
    		for(int j = 0; j < width; j++) {
    			matrix[i][j] = line[j];
    		}
    	}
    	
    	for(int i = 0; i < nString; i++) {
    		strings[i] = in.readLine();
    	}
    }
    
    static String goal;
    static int[][][] dp;
    
    static void solve() {
    	StringBuilder str = new StringBuilder();
    	
    	for(String seq : strings) {
    		goal = seq;
    		int len = seq.length();
    		
    		// DP 배열 초기화 (-1로 초기화하여 미계산 상태 표시)
    		dp = new int[height][width][len];
    		for(int i = 0; i < height; i++) {
    			for(int j = 0; j < width; j++) {
    				Arrays.fill(dp[i][j], -1);
    			}
    		}
    		
    		int cnt = 0;
    		
    		for(int y = 0; y < height; y++) {
    			for(int x = 0; x < width; x++) {
    				if(matrix[y][x] == seq.charAt(0)) {
    					cnt += findFrom(y, x, 1);
    				}
    			}
    		}
    		
    		str.append(cnt).append('\n');
    	}
    	
    	System.out.print(str.toString());
    }
    
    static int[] dy = { 0, +1, +1, +1, 0, -1, -1, -1 };
    static int[] dx = { +1, +1, 0, -1, -1, -1, 0, +1 };
    
    static int findFrom(int y, int x, int idx) {
    	// 기저 조건: 문자열을 모두 찾은 경우
    	if(idx == goal.length()) {
    		return 1;
    	}
    	
    	// 이미 계산된 값이 있으면 반환
    	if(dp[y][x][idx - 1] != -1) {
    		return dp[y][x][idx - 1];
    	}
    	
    	int sum = 0;
    	
    	for(int i = 0; i < 8; i++) {
    		int ny = next(y, dy[i], height);
    		int nx = next(x, dx[i], width);
    		
    		if(matrix[ny][nx] == goal.charAt(idx)) {
    			sum += findFrom(ny, nx, idx + 1);
    		}
    	}
    	
    	// 계산된 결과를 저장
    	return dp[y][x][idx - 1] = sum;
    }
    
    static int next(int n, int dn, int length) {
    	return (n + dn + length) % length;
    }
}