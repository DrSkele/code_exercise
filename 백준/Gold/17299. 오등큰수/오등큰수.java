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

        int[] arr = new int[N];
        int[] cnt = new int[1_000_001];
        int[] result = new int[N];
        
        for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(tokens.nextToken());
			cnt[arr[i]]++;
		}
        
        Stack<Pair> pQ = new Stack<Pair>();
        
        for(int i = 0; i < N; i++) {
        	int cur = arr[i];
        	int idx = i;
        	
        	while(!pQ.isEmpty() && cnt[cur] > cnt[pQ.peek().value]) {
        		Pair pair = pQ.pop();
        		result[pair.idx] = cur;
        	}
        	
        	pQ.push(new Pair(cur, idx));
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
