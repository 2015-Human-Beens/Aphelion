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
public class StatusProvider implements StatusProviderIntf {

//<editor-fold defaultstate="collapsed" desc="Constructors">
    {
        minStatus = 0;
        maxStatus = 10;
        status = 10;
    }

    public StatusProvider(String name, int status, int maxStatus) {
        this.status = status;
        this.maxStatus = maxStatus;
    }

    public StatusProvider(String name, int status, int maxStatus, int minStatus) {
        this.status = status;
        this.maxStatus = maxStatus;
        this.minStatus = minStatus;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Properties">
    private int status;
    private int minStatus;
    public int maxStatus;

    public void changeStatus(int change) {
        setStatus(status + change);
    }

    public void setStatus(int status) {
        this.status = Math.min(Math.max(status, 0), maxStatus);
    }

    public void setMaxStatus(int maxStatus) {
        this.maxStatus = maxStatus;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public int getMaxStatus() {
        return maxStatus;
    }

    /**
     * @return the minStatus
     */
    public int getMinStatus() {
        return minStatus;
    }

    /**
     * @param minStatus the minStatus to set
     */
    public void setMinStatus(int minStatus) {
        this.minStatus = minStatus;
    }

//</editor-fold>
}
