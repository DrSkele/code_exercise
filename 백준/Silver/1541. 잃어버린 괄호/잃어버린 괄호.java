import java.io.*;
import java.util.*;

public class Main {

	// 행렬 제곱 = A*A*A = (A*A)*A
	
    public static void main(String[] args) throws IOException {
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    	
    	init(in);
    	
    	solve();  
    }

    static void init(BufferedReader in) throws IOException {
    	String[] splitMinus = (in.readLine()).split("\\-");
    	ArrayList<Integer> sums = new ArrayList<>();
    	
    	for(int i = 0; i < splitMinus.length; i++) {
    		String[] splitPlus = splitMinus[i].split("\\+");
    		int sum = 0;
    		for(int j = 0; j < splitPlus.length; j++) {
    			sum += Integer.parseInt(splitPlus[j]);
    		}
    		sums.add(sum);
    	}
    	
    	int first = sums.get(0);
    	
    	for(int i = 1; i < sums.size(); i++) {
    		first -= sums.get(i);
    	}
    	
    	System.out.println(first);
    }

    static void solve(){
    	
    }
    
}
