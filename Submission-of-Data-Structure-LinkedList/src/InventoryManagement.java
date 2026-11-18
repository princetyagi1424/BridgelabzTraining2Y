import java.util.Scanner;
public class InventoryManagement {
    static class Item {
        String name;
        int id, quantity;
        double price;
        Item next;
        Item(String n, int i, int q, double p) {
            name = n;
            id = i;
            quantity = q;
            price = p;
        }
    }
    static Item head;
    static void add(String n, int id, int q, double price, int pos) {
        Item x = new Item(n, id, q, price);
        if (pos <= 1 || head == null) {
            x.next = head;
            head = x;
            return;
        }
        Item p = head;
        for (int i = 1; i < pos - 1 && p.next != null; i++) p = p.next;
        x.next = p.next;
        p.next = x;
    }
    static void remove(int id) {
        if (head == null) return;
        if (head.id == id) {
            head = head.next;
            return;
        }
        Item p = head;
        while (p.next != null && p.next.id != id) p = p.next;
        if (p.next != null) p.next = p.next.next;
        else System.out.println("Item not found.");
    }
    static Item find(int id) {
        for (Item p = head; p != null; p = p.next) if (p.id == id) return p;
        return null;
    }
    static void display() {
        if (head == null) System.out.println("Inventory is empty.");
        for (Item p = head; p != null; p = p.next) System.out.println(p.id + " | " + p.name + " | Qty: " + p.quantity + " | Price: " + p.price);
    }
    static void sort(boolean byPrice, boolean ascending) {
        for (Item a = head; a != null; a = a.next) for (Item b = a.next; b != null; b = b.next) {
            int cmp = byPrice ? Double.compare(a.price, b.price) : a.name.compareToIgnoreCase(b.name);
            if ((ascending && cmp > 0) || (!ascending && cmp < 0)) {
                String n = a.name;
                a.name = b.name;
                b.name = n;
                int id = a.id;
                a.id = b.id;
                b.id = id;
                int q = a.quantity;
                a.quantity = b.quantity;
                b.quantity = q;
                double v = a.price;
                a.price = b.price;
                b.price = v;
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1.Add 2.Remove 3.Update quantity 4.Search 5.Display 6.Total value 7.Sort 0.Exit");
            int c = sc.nextInt();
            sc.nextLine();
            if (c == 0) break;
            if (c == 1) {
                System.out.print("Name: ");
                String n = sc.nextLine();
                System.out.print("ID, quantity, price, position: ");
                add(n, sc.nextInt(), sc.nextInt(), sc.nextDouble(), sc.nextInt());
            } else if (c == 2) {
                System.out.print("Item ID: ");
                remove(sc.nextInt());
            } else if (c == 3) {
                System.out.print("ID and new quantity: ");
                Item x = find(sc.nextInt());
                int q = sc.nextInt();
                if (x == null) System.out.println("Item not found.");
                else x.quantity = q;
            } else if (c == 4) {
                System.out.print("Enter ID or name: ");
                String k = sc.nextLine();
                boolean found = false;
                for (Item p = head; p != null; p = p.next) if (String.valueOf(p.id).equals(k) || p.name.equalsIgnoreCase(k)) {
                    System.out.println(p.id + " | " + p.name + " | " + p.quantity + " | " + p.price);
                    found = true;
                }
                if (!found) System.out.println("Item not found.");
            } else if (c == 5) display();
            else if (c == 6) {
                double total = 0;
                for (Item p = head; p != null; p = p.next) total += p.price * p.quantity;
                System.out.println("Total inventory value: " + total);
            } else if (c == 7) {
                System.out.print("1.Name 2.Price: ");
                boolean price = sc.nextInt() == 2;
                System.out.print("1.Ascending 2.Descending: ");
                sort(price, sc.nextInt() == 1);
                display();
            }
        }
        sc.close();
    }
}
