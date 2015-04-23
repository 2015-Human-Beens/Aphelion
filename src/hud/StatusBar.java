/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import hud.Colors;
import hud.StatusProviderIntf;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author david
 */
public class StatusBar extends Object {
    
    {
        statusColor = Colors.HUD_BLUE;
        statusBackgroundColor = Colors.HUD_GREY;
        
        font = new Font("Calibri", Font.PLAIN, 18);
        showStatus = false;
    }
    
    public StatusBar(StatusProviderIntf healthProvider, Point position, Dimension size){
        this.statusProvider = healthProvider;
        this.position = position;
        this.size = size;
//        this.text = text;
    }
    
    private StatusProviderIntf statusProvider;
    private Point position;
    private Dimension size;
    
//    private String text;
    private Font font;
    private boolean showStatus;
    
    private Color statusColor;
    private Color statusBackgroundColor;

    
    public void paint(Graphics graphics){
        if (statusProvider != null){
            graphics.setColor(statusBackgroundColor);
            graphics.fill3DRect(position.x, position.y, size.width, size.height, true);

            graphics.setColor(statusColor);
            graphics.fill3DRect(position.x, position.y, size.width * statusProvider.getStatus() / statusProvider.getMaxStatus(), size.height, true);
        
            graphics.setColor(Color.WHITE);
            graphics.setFont(font);

            FontMetrics metrics = graphics.getFontMetrics();
            int textWidth = metrics.stringWidth(String.valueOf(statusProvider.getStatus()));
            graphics.drawString(String.valueOf(statusProvider.getStatus()), position.x + (size.width / 2) - (textWidth / 2), position.y);
            
        }
        
    }
    
}
