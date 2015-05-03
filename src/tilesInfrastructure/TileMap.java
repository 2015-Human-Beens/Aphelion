/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tilesInfrastructure;

import java.awt.Dimension;
import java.awt.Image;
import map.Map;

/**
 *
 * @author Benjamin
 */
public class TileMap extends Map {
    
    public TileMap(Image background, Dimension gridCellSize, int[][] map) {
        super(background, gridCellSize, new Dimension(map.length, map[0].length));
        setMap(map);
    }
    

    private int[][] map = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};

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
}