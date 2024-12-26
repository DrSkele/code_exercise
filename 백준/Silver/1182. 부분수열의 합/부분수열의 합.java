import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    	init(in);
    	solve(in);    		
    }
    
    static int size;
    static int goal;
    static int[] arr;
    static Set<Integer> visited;
    static int cnt;
    static void init(BufferedReader in) throws IOException {
    	StringTokenizer tokens = new StringTokenizer(in.readLine());
    	size = Integer.parseInt(tokens.nextToken());
    	goal = Integer.parseInt(tokens.nextToken());
    	arr = new int[size];
    	tokens = new StringTokenizer(in.readLine());
    	for(int i = 0; i < size; i++) {
    		arr[i] = Integer.parseInt(tokens.nextToken());
    	}
    	visited = new HashSet<>();
    	cnt = 0;
	}

    static void solve(BufferedReader in) throws IOException {
    	cascade(0, 0, 0);
    	System.out.println(cnt);
    }
    
    static void cascade(int idx, int sum, int mask) {
//    	if(visited.contains(mask)) return;
//    	else visited.add(mask);
    	if(idx >= size) return;
    	if(sum + arr[idx] == goal) cnt++;
    	
    	cascade(idx+1, sum + arr[idx], mask + 1<<idx);
    	cascade(idx+1, sum, mask);
    }
}


