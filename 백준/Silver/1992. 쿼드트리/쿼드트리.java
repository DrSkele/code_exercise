
import java.io.*;
import java.util.*;

// 배열 절반으로 쪼개기
// 사이즈가 1이면 해당 값 반환
// 4분면 값이 똑같을 경우 해당 한자리 수만 반환
// 다를 경우 괄호 안에 묶어서 반환

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		init(in);
		
		solve();
	}
	
	static int size;
	static int[][] matrix;
	static StringBuilder str;
	static void init(BufferedReader in) throws IOException{
		size = Integer.parseInt(in.readLine());
		matrix = new int[size][size];
		for(int i = 0; i < size; i++) {
			String line = in.readLine();
			for(int j = 0; j < size; j++) {
				matrix[i][j] = line.charAt(j) - '0';
			}
		}
		str = new StringBuilder();
	}
	
	static void solve() {
		System.out.print(decode(0, size-1, 0, size-1));
	}
	
	static String decode(int startX, int endX, int startY, int endY) {
		if(startX == endX && startY == endY) {
			return String.valueOf(matrix[startY][startX]);
		}
		
		int midX = (startX + endX)/2;
		int midY = (startY + endY)/2;
		
		String topLeft = decode(startX, midX, startY, midY);
		String topRight = decode(midX+1, endX, startY, midY);
		String bottomLeft = decode(startX, midX, midY+1, endY);
		String bottomRight = decode(midX+1, endX, midY+1, endY);
		
		if(topLeft.length() == 1 && topLeft.equals(topRight) && topLeft.equals(bottomLeft)&& topLeft.equals(bottomRight)) return topLeft;
		else {
			StringBuilder str = new StringBuilder();
			str.append("(").append(topLeft).append(topRight).append(bottomLeft).append(bottomRight).append(")");
			return str.toString();
		}
	}
}


