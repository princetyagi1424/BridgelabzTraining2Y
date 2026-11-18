import java.util.Scanner;
public class TaskScheduler {
    static class Task {
        int id, priority;
        String name, due;
        Task next;
        Task(int i, String n, int p, String d) {
            id = i;
            name = n;
            priority = p;
            due = d;
        }
    }
    static Task head, current;
    static void add(int id, String name, int priority, String due, int pos) {
        Task n = new Task(id, name, priority, due);
        if (head == null) {
            head = n;
            n.next = n;
            current = n;
            return;
        }
        if (pos <= 1) {
            Task tail = head;
            while (tail.next != head) tail = tail.next;
            n.next = head;
            tail.next = n;
            head = n;
            return;
        }
        Task p = head;
        int i = 1;
        while (i < pos - 1 && p.next != head) {
            p = p.next;
            i++;
        }
        n.next = p.next;
        p.next = n;
    }
    static void remove(int id) {
        if (head == null) return;
        Task p = head, prev = null;
        do {
            if (p.id == id) break;
            prev = p;
            p = p.next;
        }
        while (p != head);
        if (p.id != id) {
            System.out.println("Task not found.");
            return;
        }
        if (p.next == p) {
            head = current = null;
            return;
        }
        if (prev == null) {
            Task tail = head;
            while (tail.next != head) tail = tail.next;
            head = head.next;
            tail.next = head;
        } else prev.next = p.next;
        if (current == p) current = p.next;
    }
    static void display() {
        if (head == null) {
            System.out.println("No tasks.");
            return;
        }
        Task p = head;
        do {
            System.out.println(p.id + " | " + p.name + " | Priority: " + p.priority + " | Due: " + p.due);
            p = p.next;
        }
        while (p != head);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1.Add 2.Remove 3.Current and next 4.Display 5.Search priority 0.Exit");
            int ch = sc.nextInt();
            sc.nextLine();
            if (ch == 0) break;
            if (ch == 1) {
                System.out.print("ID: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("Name: ");
                String n = sc.nextLine();
                System.out.print("Priority: ");
                int p = sc.nextInt();
                sc.nextLine();
                System.out.print("Due date: ");
                String d = sc.nextLine();
                System.out.print("Position: ");
                add(id, n, p, d, sc.nextInt());
            } else if (ch == 2) {
                System.out.print("Task ID: ");
                remove(sc.nextInt());
            } else if (ch == 3) {
                if (current == null) System.out.println("No current task.");
                else {
                    System.out.println("Current: " + current.name);
                    current = current.next;
                    System.out.println("Next: " + current.name);
                }
            } else if (ch == 4) display();
            else if (ch == 5) {
                System.out.print("Priority: ");
                int priority = sc.nextInt();
                if (head != null) {
                    Task p = head;
                    do {
                        if (p.priority == priority) System.out.println(p.id + " | " + p.name + " | " + p.due);
                        p = p.next;
                    }
                    while (p != head);
                }
            }
        }
        sc.close();
    }
}
