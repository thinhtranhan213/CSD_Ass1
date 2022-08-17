package Manage;

import Book.BookList;
import Reader.ReaderList;
import java.io.IOException;
import java.util.Scanner;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author tendo
 */
public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        BookList book = new BookList();
        ReaderList reader = new ReaderList();
        while (true) {
            Menu.Menu();
            int option = Integer.parseInt(sc.nextLine());
            switch (option) {
                case 1:
                    book.Manage();
                    break;
                case 2:
                    reader.Manage();
                case 3:
                    break;
            }
        }
    }
}
