/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aphelion;

import java.awt.Point;
import java.util.ArrayList;
import map.Item;

/**
 *
 * @author Benjamin
 */
public class Visibility {

    int[][] getVisibilityArray() {
        int[][] visibilityArray = new int[getMapDrawData().getRows()][getMapDrawData().getColumns()];
        ArrayList<Item> improvements = mapImprovementData.getImprovements();

        for (int i = 0; i < visibilityArray.length; i++) {
            for (int j = 0; j < visibilityArray[i].length; j++) {
                visibilityArray[i][j] = 0;
            }
        }
        int charSightRadius = characterInfo.getCharacterScanRadius();
        for (int i = -charSightRadius; i <= charSightRadius; i++) {
            for (int j = -(charSightRadius - Math.abs(i)); j <= charSightRadius - Math.abs(i); j++) {
                int newX = characterInfo.getCharacterPoint().x + j;
                int newY = characterInfo.getCharacterPoint().y + i;
                if ((newX >= 0) && (newX <= visibilityArray.length - 1) && (newY >= 0) && (newY <= visibilityArray[0].length - 1)) {
                    visibilityArray[newX][newY] = 1;
                }
            }
        }
        
        for (Item improvement : improvements) {
            if (improvement.getType().equals("Scanner")) {
                int scannerRadius = Scanner.scannerRadius;
                for (int i = -scannerRadius; i <= scannerRadius; i++) {
                    for (int j = -(scannerRadius - Math.abs(i)); j <= scannerRadius - Math.abs(i); j++) {
                        
                        int newX = improvement.getLocation().x + j;
                        int newY = improvement.getLocation().y + i;
                        if ((newX >= 0) && (newX <= visibilityArray.length - 1) && (newY >= 0) && (newY <= visibilityArray[0].length - 1)) {
                            visibilityArray[newX][newY] = 1;
                        }
                    }
                }
            }
        }

        return visibilityArray;
    }

    //<editor-fold defaultstate="collapsed" desc="Properties">
    private MapDrawDataIntf mapDrawData;
    private MapImprovementDataIntf mapImprovementData;

    private CharacterInfoProvIntf characterInfo;

    /**
     * @return the mapDrawData
     */
    public MapDrawDataIntf getMapDrawData() {
        return mapDrawData;
    }

    /**
     * @param mapDrawData the mapDrawData to set
     */
    public void setMapDrawData(MapDrawDataIntf mapDrawData) {
        this.mapDrawData = mapDrawData;
    }

    /**
     * @return the characterInfo
     */
    public CharacterInfoProvIntf getCharacterInfo() {
        return characterInfo;
    }

    /**
     * @param characterInfo the characterInfo to set
     */
    public void setCharacterInfo(CharacterInfoProvIntf characterInfo) {
        this.characterInfo = characterInfo;
    }
//</editor-fold>

    /**
     * @return the mapImprovementData
     */
    public MapImprovementDataIntf getMapImprovementData() {
        return mapImprovementData;
    }

    /**
     * @param mapImprovementData the mapImprovementDada to set
     */
    public void setMapImprovementData(MapImprovementDataIntf mapImprovementData) {
        this.mapImprovementData = mapImprovementData;
    }

}
