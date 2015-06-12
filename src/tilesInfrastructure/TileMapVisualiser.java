/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tilesInfrastructure;

import aphelion.VisibilityProviderIntf;
import hud.UI;
import items.MapItem;
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
public class TileMapVisualiser implements MapVisualizerIntf {

    private TileProviderIntf tileProvider;
    private VisibilityProviderIntf visibilityProvider;

    private static final Color FOG_OF_WAR = new Color(0, 0, 0, 150);
    private static final int NOT_VISIBLE = 0;
    private static final int VISIBLE = 1;
    private static final int BORDER_WIDTH = 1;

    public TileMapVisualiser(TileProviderIntf tileProvider, VisibilityProviderIntf visibilityProvider) {
        this.tileProvider = tileProvider;
        this.visibilityProvider = visibilityProvider;
    }

    @Override
    public void draw(Map mapAlpha, Graphics graphics) {
        TileMap map = (TileMap) mapAlpha;
        int[][] mapData = ((TileMap) map).getMap();
        int[][] visibilityData = getVisibilityProvider().getVisibilityArray();

        for (int column = 0; column < mapData.length; column++) {
            for (int row = 0; row < mapData[column].length; row++) {
                int cellData = mapData[column][row];
                int cellVis = visibilityData[column][row];

                Point topLeft = new Point(column, row);
                graphics.drawImage(getTexture(cellData), map.getCellSystemCoordinate(topLeft).x, map.getCellSystemCoordinate(topLeft).y, null);
                graphics.drawImage(getOverlay(cellData), map.getCellSystemCoordinate(topLeft).x, map.getCellSystemCoordinate(topLeft).y, null);

                if (cellVis == NOT_VISIBLE) {
                    graphics.setColor(FOG_OF_WAR);
                    graphics.fillRect(map.getCellSystemCoordinate(topLeft).x, map.getCellSystemCoordinate(topLeft).y, map.getCellWidth(), map.getCellWidth());
                }
                if (cellVis == VISIBLE) {
                    graphics.setColor(UI.HUD_BLUE);
                    if (column - 1 >= 0 && visibilityData[column - 1][row] == NOT_VISIBLE) {
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
                    graphics.setColor(new Color(0, 255, 50, 170));
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
        graphics.setColor(new Color(0, 255, 50, 170));
        if (map.getMapFeatures() != null) {
            for (MapItem mapItem : map.getMapFeatures()) {
                Point topLeft = mapItem.getLocation();
                graphics.fillOval(map.getCellSystemCoordinate(topLeft).x, map.getCellSystemCoordinate(topLeft).y, 16, 16);
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

    /**
     * @return the visibilityProvider
     */
    public VisibilityProviderIntf getVisibilityProvider() {
        return visibilityProvider;
    }

    /**
     * @param visibilityProvider the visibilityProvider to set
     */
    public void setVisibilityProvider(VisibilityProviderIntf visibilityProvider) {
        this.visibilityProvider = visibilityProvider;
    }
}
