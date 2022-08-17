/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reader;

import Entity.Reader;

/**
 *
 * @author tendo
 */
public class Node_Reader {

    public Reader info;
    public Node_Reader next;

    public Node_Reader(Reader info, Node_Reader next) {
        this.info = info;
        this.next = next;
    }

    public Node_Reader(Reader info) {
        this.info = info;
    }

}
