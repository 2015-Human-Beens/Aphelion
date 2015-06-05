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
 * @author david
 */
public class HUDLabelComponent extends HUDComponent {

//<editor-fold defaultstate="collapsed" desc="Constructors">
    public HUDLabelComponent(Point position, Dimension size, String label) {
        super(position, size);
        this.label = label;
    }
//</editor-fold>
        
//<editor-fold defaultstate="collapsed" desc="Methods">
    @Override
    public void paint(Graphics graphics) {
        graphics.setFont(UI.standard);
        graphics.drawString(getLabel(), getPosition().x, getPosition().y);
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Properties">
    private String label;
    
    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }
    
    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }
//</editor-fold>
    
}
