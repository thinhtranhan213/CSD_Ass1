/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author tendo
 */
public class Book {

    public String bcode, title;
    public int quantity, lended;
    public double price;

    public Book() {
    }

    public Book(String bcode, String title, int quantity, int lended, double price) {
        this.bcode = bcode;
        this.title = title;
        this.quantity = quantity;
        this.lended = lended;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" + "bcode=" + bcode + ", title=" + title + ", quantity=" + quantity + ", lended=" + lended + ", price=" + price + '}';
    }
    
    
}
