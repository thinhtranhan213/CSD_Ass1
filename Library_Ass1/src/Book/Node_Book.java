/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Book;
import Entity.Book;
/**
 *
 * @author tendo
 */
public class Node_Book {
    Book info;
    Node_Book next;

    public Node_Book(Book info, Node_Book next) {
        this.info = info;
        this.next = next;
    }

    public Node_Book(Book info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
