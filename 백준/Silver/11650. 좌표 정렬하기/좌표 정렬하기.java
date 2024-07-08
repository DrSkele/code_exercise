import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    
        init(in);
        solve();        	
    }
    
    static int N;
    static List<Point> points;
    static void init(BufferedReader in) throws IOException {
    	N = Integer.parseInt(in.readLine());
    	points = new ArrayList<>();
    	
    	for(int i = 0; i < N; i++) {
    		StringTokenizer tokens = new StringTokenizer(in.readLine());
    		points.add(new Point(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())));
    	}
    	
    	Collections.sort(points);
    }

    static void solve(){
    	for(Point point : points) {
    		System.out.println(point.x + " " + point.y);
    	}
    }
    
    static class Point implements Comparable<Point>{
    	int x;
    	int y;
    	public Point(int x, int y) {
    		this.x = x;
    		this.y = y;
    	}
    	
    	@Override
    	public int compareTo(Point b) {
    		if(this.x < b.x) return -1;
    		else if(this.x > b.x) return 1;
    		else {
    			if(this.y < b.y) return -1;
    			else if(this.y > b.y) return 1;
    			else return 0;
    		}
    	}
    }
}


