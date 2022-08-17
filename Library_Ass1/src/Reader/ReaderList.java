/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reader;

import Book.Node_Book;
import Entity.Book;
import Entity.Reader;
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
public class ReaderList {

    final static private Scanner sc = new Scanner(System.in);

    public void Manage() throws IOException {

        int choice = 1;
        while (choice != 0) {
            Menu.MenuReader();
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    loadFile("Reader.txt");
                    break;
                case 2:
                    addTailList();
                    break;
                case 3:
                    Display();
                    break;
                case 4:
                    saveFile("ReaderList.txt");
                    break;
                case 5:
                    Find();
                    break;
                case 6:
                    System.out.print("Enter code:");
                    dele(sc.nextLine());
                    break;
            }
        }
    }
    //---------------Default
    Node_Reader head, tail;

    public ReaderList() {
        this.head = this.tail = null;
    }

    public boolean isEmpty() {
        return (head == null);
    }

    void addFirst(Reader b) {
        Node_Reader p = new Node_Reader(b);
        if (isEmpty()) {
            head = tail = p;
        } else {
            p.next = head;
            head = p;
        }

    }

    void addLast(Reader b) {
        Node_Reader p = new Node_Reader(b);
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
            Node_Reader p = head;
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

        String rcode, name;
        int byear;

        if (!isEmpty()) {
            System.out.println("Do you want to keep existiong data?(Y/N)");
            String c = sc.nextLine();
            if (c.equalsIgnoreCase("N")) {
                while (!isEmpty()) {
                    Node_Reader p = head;
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
            rcode = a[0].trim();
            name = a[1].trim();
            byear = Integer.parseInt(a[2].trim());
            addLast(new Reader(rcode, name, byear));
        }
        fr.close();
        br.close();
    }

    public void saveFile(String fname2) throws IOException {
        FileWriter fw = new FileWriter(fname2);
        PrintWriter pw = new PrintWriter(fw);
        Node_Reader p = head;
        pw.println("code |  Name   | Birth Year");
        pw.println("-----------------------------------------------------------------------------------------");
        while (p != null) {
            pw.printf(" %-3s|   %-5s| %-4d\r\n",
                    p.info.rcode, p.info.name, p.info.byear);
            p = p.next;
        }
        pw.close();
        fw.close();
    }
    //-----------------------------------
    //giao dien user nhap data

    Reader input() {
        String rcode, name;
        int byear;

        while (true) {

            System.out.print("Enter code: ");
            rcode = sc.nextLine().trim();

            if (checkValidCode(rcode)) {

                System.out.print("Enter name: ");
                name = sc.nextLine().trim();

                while (true) {
                    System.out.print("Enter byear: ");
                    byear = Integer.parseInt(sc.nextLine());

                    if (byear <= 2010 && byear >= 1900) {
                        break;
                    } else {
                        System.out.println("Birth year must between 1900 and 2010");
                    }
                }
                break;
            } else {
                System.out.println("Code has been existed");
            }
        }
        return new Reader(rcode, name, byear);
    }

    private boolean checkValidCode(String rcode) {
        if (isEmpty()) {
            return true;
        }
        Node_Reader p = head;
        while (p != null) {
            if (p.info.rcode.equalsIgnoreCase(rcode)) {
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

    public void Display() {
        Node_Reader p = head;
        System.out.println("code|  Name   | Birth Year");
        while (p != null) {
            System.out.printf("%-4s|   %-6s| %-3d\r\n",
                    p.info.rcode, p.info.name, p.info.byear);
            p = p.next;
        }
    }

    //-----code doing--------
    public Node_Reader search(String xCode) {
        if (isEmpty()) {
            return null;
        }
        Node_Reader p = head;
        while (p != null) {
            if (p.info.rcode.equalsIgnoreCase(xCode)) {
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
        Node_Reader q = search(xCode);
        Node_Reader p = head;

        if (q == head) {
            deleteFirst();
        } else if (q == tail) {
            deleteLast();
        } else if (q == null) {
            System.out.println("Cannot find reader");
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

}
