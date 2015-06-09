/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tilesInfrastructure;

import images.ImageManager;
import images.ResourceTools;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 *
 * @author Benjamin
 */
public class SystemTexture {
    
    private final ImageManager sysTextures;
    private final BufferedImage tileSheet;
    private final HashMap<Integer, String> sysTextureIDs;
    
    private static final int TILE_LENGTH = 16;
    
    private static final String TEXTURE_SPACE_0 = "SPACE_1";
    private static final String TEXTURE_SPACE_1 = "SPACE_2";
    private static final String TEXTURE_SPACE_2 = "SPACE_3";
    
    public Image getSysTexture(Integer iD){
        return sysTextures.getImage(sysTextureIDs.get(iD));
    }
    
    public String getSystemTileType(Integer iD){
        return sysTextureIDs.get(iD);
    }
    
    public static int spaceTexture(){
        return ((int) (Math.random() * 3) + 1) * 100;
    }
//    
//    public static int dRockTerrain(){
//        return ((int) (Math.random() * 3) + 7) * 100;
//    }
//    
//    public static int crateredTerrain(){
//        return ((int) (Math.random() * 3) + 13) * 100;
//    }

    {
        sysTextureIDs = new HashMap<>();
        sysTextureIDs.put(1, TEXTURE_SPACE_0);
        sysTextureIDs.put(2, TEXTURE_SPACE_1);
        sysTextureIDs.put(3, TEXTURE_SPACE_2);

        sysTextures = new ImageManager();
        tileSheet = (BufferedImage) ResourceTools.loadImageFromResource("resources/systemTextures.png");
        
        sysTextures.addImage(TEXTURE_SPACE_0, tileSheet.getSubimage(0, 0, TILE_LENGTH, TILE_LENGTH));
        sysTextures.addImage(TEXTURE_SPACE_1, tileSheet.getSubimage(16, 0, TILE_LENGTH, TILE_LENGTH));
        sysTextures.addImage(TEXTURE_SPACE_2, tileSheet.getSubimage(32, 0, TILE_LENGTH, TILE_LENGTH));
    }
    
}
