import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    	init(in);
    	solve(in);    		
    }
    
    static int num;
    static int length;
    static int weight;
    static int[] trucks;
    static void init(BufferedReader in) throws IOException {
    	StringTokenizer tokens = new StringTokenizer(in.readLine());
    	num = Integer.parseInt(tokens.nextToken());
    	length = Integer.parseInt(tokens.nextToken());
    	weight = Integer.parseInt(tokens.nextToken());
    	trucks = new int[num];
    	
    	tokens = new StringTokenizer(in.readLine());
    	for(int i = 0; i < num; i++) {
    		trucks[i] = Integer.parseInt(tokens.nextToken());
    	}
    }

    static void solve(BufferedReader in) throws IOException {
    	int time = 0;
    	int totalWeight = 0;
    	
    	ArrayDeque<Integer> waiting = new ArrayDeque<>();
    	for(int i = 0; i < num; i++) {
    		waiting.add(trucks[i]);
    	}
    	ArrayDeque<EstimatedArrival> onBridge = new ArrayDeque();
    	
    	while(!waiting.isEmpty() || !onBridge.isEmpty()) {
    		time++;
    		// truck getting off the bridge
    		if(!onBridge.isEmpty() && onBridge.peek().time == time) {
    			EstimatedArrival truck = onBridge.pop();
    			totalWeight -= truck.weight;
    		}
    		
    		// truck entering the bridge
    		if(!waiting.isEmpty() && totalWeight + waiting.peek() <= weight) {
    			int truck = waiting.pop();
    			totalWeight += truck;
    			onBridge.add(new EstimatedArrival(time+length, truck));
    		}
    	}
    	
    	System.out.println(time);
    }
    
    static class EstimatedArrival{
    	int time;
    	int weight;
    	public EstimatedArrival(int t, int w) {
    		this.time = t;
    		this.weight = w;
    	}
    }
    
}


