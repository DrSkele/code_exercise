import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    	
    	init(in);
    	
    	solve();  
    }

    static Map<String, String> map;
    static void init(BufferedReader in) throws IOException {
    	StringTokenizer tokens = new StringTokenizer(in.readLine());
    	int sites = Integer.parseInt(tokens.nextToken());
    	int ans = Integer.parseInt(tokens.nextToken());
    	map = new HashMap<>();
    	for(int i = 0; i < sites; i++) {
    		tokens = new StringTokenizer(in.readLine());
    		map.put(tokens.nextToken(), tokens.nextToken());
    	}
    	StringBuilder str = new StringBuilder();
    	for(int i = 0; i < ans; i++) {
    		str.append(map.get(in.readLine())).append("\n");
    	}
    	System.out.println(str.toString());
    }

    static void solve(){
    	
    }
    
}
