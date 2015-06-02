/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import java.util.ArrayList;

/**
 *
 * @author Benjamin
 */
public class Weapon extends InventoryItem {
    
    public Weapon(String weaponType, ArrayList<WeaponAttack> attacks, String attackType, int clipSize){
        super(InventoryItem.WEAPON);
        this.attacks = attacks;
        this.weaponType = weaponType;
        this.attackType = attackType;
        this.clipSize = clipSize;
    }
    
    public static Weapon createWeapon(String weaponType){
        ArrayList<WeaponAttack> attacks = new ArrayList<>();
        int clipSize = 0;
        String attackType = "";
        
        //       Weapon attack format
        //       String attackName, int aPT, double energyUse, double minRange, 
        //       double maxRange, int baseDamage, double baseAccuracy
        
        switch (weaponType){
            case Weapon.TYPE_TD_PISTOL:
                attacks.add(new WeaponAttack("Semi Automatic", "Almost automatic", 3, 1.0, 10.0, 40.0, 5, .55));
                attacks.add(new WeaponAttack("Fully Automatic" ,"Fully Automatic Pistol, Baby!", 8, 1.0, 4.0, 15.0, 4, .6));
                attacks.add(new WeaponAttack("Melee", "Pistol Whip", 0, 0.0, 0.0, 1.0, 2, .95));
                attackType = Weapon.ATTACK_PROJECTILE;
                clipSize = 8;
                break;
            case Weapon.TYPE_RAILGUN:
                attacks.add(new WeaponAttack("Single Burst", "Get Wrecked", 1, 15.0, 25.0, 75.0 , 50, .25));
                attacks.add(new WeaponAttack("Sustained Burst", "Sustained Destruction", 1, 30.0, 15.0, 100.0 , 18, .80));
                attackType = Weapon.ATTACK_ENERGY;
                clipSize = 3;
                break;
            case Weapon.TYPE_ASSAULT_RIFLE:
                attacks.add(new WeaponAttack("Semi Automatic", "Semi-Auto", 5, 1.0, 20.0, 60.0 , 16, .3));
                attacks.add(new WeaponAttack("Fully Automatic", "GAHHHHHHHH", 20, 1.0, 6.0, 25.0 , 15, .14));
                attacks.add(new WeaponAttack("Melee", "Buttstroke", 0, 0.0, 0.0, 2.0 , 5, .86));
                attackType = Weapon.ATTACK_PROJECTILE;
                clipSize = 50;
                break;
            case Weapon.TYPE_FLAMETHROWER:
                attacks.add(new WeaponAttack("Sustained Burst", "Burn, baby, burn", 1, 30.0, 4.0, 20.0 , 30, .9));
                attackType = Weapon.ATTACK_FIRE;
                clipSize = 50;
                break;
            case TYPE_CORRODER_RIFLE:
                attacks.add(new WeaponAttack("Single Shot", "Have some acid", 2, 2.0, 10.0, 50.0 , 16, .4));
                attackType = Weapon.ATTACK_CHEMICAL;
                clipSize = 12;
                break;
            default:
                createWeapon(Weapon.TYPE_TD_PISTOL);
                break;
        }
        return new Weapon(weaponType, attacks, attackType, clipSize);
    }
    
    private String weaponType = null;
    public static final String TYPE_FLAMETHROWER = "H337 Flamer";
    public static final String TYPE_TD_PISTOL = "Trusty Pistol";
    public static final String TYPE_RAILGUN = "Annihilator Railgun";
    public static final String TYPE_ASSAULT_RIFLE = "M813 Assault Rifle";
    public static final String TYPE_CORRODER_RIFLE = "HA-AA Corrosive Rifle"; //Highly Acidic-Anti Armor
    
    private String attackType = Weapon.ATTACK_PROJECTILE;    
    public static final String ATTACK_PROJECTILE = "Projectile";
    public static final String ATTACK_ENERGY = "Energy";
    public static final String ATTACK_CHEMICAL = "Chemical";
    public static final String ATTACK_FIRE = "Fire";
    
    private ArrayList<WeaponAttack> attacks;
    private int clipSize = 1;
    
    /**
     * Might add clip size instead of ammunition, also may need cycle time
     */

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

    /**
     * @return the attacks
     */
    public ArrayList<WeaponAttack> getAttacks() {
        return attacks;
    }

    /**
     * @param attacks the attacks to set
     */
    public void setAttacks(ArrayList<WeaponAttack> attacks) {
        this.attacks = attacks;
    }
    
}
