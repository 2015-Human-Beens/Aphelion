/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hud;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author kevin.lawrence
 */
public class HUD implements ParentComponentIntf {

    private static Color color = new Color(100, 240, 240, 80); 
    
    public void paint(Graphics graphics) {
        if (visible) {
            graphics.setColor(color);
            graphics.fill3DRect(position.x, position.y, size.width, size.height, true);

            getComponents().stream().forEach((HUDComponent component) -> {
                component.paint(graphics);
            });
        }
    }

//<editor-fold defaultstate="collapsed" desc="Constructors">
    {
        setComponents(new ArrayList<>());
        visible = true;
    }

    public HUD(Point position, Dimension size) {
        this.position = position;
        this.size = size;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Properties">
    private Point position;
    private final Dimension size;
    private boolean visible;
    private boolean extended = true;

    private ArrayList<HUDComponent> components;

    /**
     * @param position the position to set
     */
    public void setPosition(Point position) {
        this.position = position;
    }

//<editor-fold defaultstate="collapsed" desc="ParentComponentIntf">
    /**
     * @return the position
     */
    @Override
    public Point getPositon() {
        return position;
    }

    /**
     * @return the visible
     */
    @Override
    public boolean isVisible() {
        return visible;
    }
//</editor-fold>

    /**
     * @param visible the visible to set
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    public int getRetractedX(){
        return -this.size.width;
    }

    /**
     * @param component the components to add
     */
    public void addComponent(HUDComponent component) {
        component.setParent(this);
        getComponents().add(component);
    }

    /**
     * @param component the components to add
     */
    public void removeComponent(HUDComponent component) {
        getComponents().remove(component);
        component.clearParent();
    }

    /**
     * @return the components
     */
    public ArrayList<HUDComponent> getComponents() {
        return components;
    }

    /**
     * @param components the components to set
     */
    public void setComponents(ArrayList<HUDComponent> components) {
        this.components = components;
    }
//</editor-fold>

    /**
     * @return the extended
     */
    public boolean isExtended() {
        return extended;
    }

    /**
     * @param extended the extended to set
     */
    public void setExtended(boolean extended) {
        this.extended = extended;
    }

}
