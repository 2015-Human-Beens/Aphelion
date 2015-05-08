package aphelion;

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

    public Character(CharacterLocationValidatorIntf characterLocationValidator) {
        this.characterLocationValidator = characterLocationValidator;
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
        graphics.drawString("Fuel " + getFuel(), 100, 20);
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Fields">
    private Point STARTING_POINT = new Point(55, 30);// eventually have to change this
    private Point location = STARTING_POINT;

    private MapDrawDataIntf mapDrawData;
    private CharacterLocationValidatorIntf characterLocationValidator;

    private ArrayList<Point> revealedLocations;

    private int STARTING_SCANNED_RADIUS = 3;
    private int scanRadius = STARTING_SCANNED_RADIUS;
    private int fuel = 500;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setters/Getters">
    /**
     * @return the characterLocationValidator
     */
    public CharacterLocationValidatorIntf getCharacterLocationValidator() {
        return characterLocationValidator;
    }

    /**
     * @param characterLocationValidator the characterLocationValidator to set
     */
    public void setCharacterLocationValidator(CharacterLocationValidatorIntf characterLocationValidator) {
        this.characterLocationValidator = characterLocationValidator;
    }

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
     * @param revealedLocations the revealedLocations to set
     */
    public void setRevealedLocations(ArrayList<Point> revealedLocations) {
        this.revealedLocations = revealedLocations;
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
    public int getScanRadius() {
        return scanRadius;
    }

    /**
     * @param scanRadius the scanRadius to set
     */
    public void setScanRadius(int scanRadius) {
        this.scanRadius = scanRadius;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Other Methods">
    void drawScanned(Graphics graphics) {
        //Draw the area the character can see
        for (Point revealedLocation : getSafeScannedLocation()) {
            Point topLeft = mapDrawData.getCellSystemCoordinate(revealedLocation);
            graphics.setColor(new Color(255, 0, 0, 50));
            graphics.fillRect(topLeft.x, topLeft.y, mapDrawData.getCellWidth(), mapDrawData.getCellHeight());
        }
    }

    public ArrayList<Point> getScannedLocations() {
        //Neat awesome code that draws a diamond with the radius of whatever scanRadius is
        revealedLocations = new ArrayList<>();
        for (int i = -getScanRadius(); i <= getScanRadius(); i++) {
            for (int j = -(scanRadius - Math.abs(i)); j <= getScanRadius() - Math.abs(i); j++) {
                revealedLocations.add(new Point(getLocation().x + j, getLocation().y + i));
            }
        }
        return revealedLocations;
    }

    /**
     * @return the safeRevealedLocations
     */
    public ArrayList<Point> getSafeScannedLocation() {
        ArrayList<Point> safeRevealedLocations = new ArrayList<>();
        for (Point score : getScannedLocations()) {
            safeRevealedLocations.add(score);
        }
        return safeRevealedLocations;
    }

    void move(KeyEvent e) {
        Point newLoc = new Point(); 
        if (e.getKeyCode() == KeyEvent.VK_A) {
            newLoc = new Point(getLocation().x - 1, getLocation().y);
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            newLoc = new Point(getLocation().x, getLocation().y - 1);
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            newLoc = new Point(getLocation().x + 1, getLocation().y);
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            newLoc = new Point(getLocation().x, getLocation().y + 1);
        }
        setLocation(characterLocationValidator.validateLocation(newLoc));
    }
//</editor-fold>

}
