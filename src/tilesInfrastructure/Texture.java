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
public class Texture {

    private ImageManager textures;
    private BufferedImage tileSheet;
    private HashMap<Integer, String> textureIDs;
    
    private static final int TILE_LENGTH = 16;
    
    private static final String TEXTURE_DIRT = "DIRT";
    private static final String TEXTURE_DIRT_GRASS = "DIRT_GRASS";
    private static final String TEXTURE_GRASS = "GRASS";
    
    private static final String TEXTURE_ROCK_0 = "ROCK_1";
    private static final String TEXTURE_ROCK_1 = "ROCK_2";
    private static final String TEXTURE_WATER = "WATER";
    
    
    public Image getTexture(Integer iD){
        return textures.getImage(textureIDs.get(iD));
    }
    
    public String getTerrainType(Integer iD){
        return textureIDs.get(iD);
    }

    {
        textureIDs = new HashMap<>();
        textureIDs.put(1, TEXTURE_DIRT);
        textureIDs.put(2, TEXTURE_DIRT_GRASS);
        textureIDs.put(3, TEXTURE_GRASS);
        textureIDs.put(4, TEXTURE_ROCK_0);
        textureIDs.put(5, TEXTURE_ROCK_1);
        textureIDs.put(6, TEXTURE_WATER);
                
        textures = new ImageManager();
        tileSheet = (BufferedImage) ResourceTools.loadImageFromResource("resources/textures.png");
        textures.addImage(TEXTURE_DIRT, tileSheet.getSubimage(0, 0, TILE_LENGTH, TILE_LENGTH));
        textures.addImage(TEXTURE_DIRT_GRASS, tileSheet.getSubimage(16, 0, TILE_LENGTH, TILE_LENGTH));
        textures.addImage(TEXTURE_GRASS, tileSheet.getSubimage(32, 0, TILE_LENGTH, TILE_LENGTH));
        textures.addImage(TEXTURE_ROCK_0, tileSheet.getSubimage(0, 16, TILE_LENGTH, TILE_LENGTH));
        textures.addImage(TEXTURE_ROCK_1, tileSheet.getSubimage(16, 16, TILE_LENGTH, TILE_LENGTH));
        textures.addImage(TEXTURE_WATER, tileSheet.getSubimage(32, 16, TILE_LENGTH, TILE_LENGTH));
        
        
    }
    
    
}