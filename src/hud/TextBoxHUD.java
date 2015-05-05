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
public class TextBoxHUD extends HUD{

    public TextBoxHUD(Point position, Dimension size, HUDState state) {
        super(position, size, state);
        
        addComponent(new HUDViewController(new Point(size.width, size.height / 2), new Dimension(9, 20), HUDViewController.Direction.HORIZONTAL, this));

    }
    
}
