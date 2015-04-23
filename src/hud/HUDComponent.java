/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hud;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author kevin.lawrence
 */
public abstract class  HUDComponent {

    public HUDComponent(Point position, Dimension size) {
        this.position = position;
        this.size = size;
    }
    
    
    public abstract void paint(Graphics graphics);

    private Point position;
    private Dimension size;

    private ParentComponentIntf parent;

    /**
     * @return the position
     */
    public Point getPosition() {
        if (parent != null) {
            return new Point(parent.getPositon().x + position.x, parent.getPositon().y + position.y);
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
     * @return the parent
     */
    public ParentComponentIntf getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(ParentComponentIntf parent) {
        this.parent = parent;
    }

    /**
     * remove the parent reference
     */
    public void clearParent() {
        parent = null;
    }

}
