import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    	init(in);
    	solve(in);    		
    }
    
    static int cntA;
    static int cntB;
    static int[] arrA;
    static int[] arrB;
    static void init(BufferedReader in) throws IOException {
    	StringTokenizer tokens = new StringTokenizer(in.readLine());
    	cntA = Integer.parseInt(tokens.nextToken());
    	cntB = Integer.parseInt(tokens.nextToken());
    	
    	arrA = new int[cntA];
    	arrB = new int[cntB];
    	
    	tokens = new StringTokenizer(in.readLine());
    	for(int i = 0; i < cntA; i++) {
    		arrA[i] = Integer.parseInt(tokens.nextToken());
    	}
    	tokens = new StringTokenizer(in.readLine());
    	for(int i = 0; i < cntB; i++) {
    		arrB[i] = Integer.parseInt(tokens.nextToken());
    	}
	}

    static void solve(BufferedReader in) throws IOException {
    	Arrays.sort(arrA);
    	Arrays.sort(arrB);
    	
    	ArrayDeque<Integer> q = new ArrayDeque<>();
    	int pivotA = 0;
    	int pivotB = 0;
    	
    	for(int i = 0; i < cntA; i++) {
    		int curA = arrA[i];
    		
    		if(!contains(curA)) q.add(curA);
    	}
    	
    	System.out.println(q.size());
    	if(!q.isEmpty()) {
    		StringBuilder str = new StringBuilder();
    		while(!q.isEmpty()) {
    			str.append(q.pop()).append(" ");
    		}
    		System.out.println(str.toString());
    	}
    }
    
    static boolean contains(int num) {
    	int left = 0;
    	int right = cntB;
    	
    	while(left < right) {
    		int mid = (left + right)/2;
    		if(arrB[mid] < num) {
    			left = mid + 1;
    		} else if(arrB[mid] > num) {
    			right = mid;
    		} else {
    			return true;
    		}
    	}
    	
    	return false;
    }
}


