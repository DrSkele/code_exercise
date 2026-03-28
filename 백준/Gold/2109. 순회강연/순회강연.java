import java.io.*;
import java.util.*;

public class Main {
    static class Lecture {
        int pay;
        int day;

        Lecture(int pay, int day) {
            this.pay = pay;
            this.day = day;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Lecture[] lectures = new Lecture[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int pay = Integer.parseInt(st.nextToken());
            int day = Integer.parseInt(st.nextToken());
            lectures[i] = new Lecture(pay, day);
        }

        Arrays.sort(lectures, (a, b) -> {
            if (a.day == b.day) {
                return Integer.compare(a.pay, b.pay);
            }
            return Integer.compare(a.day, b.day);
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (Lecture lecture : lectures) {
            pq.offer(lecture.pay);

            if (pq.size() > lecture.day) {
                pq.poll();
            }
        }

        long answer = 0;
        while (!pq.isEmpty()) {
            answer += pq.poll();
        }

        System.out.println(answer);
    }
}