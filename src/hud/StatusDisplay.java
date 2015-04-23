/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hud;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author kevin.lawrence
 */
public abstract class StatusDisplay extends HUDComponent {

//<editor-fold defaultstate="collapsed" desc="Methods">
    public abstract void paint(Graphics graphics);
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Constructors">
    {
    setStatusColor(Colors.HUD_BLUE);
    setStatusBackgroundColor(Colors.HUD_GREY);
    
    setFont(new Font("Calibri", Font.PLAIN, 18));
    setShowStatus(false);
}
    
    public StatusDisplay(Point position, Dimension size, StatusProviderIntf statusProvider) {
        super(position, size);
        this.statusProvider = statusProvider;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Properties">
    protected StatusProviderIntf statusProvider;

    protected Font font;
    protected boolean showStatus;
    
    protected Color statusColor;
    protected Color statusBackgroundColor;
    
    /**
     * @return the statusProvider
     */
    public StatusProviderIntf getStatusProvider() {
        return statusProvider;
    }
    
    /**
     * @param statusProvider the statusProvider to set
     */
    public void setStatusProvider(StatusProviderIntf statusProvider) {
        this.statusProvider = statusProvider;
    }
    
    /**
     * @return the font
     */
    public Font getFont() {
        return font;
    }
    
    /**
     * @param font the font to set
     */
    public void setFont(Font font) {
        this.font = font;
    }
    
    /**
     * @return the showStatus
     */
    public boolean isShowStatus() {
        return showStatus;
    }
    
    /**
     * @param showStatus the showStatus to set
     */
    public void setShowStatus(boolean showStatus) {
        this.showStatus = showStatus;
    }
    
    /**
     * @return the statusColor
     */
    public Color getStatusColor() {
        return statusColor;
    }
    
    /**
     * @param statusColor the statusColor to set
     */
    public void setStatusColor(Color statusColor) {
        this.statusColor = statusColor;
    }
    
    /**
     * @return the statusBackgroundColor
     */
    public Color getStatusBackgroundColor() {
        return statusBackgroundColor;
    }
    
    /**
     * @param statusBackgroundColor the statusBackgroundColor to set
     */
    public void setStatusBackgroundColor(Color statusBackgroundColor) {
        this.statusBackgroundColor = statusBackgroundColor;
    }
//</editor-fold>
}
