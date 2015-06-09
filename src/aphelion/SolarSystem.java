/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aphelion;

import items.MapItem;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import map.Map;
import map.MapVisualizerIntf;
import tilesInfrastructure.TileMap;

/**
 *
 * @author Benjamin
 */
public class SolarSystem extends Map {
    
    public SolarSystem(Image background, Dimension gridCellSize, int[][] solarMap, SystemInterface systemInterface, MapVisualizerIntf mapVisualizer) {
        super(background, gridCellSize, new Dimension(solarMap.length, solarMap[0].length)); 
        setSolarMap(solarMap);
        this.systemInterface = systemInterface; 
        this.setMapVisualizer(mapVisualizer);
    }
    
    @Override
    public void drawMap(Graphics graphics) {
        super.drawMap(graphics); //To change body of generated methods, choose Tools | Templates.
//        if (getMapFeatures() == null) {
//            setMapFeatures(new ArrayList<>());
//        }
//        for (MapItem mapItem : getMapFeatures()) {
//            Point topLeft = super.getCellSystemCoordinate(mapItem.getLocation());
//            graphics.fillOval(topLeft.x, topLeft.y, 16, 16);
//        }
    }
    
    private ArrayList<TileMap> planetMaps;
    private int[][] solarMap;
    private SystemInterface systemInterface;

    /**
     * @return the planetMaps
     */
    public ArrayList<TileMap> getPlanetMaps() {
        return planetMaps;
    }

    /**
     * @param planetMaps the planetMaps to set
     */
    public void setPlanetMaps(ArrayList<TileMap> planetMaps) {
        this.planetMaps = planetMaps;
    }

    /**
     * @return the solarMap
     */
    public int[][] getSolarMap() {
        return solarMap;
    }

    /**
     * @param solarMap the solarMap to set
     */
    public void setSolarMap(int[][] solarMap) {
        this.solarMap = solarMap;
    }

    /**
     * @return the systemInterface
     */
    public SystemInterface getSystemInterface() {
        return systemInterface;
    }

    /**
     * @param systemInterface the systemInterface to set
     */
    public void setSystemInterface(SystemInterface systemInterface) {
        this.systemInterface = systemInterface;
    }
    
    public void addPlanetTileMap(){
        if (planetMaps == null) {
            planetMaps = new ArrayList<>();
        }
        TileMap newPlanet = systemInterface.createPlanetMap(this, new Point(0, 0));
        planetMaps.add(newPlanet);
    }
    
    @Override
    public void setPosition(Point position) {
        super.getGrid().setPosition(position);
    }

    @Override
    public Point getPosition() {
        return super.getGrid().getPosition();
    }
    
    
}
