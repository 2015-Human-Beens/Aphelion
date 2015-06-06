/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hud;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
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
        FontMetrics metrics = graphics.getFontMetrics();        
        int textWidth = metrics.stringWidth(String.valueOf(getLabel()));
        
        graphics.setColor(UI.HUD_GREY);
        graphics.fill3DRect(getPosition().x - (textWidth / 2) - 1, getPosition().y - 20, textWidth + 2, 20, true);
        graphics.setColor(Color.WHITE);
        graphics.setFont(UI.standard);
        graphics.drawString(String.valueOf(getLabel()), getPosition().x + (getSize().width / 2) - (textWidth / 2), getPosition().y + getSize().height - 5);
//        graphics.drawString(getLabel(), getPosition().x, getPosition().y);
        
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
