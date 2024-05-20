import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    	
    	int T = Integer.parseInt(in.readLine());
    	for(int t = 0; t < T; t++) {
    		init(in);
    		
    		solve();      		
    	}
    }

    static Map<String, ArrayList<String>> map;
    static String[] categories;
    static int sum;
    static void init(BufferedReader in) throws IOException {
    	int wears = Integer.parseInt(in.readLine());
    	map = new HashMap<>();
    	if(wears == 0) {
    		System.out.println(0);
    		return;
    	}
    	for(int i = 0; i < wears; i++) {
    		StringTokenizer tokens = new StringTokenizer(in.readLine());
    		String wear = tokens.nextToken();
    		String category = tokens.nextToken();
    		if(!map.containsKey(category)) map.put(category, new ArrayList<>());
    		map.get(category).add(wear);
    	}
    	categories = map.keySet().toArray(new String[0]);
    	sum = 0;
    }

    static void solve(){
    	if(map.isEmpty()) return;
    	
    	int sum = 1;
    	for(String key : map.keySet().toArray(new String[0])) {
    		sum *= (map.get(key).size()+1);
    	}
    	
    	System.out.println(sum-1);
    }
    
}
