import java.util.Scanner;
public class TextUndoRedo {
    static class State {
        String text;
        State prev, next;
        State(String t) {
            text = t;
        }
    }
    static State head, tail, current;
    static int size;
    static final int LIMIT = 10;
    static void add(String text) {
        if (current != tail) {
            current.next = null;
            tail = current;
            size = 0;
            for (State p = head; p != null; p = p.next) size++;
        }
        State n = new State(text);
        if (tail == null) head = tail = current = n;
        else {
            tail.next = n;
            n.prev = tail;
            tail = current = n;
        }
        size++;
        if (size > LIMIT) {
            head = head.next;
            head.prev = null;
            size--;
        }
    }
    static void undo() {
        if (current != null && current.prev != null) current = current.prev;
        else System.out.println("Nothing to undo.");
    }
    static void redo() {
        if (current != null && current.next != null) current = current.next;
        else System.out.println("Nothing to redo.");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        add("");
        while (true) {
            System.out.println("\n1.Type/replace text 2.Undo 3.Redo 4.Show current text 5.Show history 0.Exit");
            int c = sc.nextInt();
            sc.nextLine();
            if (c == 0) break;
            if (c == 1) {
                System.out.print("Enter text: ");
                add(sc.nextLine());
            } else if (c == 2) undo();
            else if (c == 3) redo();
            else if (c == 4) System.out.println(current == null ? "" : current.text);
            else if (c == 5) {
                for (State p = head; p != null; p = p.next) System.out.println((p == current ? " > " : "  ") + p.text);
            }
        }
        sc.close();
    }
}
