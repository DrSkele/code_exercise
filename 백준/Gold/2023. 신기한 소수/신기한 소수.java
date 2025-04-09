import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static int length;
	static StringBuilder str;
	static void input(BufferedReader in) throws IOException {
		length = Integer.parseInt(in.readLine());
		str = new StringBuilder();
	}
	
	static void solve(){
		checkNum(0, 0);
		System.out.println(str.toString());
	}
	
	static void checkNum(int num, int len) {
		if(len == length) {
			if(isPrime(num)) str.append(num).append("\n");
			return;
		}
		
		for(int i = 0; i < 10; i++) {
			int next = num*10 + i;
			if(isPrime(next)) checkNum(next, len+1);
		}
	}
	
	static boolean isPrime(int num) {
		if(num < 2) return false;
		
		for(int i = 2; i <= Math.sqrt(num); i++) {
			if(num % i == 0) return false;
		}
		
		return true;
	}
}