/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aphelion;

import environment.Environment;
import grid.Grid;
import hud.HUD;
import hud.StatusBar;
import hud.StatusProvider;
//import hud.;
import hud.StatusProviderIntf;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author Benjamin
 */
class AphelionEnvironment extends Environment implements MapDrawDataIntf {
    
    private HUD resourceHUD;
    
    private StatusBar health;
    private StatusProviderIntf healthStatusProvider;

    private StatusBar oxygen;
    private StatusProviderIntf oxygenStatusProvider;

    
    //<editor-fold defaultstate="collapsed" desc="Agenda">
    /**
     * V1.0 Alpha (27/03/15) (Finish the following by 03/04/15)
     * - implement the following:
     *      - *Done* (Without nice icon) Fuel (A nice icon would be cool)
     *      - Starting Asteroid with complimentary map (Need graphics designer)
     *      - Map Portals (Enter a new map when you step on a planet in the system Map)
     * - change:
     *      - *Done* visiblePoints from ArrayList to mapPoints[][] 2D Array that stores visibility 
     * - suggestions?:
     *      - enter here
     */
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="AphelionEnvironment">
    public AphelionEnvironment() {
        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getRows(); j++) {
                mapPoints.add(new Point(j, i));
            }
        }
        updateScannedArea();
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="AbstractMethods">
    //<editor-fold defaultstate="collapsed" desc="initializeEnvironment">
    @Override
    public void initializeEnvironment() {
        grid = new Grid(101, 101, 15, 15, new Point(25, 25), Color.BLACK);
        human_bean = new Character();
        human_bean.setMapDrawData(this);
        mapPoints = new ArrayList<>();
        
        setMapPointsBeta(new int[grid.getColumns()][grid.getRows()]);
        
        setObjects(new ArrayList<>());
        
        for (int i = 0; i < grid.getColumns(); i++) {
            for (int j = 0; j < grid.getRows(); j++) {
                getMapPointsBeta()[i][j] = 0;
            }
        }
        
        resourceHUD = new HUD(new Point(10, 300), new Dimension(200, 150));
    
        healthStatusProvider = new StatusProvider("Health", 90, 100);
        health = new StatusBar(new Point(10, 10), new Dimension(100, 20), healthStatusProvider);
        
        oxygenStatusProvider = new StatusProvider("Health", 900, 1200);
        oxygen = new StatusBar(new Point(10, 40), new Dimension(100, 20), oxygenStatusProvider);
        
        resourceHUD.addComponent(health);
        resourceHUD.addComponent(oxygen);
        
        
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="timerTaskHandler">
    @Override
    public void timerTaskHandler() {
        
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="keyPressedHandler">
    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_S) {
            move(e);
        } else if (e.getKeyCode() == KeyEvent.VK_B) {
            objects.add(new SpaceObject(SpaceObjectType.T_PLANET, new Point(2, 2), this));
            objects.add(new SpaceObject(SpaceObjectType.G_GIANT, new Point(5, 4), this));
        } else if (e.getKeyCode() == KeyEvent.VK_1) {
            healthStatusProvider.changeStatus(1);
        } else if (e.getKeyCode() == KeyEvent.VK_2) {
            healthStatusProvider.changeStatus(-1);
        } else if (e.getKeyCode() == KeyEvent.VK_3) {
            oxygenStatusProvider.changeStatus(33);
        } else if (e.getKeyCode() == KeyEvent.VK_4) {
            oxygenStatusProvider.changeStatus(-55);
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="keyReleasedHandler">
    @Override
    public void keyReleasedHandler(KeyEvent e) {
//        if (e.getKeyCode() == KeyEvent.VK_A) {
//            human_bean.setLocation(new Point(human_bean.getLocation().x - 1, human_bean.getLocation().y));
//            updateScannedArea();
//        } else if (e.getKeyCode() == KeyEvent.VK_W) {
//            human_bean.setLocation(new Point(human_bean.getLocation().x, human_bean.getLocation().y - 1));
//            updateScannedArea();
//        } else if (e.getKeyCode() == KeyEvent.VK_D) {
//            human_bean.setLocation(new Point(human_bean.getLocation().x + 1, human_bean.getLocation().y));
//            updateScannedArea();
//        } else if (e.getKeyCode() == KeyEvent.VK_S) {
//            human_bean.setLocation(new Point(human_bean.getLocation().x, human_bean.getLocation().y + 1));
//            updateScannedArea();
//        } else if (e.getKeyCode() == KeyEvent.VK_B) {
//            objects.add(new SpaceObject(SpaceObjectType.T_PLANET, new Point(2, 2), this));
//            objects.add(new SpaceObject(SpaceObjectType.G_GIANT, new Point(5, 4), this));
//        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="environmentMouseClicked">
    @Override
    public void environmentMouseClicked(MouseEvent e) {
        
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="paintEnvironment">
    @Override
    public void paintEnvironment(Graphics graphics) {
        //<editor-fold defaultstate="collapsed" desc="Antialias">
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//</editor-fold>
        for (Point mapPoint : mapPoints) {
            Point topLeft = grid.getCellSystemCoordinate(mapPoint);
            if (visiblePoints[mapPoint.getLocation().x][mapPoint.getLocation().y] == 1) {
                graphics.setColor(Color.BLACK);
            } else {
                graphics.setColor(Color.GRAY);
            }
            graphics.fillRect(topLeft.x, topLeft.y, grid.getCellWidth(), grid.getCellHeight());
        }
        for (SpaceObject object : getObjects()) {
            if (visiblePoints[object.getLocation().x][object.getLocation().y] == 1) {
                object.paintObject(graphics);
            }
        }
        if (human_bean != null/** && human_bean.getScannedLocations() != null*/) {
            human_bean.paint(graphics);
//            human_bean.drawScanned(graphics);
        }
    
        if (resourceHUD != null){
            resourceHUD.paint(graphics);
        }
    
    }
//</editor-fold>
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Fields">
    private Grid grid;
    private ArrayList<Point> mapPoints = new ArrayList<>();
    private ArrayList<SpaceObject> objects = new ArrayList<>();
    private int[][] visiblePoints = new int[grid.getColumns()][grid.getRows()];
    private Character human_bean;
    
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="MapDrawDataIntf">
    /**
     * @return the gridLocations
     */
    @Override
    public ArrayList<Point> getGridLocations() {
        return mapPoints;
    }

    @Override
    public int getCellHeight() {
        return grid.getCellHeight();
    }

    @Override
    public int getCellWidth() {
        return grid.getCellWidth();
    }

    @Override
    public Point getCellSystemCoordinate(Point cellLocation) {
        return grid.getCellSystemCoordinate(cellLocation);
    }

    @Override
    public int getColumns() {
        return grid.getColumns();
    }

    @Override
    public int getRows() {
        return grid.getRows();
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Setters/Getters">
    /**
     * @return the gridPoints
     */
    public ArrayList<SpaceObject> getGridPoints() {
        return getObjects();
    }
    
    /**
     * @param gridLocations the gridLocations to set
     */
    public void setGridLocations(ArrayList<Point> gridLocations) {
        this.mapPoints = gridLocations;
    }
    
    /**
     * @param gridPoints the gridPoints to set
     */
    public void setGridPoints(ArrayList<SpaceObject> gridPoints) {
        this.setObjects(gridPoints);
    }
    
    /**
     * @return the objects
     */
    public ArrayList<SpaceObject> getObjects() {
        return objects;
    }
    
    /**
     * @param objects the objects to set
     */
    public void setObjects(ArrayList<SpaceObject> objects) {
        this.objects = objects;
    }
    
    /**
     * @return the mapPointsBeta
     */
    public int[][] getMapPointsBeta() {
        return visiblePoints;
    }

    /**
     * @param mapPointsBeta the mapPointsBeta to set
     */
    public void setMapPointsBeta(int[][] mapPointsBeta) {
        this.visiblePoints = mapPointsBeta;
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Other Methods">
    public void updateScannedArea() {
        for (Point revealedLocation : human_bean.getScannedLocations()) {
            if (mapPoints.contains(revealedLocation)) {
                visiblePoints[revealedLocation.x][revealedLocation.y] = 1;
            }
        }
    }
    
    private void move(KeyEvent e) {
        human_bean.move(e);
        updateScannedArea();
    }
//</editor-fold>

}
