import java.util.Scanner;
public class LibraryManagement {
    static class Book {
        String title, author, genre;
        int id;
        boolean available;
        Book prev, next;
        Book(String t, String a, String g, int i, boolean v) {
            title = t;
            author = a;
            genre = g;
            id = i;
            available = v;
        }
    }
    static Book head, tail;
    static void add(Book x, int pos) {
        if (head == null) {
            head = tail = x;
            return;
        }
        if (pos <= 1) {
            x.next = head;
            head.prev = x;
            head = x;
            return;
        }
        Book p = head;
        for (int i = 1; i < pos - 1 && p.next != null; i++) p = p.next;
        x.next = p.next;
        x.prev = p;
        if (p.next != null) p.next.prev = x;
        else tail = x;
        p.next = x;
    }
    static void remove(int id) {
        Book p = head;
        while (p != null && p.id != id) p = p.next;
        if (p == null) {
            System.out.println("Book not found.");
            return;
        }
        if (p.prev == null) head = p.next;
        else p.prev.next = p.next;
        if (p.next == null) tail = p.prev;
        else p.next.prev = p.prev;
    }
    static void display(boolean reverse) {
        Book p = reverse ? tail : head;
        if (p == null) System.out.println("No books in library.");
        while (p != null) {
            System.out.println(p.id + " | " + p.title + " | " + p.author + " | " + p.genre + " | " + (p.available ? "Available" : "Issued"));
            p = reverse ? p.prev : p.next;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1.Add 2.Remove 3.Search 4.Update availability 5.Forward 6.Reverse 7.Count 0.Exit");
            int c = sc.nextInt();
            sc.nextLine();
            if (c == 0) break;
            if (c == 1) {
                System.out.print("Title: ");
                String t = sc.nextLine();
                System.out.print("Author: ");
                String a = sc.nextLine();
                System.out.print("Genre: ");
                String g = sc.nextLine();
                System.out.print("Book ID: ");
                int id = sc.nextInt();
                System.out.print("Available ? true/false: ");
                boolean v = sc.nextBoolean();
                System.out.print("Position: ");
                add(new Book(t, a, g, id, v), sc.nextInt());
            } else if (c == 2) {
                System.out.print("Book ID: ");
                remove(sc.nextInt());
            } else if (c == 3) {
                System.out.print("Title or author: ");
                String k = sc.nextLine();
                boolean found = false;
                for (Book p = head; p != null; p = p.next) if (p.title.equalsIgnoreCase(k) || p.author.equalsIgnoreCase(k)) {
                    System.out.println(p.id + " | " + p.title + " | " + p.author + " | " + p.genre);
                    found = true;
                }
                if (!found) System.out.println("Book not found.");
            } else if (c == 4) {
                System.out.print("Book ID and availability: ");
                int id = sc.nextInt();
                boolean v = sc.nextBoolean();
                Book p = head;
                while (p != null && p.id != id) p = p.next;
                if (p == null) System.out.println("Book not found.");
                else p.available = v;
            } else if (c == 5) display(false);
            else if (c == 6) display(true);
            else if (c == 7) {
                int count = 0;
                for (Book p = head; p != null; p = p.next) count++;
                System.out.println("Total books: " + count);
            }
        }
        sc.close();
    }
}
