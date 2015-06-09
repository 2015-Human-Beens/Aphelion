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
public class FuelHUD extends HUD{

    public FuelHUD(Point position, Dimension size, HUDState state, StatusProviderIntf fuelStatusProvider) {
        super(position, size, state);
        
        addComponent(new StatusBar(new Point(10, 5), new Dimension(100, 10), fuelStatusProvider));
        
        addComponent(new HUDViewController(new Point(-15, 0), new Dimension(10, 15), HUDViewController.Direction.HORIZONTAL, this));
    }
    
    private StatusBar fuelStatusBar;
    
}
