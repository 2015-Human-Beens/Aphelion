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
public class Weapon extends InventoryItem {
    
    public Weapon(String weaponType){
        super(InventoryItem.WEAPON);
        
        this.weaponType = weaponType; 
        
        switch (weaponType){
            case Weapon.TYPE_PISTOL:
                setBaseAttack(5);
                setAttackType(Weapon.ATTACK_PROJECTILE);
                setClipSize(8);
                break;
            case Weapon.TYPE_RAILGUN:
                setBaseAttack(10);
                setAttackType(Weapon.ATTACK_ENERGY);
                setClipSize(3);
                break;
        }
    }
    
    /**
     * 
     */
    
    private String weaponType = null;
    public static final String TYPE_PISTOL = "Pistol";
    public static final String TYPE_RAILGUN = "Railgun";
    
    private String attackType = Weapon.ATTACK_PROJECTILE;    
    public static final String ATTACK_PROJECTILE = "Projectile";
    public static final String ATTACK_ENERGY = "Energy";
    public static final String ATTACK_CHEMICAL = "Chemical";
        
    private int baseAttack = 0;
    
    private int clipSize = 1;
    
    /**
     * Might add clip size instead of ammunition, also may need cycle time
     */

    /**
     * @return the baseAttack
     */
    public int getBaseAttack() {
        return baseAttack;
    }

    /**
     * @param baseAttack the baseAttack to set
     */
    public void setBaseAttack(int baseAttack) {
        this.baseAttack = baseAttack;
    }

    /**
     * @return the attackType
     */
    public String getAttackType() {
        return attackType;
    }

    /**
     * @param attackType the attackType to set
     */
    public void setAttackType(String attackType) {
        this.attackType = attackType;
    }

    /**
     * @return the clipSize
     */
    public int getClipSize() {
        return clipSize;
    }

    /**
     * @param clipSize the clipSize to set
     */
    public void setClipSize(int clipSize) {
        this.clipSize = clipSize;
    }

    /**
     * @return the weaponType
     */
    public String getWeaponType() {
        return weaponType;
    }

    /**
     * @param weaponType the weaponType to set
     */
    public void setWeaponType(String weaponType) {
        this.weaponType = weaponType;
    }
    
}
