import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(tokens.nextToken());
		
		int num = 666;
		int cnt = 1;
		
		while(cnt < N) {
			num++;
			if(String.valueOf(num).contains("666")) cnt++;
		}
		
		System.out.print(num);
	}
}
