package aphelion;

import items.InventoryItem;
import hud.StatusProviderIntf;
import images.ResourceTools;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Benjamin
 */
public class Character {

    Character(String name) {
        this.name = name;
    }

    //<editor-fold defaultstate="collapsed" desc="paintCharacter">
    void paintShip(Graphics graphics) {
        Point topLeft = drawData.getCellSystemCoordinate(getLocation());
        graphics.drawImage(shipImage, topLeft.x, topLeft.y, null);
    }
    
    void paintChar(Graphics graphics) {
        Point topLeft = drawData.getCellSystemCoordinate(getLocation());
        graphics.drawImage(charImage, topLeft.x, topLeft.y, null);
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
    private Point STARTING_POINT = new Point(55, 30);// eventually have to change this
    private Point location = STARTING_POINT;

    private DrawDataIntf drawData;
    
    private ArrayList<InventoryItem> inventory;
    
    private String mapFlag;

    private int STARTING_SCANNED_RADIUS = 3;
    private int scanRadius = STARTING_SCANNED_RADIUS;
    private StatusProviderIntf fuelStatusProvider;
    private StatusProviderIntf healthStatusProvider;
    
    private String name;
    private final Image shipImage = ResourceTools.loadImageFromResource("resources/shipUp.png");
    private final Image charImage = ResourceTools.loadImageFromResource("resources/characterOverhead.png");
    
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
     * @return the drawData
     */
    public DrawDataIntf getDrawData() {
        return drawData;
    }

    /**
     * @param drawData the drawData to set
     */
    public void setDrawData(DrawDataIntf drawData) {
        this.drawData = drawData;
    }

    /**
     * @return the scannedArea
     */
    public int getScannedArea() {
        return getScanRadius();
    }

    /**
     * @param scannedArea the scannedArea to set
     */
    public void setScannedArea(int scannedArea) {
        this.setScanRadius(scannedArea);
    }

    /**
     * @return the Difficulty
     */
    public int getDifficulty() {
        // Checks for difficulty within a diamond with a radius of 10 in relation to STARTING_POINT
        return (int) (Math.floorDiv((Math.abs(getLocation().x - STARTING_POINT.x) + Math.abs(getLocation().y - STARTING_POINT.y)), 10) + 1);
    }

    /**
     * @return the scanRadius
     */
    public int getScanRadius() {
        return scanRadius;
    }

    /**
     * @param scanRadius the scanRadius to set
     */
    public void setScanRadius(int scanRadius) {
        this.scanRadius = scanRadius;
    }

    /**
     * @return the mapFlag
     */
    public String getMapFlag() {
        return mapFlag;
    }

    /**
     * @param mapFlag the mapFlag to set
     */
    public void setMapFlag(String mapFlag) {
        this.mapFlag = mapFlag;
    }

    /**
     * @return the inventory
     */
    public ArrayList<InventoryItem> getInventory() {
        if (inventory == null) {
            inventory = new ArrayList<>();
        }
        return inventory;
    }

    /**
     * @param inventory the inventory to set
     */
    public void setInventory(ArrayList<InventoryItem> inventory) {
        this.inventory = inventory;
    }
    
    public void addToInventory(InventoryItem item) {
        if (inventory == null) {
            inventory = new ArrayList<>();
        }
        inventory.add(item);
    }
    
    public void setFuelStatusProvider(StatusProviderIntf fuelStatusProvider) {
        this.fuelStatusProvider = fuelStatusProvider;
    }

    public void setHealthStatusProvider(StatusProviderIntf fuelStatusProvider) {
        this.healthStatusProvider = healthStatusProvider;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
//</editor-fold>
    
    
}
