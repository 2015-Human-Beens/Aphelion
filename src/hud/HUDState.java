/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hud;

import java.awt.Point;

/**
 *
 * @author kevin.lawrence
 */
public class HUDState {

//<editor-fold defaultstate="collapsed" desc="Constructors">
    public HUDState(boolean open, Point openPosition, Point closedPosition) {
        this.open = open;
        this.openPosition = openPosition;
        this.closedPosition = closedPosition;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Properties">
//    public static enum Position {OPEN, CLOSED};
//    private Position position;

    private boolean open;
    private Point closedPosition;
    private Point openPosition;
    

    /**
     * @return the open
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * @param open the open to set
     */
    public void setOpen(boolean open) {
        this.open = open;
    }

    /**
     * @return the closedPosition
     */
    public Point getClosedPosition() {
        return closedPosition;
    }
    
    /**
     * @param closedPosition the closedPosition to set
     */
    public void setClosedPosition(Point closedPosition) {
        this.closedPosition = closedPosition;
    }
    
    /**
     * @return the openPosition
     */
    public Point getOpenPosition() {
        return openPosition;
    }
    
    /**
     * @param openPosition the openPosition to set
     */
    public void setOpenPosition(Point openPosition) {
        this.openPosition = openPosition;
    }
//</editor-fold>
}
