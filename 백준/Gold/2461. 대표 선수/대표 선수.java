import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    input(in);
    solve();
  }
  
  static int nClass;
  static int nStudent;
  static PriorityQueue<Student>[] classes;
  public static void input(BufferedReader in) throws IOException {
    StringTokenizer tokens = new StringTokenizer(in.readLine());
    
    nClass = Integer.parseInt(tokens.nextToken());
    nStudent = Integer.parseInt(tokens.nextToken());
    
    classes = new PriorityQueue[nClass];
    
    for(int i = 0;i < nClass; i++) {
      classes[i] = new PriorityQueue<>();
    }
    
    for(int i = 0; i < nClass; i++) {
      tokens = new StringTokenizer(in.readLine());
      for(int j = 0; j < nStudent; j++) {
        long ability = Long.parseLong(tokens.nextToken());
        classes[i].add(new Student(i, ability));
      }
    }
  }
  
  public static void solve() {
    
    long minGap = Long.MAX_VALUE;
    
    PriorityQueue<Student> pick = new PriorityQueue<>();
    
    long max = 0;
    
    for(int i = 0; i < nClass; i++) {
      Student student = classes[i].poll();
      pick.add(student);
      max = Math.max(max, student.ability);
    }
    
    while(true) {
      Student cur = pick.poll();
      
      minGap = Math.min(minGap, max - cur.ability);
      
      if(classes[cur.classIdx].isEmpty()) break;
      
      Student student = classes[cur.classIdx].poll();
      pick.add(student);
      max = Math.max(max, student.ability);
    }
    
    System.out.println(minGap);
  }
  
  static class Student implements Comparable<Student>{
    int classIdx;
    long ability;
    public Student(int c, long a) {
      classIdx = c;
      ability = a;
    }
    public int compareTo(Student s) {
      if(ability < s.ability) return -1;
      else if(ability > s.ability) return 1;
      else return 0;
    }
  }
  
}