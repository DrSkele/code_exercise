import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    
        init(in);
        solve(in);        	
    }
    
    static int N;
    static User[] users;
    static void init(BufferedReader in) throws IOException {
    	N = Integer.parseInt(in.readLine());
    	users = new User[N];
    	for(int i = 0; i < N; i++) {
    		StringTokenizer tokens = new StringTokenizer(in.readLine());
    		users[i] = new User(Integer.parseInt(tokens.nextToken()), tokens.nextToken());
    	}
    	Arrays.sort(users);
    }

    static void solve(BufferedReader in) throws IOException {
    	StringBuilder str = new StringBuilder();
    	for(User user : users) {
    		str.append(user.age).append(" ").append(user.name).append("\n");
    	}
    	System.out.println(str.toString());
    }
    
    static class User implements Comparable<User>{
    	int age;
    	String name;
    	
    	public User(int age, String name) {
    		this.age = age;
    		this.name = name;
    	}
    	
    	@Override
    	public int compareTo(User other) {
    		if(this.age < other.age) return -1;
    		else if(this.age > other.age) return 1;
    		else return 0;
    	}
    }
}


