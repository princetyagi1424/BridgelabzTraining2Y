import java.util.Scanner;
public class TicketReservation {
    static class Ticket {
        int id, seat;
        String customer, movie, time;
        Ticket next;
        Ticket(int i, String c, String m, int s, String t) {
            id = i;
            customer = c;
            movie = m;
            seat = s;
            time = t;
        }
    }
    static Ticket head, tail;
    static int count;
    static void add(Ticket x) {
        if (head == null) {
            head = tail = x;
            x.next = x;
        } else {
            x.next = head;
            tail.next = x;
            tail = x;
        }
        count++;
    }
    static void remove(int id) {
        if (head == null) {
            System.out.println("No bookings.");
            return;
        }
        Ticket p = head, prev = tail;
        do {
            if (p.id == id) break;
            prev = p;
            p = p.next;
        }
        while (p != head);
        if (p.id != id) {
            System.out.println("Ticket not found.");
            return;
        }
        if (p == head && p == tail) head = tail = null;
        else {
            prev.next = p.next;
            if (p == head) head = p.next;
            if (p == tail) tail = prev;
            tail.next = head;
        }
        count--;
    }
    static void display() {
        if (head == null) {
            System.out.println("No bookings.");
            return;
        }
        Ticket p = head;
        do {
            System.out.println(p.id + " | " + p.customer + " | " + p.movie + " | Seat " + p.seat + " | " + p.time);
            p = p.next;
        }
        while (p != head);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1.Book ticket 2.Cancel ticket 3.Display bookings 4.Search 5.Count tickets 0.Exit");
            int c = sc.nextInt();
            sc.nextLine();
            if (c == 0) break;
            if (c == 1) {
                System.out.print("Ticket ID: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("Customer name: ");
                String name = sc.nextLine();
                System.out.print("Movie name: ");
                String movie = sc.nextLine();
                System.out.print("Seat number: ");
                int seat = sc.nextInt();
                sc.nextLine();
                System.out.print("Booking time: ");
                add(new Ticket(id, name, movie, seat, sc.nextLine()));
            } else if (c == 2) {
                System.out.print("Ticket ID: ");
                remove(sc.nextInt());
            } else if (c == 3) display();
            else if (c == 4) {
                System.out.print("Customer or movie name: ");
                String k = sc.nextLine();
                boolean found = false;
                if (head != null) {
                    Ticket p = head;
                    do {
                        if (p.customer.equalsIgnoreCase(k) || p.movie.equalsIgnoreCase(k)) {
                            System.out.println(p.id + " | " + p.customer + " | " + p.movie + " | Seat " + p.seat);
                            found = true;
                        }
                        p = p.next;
                    }
                    while (p != head);
                }
                if (!found) System.out.println("Ticket not found.");
            } else if (c == 5) System.out.println("Booked tickets: " + count);
        }
        sc.close();
    }
}
