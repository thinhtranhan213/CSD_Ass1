/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Book;

import Entity.Book;
import Manage.Menu;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author tendo
 */
public class BookList {

    private static final Scanner sc = new Scanner(System.in);

    public void Manage() throws IOException {
        int choice = 1;
        while (choice != 0) {
            Menu.MenuBook();
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    loadFile("book1.txt");
                    break;
                case 2:
                    addTailList();
                    break;
                case 3:
                    Display();
                    break;
                case 4:
                    saveFile("BookList.txt");
                    break;
                case 5:
                    Find();
                    break;
                case 6:
                    System.out.print("Enter code: ");
                    dele(sc.nextLine());
                    break;
                case 7:
                    sortByCode();
                    break;
                case 8:
                    addHeadList();
                    break;
                case 9:
                    addK(enterK());
                    break;
                case 10:
                    DelK(enterK());
                    break;
            }
        }

    }

    //-------------Default-----------------
    Node_Book head, tail;

    public BookList() {
        head = tail = null;
    }

    public boolean isEmpty() {
        return (head == null);
    }

    void addFirst(Book b) {
        Node_Book p = new Node_Book(b);
        if (isEmpty()) {
            head = tail = p;
        } else {
            p.next = head;
            head = p;
        }

    }

    void addLast(Book b) {
        Node_Book p = new Node_Book(b);
        if (isEmpty()) {
            head = tail = p;
        } else {
            tail.next = p;
            tail = p;
        }
    }

    void deleteFirst() {
        if (isEmpty()) {
            System.out.println("List is empty");
        } else if (head == tail && head != null) {
            head = null;
        } else {
            head = head.next;
        }
    }

    void deleteLast() {
        if (isEmpty()) {
            System.out.println("List is empty");
        } else if (head == tail && head != null) {
            head = null;
        } else {
            Node_Book p = head;
            while (true) {
                if (p.next == tail) {
                    tail = p;
                    tail.next = null;
                    break;
                }
                p = p.next;
            }
        }
    }

    //--------File----------
    public void loadFile(String fname) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(fname);
        BufferedReader br = new BufferedReader(fr);
        String s;
        String[] a;

        String bcode, title;
        int quantity;
        double price;

        if (!isEmpty()) {
            System.out.println("Do you want to keep existiong data?(Y/N)");
            String c = sc.nextLine();
            if (c.equalsIgnoreCase("N")) {
                while (!isEmpty()) {
                    Node_Book p = head;
                    deleteFirst();
                }
            }

        }
        while (true) {
            s = br.readLine();
            if (s == null || s.trim().length() < 6) {
                break;
            }
            a = s.split("[|]");
            bcode = a[0].trim();
            title = a[1].trim();
            quantity = Integer.parseInt(a[2].trim());
            price = Double.parseDouble(a[3].trim());
            addLast(new Book(bcode, title, quantity, 0, price));
        }
        fr.close();
        br.close();
    }

    public void saveFile(String fname2) throws IOException {
        FileWriter fw = new FileWriter(fname2);
        PrintWriter pw = new PrintWriter(fw);
        Node_Book p = head;
        pw.println("code |    Title    | Quantity | Lended| Price | Value");
        pw.println("-----------------------------------------------------------------------------------------");
        while (p != null) {
            pw.printf(" %-4s|   %-10s|     %-5d|   %-4d|  %-4.1f | %-4.1f\r\n",
                    p.info.bcode, p.info.title, p.info.quantity, p.info.lended, p.info.price, p.info.price * p.info.quantity);
            p = p.next;
        }
        pw.close();
        fw.close();
    }

    //-----------------------------------
    //giao dien user nhap data
    Book input() {
        String bcode, title;
        int quantity, lended;
        double price;

        while (true) {

            System.out.print("Enter code: ");
            bcode = sc.nextLine().trim();

            if (checkValidCode(bcode)) {

                System.out.print("Enter title: ");
                title = sc.nextLine().trim();

                while (true) {
                    System.out.print("Enter quantity: ");
                    quantity = Integer.parseInt(sc.nextLine());

                    System.out.print("Enter lended: ");
                    lended = Integer.parseInt(sc.nextLine());

                    if (lended <= quantity) {
                        System.out.print("Enter price: ");
                        price = Double.parseDouble(sc.nextLine());
                        break;
                    } else {
                        System.out.println("The number of lended exceeds quantity");
                    }
                }
                break;
            } else {
                System.out.println("Code has been existed");
            }
        }
        return new Book(bcode, title, quantity, lended, price);
    }

    private boolean checkValidCode(String bcode) {
        if (isEmpty()) {
            return true;
        }
        Node_Book p = head;
        while (p != null) {
            if (p.info.bcode.equalsIgnoreCase(bcode)) {
                return false;
            }
            p = p.next;
        }
        return true;
    }

    public void addTailList() {
        addLast(input());
        System.out.println("Added to the end of the list!");
    }

    public void addHeadList() {
        addFirst(input());
        System.out.println("Added to the begining of the list!");
    }

    public void Display() {
        Node_Book p = head;
        System.out.println("code |    Title    | Quantity | Lended| Price | Value");
        while (p != null) {
            System.out.printf(" %-4s|   %-10s|     %-5d|   %-4d|  %-4.1f | %-4.1f\r\n",
                    p.info.bcode, p.info.title, p.info.quantity, p.info.lended, p.info.price, p.info.price * p.info.quantity);
            p = p.next;
        }
    }

    //-----code doing--------
    public Node_Book search(String xCode) {
        if (isEmpty()) {
            return null;
        }
        Node_Book p = head;
        while (p != null) {
            if (p.info.bcode.equalsIgnoreCase(xCode)) {
                return p;
            }
            p = p.next;
        }
        return null;
    }

    public void Find() {
        System.out.print("Enter code: ");
        String xCode = sc.nextLine();

        if (search(xCode) == null) {
            System.err.println("Not found");
        } else {
            System.err.println("Found");
        }
    }

    public void dele(String xCode) {
        Node_Book q = search(xCode);
        Node_Book p = head;

        if (q == head) {
            deleteFirst();
        } else if (q == tail) {
            deleteLast();
        } else if (q == null) {
            System.out.println("Cannot find book");
        } else {
            while (p != null) {
                if (p.next == q) {
                    p.next = q.next;
                    System.out.println("Delete success");
                    break;
                }
                p = p.next;
            }
        }
    }

    public void sortByCode() {
        Node_Book p = head;
        while (p.next != null) {
            Node_Book q = p.next;
            while (q != null) {
                if (p.info.bcode.compareTo(q.info.bcode) > 0) {
                    Book tmp = p.info;
                    p.info = q.info;
                    q.info = tmp;
                }
                q = q.next;
            }
            p = p.next;
        }
        System.out.println("Success!");
    }

    //------position doing-------
    public int enterK() {
        while (true) {
            System.out.print("Enter position(0-" + size() + "): ");
            int k = Integer.parseInt(sc.nextLine());
            if (k >= 0 && k <= size()) {
                return k;
            }
        }
    }

    int size() {
        if (isEmpty()) {
            return 0;
        }
        Node_Book p = head;
        int count = 0;
        while (p != null) {
            count++;
            p = p.next;
        }
        return (count - 1);
    }

    public void addK(int k) {
        if (isEmpty()) {
            addFirst(input());
        } else if (k == 0) {
            Node_Book q = new Node_Book(input());
            q.next = head.next;
            head.next = q;
        } else if (k == size()) {
            addLast(input());
        } else {
            Node_Book p = head;
            Node_Book q = new Node_Book(input());
            for (int i = 0; i < k; i++) {
                p = p.next;
            }
            q.next = p.next;
            p.next = q;
        }
    }

    public void DelK(int k) {
        if (isEmpty()) {
            System.out.println("Empty list");
        } else {
            Node_Book p = head;
            for (int i = 0; i < k; i++) {
                p = p.next;
            }
            dele(p.info.bcode);
        }
    }
}
