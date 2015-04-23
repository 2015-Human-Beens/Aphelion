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

    public ResourceHUD(Point position, Dimension size, 
            StatusProviderIntf healthStatusProvider, 
            StatusProviderIntf oxygenStatusProvider) {
        super(position, size);
        
        StatusBar health = new StatusBar(new Point(10, 10), new Dimension(100, 20), healthStatusProvider);
        StatusBar oxygen = new StatusBar(new Point(10, 40), new Dimension(100, 20), oxygenStatusProvider);
        
        addComponent(health);
        addComponent(oxygen);
    }
    
}
