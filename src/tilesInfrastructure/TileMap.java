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

    public TileMap(Image background, Dimension gridCellSize, Dimension gridSize) {
        super(background, gridCellSize, gridSize);
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