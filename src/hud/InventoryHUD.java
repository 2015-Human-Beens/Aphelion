/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hud;

import java.awt.Dimension;
import java.awt.Point;

/**
 *
 * @author david
 */
public class InventoryHUD extends HUD{

    public InventoryHUD(Point position, Dimension size, HUDState state) {
        super(position, size, state);
        
        inventory = new HUDLabelComponent(new Point(150, 20), new Dimension(40, 20), "Hello");
        addComponent(inventory);
        
    }
    
    private HUDLabelComponent inventory;
    
}
