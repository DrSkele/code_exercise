import java.io.*;
import java.util.*;

class Main{

	static class Pair{
		int value;
		int idx;
		
		public Pair(int value, int idx) {
			this.value = value;
			this.idx = idx;
		}
	}

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(in.readLine());
        StringTokenizer tokens = new StringTokenizer(in.readLine());

        int[] result = new int[N];
        
        Stack<Pair> pQ = new Stack<Pair>();
        
        for(int i = 0; i < N; i++) {
        	int value = Integer.parseInt(tokens.nextToken());
        	int idx = i;
        	
        	while(!pQ.isEmpty() && value > pQ.peek().value) {
        		Pair cur = pQ.pop();
        		result[cur.idx] = value;
        	}
        	
        	pQ.push(new Pair(value, idx));
        }
        
        while(!pQ.isEmpty()) {
        	Pair cur = pQ.pop();
        	result[cur.idx] = -1;
        }
        
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < N; i++) {
        	str.append(result[i]).append(" ");
        }
        System.out.print(str.toString());
    }
    

    
}
