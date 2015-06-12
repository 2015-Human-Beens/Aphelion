/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aphelion;

import environment.Environment;
import hud.CombatHUD;
import hud.FuelHUD;
import hud.HUD;
import hud.HUDState;
import hud.InventoryHUD;
import hud.MouseEventListenerIntf;
import hud.StatusProvider;
import hud.StatusProviderIntf;
import items.InventoryItem;
import items.MapItem;
import items.Weapon;
import items.WeaponAttack;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import map.Item;
import map.Map;
import tilesInfrastructure.Overlay;
import tilesInfrastructure.SysMapVisualiser;
import tilesInfrastructure.SysTileProviderIntf;
import tilesInfrastructure.SystemTexture;
import tilesInfrastructure.TerrainTypeIntf;
import tilesInfrastructure.Texture;
import tilesInfrastructure.TileMap;
import tilesInfrastructure.TileMapVisualiser;
import tilesInfrastructure.TileProviderIntf;

/**
 *
 * @author Benjamin
 */
class AphelionEnvironment extends Environment implements DrawDataIntf, TileProviderIntf, TerrainTypeIntf,
        VisibilityProviderIntf, CharacterInfoProvIntf, ImprovementDataIntf, SystemInterface, SysTileProviderIntf {

    //<editor-fold defaultstate="collapsed" desc="initializeEnvironment">
    @Override
    public void initializeEnvironment() {
        human_bean = new Character("Go-zirra");
        nonPlayerCharacter = new Character("Nuck Chorris");
        mapTexture = new Texture();
        sysTexture = new SystemTexture();
        overlay = new Overlay();

//        solarSystem = new SolarSystem(null, new Dimension(CELL_SIDE, CELL_SIDE), getSpaceMap(), this, new SysMapVisualiser(this, this));
        solarSystem = createSolarSystem();

        solarSystem.addPlanetTileMap();

        currentTileMap = solarSystem.getPlanetMaps().get(0);
        currentSystem = solarSystem;

        mapVisibility = new MapVisibility(this, this, this);
        systemVisibility = new SystemVisibility(this, this, this);

        mouseEventListeners = new ArrayList<>();

        soundPlayer = new AphelionSoundPlayer();
        soundPlayer.play(AphelionSoundPlayer.DARK_TIMES);

        ArrayList<InventoryItem> mapItemInventory = new ArrayList<>();
        mapItemInventory.add(Weapon.createWeapon(Weapon.TYPE_TD_PISTOL));

        MapItem mapItem = new MapItem(mapItemInventory, new Point(5, 5));

        currentTileMap = solarSystem.getPlanetMaps().get(0);
        currentTileMap.addMapItem(mapItem);

        pcHealthStatusProvider = new StatusProvider("Health", 90, 100);
        npcHealthStatusProvider = new StatusProvider("Health", 90, 100);
        oxygenStatusProvider = new StatusProvider("Oxygen", 900, 1200);
        fuelStatusProvider = new StatusProvider("fuel", 1200, 1200);

        human_bean.setDrawData(this);
        human_bean.setFuelStatusProvider(fuelStatusProvider);
        human_bean.setHealthStatusProvider(pcHealthStatusProvider);

        huds = new ArrayList<>();
        mouseEventListeners = new ArrayList<>();

//        resourceHUD = new ResourceHUD(new Point(300, 605), new Dimension(1135, 250),
//            new HUDState(true, new Point(300, 605), new Point(300, 855)),
//            fuelStatusProvider); //Vertical
//        textBoxHUD = new TextBoxHUD(new Point(0, 0), new Dimension(300, 855),
//                new HUDState(true, new Point(0, 0), new Point(-300, 0))); //Horizontal
        combatHUD = new CombatHUD(new Point(1500, -200), new Dimension(400, 400),
                new HUDState(true, new Point(400, 100), new Point(1500, -200)),
                human_bean, nonPlayerCharacter, pcHealthStatusProvider, npcHealthStatusProvider); //Horizontal
        inventoryHUD = new InventoryHUD(new Point(1500, -200), new Dimension(400, 400),
                new HUDState(true, new Point(400, 100), new Point((int) screenWidth, (int) screenHeight)));
        fuelHUD = new FuelHUD(new Point(1920, 800), new Dimension(125, 20),
                new HUDState(true, new Point(1795, 800), new Point(1920, 800)), fuelStatusProvider);

//        addHUD(resourceHUD);
//        addHUD(textBoxHUD);
        addHUD(fuelHUD);

        addHUD(inventoryHUD);
        addHUD(combatHUD);
    }
    //</editor-fold>

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
    
    //<editor-fold defaultstate="collapsed" desc="AbstractMethods">
    //<editor-fold defaultstate="collapsed" desc="environmentMouseClicked">
    private ArrayList<MouseEventListenerIntf> mouseEventListeners;

    @Override
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
    
    //<editor-fold defaultstate="collapsed" desc="timerTaskHandler">
    @Override
    public void timerTaskHandler() {

    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="keyPressedHandler">
    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_W
                || e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_S) {
            move(e);
        } else if (e.getKeyCode() == KeyEvent.VK_B) {
            switch (zoomLevel) {
                case MAP_ZOOM:
                    getCurrentMap().addItem(new Scanner(human_bean.getLocation()));
                    break;
                case SYSTEM_ZOOM:
                    getCurrentSystem().addItem(new Scanner(human_bean.getLocation()));
                    break;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_1) {
            pcHealthStatusProvider.changeStatus(1);
        } else if (e.getKeyCode() == KeyEvent.VK_2) {
            pcHealthStatusProvider.changeStatus(-1);
        } else if (e.getKeyCode() == KeyEvent.VK_3) {
            oxygenStatusProvider.changeStatus(33);
        } else if (e.getKeyCode() == KeyEvent.VK_4) {
            oxygenStatusProvider.changeStatus(-55);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            switch (zoomLevel) {
                case MAP_ZOOM:
                    currentTileMap.setPosition(new Point(currentTileMap.getPosition().x, currentTileMap.getPosition().y - currentTileMap.getCellHeight()));
                    break;
                case SYSTEM_ZOOM:
                    solarSystem.setPosition(new Point(solarSystem.getPosition().x, solarSystem.getPosition().y - solarSystem.getCellHeight()));
                    break;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            switch (zoomLevel) {
                case MAP_ZOOM:
                    currentTileMap.setPosition(new Point(currentTileMap.getPosition().x, currentTileMap.getPosition().y + currentTileMap.getCellWidth()));
                    break;
                case SYSTEM_ZOOM:
                    solarSystem.setPosition(new Point(solarSystem.getPosition().x, solarSystem.getPosition().y + solarSystem.getCellWidth()));
                    break;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            switch (zoomLevel) {
                case MAP_ZOOM:
                    currentTileMap.setPosition(new Point(currentTileMap.getPosition().x - currentTileMap.getCellWidth(), currentTileMap.getPosition().y));
                    break;
                case SYSTEM_ZOOM:
                    solarSystem.setPosition(new Point(solarSystem.getPosition().x - solarSystem.getCellWidth(), solarSystem.getPosition().y));
                    break;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            switch (zoomLevel) {
                case MAP_ZOOM:
                    currentTileMap.setPosition(new Point(currentTileMap.getPosition().x + currentTileMap.getCellWidth(), currentTileMap.getPosition().y));
                    break;
                case SYSTEM_ZOOM:
                    solarSystem.setPosition(new Point(solarSystem.getPosition().x + solarSystem.getCellWidth(), solarSystem.getPosition().y));
                    break;
            }

        } else if (e.getKeyCode() == KeyEvent.VK_I) {
            System.out.printf("screenWidth = %d\n", screenWidth);
            System.out.printf("screenHeight = %d\n", screenHeight);
            toggleHUDState(inventoryHUD);
            //CALLS UP EVERYTHING FROM WEAPON
            for (InventoryItem item : human_bean.getInventory()) {
                System.out.printf("\nThis inventory item is an instanceof %s\n", item.getItemType());
                if (human_bean.getInventory().get(0) instanceof Weapon) {
                    Weapon weapon = ((Weapon) item);
                    System.out.printf("The weapon's name is %s\n", weapon.getWeaponType());
                    System.out.printf("Its attackType is %s\n", weapon.getAttackType());
                    System.out.println("The possible attacks for this weapon are listed below");
                    for (WeaponAttack attack : weapon.getAttacks()) {
                        System.out.printf("    %s(%s). \n        The attack consumes %d rounds from the magazine and uses %.1f energy."
                                + "\n        It has an effective range from %.1fm to %.1fm"
                                + "\n        The base damage is %d hp, and the base hit rate (accuracy) is %.2f\n",
                                attack.getAttackName(), attack.getAttackNickname(), attack.getaPT(), attack.getEnergyUse(), attack.getMinRange(),
                                attack.getMaxRange(), attack.getBaseDamage(), attack.getBaseAccuracy());
                    }
                }
            }
        } else if (e.getKeyCode() == KeyEvent.VK_C) {
            toggleHUDState(combatHUD);
        } else if (e.getKeyCode() == KeyEvent.VK_Z) {
            switchZoom();
        }
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="keyReleasedHandler">
    @Override
    public void keyReleasedHandler(KeyEvent e) {

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
        switch (zoomLevel) {
            case SYSTEM_ZOOM:
                if (solarSystem != null) {
                    solarSystem.drawMap(graphics);
                }
                if (human_bean != null) {
                    human_bean.paintShip(graphics);
                }
                break;
            case MAP_ZOOM:
                if (solarSystem != null) {
                    currentTileMap.drawMap(graphics);
                }
                if (human_bean != null) {
                    human_bean.paintChar(graphics);
                }
                break;
        }
        if (huds != null) {
            huds.stream().forEach((hud) -> {
                hud.paint(graphics);
            });
        }
    }
//</editor-fold>
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Fields">
    AphelionSoundPlayer soundPlayer;

    private HUD fuelHUD;
    private HUD actionBoxHUD;
    private HUD combatHUD;
    private HUD inventoryHUD;

    private HUDState state;

    private StatusProviderIntf pcHealthStatusProvider;
    private StatusProviderIntf npcHealthStatusProvider;
    private StatusProviderIntf oxygenStatusProvider;
    private StatusProviderIntf fuelStatusProvider;

    private final String MAP_ZOOM = "Map Zoom";
    private final String SYSTEM_ZOOM = "System Zoom";
    private String zoomLevel = SYSTEM_ZOOM;
    private final int CELL_SIDE = 16;

    private SolarSystem solarSystem;

    private SolarSystem currentSystem;
    private TileMap currentTileMap;

    private Texture mapTexture;
    private SystemTexture sysTexture;
    private Overlay overlay;
    private MapVisibility mapVisibility;
    private SystemVisibility systemVisibility;

    private Character human_bean;
    private Character nonPlayerCharacter;

    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int screenWidth = (int) screenSize.getWidth();
    private int screenHeight = (int) screenSize.getHeight();
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Interfaces">
    //<editor-fold defaultstate="collapsed" desc="DrawDataIntf">
    @Override
    public int getCellHeight() {
        switch (zoomLevel) {
            case SYSTEM_ZOOM:
                return (currentSystem != null) ? currentSystem.getCellHeight() : 0;
            case MAP_ZOOM:
            default:
                return (currentTileMap != null) ? currentTileMap.getCellHeight() : 0;
        }
    }

    @Override
    public int getCellWidth() {
        switch (zoomLevel) {
            case SYSTEM_ZOOM:
                return (currentSystem != null) ? currentSystem.getCellWidth() : 0;
            case MAP_ZOOM:
            default:
                return (currentTileMap != null) ? currentTileMap.getCellWidth() : 0;
        }
    }

    @Override
    public Point getCellSystemCoordinate(Point cellLocation) {
        switch (zoomLevel) {
            case SYSTEM_ZOOM:
                return (currentSystem != null) ? currentSystem.getCellSystemCoordinate(cellLocation) : null;
            case MAP_ZOOM:
            default:
                return (currentTileMap != null) ? currentTileMap.getCellSystemCoordinate(cellLocation) : null;
        }
    }

    @Override
    public int getColumns() {
        switch (zoomLevel) {
            case SYSTEM_ZOOM:
                return currentSystem.getGrid().getColumns();
            case MAP_ZOOM:
            default:
                return currentTileMap.getGrid().getColumns();
        }
    }

    @Override
    public int getRows() {
        switch (zoomLevel) {
            case SYSTEM_ZOOM:
                return currentSystem.getGrid().getRows();
            case MAP_ZOOM:
            default:
                return currentTileMap.getGrid().getRows();
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="TileProviderIntf">
    @Override
    public Image getTileTexture(Integer iD) {
        return mapTexture.getMapTexture(iD);
    }

    @Override
    public Image getTileOverlay(Integer iD) {
        return overlay.getOverlay(iD);
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="SysTileProviderIntf">
    @Override
    public Image getSysTexture(Integer iD) {
        return sysTexture.getSysTexture(iD);
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="TerrainTypeIntf">
    @Override
    public String getTerrainType(Integer iD) {
        return mapTexture.getTerrainType((iD / 100) % 100);
    }

    @Override
    public String getOverlayType(Integer iD) {
        return overlay.getOverlayType(iD % 100);
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="VisibilityProviderIntf">
    @Override
    public int[][] getVisibilityArray() {
        switch (zoomLevel) {
            case SYSTEM_ZOOM:
                return (systemVisibility != null) ? systemVisibility.getVisibilityArray() : null;
            case MAP_ZOOM:
            default:
                return (mapVisibility != null) ? mapVisibility.getVisibilityArray() : null;
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="CharacterInfoProvIntf">
    @Override
    public Point getCharacterLocation() {
        return human_bean.getLocation();
    }

    @Override
    public int getCharacterScanRadius() {
        return human_bean.getScanRadius();
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="ImprovementDataIntf">
    /**
     * items only include scanners right now
     */
    @Override
    public ArrayList<Item> getImprovements() {
        switch (zoomLevel) {
            case SYSTEM_ZOOM:
                return (currentSystem != null) ? currentSystem.getItems() : null;
            case MAP_ZOOM:
            default:
                return (currentTileMap != null) ? currentTileMap.getItems() : null;
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="SystemInterface">
    @Override
    public TileMap createPlanetMap(SolarSystem system, Point location) {
        TileMap tileMap = new TileMap(null, new Dimension(CELL_SIDE, CELL_SIDE), Generate.randomContinents(), new TileMapVisualiser(this, this));
        tileMap.setSystemLocation(location);
        return tileMap;
    }
//</editor-fold>
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setters/Getters">
    /**
     * @return the currentTileMap
     */
    public TileMap getCurrentMap() {
        return currentTileMap;
    }

    /**
     * @param currentMap the currentTileMap to set
     */
    public void setCurrentMap(TileMap currentMap) {
        this.currentTileMap = currentMap;
    }

    /**
     * @return the currentSystem
     */
    public SolarSystem getCurrentSystem() {
        return currentSystem;
    }

    /**
     * @param currentSystem the currentSystem to set
     */
    public void setCurrentSystem(SolarSystem currentSystem) {
        this.currentSystem = currentSystem;
    }

    public ArrayList<MapItem> getCurrentMapItems() {
        ArrayList<MapItem> mapFeatures = new ArrayList<>();
        for (MapItem mapItem : getCurrentMap().getMapFeatures()) {
            mapFeatures.add(mapItem);
        }
        return mapFeatures;
    }

    //System Features = new array list?
    public ArrayList<Item> getCurrentSystemItems() {
        ArrayList<Item> mapFeatures = new ArrayList<>();
        for (Item item : getCurrentSystem().getItems()) {
            mapFeatures.add(item);
        }
        return mapFeatures;
    }

    /**
     * @return the zoomLevel
     */
    public String getZoomLevel() {
        return zoomLevel;
    }

    /**
     * @param zoomLevel the zoomLevel to set
     */
    public void setZoomLevel(String zoomLevel) {
        this.zoomLevel = zoomLevel;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Character Movement">
    private void move(KeyEvent e) {
        Point proposedPoint = new Point();
        Point CURRENT_LOCATION = human_bean.getLocation();

        if (e.getKeyCode() == KeyEvent.VK_A) {
            proposedPoint = new Point(CURRENT_LOCATION.x - 1, CURRENT_LOCATION.y);
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            proposedPoint = new Point(CURRENT_LOCATION.x, CURRENT_LOCATION.y - 1);
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            proposedPoint = new Point(CURRENT_LOCATION.x + 1, CURRENT_LOCATION.y);
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            proposedPoint = new Point(CURRENT_LOCATION.x, CURRENT_LOCATION.y + 1);
        }
        validateLocation(proposedPoint);
    }

    public void validateLocation(Point proposedPoint) {
        Point newLoc = human_bean.getLocation();
        if (proposedPoint.x < 0 || proposedPoint.x > currentTileMap.getMap().length
                || proposedPoint.y < 0 || proposedPoint.y > currentTileMap.getMap()[0].length) {
        } //            else if (true) {
        //            //more conditions for non-movement
        //        } 
        else {
            fuelStatusProvider.changeStatus(-5);
            int data = currentTileMap.getMap()[proposedPoint.x][proposedPoint.y];

            System.out.printf("The proposed location's terrain type is %s\n", getTerrainType(data));

//            if (getTerrainType(data).equals("WATER")) {
//                newLoc = human_bean.getLocation();
//            } else {
            newLoc = proposedPoint;
//            }
            switch (zoomLevel) {
                case MAP_ZOOM:
                    int mapItemNo = 0;
                    for (MapItem mapItem : getCurrentMapItems()) {
                        if (newLoc.equals(mapItem.getLocation())) {
                            ArrayList<InventoryItem> inventory = mapItem.getInventory();
                            for (InventoryItem item : inventory) {
                                human_bean.addToInventory(item);
                            }
                            getCurrentMap().getMapFeatures().remove(mapItemNo);

                        }
                        mapItemNo++;
                    }
                    break;
                case SYSTEM_ZOOM:
                    int systemItemNo = 0;
                    for (Item item : getCurrentSystemItems()) {
                        if (newLoc.equals(item.getLocation())) {
                            
                        }
                    }
            }

        }
        human_bean.setLocation(newLoc);
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Other Methods">
    private void toggleHUDState(HUD hud) {
        if (hud.getState().isOpen()) {
            hud.close();
        } else {
            hud.open();
        }
    }
//</editor-fold>

    private void switchZoom() {
        zoomLevel = (this.zoomLevel.equals(MAP_ZOOM)) ? SYSTEM_ZOOM : MAP_ZOOM;
    }

    private SolarSystem createSolarSystem() {
        SolarSystem system = new SolarSystem(null, new Dimension(CELL_SIDE, CELL_SIDE), Generate.solarSystem(), this, new SysMapVisualiser(this, this));
        int numberPlanets = (int) (Math.random() * 6) + 6;
        ArrayList<Point> planetLocations = new ArrayList<>();
        for (int i = 0; i < numberPlanets; i++) {
            Point point = Generate.randomPointIn(system.getSolarMap());
            planetLocations.add(point);
        }
        return system;
    }

}
