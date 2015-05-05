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
public class StatusArc extends StatusDisplay {

    public StatusArc(Point position, Dimension size, StatusProviderIntf statusProvider) {
        super(position, size, statusProvider);
    }

    @Override
    public void paint(Graphics graphics) {
        if (statusProvider != null) {
            graphics.setColor(statusColor);
            graphics.fillArc(getPosition().x, getPosition().y, getSize().width, getSize().height, -180, (int) (-180 * (1.0 * statusProvider.getStatus() / statusProvider.getMaxStatus())));
            graphics.setColor(getStatusBackgroundColor());
            graphics.fillArc(getPosition().x + (getSize().width * 1/20), getPosition().y + (getSize().width * 1/20), getSize().width * 9/10, getSize().height * 9/10, -180, -180);
        }
    }

//    private void drawStatusArc(Graphics graphics, Point position) {
//        //Arch
////        graphics.setColor(Color.GREEN);
////        graphics.fillArc(position.x - 20, position.y - 10, getSize(), , -180, (int) (-180 * (1.0 * getStatus() / getMaxStatus())));
//        graphics.setColor(statusColor);
//        graphics.fillArc(position.x, position.y, getSize().width, getSize().height, -180, (int) (-180 * (1.0 * statusProvider.getStatus() / statusProvider.getMaxStatus())));
//        graphics.setColor(statusBackgroundColor);
//        graphics.fillArc(position.x, position.y, 330, 290, -180, -180);
//    }
}
