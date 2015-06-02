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
        numberOfMaps++;
    }
    
    private int[][] map = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
    private ArrayList<MapItem> mapItems = new ArrayList<>();

    @Override
    public void drawMap(Graphics graphics) {
        super.drawMap(graphics); //To change body of generated methods, choose Tools | Templates.
        if (getMapItems() == null) {
            setMapItems(new ArrayList<>());
        }
        graphics.setColor(new Color(0, 255, 50, 170));
        for (MapItem mapItem : getMapItems()) {
            Point topLeft = super.getCellSystemCoordinate(mapItem.getLocation());
            graphics.fillOval(topLeft.x, topLeft.y, 16, 16);
        }
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
//</editor-fold>

    /**
     * @return the mapItems
     */
    public ArrayList<MapItem> getMapItems() {
        return mapItems;
    }

    /**
     * @param mapItems the mapItems to set
     */
    public void setMapItems(ArrayList<MapItem> mapItems) {
        this.mapItems = mapItems;
    }
    
    public void addMapItem(MapItem mapItem){
        if (mapItems == null) {
            mapItems = new ArrayList<>();
        }
        mapItems.add(mapItem);
    }
}