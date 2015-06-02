/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Benjamin
 */
public class MapItem {
    
    public MapItem(ArrayList<InventoryItem> inventory, Point location){
        this.inventory = inventory; this.location = location;
    }
    
    // WHY NO COMMITABLE
    
    private ArrayList<InventoryItem> inventory;
    private Point location;

    /**
     * @return the location
     */
    public Point getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Point location) {
        this.location = location;
    }

    /**
     * @return the inventory
     */
    public ArrayList<InventoryItem> getInventory() {
        return inventory;
    }

    /**
     * @param inventory the inventory to set
     */
    public void setInventory(ArrayList<InventoryItem> inventory) {
        this.inventory = inventory;
    }
    
}
