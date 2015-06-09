/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aphelion;

import java.awt.Point;
import tilesInfrastructure.TileMap;

/**
 *
 * @author Benjamin
 */
public interface SystemInterface {
    public TileMap createPlanetMap(SolarSystem system, Point location);
}
