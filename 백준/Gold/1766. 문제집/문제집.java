import java.io.*;
import java.util.*;

public class Main {

	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		init(in);
		
		solve(in);
	}
	
	static int nQuest;
	static int nOrder;
	static ArrayList<Integer>[] order;
	static int[] degree;
	public static void init(BufferedReader in) throws IOException{
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		nQuest = Integer.parseInt(tokens.nextToken());
		nOrder = Integer.parseInt(tokens.nextToken());
		
		order = new ArrayList[nQuest+1];
		for(int i = 0; i < order.length; i++) {
			order[i] = new ArrayList<>();
		}
		
		degree = new int[nQuest+1];
		for(int i = 0; i < nOrder; i++) {
			tokens = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(tokens.nextToken());
			int to = Integer.parseInt(tokens.nextToken());
			order[from].add(to);
			degree[to]++;
		}
	}
	
	public static void solve(BufferedReader in) throws IOException {
		
		Set<Integer> questions = new HashSet<>();
		for(int i = 1; i <= nQuest; i++) {
			questions.add(i);
		}
		
		PriorityQueue<Integer> q = new PriorityQueue<>();
		
		for(int i = 1; i < degree.length; i++) {
			if(degree[i] == 0) q.add(i);
		}
		
		ArrayList<Integer> answer = new ArrayList<>();
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			questions.remove(cur);
			answer.add(cur);
			
			for(int next : order[cur]) {
				degree[next]--;
				if(degree[next] == 0) q.add(next);
			}
			
			if(q.isEmpty() && !questions.isEmpty()) {
				int min = Integer.MAX_VALUE;
				int minNum = 0;
				for(int n : questions) {
					if(degree[n] < min) {
						min = degree[n];
						minNum = n;
					}
				}
				q.add(minNum);
			}
		}
		
		StringBuilder str = new StringBuilder();
		for(int n : answer) {
			str.append(n).append(" ");
		}
		System.out.println(str.toString());
	}
}
