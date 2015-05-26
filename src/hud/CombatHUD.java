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

    public CombatHUD(Point position, Dimension size, HUDState state) {
        super(position, size, state);
        
        addComponent(new HUDViewController(new Point(-16, size.width / 2), new Dimension(16, 20), HUDViewController.Direction.HORIZONTAL, this));
    }
    
}