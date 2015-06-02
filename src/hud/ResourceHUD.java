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
 * @author kevin.lawrence
 */
public class ResourceHUD extends HUD {

    public ResourceHUD(Point position, Dimension size, HUDState state,
            StatusProviderIntf fuelStatusProvider) {
        super(position, size, state);
        
        addComponent(new StatusCircle(new Point(10, 70), new Dimension(100, 20), fuelStatusProvider));

        addComponent(new HUDViewController(new Point( size.width / 2, -15), new Dimension(20, 15), HUDViewController.Direction.VERTICAL, this));
    }
    
}
