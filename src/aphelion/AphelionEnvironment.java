/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aphelion;

import environment.Environment;
import grid.Grid;
import hud.HUD;
import hud.HUDState;
import hud.MouseEventListenerIntf;
import hud.ResourceHUD;
import hud.StatusBar;
import hud.StatusProvider;
import hud.StatusProviderIntf;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import map.Item;
import tilesInfrastructure.Overlay;
import tilesInfrastructure.TerrainTypeIntf;
import tilesInfrastructure.Texture;
import tilesInfrastructure.TileMap;
import tilesInfrastructure.TileMapVisualizer;
import tilesInfrastructure.TileProviderIntf;

/**
 *
 * @author Benjamin
 */
class AphelionEnvironment extends Environment implements MapDrawDataIntf, TileProviderIntf, 
        TerrainTypeIntf, VisibilityProviderIntf, CharacterInfoProvIntf, MapImprovementDataIntf {

    //<editor-fold defaultstate="collapsed" desc="Agenda">

    /**
     * V1.0 Alpha (27/03/15) (Finish the following by 03/04/15) - implement the
     * following: - *Done* (Without nice icon) Fuel (A nice icon would be cool)
     * - Starting Asteroid with complimentary map (Need graphics designer) - Map
     * Portals (Enter a new map when you step on a planet in the system Map) -
     * change: - *Done* visiblePoints from ArrayList to mapPoints[][] 2D Array
     * 
     */
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="AphelionEnvironment">

    public AphelionEnvironment() {
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="AbstractMethods">
    //<editor-fold defaultstate="collapsed" desc="initializeEnvironment">
    @Override
    public void initializeEnvironment() {
        human_bean = new Character();
        human_bean.setMapDrawData(this);

        texture = new Texture();
        overlay = new Overlay();

        tileMap = new TileMap(null, new Dimension(16, 16), randomContinents());
        tileMap.setMapVisualizer(new TileMapVisualizer(this, this));
        
        visibility = new Visibility();
        visibility.setMapDrawData(this);
        visibility.setCharacterInfo(this);
        visibility.setMapImprovementData(this);
        
        healthStatusProvider = new StatusProvider("Health", 90, 100);
        oxygenStatusProvider = new StatusProvider("Health", 900, 1200);

        resourceHUDBeta = new ResourceHUD(new Point(0, 100), new Dimension(200, 150), 
                new HUDState(true, new Point(0, 100), new Point(-200, 100)),
                healthStatusProvider, oxygenStatusProvider);

        resourceHUD = new ResourceHUD(new Point(0, 300), new Dimension(200, 150),
                new HUDState(true, new Point(0, 300), new Point(-200, 300)),
                healthStatusProvider, oxygenStatusProvider);

        mouseEventListeners = new ArrayList<>();

        addHUD(resourceHUDBeta);
        addHUD(resourceHUD);
        
    }

    //<editor-fold defaultstate="collapsed" desc="HUDs">
    private ArrayList<HUD> huds;

    private void addHUD(HUD hud) {
        if (huds == null) {
            huds = new ArrayList<>();
        }
        huds.add(hud);
        registerMouseEventListener(hud.getMouseEventListeners());
    }

    private void removeHUD(HUD hud) {
        if (huds != null) {
            huds.remove(hud);
        }
        deregisterMouseEventListeners(hud.getMouseEventListeners());
    }

//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="environmentMouseClicked">
    private ArrayList<MouseEventListenerIntf> mouseEventListeners;

    
        
    public void environmentMouseClicked(MouseEvent e) {
        System.out.println("Environment ME");

        mouseEventListeners.stream().forEach((listener) -> {
            listener.onMouseClick(e);
        });
    }

    private void registerMouseEventListener(ArrayList<MouseEventListenerIntf> mouseEventListeners) {
        if (this.mouseEventListeners == null) {
            this.mouseEventListeners = new ArrayList<>();
        }
        this.mouseEventListeners.addAll(mouseEventListeners);
    }

    private void deregisterMouseEventListeners(ArrayList<MouseEventListenerIntf> mouseEventListeners) {
        if (this.mouseEventListeners != null) {
            this.mouseEventListeners.removeAll(mouseEventListeners);
        }
    }

//</editor-fold>

//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="timerTaskHandler">
    @Override
    public void timerTaskHandler() {
//        if (resourceHUD != null) {
//            if (resourceHUD.isExtended() && !(resourceHUD.getPositon().x > 0)) {
//                resourceHUD.getPositon().x += 5;
//            } else if (!resourceHUD.isExtended() && !(resourceHUD.getPositon().x < resourceHUD.getRetractedX())) {
//                resourceHUD.getPositon().x -= 5;
//            }
//        }
//        if (resourceHUDBeta != null) {
//            if (resourceHUDBeta.isExtended() && !(resourceHUDBeta.getPositon().x > 0)) {
//                resourceHUDBeta.getPositon().x += 5;
//            } else if (!resourceHUDBeta.isExtended() && !(resourceHUDBeta.getPositon().x < resourceHUDBeta.getRetractedX())) {
//                resourceHUDBeta.getPositon().x -= 5;
//            }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="keyPressedHandler">
    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_W || 
                e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_S) {
            move(e);
        } else if (e.getKeyCode() == KeyEvent.VK_B) {
            tileMap.addItem(new Scanner(human_bean.getLocation()));
        } else if (e.getKeyCode() == KeyEvent.VK_1) {
            healthStatusProvider.changeStatus(1);
        } else if (e.getKeyCode() == KeyEvent.VK_2) {
            healthStatusProvider.changeStatus(-1);
        } else if (e.getKeyCode() == KeyEvent.VK_3) {
            oxygenStatusProvider.changeStatus(33);
        } else if (e.getKeyCode() == KeyEvent.VK_4) {
            oxygenStatusProvider.changeStatus(-55);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            tileMap.setPosition(new Point(tileMap.getPosition().x, tileMap.getPosition().y - tileMap.getCellHeight()));
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            tileMap.setPosition(new Point(tileMap.getPosition().x, tileMap.getPosition().y + tileMap.getCellHeight()));
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            tileMap.setPosition(new Point(tileMap.getPosition().x - tileMap.getCellWidth(), tileMap.getPosition().y));
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            tileMap.setPosition(new Point(tileMap.getPosition().x + tileMap.getCellWidth(), tileMap.getPosition().y));
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

        if (tileMap != null) {
            tileMap.drawMap(graphics);
        }
        if (human_bean != null/**
                 * && human_bean.getScannedLocations() != null
                 */
                ) {
            human_bean.paint(graphics);
//            human_bean.drawScanned(graphics);
        }

        if (huds != null){
            huds.stream().forEach((hud) -> {
                hud.paint(graphics);
            });
        }

    }
//</editor-fold>
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Fields">    
    private HUD resourceHUD;
    private ResourceHUD resourceHUDBeta;

    private StatusBar health;
    private StatusProviderIntf healthStatusProvider;

    private StatusBar oxygen;
    private StatusProviderIntf oxygenStatusProvider;

    private TileMap tileMap;
    private Texture texture;
    private Overlay overlay;
    private Visibility visibility;

    private ArrayList<Point> mapPoints = new ArrayList<>();
    private ArrayList<SpaceObject> objects = new ArrayList<>();
    private int[][] visiblePoints = new int[tileMap.getGrid().getColumns()][tileMap.getGrid().getRows()];
    private Character human_bean;

//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Interfaces">
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
        return tileMap.getCellHeight();
    }

    @Override
    public int getCellWidth() {
        return tileMap.getCellWidth();
    }

    @Override
    public Point getCellSystemCoordinate(Point cellLocation) {
        return tileMap.getCellSystemCoordinate(cellLocation);
    }

    @Override
    public int getColumns() {
        return tileMap.getGrid().getColumns();
    }

    @Override
    public int getRows() {
        return tileMap.getGrid().getRows();
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="TileProviderIntf">
    @Override
    public Image getTileTexture(Integer iD) {
        return texture.getTexture(iD);
    }

    @Override
    public Image getTileOverlay(Integer iD) {
        return overlay.getOverlay(iD);
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="TerrainTypeIntf">
    @Override
    public String getTerrainType(Integer iD) {
        return texture.getTerrainType(iD);
    }

    @Override
    public String getOverlayType(Integer iD) {
        return overlay.getOverlayType(iD);
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="VisibilityProviderIntf">
    @Override
    public int[][] getVisibilityArray() {
        return visibility.getVisibilityArray();
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="CharacterInfoProvIntf">
    @Override
    public Point getCharacterPoint() {
        return human_bean.getLocation();
    }
    
    @Override
    public int getCharacterScanRadius() {
        return human_bean.getScanRadius();
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="MapImprovementDataIntf">
    @Override /** items only include scanners right now*/
    public ArrayList<Item> getImprovements() {
        return tileMap.getItems();
    }
//</editor-fold>
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

    private static int[][] getRandomArray() {
        int[][] array = new int[120][60];
        for (int col = 0; col < array.length; col++) {
            for (int row = 0; row < array[col].length; row++) {
                array[col][row] = 601;
            }
        }
        ArrayList<Point> sparks = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            int x = (int) (Math.random() * (array.length));
            int y = (int) (Math.random() * (array[x].length));
            sparks.add(new Point(x, y));
        }
        for (int k = 0; k < sparks.size(); k++) {

            int radius = (int) ((Math.random() * 6) + 2);
            for (int i = -radius; i <= radius; i++) {
                for (int j = -(radius - Math.abs(i)); j <= radius - Math.abs(i); j++) {
                    int newX = sparks.get(k).x + j;
                    int newY = sparks.get(k).y + i;

                    if ((newX >= 0) && (newX <= array.length - 1) && (newY >= 0) && (newY <= array[0].length - 1)) {
                        array[newX][newY] = 400;
                        if (Math.random() > 0.5) {
                            array[newX][newY] += 100;
                        }
                    }
                }
            }
        }
        return array;
    }

    public static int[][] randomContinents() {
        int BACKGROUND_TERRAIN = 100;
//        int BACKGROUND_TERRAIN = (int) (Math.floor((Math.random() * 2) + 3) * 100);
        int CONTINENT_TERRAIN = 300;
        int BEACH_TERRAIN = 200;

        int[][] array = new int[120][60];
        // Background terrain type
        for (int col = 0; col < array.length; col++) {
            for (int row = 0; row < array[col].length; row++) {
                array[col][row] = BACKGROUND_TERRAIN;
            }
        }
        ArrayList<Point> sparks = new ArrayList<>();
        int continents = (int) (Math.random() * 4) + 2;
        for (int i = 0; i < continents; i++) {
            int x = (int) (Math.random() * (array.length));
            int y = (int) (Math.random() * (array[x].length));
            sparks.add(new Point(x, y));
        }

        for (int k = 0; k < sparks.size(); k++) {
            ArrayList<Point> bumps = new ArrayList<>();
            int radius = (int) ((Math.random() * 9) + 8);
            for (int i = -radius; i <= radius; i++) {
                for (int j = -(radius - Math.abs(i)); j <= radius - Math.abs(i); j++) {
                    int newX = sparks.get(k).x + j;
                    int newY = sparks.get(k).y + i;
                    if ((newX >= 0) && (newX <= array.length - 1) && (newY >= 0) && (newY <= array[0].length - 1)) {
                        array[newX][newY] = CONTINENT_TERRAIN;
                        if (Math.abs(newX - sparks.get(k).x) + Math.abs(newY - sparks.get(k).y) == radius) {
                            array[newX][newY] = BEACH_TERRAIN;
                            if (Math.random() > .76) {
                                bumps.add(new Point(newX, newY));
                            }
                        }
                    }
                }
            }
            int randomBump = (int) (Math.random() * bumps.size() - 1);

            for (int l = 0; l < bumps.size(); l++) {
                int bumpRadius = 0;
                if (bumps.indexOf(l) == randomBump) {
                    bumpRadius = (int) ((Math.random() * 5) + 6);
                } else {
                    bumpRadius = (int) ((Math.random() * 4) + 1);
                }
                for (int i = -bumpRadius; i <= bumpRadius; i++) {
                    for (int j = -(bumpRadius - Math.abs(i)); j <= bumpRadius - Math.abs(i); j++) {
                        int newX = bumps.get(l).x + j;
                        int newY = bumps.get(l).y + i;
                        if ((newX >= 0) && (newX <= array.length - 1) && (newY >= 0) && (newY <= array[0].length - 1)) {
                            array[newX][newY] = CONTINENT_TERRAIN;
                            if (Math.abs(newX - bumps.get(l).x) + Math.abs(newY - bumps.get(l).y) == bumpRadius) {
                                array[newX][newY] = BEACH_TERRAIN;
                            }
                        }
                    }
                }
            }
        }

        return array;
    }
//</editor-fold>

    

}
