import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(in.readLine());
        StringTokenizer tokens = new StringTokenizer(in.readLine());

        int[] result = new int[N];
        
        PriorityQueue<Pair> pQ = new PriorityQueue<Pair>(N, new PairComparator());
        
        for(int i = 0; i < N; i++) {
        	int value = Integer.parseInt(tokens.nextToken());
        	int idx = i;
        	
        	while(!pQ.isEmpty() && value > pQ.peek().value) {
        		Pair cur = pQ.poll();
        		result[cur.idx] = value;
        	}
        	
        	pQ.add(new Pair(value, idx));
        }
        
        while(!pQ.isEmpty()) {
        	Pair cur = pQ.poll();
        	result[cur.idx] = -1;
        }
        
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < N; i++) {
        	str.append(result[i]).append(" ");
        }
        System.out.print(str.toString());
    }
    

	static class Pair{
		int value;
		int idx;
		
		public Pair(int value, int idx) {
			this.value = value;
			this.idx = idx;
		}
	}

	static class PairComparator implements Comparator<Pair>{
	
		@Override
		public int compare(Pair o1, Pair o2) {
			int result = 0;
			if(o1.value < o2.value) result = -1;
			else if(o1.value > o2.value) result = 1;
			return result;
		}
	}

    
}
