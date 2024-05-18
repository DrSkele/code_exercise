import java.io.*;
import java.util.*;

public class Main {

	// 행렬 제곱 = A*A*A = (A*A)*A
	
    public static void main(String[] args) throws IOException {
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    	
    	init(in);
    	
    	solve();  
    }

    static int N;
    static int len;
    static String ref;
    static String line;
    static void init(BufferedReader in) throws IOException {
    	N = Integer.parseInt(in.readLine());
    	len = Integer.parseInt(in.readLine());
    	
    	StringBuilder str = new StringBuilder();
    	str.append("I");
    	for(int i = 0; i < N; i++) {
    		str.append("OI");
    	}
    	ref = str.toString();
    	line = in.readLine();
    }

    static void solve(){
    	StringBuilder str = new StringBuilder();
    	
    	for(int i = 0; i < ref.length(); i++) {
    		str.append(line.charAt(i));
    	}
    	int cnt = str.toString().equals(ref) ? 1 : 0;
    	for(int i = 0; i < line.length()-ref.length(); i++) {
    		str.deleteCharAt(0);
    		str.append(line.charAt(ref.length()+i));
    		
    		if(str.toString().equals(ref)) cnt++;
    	}
    	System.out.println(cnt);
    }
    
}
