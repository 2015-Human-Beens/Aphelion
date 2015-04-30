/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aphelion;

import java.awt.Point;
import map.Item;

/**
 *
 * @author Benjamin
 */
public class Scanner extends Item {
    
    public static int scannerRadius = 5;

    public Scanner(Point location) {
        super(location, "Scanner");
    }
    
}
