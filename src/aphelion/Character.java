package aphelion;

<<<<<<< HEAD
import items.InventoryItem;
=======
import hud.StatusProvider;
import hud.StatusProviderIntf;
>>>>>>> dmk-new-statusArc
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
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

    //<editor-fold defaultstate="collapsed" desc="paintCharacter">
    void paint(Graphics graphics) {
        Point topLeft = mapDrawData.getCellSystemCoordinate(getLocation());
        graphics.setColor(Color.WHITE);
//        graphics.fillRect(topLeft.x, topLeft.y, mapDrawData.getCellWidth(), mapDrawData.getCellHeight());
//        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("Courier New", Font.PLAIN, 10));
<<<<<<< HEAD
        graphics.fillOval(topLeft.x, topLeft.y, 16, 16);
=======
        graphics.drawString("@", topLeft.x, topLeft.y + (mapDrawData.getCellHeight() * 3 / 4));
>>>>>>> dmk-new-statusArc
        graphics.setColor(Color.BLACK);
        graphics.drawString("Difficulty " + getDifficulty(), 20, 20);
//        graphics.drawString("Fuel " + fuel, 100, 20);
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Fields">
    private Point STARTING_POINT = new Point(55, 30);// eventually have to change this
    private Point location = STARTING_POINT;

    private MapDrawDataIntf mapDrawData;
    
    private ArrayList<InventoryItem> inventory;
    
    private String mapFlag;

    private int STARTING_SCANNED_RADIUS = 3;
    private int scanRadius = STARTING_SCANNED_RADIUS;
<<<<<<< HEAD
    private int fuel = 500;
=======
    private int difficulty;
    private StatusProviderIntf fuelStatusProvider;
    private StatusProviderIntf healthStatusProvider;


//    private int fuel = fuelStatusProvider.getStatus();

>>>>>>> dmk-new-statusArc
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setters/Getters">
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
     * @return the fuel
     */
    public int getFuel() {
        return fuel;
    }

    /**
     * @param fuel the fuel to set
     */
    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    /**
     * @return the scanRadius
     */
<<<<<<< HEAD
    public int getScanRadius() {
        return scanRadius;
    }

    /**
     * @param scanRadius the scanRadius to set
     */
    public void setScanRadius(int scanRadius) {
        this.scanRadius = scanRadius;
=======
    public int getFuel() {
            return fuelStatusProvider.getStatus();
    }

//    /**
//     * @param fuel the fuel to set
//     */
    public void setFuel(int fuel) {
        this.fuelStatusProvider.changeStatus(fuel);
>>>>>>> dmk-new-statusArc
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
//</editor-fold>

    /**
     * @return the inventory
     */
<<<<<<< HEAD
    public ArrayList<InventoryItem> getInventory() {
        if (inventory == null) {
            inventory = new ArrayList<>();
=======
    public ArrayList<Point> getSafeScannedLocation() {
        ArrayList<Point> safeRevealedLocations = new ArrayList<>();
        for (Point score : getScannedLocations()) {
            safeRevealedLocations.add(score);
        }
        return safeRevealedLocations;
    }

    void move(KeyEvent e) {
        if (fuelStatusProvider.getStatus()> 0){ // I get an error everytime I try using getStatus of fuel.
            if (e.getKeyCode() == KeyEvent.VK_A) {
                setLocation(new Point(getLocation().x - 1, getLocation().y));
            } else if (e.getKeyCode() == KeyEvent.VK_W) {
                setLocation(new Point(getLocation().x, getLocation().y - 1));
            } else if (e.getKeyCode() == KeyEvent.VK_D) {
                setLocation(new Point(getLocation().x + 1, getLocation().y));
            } else if (e.getKeyCode() == KeyEvent.VK_S) {
                setLocation(new Point(getLocation().x, getLocation().y + 1));
            }
        setFuel(-2);
>>>>>>> dmk-new-statusArc
        }
        return inventory;
    }

    /**
<<<<<<< HEAD
     * @param inventory the inventory to set
     */
    public void setInventory(ArrayList<InventoryItem> inventory) {
        this.inventory = inventory;
    }
    
=======
     * @param fuelStatusProvider the fuelStatusProvider to set
     */
    public void setFuelStatusProvider(StatusProviderIntf fuelStatusProvider) {
        this.fuelStatusProvider = fuelStatusProvider;
    }
    public void setHealthStatusProvider(StatusProviderIntf fuelStatusProvider) {
        this.healthStatusProvider = healthStatusProvider;
    }

>>>>>>> dmk-new-statusArc
}
