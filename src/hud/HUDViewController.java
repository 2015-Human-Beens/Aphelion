/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hud;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 *
 * @author kevin.lawrence
 */
public class HUDViewController extends HUDComponent implements MouseEventListenerIntf {

    public static enum Direction {
        VERTICAL, HORIZONTAL
    };

    {
        this.setFocusable(true);
        this.setEnabled(true);
    }

    public HUDViewController(Point position, Dimension size, Direction direction, EventListenerIntf eventListener) {
        super(position, size);

        this.direction = direction;
        this.eventListener = eventListener;
    }

//<editor-fold defaultstate="collapsed" desc="MouseEventListenerIntf">
    @Override
    public void onMouseClick(MouseEvent event) {
        if ((eventListener != null) && (contains(event.getPoint()))) {
            eventListener.onEvent(HUDEvent.OPEN_CLOSE_COMMAND);
        }
    }
//</editor-fold>

    @Override
    public void paint(Graphics graphics) {
        graphics.setColor(UI.HUD_PANEL);
        graphics.fill3DRect(getPosition().x, getPosition().y, getSize().width, getSize().height, true);

        graphics.setColor(UI.HUD_DETAIL);
        switch (direction) {
            case VERTICAL:
                graphics.drawLine(getPosition().x + (getSize().width * 9 / 10), getPosition().y + (getSize().height * 1 / 3), getPosition().x + (getSize().width / 10), getPosition().y + (getSize().height * 1 / 3));
                graphics.drawLine(getPosition().x + (getSize().width * 9 / 10), getPosition().y + (getSize().height * 2 / 3), getPosition().x + (getSize().width / 10), getPosition().y + (getSize().height * 2 / 3));
                break;

            default:
            case HORIZONTAL:
                graphics.drawLine(getPosition().x + (getSize().width * 1 / 3), getPosition().y + (getSize().height * 9 / 10), getPosition().x + (getSize().width * 1 / 3), getPosition().y + (getSize().height / 10));
                graphics.drawLine(getPosition().x + (getSize().width * 2 / 3), getPosition().y + (getSize().height * 9 / 10), getPosition().x + (getSize().width * 2 / 3), getPosition().y + (getSize().height / 10));
        }
    }

//<editor-fold defaultstate="collapsed" desc="Properties">
    private Direction direction;
    private EventListenerIntf eventListener;

    /**
     * @return the direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
//</editor-fold>

}
