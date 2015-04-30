/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tilesInfrastructure;

import aphelion.VisibilityProviderIntf;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import map.Item;
import map.Map;
import map.MapVisualizerIntf;

/**
 *
 * @author Benjamin
 */
public class TileMapVisualizer implements MapVisualizerIntf {

    private TileProviderIntf tileProvider;
    private VisibilityProviderIntf visibilityProvider;
    private final Color FOG_OF_WAR = new Color(255, 255, 255, 150);

    public TileMapVisualizer(TileProviderIntf tileProvider, VisibilityProviderIntf visibilityProvider) {
        this.tileProvider = tileProvider;
        this.visibilityProvider = visibilityProvider;
    }

    @Override
    public void draw(Map map, Graphics graphics) {
//        System.out.println("Drawing map...");
        int[][] mapData = ((TileMap) map).getMap();
        int[][] visibilityData = visibilityProvider.getVisibilityArray();

        for (int column = 0; column < mapData.length; column++) {
            for (int row = 0; row < mapData[column].length; row++) {
//                graphics.drawString(String.valueOf(mapData[column][row]), map.getPosition().x + (row * map.getCellWidth()), map.getPosition().y + ((column + 1) * map.getCellHeight()));
                int cellData = mapData[column][row];
                int cellVis = visibilityData[column][row];

                Point topLeft = new Point(column, row);
                graphics.drawImage(getTexture(cellData), map.getCellSystemCoordinate(topLeft).x, map.getCellSystemCoordinate(topLeft).y, null);
                graphics.drawImage(getOverlay(cellData), map.getCellSystemCoordinate(topLeft).x, map.getCellSystemCoordinate(topLeft).y, null);

                graphics.setColor(FOG_OF_WAR);
                if (cellVis == 0) {
                    graphics.fillRect(map.getCellSystemCoordinate(topLeft).x, map.getCellSystemCoordinate(topLeft).y, map.getCellWidth(), map.getCellWidth());
                }
            }
        }

        if (map.getItems() != null) {
            for (Item item : map.getItems()) {
                Point topLeft = item.getLocation();
                switch (item.getType()) {
                    case "Scanner":
                        graphics.setColor(Color.CYAN);
                        graphics.fillOval(map.getCellSystemCoordinate(topLeft).x, map.getCellSystemCoordinate(topLeft).y, 16, 16);
                        break;
                }
            }
        }

    }

    private Image getTexture(int data) {
        return tileProvider.getTileTexture((int) ((data / 100) % 100));
//        return tileProvider.getTileTexture((int) data / 100);
    }

    private Image getOverlay(int data) {
        return tileProvider.getTileOverlay((int) (data % 100));
    }

    /**
     * @return the tileProvider
     */
    public TileProviderIntf getTileProvider() {
        return tileProvider;
    }

    /**
     * @param tileProvider the tileProvider to set
     */
    public void setTileProvider(TileProviderIntf tileProvider) {
        this.tileProvider = tileProvider;
    }
}
