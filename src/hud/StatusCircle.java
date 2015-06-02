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
    
    private int r;
    private int g;
    private int b;

    private int getB() {
        if(statusProvider.getStatus() > (statusProvider.getMaxStatus()) * (80 / 120)){
            b = 255;
        }else if (statusProvider.getStatus() <= 80 && statusProvider.getStatus() >= 60) {
            b = ((int) (255 / 20) * (statusProvider.getStatus() - 60));
        } else {
            b = 0;
        }
        return b;
    }

    private int getR() {
        if (statusProvider.getStatus() > 60) {
            r = 0; 
        }else if (statusProvider.getStatus() >= 30 && statusProvider.getStatus() <= 60) {
            r = 0 + (int) (255 / 30) * (60 - statusProvider.getStatus());
        } else if (statusProvider.getStatus() <= 30) {
            r = 255;
        }
        return r;
    }

    private int getG() {
        if (statusProvider.getStatus() > 30) {
            g = 255;
        }
        if (statusProvider.getStatus() >= 20 && statusProvider.getStatus() <= 30) {
            g = 255 - (int) (255 / 10) * (30 - statusProvider.getStatus());
        } else if(statusProvider.getStatus() <= 20) {
            g = 0;
        }
        return g;
    }
    
    public Color getStatusColor() {
        return new Color(getR(), getG(), getB());
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.setColor(getStatusBackgroundColorTransparent());
        graphics.fillArc(getPosition().x, getPosition().y, 40, 40, 90, 360);
        graphics.setColor(getStatusColor());
        graphics.fillArc(getPosition().x + 5, getPosition().y + 5, 30, 30, 90, 
                        (int) (360 *(1.0 * statusProvider.getStatus() / statusProvider.getMaxStatus())));
        graphics.setColor(getStatusColor());
        graphics.setFont(font);
        graphics.drawString(Integer.toString(statusProvider.getStatus()), getPosition().x + 32, getPosition().y + 45);
    }

    
}
