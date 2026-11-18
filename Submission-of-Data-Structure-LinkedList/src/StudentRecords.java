import java.util.Scanner;
public class StudentRecords {
    static class Student {
        int roll, age;
        String name, grade;
        Student next;
        Student(int roll, String name, int age, String grade) {
            this.roll = roll;
            this.name = name;
            this.age = age;
            this.grade = grade;
        }
    }
    static Student head;
    static void add(int roll, String name, int age, String grade, int pos) {
        Student n = new Student(roll, name, age, grade);
        if (pos <= 1 || head == null) {
            n.next = head;
            head = n;
            return;
        }
        Student p = head;
        for (int i = 1; i < pos - 1 && p.next != null; i++) p = p.next;
        n.next = p.next;
        p.next = n;
    }
    static void delete(int roll) {
        if (head == null) return;
        if (head.roll == roll) {
            head = head.next;
            return;
        }
        Student p = head;
        while (p.next != null && p.next.roll != roll) p = p.next;
        if (p.next != null) p.next = p.next.next;
    }
    static Student search(int roll) {
        for (Student p = head; p != null; p = p.next) if (p.roll == roll) return p;
        return null;
    }
    static void display() {
        if (head == null) {
            System.out.println("No student records.");
            return;
        }
        for (Student p = head; p != null; p = p.next) System.out.println(p.roll + " | " + p.name + " | " + p.age + " | " + p.grade);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1.Add 2.Delete 3.Search 4.Display 5.Update grade 0.Exit");
            int ch = sc.nextInt();
            if (ch == 0) break;
            if (ch == 1) {
                System.out.print("Roll, name, age, grade, position: ");
                int r = sc.nextInt();
                String n = sc.next();
                int a = sc.nextInt();
                String g = sc.next();
                int pos = sc.nextInt();
                add(r, n, a, g, pos);
            } else if (ch == 2) {
                System.out.print("Roll: ");
                delete(sc.nextInt());
            } else if (ch == 3) {
                System.out.print("Roll: ");
                Student s = search(sc.nextInt());
                System.out.println(s == null ? "Student not found." : s.roll + " | " + s.name + " | " + s.age + " | " + s.grade);
            } else if (ch == 4) display();
            else if (ch == 5) {
                System.out.print("Roll and new grade: ");
                Student s = search(sc.nextInt());
                String g = sc.next();
                if (s == null) System.out.println("Student not found.");
                else s.grade = g;
            }
        }
        sc.close();
    }
}
