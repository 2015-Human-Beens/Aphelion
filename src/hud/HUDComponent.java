/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hud;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author kevin.lawrence
 */
public abstract class HUDComponent extends java.awt.Component {

    public HUDComponent(Point position, Dimension size) {
        this.position = position;
        this.size = size;
    }
    
    public abstract void paint(Graphics graphics);
    
    @Override
    public boolean contains(Point point){
        Rectangle temp = new Rectangle(getPosition(), size);
        boolean yesNo = temp.contains(point);
        return yesNo;
//        return new Rectangle(getPosition(), size).contains(point);
    }

    //<editor-fold defaultstate="collapsed" desc="Properties">
    private Point position;
    private Dimension size;
    
    private PositionProviderIntf positionProvider;
    
    /**
     * @return the position
     */
    public Point getPosition() {
        if (positionProvider != null) {
            return new Point(positionProvider.getPosition().x + position.x, positionProvider.getPosition().y + position.y);
        } else {
            return position;
        }
    }
    
    /**
     * @param position the position to set
     */
    public void setPosition(Point position) {
        this.position = position;
    }
    
    /**
     * @return the size
     */
    public Dimension getSize() {
        return size;
    }
    
    /**
     * @param size the size to set
     */
    public void setSize(Dimension size) {
        this.size = size;
    }
    
    /**
     * @return the positionProvider
     */
    public PositionProviderIntf getPositionProvider() {
        return positionProvider;
    }
    
    /**
     * @param positionProvider the positionProvider to set
     */
    public void setPositionProvider(PositionProviderIntf positionProvider) {
        this.positionProvider = positionProvider;
    }
    
    /**
     * remove the positionProvider reference
     */
    public void clearPositionProvider() {
        positionProvider = null;
    }
//</editor-fold>
}
