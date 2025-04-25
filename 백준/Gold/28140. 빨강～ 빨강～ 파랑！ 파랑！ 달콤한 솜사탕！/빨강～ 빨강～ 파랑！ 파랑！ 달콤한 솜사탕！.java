import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        input(in);
        solve(in);
    }
    
    static int length;
    static int cnt;
    static String string;
    static ArrayList<Integer> idxR;
    static ArrayList<Integer> idxB;
    static void input(BufferedReader in) throws IOException {
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        length = Integer.parseInt(tokens.nextToken());
        cnt = Integer.parseInt(tokens.nextToken());
        string = in.readLine();
        
        idxR = new ArrayList<>();
        idxB = new ArrayList<>();
        
        for(int i = 0; i < string.length(); i++) {
        	if(string.charAt(i) == 'R') idxR.add(i);
        	if(string.charAt(i) == 'B') idxB.add(i);
        }
    }
    
    static void solve(BufferedReader in) throws IOException {
    	StringBuilder str = new StringBuilder();
    	
        for(int i = 0; i < cnt; i++) {
        	StringTokenizer tokens = new StringTokenizer(in.readLine());
        	
        	int start = Integer.parseInt(tokens.nextToken());
        	int end = Integer.parseInt(tokens.nextToken());
        	
        	int a = findR(start);
        	int b = findR(a+1);
        	int c = findB(b+1);
        	int d = findB(c+1);
        	
        	if(start <= a && a < b && b < c && c < d && d <= end) str.append(a).append(' ').append(b).append(' ').append(c).append(' ').append(d);
        	else str.append(-1);
        	
        	str.append("\n");
        }
        
        System.out.println(str.toString());
    }
    
    static int findR(int start) {
    	int left = 0;
    	int right = idxR.size()-1;
    	int result = -1;
    	
    	while(left <= right) {
    		int mid = left + (right - left)/2;
    		int idx = idxR.get(mid);
    		
    		if(start <= idx) {
    			result = idx;
    			right = mid - 1;
    		} else {
    			left = mid + 1;
    		}
    	}
    	
    	return result;
    }
    
    static int findB(int start) {
    	int left = 0;
    	int right = idxB.size()-1;
    	int result = -1;
    	
    	while(left <= right) {
    		int mid = left + (right - left)/2;
    		int idx = idxB.get(mid);
    		
    		if(start <= idx) {
    			result = idx;
    			right = mid - 1;
    		} else {
    			left = mid + 1;
    		}
    	}
    	
    	return result;
    }
}