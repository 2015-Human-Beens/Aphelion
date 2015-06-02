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
public class CombatHUD extends HUD{

    public CombatHUD(Point position, Dimension size, HUDState state,
            StatusProviderIntf healthStatusProvider) {
        super(position, size, state);
        addComponent(new StatusBar(new Point(10, 70), new Dimension(100, 20), healthStatusProvider));
        
        addComponent(new HUDViewController(new Point(-20, size.height - size.height), new Dimension(20, 20), HUDViewController.Direction.HORIZONTAL, this));
    }
}    
    
