import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class RoundRobinScheduling {
    static class Process {
        int id, burst, remaining, priority, waiting, turnaround;
        Process(int i, int b, int p) {
            id = i;
            burst = b;
            remaining = b;
            priority = p;
        }
    }
    static class Node {
        Process process;
        Node next;
        Node(Process p) {
            process = p;
        }
    }
    static Node head, tail;
    static void add(Process p) {
        Node n = new Node(p);
        if (head == null) {
            head = tail = n;
            n.next = n;
        } else {
            n.next = head;
            tail.next = n;
            tail = n;
        }
    }
    static void remove(Node previous, Node current) {
        if (current == head && current == tail) {
            head = tail = null;
        } else {
            if (current == head) head = head.next;
            if (current == tail) tail = previous;
            previous.next = current.next;
            tail.next = head;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List <Process> all = new ArrayList <  > ();
        System.out.print("Number of processes: ");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.print("Process ID, burst time, priority: ");
            Process p = new Process(sc.nextInt(), sc.nextInt(), sc.nextInt());
            all.add(p);
            add(p);
        }
        System.out.print("Time quantum: ");
        int quantum = sc.nextInt();
        if (n == 0) {
            System.out.println("No processes to schedule.");
            sc.close();
            return;
        }
        if (quantum <= 0) {
            System.out.println("Time quantum must be positive.");
            return;
        }
        int time = 0;
        while (head != null) {
            Node p = head, prev = tail;
            do {
                Process x = p.process;
                int used = Math.min(quantum, x.remaining);
                x.remaining -= used;
                time += used;
                System.out.println("P" + x.id + " executed for " + used + " units");
                if (x.remaining == 0) {
                    x.turnaround = time;
                    x.waiting = x.turnaround - x.burst;
                    Node next = p.next;
                    remove(prev, p);
                    p = next;
                    if (head == null) break;
                } else {
                    prev = p;
                    p = p.next;
                }
            }
            while (p != head && head != null);
            System.out.print("Queue: ");
            if (head == null) System.out.println("empty");
            else {
                Node q = head;
                do {
                    System.out.print("P" + q.process.id + "(" + q.process.remaining + ") ");
                    q = q.next;
                }
                while (q != head);
                System.out.println();
            }
        }
        double wt = 0, tt = 0;
        for (Process p : all) {
            wt += p.waiting;
            tt += p.turnaround;
        }
        System.out.printf("Average waiting time: %.2f%n", wt / n);
        System.out.printf("Average turnaround time: %.2f%n", tt / n);
        sc.close();
    }
}
