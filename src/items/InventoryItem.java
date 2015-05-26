/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

/**
 *
 * @author Benjamin
 */
public abstract class InventoryItem {
    public InventoryItem(String itemType){
        this.itemType = itemType;
    }
    
    public static String WEAPON = "Weapon";
    public static String EQUIPMENT = "Equipment";
    public static String RESOURCE = "Resource";
    
    private String itemType;

    /**
     * @return the itemType
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * @param itemType the itemType to set
     */
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
}
