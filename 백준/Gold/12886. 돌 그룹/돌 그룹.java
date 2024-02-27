import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        Set<Node> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        Node start = new Node(A, B, C);
        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Node cur = queue.remove();

            if (cur.A == cur.B && cur.B == cur.C) {
                System.out.println(1);
                return;
            }

            if (cur.A != cur.B) {
                Node next = cur;
                if (cur.A < cur.B) {
                    next.B -= next.A;
                    next.A += next.A;
                } else {
                    next.A -= next.B;
                    next.B += next.B;
                }
                if (!visited.contains(next)) {
                    queue.offer(next);
                    visited.add(next);
                }
            }

            if (cur.A != cur.C) {
                Node next = cur;
                if (cur.A < cur.C) {
                    next.C -= next.A;
                    next.A += next.A;
                } else {
                    next.A -= next.C;
                    next.C += next.C;
                }
                if (!visited.contains(next)) {
                    queue.offer(next);
                    visited.add(next);
                }
            }

            if (cur.B != cur.C) {
                Node next = cur;
                if (cur.B < cur.C) {
                    next.C -= next.B;
                    next.B += next.B;
                } else {
                    next.B -= next.C;
                    next.C += next.C;
                }
                if (!visited.contains(next)) {
                    queue.offer(next);
                    visited.add(next);
                }
            }
        }

        System.out.println(0);
    }
}

class Node {
    int A;
    int B;
    int C;

    public Node(int a, int b, int c) {
        A = a;
        B = b;
        C = c;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Node) {
            Node node = (Node) obj;
            return A == node.A && B == node.B && C == node.C;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(A, B, C);
    }
}