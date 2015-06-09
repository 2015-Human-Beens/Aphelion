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
     public CombatHUD(Point position, Dimension size, HUDState state,
            Character playerCharacter,
            Character nonPlayerCharacter,
            StatusProviderIntf pcHealthStatusProvider,
            StatusProviderIntf npcHealthStatusProvider) {
        super(position, size, state);         
        
        pcName = new HUDLabelComponent(new Point(5, 5), new Dimension(), playerCharacter.getName());
        addComponent(pcName);
        npcName = new HUDLabelComponent(new Point(290, 5), new Dimension(), nonPlayerCharacter.getName());
        addComponent(npcName);
        versus = new HUDLabelComponent(new Point(170, 5), new Dimension(), "Vs");
        addComponent(versus); 
        
        pcHealth = new StatusBar(new Point(5, 200), new Dimension(100, 10), pcHealthStatusProvider);
         addComponent(pcHealth);
         
        npcHealth = new StatusBar(new Point(290, 200), new Dimension(100, 10), npcHealthStatusProvider);
        addComponent(npcHealth);
        
        addComponent(new HUDViewController(new Point(-20, 0), new Dimension(20, 20), HUDViewController.Direction.VERTICAL, this));
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
    private Character npc;
    
    private HUDLabelComponent pcName;
    private HUDLabelComponent npcName;
    private HUDLabelComponent versus;
    private StatusBar pcHealth;
    private StatusBar npcHealth;

    
    
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
