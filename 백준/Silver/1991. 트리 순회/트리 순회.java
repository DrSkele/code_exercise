
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		init(in);
		
		solve();
	}
	
	static int N;
	static Map<Character, Child> tree;
	static StringBuilder str;
	static void init(BufferedReader in) throws IOException{
		N = Integer.parseInt(in.readLine());
		tree = new HashMap<>();
		str = new StringBuilder();
		
		for(int i = 0; i < N; i++) {
			StringTokenizer tokens = new StringTokenizer(in.readLine());
			
			char root = tokens.nextToken().charAt(0);
			char left = tokens.nextToken().charAt(0);
			char right = tokens.nextToken().charAt(0);
			
			tree.put(root, new Child(left, right));
		}
	}
	
	static void solve() {
		preorder('A');
		str.append("\n");
		inorder('A');
		str.append("\n");
		postorder('A');
		
		System.out.println(str.toString());
	}
	
	static void preorder(char root) {
		
		Child child = tree.get(root);
		str.append(root);
		if(child.left != '.') preorder(child.left);
		if(child.right != '.') preorder(child.right);
	}
	
	static void inorder(char root) {
		
		Child child = tree.get(root);
		if(child.left != '.') inorder(child.left);
		str.append(root);
		if(child.right != '.') inorder(child.right);
	}
	
	static void postorder(char root) {
		
		Child child = tree.get(root);
		if(child.left != '.') postorder(child.left);
		if(child.right != '.') postorder(child.right);
		str.append(root);
	}
	
	static class Child{
		char left;
		char right;
		public Child(char left, char right) {
			this.left = left;
			this.right = right;
		}
	}
}


