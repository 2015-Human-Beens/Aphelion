/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tilesInfrastructure;

import aphelion.VisibilityProviderIntf;
import hud.Colors;
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
    private static final Color FOG_OF_WAR = new Color(0, 0, 0, 150);
    private static final int NOT_VISIBLE = 0;
    private static final int BORDER_WIDTH = 1;
    

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

                
                if (cellVis == NOT_VISIBLE) {
                    graphics.setColor(FOG_OF_WAR);
//                    if ((column - 1 >= 0 && visibilityData[column - 1][row] == 1) || (column + 1 < visibilityData.length && visibilityData[column + 1][row] == 1) ||
//                            (row - 1 >= 0 && visibilityData[column][row - 1] == 1) || (row + 1 < visibilityData[0].length && visibilityData[column][row + 1] == 1)) {
//                        graphics.setColor(BORDER);
//                    }
                    graphics.fillRect(map.getCellSystemCoordinate(topLeft).x, map.getCellSystemCoordinate(topLeft).y, map.getCellWidth(), map.getCellWidth());
                }
                if (cellVis == 1) {
                graphics.setColor(Colors.HUD_BLUE);
                    if (column - 1 >= 0 && visibilityData[column - 1][row] == NOT_VISIBLE){
                        graphics.fillRect(map.getCellSystemCoordinate(topLeft).x, map.getCellSystemCoordinate(topLeft).y, BORDER_WIDTH, map.getCellHeight());
                    }
                    if (column + 1 < visibilityData.length && visibilityData[column + 1][row] == NOT_VISIBLE) {
                        graphics.fillRect(map.getCellSystemCoordinate(topLeft).x + map.getCellWidth() - 1, map.getCellSystemCoordinate(topLeft).y, BORDER_WIDTH, map.getCellHeight());
                    }
                    if (row - 1 >= 0 && visibilityData[column][row - 1] == NOT_VISIBLE) {
                        graphics.fillRect(map.getCellSystemCoordinate(topLeft).x, map.getCellSystemCoordinate(topLeft).y, map.getCellWidth(), BORDER_WIDTH);
                    }
                    if (row + 1 < visibilityData[0].length && visibilityData[column][row + 1] == NOT_VISIBLE) {
                        graphics.fillRect(map.getCellSystemCoordinate(topLeft).x, map.getCellSystemCoordinate(topLeft).y + map.getCellHeight() - 1, map.getCellWidth(), BORDER_WIDTH);
                    }
                }
            }
        }

        if (map.getItems() != null) {
            for (Item item : map.getItems()) {
                Point topLeft = item.getLocation();
                switch (item.getType()) {
                    case "Scanner":
                        graphics.setColor(Color.CYAN);
                        graphics.fillOval(map.getCellSystemCoordinate(topLeft).x, map.getCellSystemCoordinate(topLeft).y, map.getCellWidth(), map.getCellHeight());
                        break;
                }
            }
        }

    }

    private Image getTexture(int data) {
        return tileProvider.getTileTexture((int) ((data / 100) % 100));
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
