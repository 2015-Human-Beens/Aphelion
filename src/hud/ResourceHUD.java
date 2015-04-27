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
            StatusProviderIntf healthStatusProvider, 
            StatusProviderIntf oxygenStatusProvider) {
        super(position, size, state);
        
        addComponent(new StatusBar(new Point(10, 10), new Dimension(100, 20), healthStatusProvider));
        addComponent(new StatusBar(new Point(10, 40), new Dimension(100, 20), oxygenStatusProvider));

        addComponent(new HUDViewController(new Point(size.width, 0), new Dimension(9, 20), HUDViewController.Direction.HORIZONTAL, this));
    }
    
}
