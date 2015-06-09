/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tilesInfrastructure;

import items.MapItem;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import map.Map;
import map.MapVisualizerIntf;

/**
 *
 * @author Benjamin
 */
public class TileMap extends Map {
    
    public TileMap(Image background, Dimension gridCellSize, int[][] map, MapVisualizerIntf mapVisualizer) {
        super(background, gridCellSize, new Dimension(map.length, map[0].length));
        setFlag(Integer.toString(numberOfMaps));
        setMap(map);
        this.setMapVisualizer(mapVisualizer);
        if (getMapFeatures() == null) {
            setMapFeatures(new ArrayList<>());
        }
        numberOfMaps++;
    }
    
    private Point systemLocation;
    
    private int[][] map = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
    private ArrayList<MapItem> mapFeatures = new ArrayList<>();

    @Override
    public void drawMap(Graphics graphics) {
        super.drawMap(graphics); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the map
     */
    public int[][] getMap() {
        return map;
    }

    /**
     * @param map the map to set
     */
    public void setMap(int[][] map) {
        this.map = map;
    }

    @Override
    public void setPosition(Point position) {
        super.getGrid().setPosition(position);
    }

    @Override
    public Point getPosition() {
        return super.getGrid().getPosition();
    }
    
    //<editor-fold defaultstate="collapsed" desc="MoreProperties">
    private static int numberOfMaps = 0;
    private String flag;
    
    /**
     * @return the flag
     */
    public String getFlag() {
        return flag;
    }
    
    /**
     * @param flag the flag to set
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }
    /**
     * @return the mapFeatures
     */
    public ArrayList<MapItem> getMapFeatures() {
        return mapFeatures;
    }

    /**
     * @param mapFeatures the mapFeatures to set
     */
    public void setMapFeatures(ArrayList<MapItem> mapFeatures) {
        this.mapFeatures = mapFeatures;
    }
    
    public void addMapItem(MapItem mapItem){
        if (mapFeatures == null) {
            mapFeatures = new ArrayList<>();
        }
        mapFeatures.add(mapItem);
    }
//</editor-fold>

    /**
     * @return the systemLocation
     */
    public Point getSystemLocation() {
        return systemLocation;
    }

    /**
     * @param systemLocation the systemLocation to set
     */
    public void setSystemLocation(Point systemLocation) {
        this.systemLocation = systemLocation;
    }
}