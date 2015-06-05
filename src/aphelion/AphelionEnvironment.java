/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aphelion;

import environment.Environment;
import hud.CombatHUD;
import hud.HUD;
import hud.HUDState;
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
class AphelionEnvironment extends Environment implements MapDrawDataIntf, TileProviderIntf, TerrainTypeIntf,
        VisibilityProviderIntf, CharacterInfoProvIntf, MapImprovementDataIntf {

//<editor-fold defaultstate="collapsed" desc="Fields">
    AphelionSoundPlayer soundPlayer;
    
    private HUD resourceHUD;
    private HUD statusHUD;
    private HUD mainMenuHUD;
    private HUD mapHUD;
    private HUD actionBoxHUD;
    private HUD combatHUD;
    
    private HUDState state;
    
    private StatusProviderIntf healthStatusProvider;
    private StatusProviderIntf oxygenStatusProvider;
    private StatusProviderIntf fuelStatusProvider;

    private ArrayList<TileMap> maps;

    private TileMap currentMap;

    private Texture texture;
    private Overlay overlay;
    private Visibility visibility;

    private ArrayList<Point> mapPoints = new ArrayList<>();
    private Character human_bean;
    private Character nonPlayerCharacter;
    private Character playerCharacter;

    
    //</editor-fold>

    
//<editor-fold defaultstate="collapsed" desc="AbstractMethods">

//<editor-fold defaultstate="collapsed" desc="initializeEnvironment">
    @Override
    public void initializeEnvironment() {
        playerCharacter = new Character("Go-zirra");
        nonPlayerCharacter = new Character("Nuck Chorris");
        human_bean = new Character("The Hero");
        maps = new ArrayList<>();

        texture = new Texture();
        overlay = new Overlay();

        maps.add(new TileMap(null, new Dimension(16, 16), randomContinents(), new TileMapVisualizer(this, this)));
        maps.add(new TileMap(null, new Dimension(16, 16), randomContinents(), new TileMapVisualizer(this, this)));

        currentMap = maps.get(0);

        visibility = new Visibility();
        visibility.setMapDrawData(this);
        visibility.setCharacterInfo(this);
        visibility.setMapImprovementData(this);

        mouseEventListeners = new ArrayList<>();

        soundPlayer = new AphelionSoundPlayer();
        soundPlayer.play(AphelionSoundPlayer.DARK_TIMES);
        
        human_bean.getInventory().add(Weapon.createWeapon(Weapon.TYPE_TD_PISTOL));
        ArrayList<InventoryItem> mapItemInventoryA = new ArrayList<>();
        mapItemInventoryA.add(Weapon.createWeapon(Weapon.TYPE_ASSAULT_RIFLE));
        
        MapItem mapItemA = new MapItem(mapItemInventoryA, new Point(5, 5));
        maps.get(0).addMapItem(mapItemA);

        ArrayList<InventoryItem> mapItemInventory = new ArrayList<>();
        mapItemInventory.add(Weapon.createWeapon(Weapon.TYPE_ASSAULT_RIFLE));

        healthStatusProvider = new StatusProvider("Health", 90, 100);
        oxygenStatusProvider = new StatusProvider("Oxygen", 900, 1200);
        fuelStatusProvider = new StatusProvider("fuel", 1200, 1200);
        
        human_bean.setMapDrawData(this);
        human_bean.setFuelStatusProvider(fuelStatusProvider);
        human_bean.setHealthStatusProvider(healthStatusProvider);
        
        
        huds = new ArrayList<>();
        mouseEventListeners = new ArrayList<>();
        
//        resourceHUD = new ResourceHUD(new Point(300, 605), new Dimension(1135, 250),
//            new HUDState(true, new Point(300, 605), new Point(300, 855)),
//            fuelStatusProvider); //Vertical
//        textBoxHUD = new TextBoxHUD(new Point(0, 0), new Dimension(300, 855),
//                new HUDState(true, new Point(0, 0), new Point(-300, 0))); //Horizontal
        combatHUD = new CombatHUD(new Point(400, 100), new Dimension(400, 400),
                new HUDState(true, new Point(400, 100), new Point(1500, -200)), (StatusProvider) fuelStatusProvider,
                playerCharacter, nonPlayerCharacter); //Horizontal
        
//        addHUD(resourceHUD);
//        addHUD(textBoxHUD);
        addHUD(combatHUD);
        
        MapItem mapItem = new MapItem(mapItemInventory, new Point(5, 5));
        maps.get(0).addMapItem(mapItem);

        human_bean.getInventory().add(Weapon.createWeapon(Weapon.TYPE_ASSAULT_RIFLE));
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
            getCurrentMap().addItem(new Scanner(human_bean.getLocation()));
        } else if (e.getKeyCode() == KeyEvent.VK_1) {
            healthStatusProvider.changeStatus(1);
        } else if (e.getKeyCode() == KeyEvent.VK_2) {
            healthStatusProvider.changeStatus(-1);
        } else if (e.getKeyCode() == KeyEvent.VK_3) {
            oxygenStatusProvider.changeStatus(33);
        } else if (e.getKeyCode() == KeyEvent.VK_4) {
            oxygenStatusProvider.changeStatus(-55);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            currentMap.setPosition(new Point(currentMap.getPosition().x, currentMap.getPosition().y + currentMap.getCellHeight()));
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            currentMap.setPosition(new Point(currentMap.getPosition().x, currentMap.getPosition().y - currentMap.getCellHeight()));
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            currentMap.setPosition(new Point(currentMap.getPosition().x + currentMap.getCellWidth(), currentMap.getPosition().y));
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            currentMap.setPosition(new Point(currentMap.getPosition().x - currentMap.getCellWidth(), currentMap.getPosition().y));
//        } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD1) {
//            setCurrentMap(maps.get(0));
//        } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD2) {
//            setCurrentMap(maps.get(1));
        } else if (e.getKeyCode() == KeyEvent.VK_I) {
            System.out.println("");
            InventoryItem item = human_bean.getInventory().get(0);
            System.out.printf("This inventory item is an instanceof %s\n", item.getItemType());
            if (human_bean.getInventory().get(0) instanceof Weapon){
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
            System.out.println("");
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

        if (maps != null) {
            currentMap.drawMap(graphics);
        }
        if (human_bean != null) {
            human_bean.paint(graphics);
        }

        if (huds != null) {
            huds.stream().forEach((hud) -> {
                hud.paint(graphics);
            });
        }
    }
//</editor-fold>
    
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
        return currentMap.getCellHeight();
    }

    @Override
    public int getCellWidth() {
        return currentMap.getCellWidth();
    }

    @Override
    public Point getCellSystemCoordinate(Point cellLocation) {
        return currentMap.getCellSystemCoordinate(cellLocation);
    }

    @Override
    public int getColumns() {
        return currentMap.getGrid().getColumns();
    }

    @Override
    public int getRows() {
        return currentMap.getGrid().getRows();
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
        return texture.getTerrainType((iD / 100) % 100);
    }

    @Override
    public String getOverlayType(Integer iD) {
        return overlay.getOverlayType(iD % 100);
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
    public Point getCharacterLocation() {
        return human_bean.getLocation();
    }

    @Override
    public int getCharacterScanRadius() {
        return human_bean.getScanRadius();
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="MapImprovementDataIntf">
    /**
     * items only include scanners right now
     */
    public ArrayList<Item> getImprovements() {
        if (currentMap != null) {
            return currentMap.getItems();
        } else {
            return null;
        }
    }
//</editor-fold>
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Setters/Getters">
    /**
     * @param gridLocations the gridLocations to set
     */
    public void setGridLocations(ArrayList<Point> gridLocations) {
        this.mapPoints = gridLocations;
    }

    /**
     * @return the currentMap
     */
    public TileMap getCurrentMap() {
        return currentMap;
    }

    /**
     * @param currentMap the currentMap to set
     */
    public void setCurrentMap(TileMap currentMap) {
        this.currentMap = currentMap;
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
        Point newLoc;

        if (proposedPoint.x < 0 || proposedPoint.x > currentMap.getMap().length
                || proposedPoint.y < 0 || proposedPoint.y > currentMap.getMap()[0].length) {
            newLoc = human_bean.getLocation();
        } else {
            int data = currentMap.getMap()[proposedPoint.x][proposedPoint.y];

            System.out.printf("The proposed location's terrain type is %s\n", getTerrainType(data));
//
//            if (getTerrainType(data).equals("WATER")) {
//                newLoc = human_bean.getLocation();
//            } else {
                newLoc = proposedPoint;
//            }
        }
        human_bean.setLocation(newLoc);
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Other Methods">
    public static int[][] randomContinents() {
        int BACKGROUND_TERRAIN = 1000;
        int CONTINENT_TERRAIN = 200;
        int BEACH_TERRAIN = 100;

        int[][] array = new int[120][70];
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
                    if ((newX >= 0) && (newX < array.length) && (newY >= 0) && (newY < array[0].length)) {
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
