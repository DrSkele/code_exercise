import java.io.*;
import java.util.*;

class Main{
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		
		PriorityQueue<Integer> q = new PriorityQueue<>();
		
		for(int i = 0; i < n; i++) {
			q.add(Integer.parseInt(in.readLine()));
		}
		int sum = 0;
		while(q.size() > 1) {
			int e1 = q.poll();
			int e2 = q.poll();
			
			int cur = e1 + e2;
			sum += cur;
			
			q.add(cur);
		}
		System.out.println(sum);
	}
}
