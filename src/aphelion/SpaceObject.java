/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aphelion;

import images.ResourceTools;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

/**
 *
 * @author Benjamin
 */
public class SpaceObject {

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    public SpaceObject(SpaceObjectType type, Point location, MapDrawDataIntf mapDrawData) {
        this.type = type; this.location = location; this.gridDrawData = mapDrawData;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="paintObject">
    public void paintObject(Graphics graphics) {
        Point topLeft = gridDrawData.getCellSystemCoordinate(getLocation());
        switch (type) {
            case T_PLANET:
                graphics.drawImage(earth, topLeft.x, topLeft.y, null);
                break;
            case G_GIANT:
                graphics.drawImage(saturn, topLeft.x, topLeft.y, null);
                break;
            default:
                break;
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Fields">
    private SpaceObjectType type;
    private Point location;
    private MapDrawDataIntf gridDrawData;
    private Image earth = ResourceTools.loadImageFromResource("resources/earth_half_dark.gif");
    private Image saturn = ResourceTools.loadImageFromResource("resources/ringed_gas.gif");
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    /**
     * @return the type
     */
    public SpaceObjectType getType() {
        return type;
    }
    
    /**
     * @param type the type to set
     */
    public void setType(SpaceObjectType type) {
        this.type = type;
    }
    
    /**
     * @return the location
     */
    public Point getLocation() {
        return location;
    }
    
    /**
     * @param location the location to set
     */
    public void setLocation(Point location) {
        this.location = location;
    }
    
    /**
     * @return the mapDrawData
     */
    public MapDrawDataIntf getMapDrawData() {
        return gridDrawData;
    }
    
    /**
     * @param mapDrawData the mapDrawData to set
     */
    public void setMapDrawData(MapDrawDataIntf mapDrawData) {
        this.gridDrawData = mapDrawData;
    }
//</editor-fold>

}
