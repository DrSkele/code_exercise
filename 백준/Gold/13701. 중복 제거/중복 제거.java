import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		solve(in);
	}
	
	public static void solve(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		
		StringBuilder str = new StringBuilder();
		
		// 최대 2^25제곱 길이의 입력이 들어오고, 각 int가 32비트로 값당 2^5까지 저장가능하다.  
		int[] mark = new int[1 << 20]; // 따라서 배열의 길이는 2^20이면 충분하다.
		
		while(tokens.hasMoreTokens()) {
			int num = Integer.parseInt(tokens.nextToken());
			
			int quotient = num/32; // 숫자값의 몫이 배열의 idx가 된다.
			int remainder = num%32; // 숫자의 나머지가 해당 idx 값의 비트 값이 된다.
			// 결과적으로 일차원 배열을 이차원 배열로 사용한다.
			
			if((mark[quotient] & (1 << remainder)) == 0) {
				mark[quotient] |= (1 << remainder);
				str.append(num).append(" ");
			}
		}
		
		System.out.println(str.toString());
	}
}