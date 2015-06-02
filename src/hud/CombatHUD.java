/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hud;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import aphelion.Character;

/**
 *
 * @author david
 */
public class CombatHUD extends HUD {

//<editor-fold defaultstate="collapsed" desc="Constructors">
    {

    }
    
    public CombatHUD(Point position, Dimension size, HUDState state,
            Character playerCharacter, Character nonPlayerCharacter) {
        super(position, size, state);
        
        this.pc = playerCharacter;
        
        pcName = new HUDLabelComponent(new Point(10, 10), new Dimension(10, 10), playerCharacter.getName());
        addComponent(pcName);
        
        
        
        
//        npcName = new HUDLabelComponent(new Point(210, 10), new Dimension(10, 10), nonPlayerCharacter.getName());
        addComponent(new HUDLabelComponent(new Point(210, 10), new Dimension(10, 10), nonPlayerCharacter.getName()));

        
        
        
        this.npc = nonPlayerCharacter;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Drawing">
    /**
     *
     * @param graphics
     */
    @Override
    public void paint(Graphics graphics){
        super.paint(graphics);
        
    }
//</editor-fold>
    
    
//<editor-fold defaultstate="collapsed" desc="Properties">
    private Character pc;
    private aphelion.Character npc;
    
    private HUDLabelComponent pcName;
    private HUDLabelComponent npcName;
    
    /**
     * @return the pc
     */
    public Character getPlayerCharacter() {
        return pc;
    }
    
    /**
     * @param playerCharacter the pc to set
     */
    public void setPlayerCharacter(Character playerCharacter) {
        this.pc = playerCharacter;
    }
    
    /**
     * @return the npc
     */
    public Character getNonPlayerCharacter() {
        return npc;
    }
    
    /**
     * @param nonPlayerCharacter the npc to set
     */
    public void setNonPlayerCharacter(Character nonPlayerCharacter) {
        this.npc = nonPlayerCharacter;
    }
//</editor-fold>
}
