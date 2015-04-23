/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hud;

/**
 *
 * @author kevin.lawrence
 */
public interface StatusProviderIntf {
    
    public void setStatus(int status);
    public int getStatus();
    public void changeStatus(int change);
    
    public void setMinStatus(int minStatus);
    public int getMinStatus();

    public void setMaxStatus(int maxStatus);
    public int getMaxStatus();
    
 }
