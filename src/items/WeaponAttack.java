/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

/**
 *
 * @author Benjamin
 */
public class WeaponAttack {
    public WeaponAttack(String attackName, String attackNickname, int aPT /*attacks Per Turn*/, double energyUse, 
            double minRange, double maxRange, int baseDamage, double baseAccuracy){
        this.attackName = attackName; this.attackNickname = attackNickname; this.aPT = aPT; this.minRange = minRange; 
        this.maxRange = maxRange; this.energyUse = energyUse; this.baseDamage = baseDamage; this.baseAccuracy = baseAccuracy;
        
    }
    
    private String attackName;
    private String attackNickname;
    private int aPT;
    private double minRange;
    private double maxRange;
    private double energyUse;
    private int baseDamage;
    private double baseAccuracy;

    /**
     * @return the aPT
     */
    public int getaPT() {
        return aPT;
    }

    /**
     * @param aPT the aPT to set
     */
    public void setaPT(int aPT) {
        this.aPT = aPT;
    }

    /**
     * @return the attackName
     */
    public String getAttackName() {
        return attackName;
    }

    /**
     * @param attackName the attackName to set
     */
    public void setAttackName(String attackName) {
        this.attackName = attackName;
    }

    /**
     * @return the baseDamage
     */
    public int getBaseDamage() {
        return baseDamage;
    }

    /**
     * @param baseDamage the baseDamage to set
     */
    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    /**
     * @return the baseAccuracy
     */
    public double getBaseAccuracy() {
        return baseAccuracy;
    }

    /**
     * @param baseAccuracy the baseAccuracy to set
     */
    public void setBaseAccuracy(double baseAccuracy) {
        this.baseAccuracy = baseAccuracy;
    }

    /**
     * @return the energyUse
     */
    public double getEnergyUse() {
        return energyUse;
    }

    /**
     * @param energyUse the energyUse to set
     */
    public void setEnergyUse(double energyUse) {
        this.energyUse = energyUse;
    }

    /**
     * @return the minRange
     */
    public double getMinRange() {
        return minRange;
    }

    /**
     * @param minRange the minRange to set
     */
    public void setMinRange(double minRange) {
        this.minRange = minRange;
    }

    /**
     * @return the maxRange
     */
    public double getMaxRange() {
        return maxRange;
    }

    /**
     * @param maxRange the maxRange to set
     */
    public void setMaxRange(double maxRange) {
        this.maxRange = maxRange;
    }

    /**
     * @return the attackNickname
     */
    public String getAttackNickname() {
        return attackNickname;
    }

    /**
     * @param attackNickname the attackNickname to set
     */
    public void setAttackNickname(String attackNickname) {
        this.attackNickname = attackNickname;
    }
    
}
