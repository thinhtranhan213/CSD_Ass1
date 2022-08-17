/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manage;

import Book.BookList;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author tendo
 */
public class Menu {

    public static void Menu() {
        System.out.println("1.Handle with book");
        System.out.println("2.Handle with reader");
        System.out.println("3.Handle with lending");
        System.out.print("Enter choice: ");
    }

    public static void MenuBook() {
        System.out.println(
                "1.      Load data from file\n"
                + "2.      Input & add to the end\n"
                + "3.      Display data\n"
                + "4.      Save book list to file\n"
                + "5.      Search by bcode\n"
                + "6.      Delete by bcode\n"
                + "7.      Sort by bcode\n"
                + "8.      Input & add to beginning\n"
                + "9.      Add after position  k\n"
                + "10.    Delete position k");
        System.out.print("Enter choice :");
    }

    public static void MenuReader() {
        System.out.println(
                "11.      Load data from file\n"
                + "12.      Input & add to the end\n"
                + "13.      Display data\n"
                + "14.      Save reader list to file\n"
                + "15.      Search by rcode\n"
                + "16.      Delete by rcode");
        System.out.print("Enter choice: ");
    }

    public void MenuLending() {
        System.out.println(
                "21.      Input data\n"
                + "22.      Display data\n"
                + "23.      Sort  by bcode + rcode\n");
        System.out.print("Enter choice: ");
    }
}
