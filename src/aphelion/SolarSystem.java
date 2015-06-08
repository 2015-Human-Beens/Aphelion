/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aphelion;

import java.awt.Point;
import java.util.ArrayList;
import tilesInfrastructure.TileMap;

/**
 *
 * @author Benjamin
 */
public class SolarSystem {
    
    public SolarSystem(int[][] solarMap, SystemInterface systemInterface){
        this.solarMap = solarMap; this.systemInterface = systemInterface;
        planetMaps = new ArrayList<>();
    }
    
    private ArrayList<TileMap> planetMaps;
    private int[][] solarMap;
    private SystemInterface systemInterface;

    /**
     * @return the planetMaps
     */
    public ArrayList<TileMap> getPlanetMaps() {
        return planetMaps;
    }

    /**
     * @param planetMaps the planetMaps to set
     */
    public void setPlanetMaps(ArrayList<TileMap> planetMaps) {
        this.planetMaps = planetMaps;
    }

    /**
     * @return the solarMap
     */
    public int[][] getSolarMap() {
        return solarMap;
    }

    /**
     * @param solarMap the solarMap to set
     */
    public void setSolarMap(int[][] solarMap) {
        this.solarMap = solarMap;
    }

    /**
     * @return the systemInterface
     */
    public SystemInterface getSystemInterface() {
        return systemInterface;
    }

    /**
     * @param systemInterface the systemInterface to set
     */
    public void setSystemInterface(SystemInterface systemInterface) {
        this.systemInterface = systemInterface;
    }
    
    public void addPlanetTileMap(){
        if (planetMaps == null) {
            planetMaps = new ArrayList<>();
        }
        TileMap newPlanetMap = systemInterface.createPlanetMap(this, new Point(0, 0));
        planetMaps.add(newPlanetMap);
    }
    
    
}
