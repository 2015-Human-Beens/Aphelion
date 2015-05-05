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

/**
 *
 * @author david
 */
public class StatusCircle extends StatusDisplay{

    public StatusCircle(Point position, Dimension size, StatusProviderIntf statusProvider) {
        super(position, size, statusProvider);
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.setColor(getStatusBackgroundColorTransparent());
        graphics.fillArc(getPosition().x, getPosition().y, 40, 40, 90, 360);
        graphics.setColor(getStatusColor());
        graphics.fillArc(getPosition().x + 5, getPosition().y + 5, 30, 30, 90, 
                        (int) (360 *(1.0 * statusProvider.getStatus() / statusProvider.getMaxStatus())));
        graphics.setColor(Color.BLACK);
        graphics.setFont(font);
        if (statusProvider.getStatus() <= 30) {
            graphics.setColor(Color.RED);
        }
        graphics.drawString(Integer.toString(statusProvider.getStatus()), getPosition().x + 12, getPosition().y + 23);
    }

    
}
