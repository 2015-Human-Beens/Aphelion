/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hud;

import java.awt.Dimension;
import java.awt.Point;

/**
 *
 * @author david
 */
public class StatusHUD extends HUD {

    public StatusHUD(Point position, Dimension size, HUDState state, 
            StatusProviderIntf healthStatusProvider,
            StatusProviderIntf fuelStatusProvider
            ) {
        super(position, size, state);
        
        addComponent(new StatusArc(new Point(10, 10), new Dimension(380, 380), healthStatusProvider));
        addComponent(new StatusCircle(new Point (50, 50), new Dimension(100, 20), fuelStatusProvider));
        
        addComponent(new HUDViewController(new Point(size.width / 2, -9), new Dimension(20, 9), HUDViewController.Direction.VERTICAL, this));

    }
    
}
