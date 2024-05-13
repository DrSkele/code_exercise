import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    	int T = Integer.parseInt(in.readLine());
    	for(int t = 1; t <= T; t++) {
    		init(in);
    		
    		solve();  
    	}    	
    }

    static int num;
    static int con;
    static int infected;
    static ArrayList<ArrayList<Connection>> cons;
    static int[] infectTime;
    static void init(BufferedReader in) throws IOException {
    	StringTokenizer tokens = new StringTokenizer(in.readLine());
    	num = Integer.parseInt(tokens.nextToken());
    	con = Integer.parseInt(tokens.nextToken());
    	infected = Integer.parseInt(tokens.nextToken())-1;
    	//System.out.println(num +"," + con + "," + infected);
    	
    	cons = new ArrayList<>();
    	infectTime = new int[num];
    	for(int i = 0; i < num; i++) {
    		cons.add(new ArrayList<>());
    		infectTime[i] = Integer.MAX_VALUE;
    	}
    	
    	for(int i = 0; i < con; i++) {
    		tokens = new StringTokenizer(in.readLine());
    		//System.out.println(i);
    		int to = Integer.parseInt(tokens.nextToken())-1;
        	int from = Integer.parseInt(tokens.nextToken())-1;
        	int time = Integer.parseInt(tokens.nextToken());
    		cons.get(from).add(new Connection(to, time));
    	}
    }

    static void solve(){
    	dijkstra();
    }
    
    static void dijkstra() {
    	
    	PriorityQueue<Connection> q = new PriorityQueue<>(new Comparator<Connection>() {
    		@Override
    		public int compare(Connection c1, Connection c2) {
    			return c1.time - c2.time;
    		}
    	});
    	
    	q.add(new Connection(infected, 0));
    	infectTime[infected] = 0;
    	
    	while(!q.isEmpty()) {
    		Connection cur = q.poll();
    		//System.out.println(cur.to);
    		
    		if(cur.time > infectTime[cur.to]) continue;
    		
    		for(Connection next : cons.get(cur.to)) {
    			int val = infectTime[cur.to] + next.time;
    			
    			if(val >= infectTime[next.to]) continue;
    			
    			q.add(new Connection(next.to, val));
    			infectTime[next.to] = val;
    		}
    	}
    	
    	int cnt = 0;
    	int max = 0;
    	for(int i : infectTime) {
    		if(i < Integer.MAX_VALUE) {
    			cnt++;
    			max = Math.max(max, i);
    		}
    	}
    	System.out.println(cnt + " " + max);
    }
    
    static class Connection{
    	int to;
    	int time;
    	public Connection(int to, int time) {
    		this.to = to;
    		this.time = time;
    	}
    }
}
