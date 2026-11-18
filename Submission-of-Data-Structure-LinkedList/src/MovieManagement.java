import java.util.Scanner;
public class MovieManagement {
    static class Movie {
        String title, director;
        int year;
        double rating;
        Movie prev, next;
        Movie(String t, String d, int y, double r) {
            title = t;
            director = d;
            year = y;
            rating = r;
        }
    }
    static Movie head, tail;
    static void add(String t, String d, int y, double r, int pos) {
        Movie n = new Movie(t, d, y, r);
        if (head == null) {
            head = tail = n;
            return;
        }
        if (pos <= 1) {
            n.next = head;
            head.prev = n;
            head = n;
            return;
        }
        Movie p = head;
        for (int i = 1; i < pos - 1 && p.next != null; i++) p = p.next;
        n.next = p.next;
        n.prev = p;
        if (p.next != null) p.next.prev = n;
        else tail = n;
        p.next = n;
    }
    static void remove(String title) {
        Movie p = head;
        while (p != null && !p.title.equalsIgnoreCase(title)) p = p.next;
        if (p == null) {
            System.out.println("Movie not found.");
            return;
        }
        if (p.prev == null) head = p.next;
        else p.prev.next = p.next;
        if (p.next == null) tail = p.prev;
        else p.next.prev = p.prev;
    }
    static void display(boolean reverse) {
        Movie p = reverse ? tail : head;
        if (p == null) {
            System.out.println("No movies added.");
            return;
        }
        while (p != null) {
            System.out.println(p.title + " | " + p.director + " | " + p.year + " | " + p.rating);
            p = reverse ? p.prev : p.next;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1.Add 2.Remove by title 3.Search director/rating 4.Forward 5.Reverse 6.Update rating 0.Exit");
            int ch = sc.nextInt();
            sc.nextLine();
            if (ch == 0) break;
            if (ch == 1) {
                System.out.print("Title: ");
                String t = sc.nextLine();
                System.out.print("Director: ");
                String d = sc.nextLine();
                System.out.print("Year and rating: ");
                int y = sc.nextInt();
                double r = sc.nextDouble();
                System.out.print("Position: ");
                int pos = sc.nextInt();
                add(t, d, y, r, pos);
            } else if (ch == 2) {
                System.out.print("Title: ");
                remove(sc.nextLine());
            } else if (ch == 3) {
                System.out.print("Director or rating: ");
                String key = sc.nextLine();
                for (Movie p = head; p != null; p = p.next) if (p.director.equalsIgnoreCase(key) || String.valueOf(p.rating).equals(key)) System.out.println(p.title + " | " + p.director + " | " + p.year + " | " + p.rating);
            } else if (ch == 4) display(false);
            else if (ch == 5) display(true);
            else if (ch == 6) {
                System.out.print("Title: ");
                String t = sc.nextLine();
                Movie p = head;
                while (p != null && !p.title.equalsIgnoreCase(t)) p = p.next;
                if (p == null) System.out.println("Movie not found.");
                else {
                    System.out.print("New rating: ");
                    p.rating = sc.nextDouble();
                }
            }
        }
        sc.close();
    }
}
