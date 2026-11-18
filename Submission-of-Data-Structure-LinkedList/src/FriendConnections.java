import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class FriendConnections {
    static class User {
        int id, age;
        String name;
        List <Integer> friends = new ArrayList <  > ();
        User next;
        User(int i, String n, int a) {
            id = i;
            name = n;
            age = a;
        }
    }
    static User head;
    static User find(int id) {
        for (User p = head; p != null; p = p.next) if (p.id == id) return p;
        return null;
    }
    static void addUser(int id, String name, int age) {
        if (find(id) != null) {
            System.out.println("User ID already exists.");
            return;
        }
        User n = new User(id, name, age);
        if (head == null) head = n;
        else {
            User p = head;
            while (p.next != null) p = p.next;
            p.next = n;
        }
    }
    static void connect(int a, int b) {
        User x = find(a), y = find(b);
        if (x == null || y == null || a == b) {
            System.out.println("Check the user IDs.");
            return;
        }
        if (!x.friends.contains(b)) x.friends.add(b);
        if (!y.friends.contains(a)) y.friends.add(a);
    }
    static void disconnect(int a, int b) {
        User x = find(a), y = find(b);
        if (x != null) x.friends.remove(Integer.valueOf(b));
        if (y != null) y.friends.remove(Integer.valueOf(a));
    }
    static void showFriends(int id) {
        User u = find(id);
        if (u == null) {
            System.out.println("User not found.");
            return;
        }
        for (int f : u.friends) {
            User x = find(f);
            if (x != null) System.out.println(x.id + " | " + x.name + " | " + x.age);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1.Add user 2.Add connection 3.Remove connection 4.Mutual friends 5.Show friends 6.Search user 7.Friend counts 0.Exit");
            int c = sc.nextInt();
            sc.nextLine();
            if (c == 0) break;
            if (c == 1) {
                System.out.print("ID: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("Name: ");
                String name = sc.nextLine();
                System.out.print("Age: ");
                addUser(id, name, sc.nextInt());
            } else if (c == 2 || c == 3) {
                System.out.print("Two user IDs: ");
                int a = sc.nextInt(), b = sc.nextInt();
                if (c == 2) connect(a, b);
                else disconnect(a, b);
            } else if (c == 4) {
                System.out.print("Two user IDs: ");
                User a = find(sc.nextInt()), b = find(sc.nextInt());
                if (a == null || b == null) System.out.println("User not found.");
                else for (int id : a.friends) if (b.friends.contains(id)) {
                    User x = find(id);
                    if (x != null) System.out.println(x.id + " | " + x.name);
                }
            } else if (c == 5) {
                System.out.print("User ID: ");
                showFriends(sc.nextInt());
            } else if (c == 6) {
                System.out.print("Enter ID or name: ");
                String k = sc.nextLine();
                boolean found = false;
                for (User p = head; p != null; p = p.next) if (String.valueOf(p.id).equals(k) || p.name.equalsIgnoreCase(k)) {
                    System.out.println(p.id + " | " + p.name + " | " + p.age);
                    found = true;
                }
                if (!found) System.out.println("User not found.");
            } else if (c == 7) for (User p = head; p != null; p = p.next) System.out.println(p.name + ": " + p.friends.size() + " friends");
        }
        sc.close();
    }
}
