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
public class MainMenuHUD extends HUD{

    public MainMenuHUD(Point position, Dimension size, HUDState state,
            StatusProviderIntf oxygenStatusProvider) {
        super(position, size, state);
        addComponent(new StatusCircle(new Point(10, 20), new Dimension(100, 20), oxygenStatusProvider));

        
        addComponent(new HUDViewController(new Point((size.width / 2) - 20, -9), new Dimension(20, 9), HUDViewController.Direction.VERTICAL, this));
    }
}
