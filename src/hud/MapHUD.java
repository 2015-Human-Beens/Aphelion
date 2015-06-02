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
public class MapHUD extends HUD{

    public MapHUD(Point position, Dimension size, HUDState state) {
        super(position, size, state);
        
        addComponent(new HUDViewController(new Point(-9, size.height / 2), new Dimension(9, 20), HUDViewController.Direction.HORIZONTAL, this));

    }
    
}
