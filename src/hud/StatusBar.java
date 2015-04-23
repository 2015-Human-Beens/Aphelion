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
 * @author kevin.lawrence
 */
public class StatusBar extends StatusDisplay {

    public StatusBar(Point position, Dimension size, StatusProviderIntf statusProvider) {
        super(position, size, statusProvider);
    }

    @Override
    public void paint(Graphics graphics){
        if (statusProvider != null){
            graphics.setColor(statusBackgroundColor);
            graphics.fill3DRect(getPosition().x, getPosition().y, getSize().width, getSize().height, true);

            graphics.setColor(statusColor);
            graphics.fill3DRect(getPosition().x, getPosition().y, getSize().width * statusProvider.getStatus() / statusProvider.getMaxStatus(), getSize().height, true);
        
            graphics.setColor(Color.WHITE);
            graphics.setFont(font);

            FontMetrics metrics = graphics.getFontMetrics();
            int textWidth = metrics.stringWidth(String.valueOf(statusProvider.getStatus()));
            graphics.drawString(String.valueOf(statusProvider.getStatus()), getPosition().x + (getSize().width / 2) - (textWidth / 2), getPosition().y + getSize().height - 5);
        }
    }
    
}
