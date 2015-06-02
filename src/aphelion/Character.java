package aphelion;

import items.InventoryItem;
import hud.StatusProviderIntf;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
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
    void paint(Graphics graphics) {
        Point topLeft = mapDrawData.getCellSystemCoordinate(getLocation());
        graphics.setColor(Color.WHITE);
//        graphics.fillRect(topLeft.x, topLeft.y, mapDrawData.getCellWidth(), mapDrawData.getCellHeight());
//        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("Courier New", Font.PLAIN, 10));
        graphics.fillOval(topLeft.x, topLeft.y, 16, 16);
        graphics.setColor(Color.BLACK);
        graphics.drawString("Difficulty " + getDifficulty(), 20, 20);
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
    private Point STARTING_POINT = new Point(55, 30);// eventually have to change this
    private Point location = STARTING_POINT;

    private MapDrawDataIntf mapDrawData;
    
    private ArrayList<InventoryItem> inventory;
    
    private String mapFlag;

    private int STARTING_SCANNED_RADIUS = 3;
    private int scanRadius = STARTING_SCANNED_RADIUS;
    private int difficulty;
    private StatusProviderIntf fuelStatusProvider;
    private StatusProviderIntf healthStatusProvider;
    
    private String name;
    
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
     * @return the mapDrawData
     */
    public MapDrawDataIntf getMapDrawData() {
        return mapDrawData;
    }

    /**
     * @param mapDrawData the mapDrawData to set
     */
    public void setMapDrawData(MapDrawDataIntf mapDrawData) {
        this.mapDrawData = mapDrawData;
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
