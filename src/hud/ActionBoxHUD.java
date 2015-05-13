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
public class ActionBoxHUD extends HUD{

    public ActionBoxHUD(Point position, Dimension size, HUDState state) {
        super(position, size, state);
        
        addComponent(new HUDViewController(new Point(size.width / 2, -9), new Dimension(20, 9), HUDViewController.Direction.VERTICAL, this));
    }
    
}
