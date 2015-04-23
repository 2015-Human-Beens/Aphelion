/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tilesInfrastructure;

import java.awt.Image;

/**
 *
 * @author Benjamin
 */
public interface TileProviderIntf {
    public Image getTileTexture(Integer iD);
    public Image getTileOverlay(Integer iD);
}