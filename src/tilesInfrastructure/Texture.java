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
    private static final String TEXTURE_ROCK_2 = "ROCK_2";
    
    private static final String TEXTURE_DKROCK_0 = "DKROCK_1";
    private static final String TEXTURE_DKROCK_1 = "DKROCK_2";
    private static final String TEXTURE_DKROCK_2 = "DKROCK_3";
    
    private static final String TEXTURE_WATER = "WATER";
    private static final String TEXTURE_VOLCANIC = "VOLCANIC";
    private static final String TEXTURE_LAVA = "LAVA";
    
    private static final String TEXTURE_CRATER_0 = "CRATER_1";
    private static final String TEXTURE_CRATER_1 = "CRATER_2";
    private static final String TEXTURE_CRATER_2 = "CRATER_3";
    
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
        textureIDs.put(6, TEXTURE_ROCK_2);
        textureIDs.put(7, TEXTURE_DKROCK_0);
        textureIDs.put(8, TEXTURE_DKROCK_1);
        textureIDs.put(9, TEXTURE_DKROCK_2);
        textureIDs.put(10, TEXTURE_WATER);
        textureIDs.put(11, TEXTURE_VOLCANIC);
        textureIDs.put(12, TEXTURE_LAVA);
        textureIDs.put(13, TEXTURE_CRATER_0);
        textureIDs.put(14, TEXTURE_CRATER_1);
        textureIDs.put(15, TEXTURE_CRATER_2);
                
        textures = new ImageManager();
//        tileSheet = (BufferedImage) ResourceTools.loadImageFromResource("resources/textures.png");
        tileSheet = (BufferedImage) ResourceTools.loadImageFromResource("resources/texturesBeta.png");

        textures.addImage(TEXTURE_DIRT, tileSheet.getSubimage(0, 0, TILE_LENGTH, TILE_LENGTH));
        textures.addImage(TEXTURE_DIRT_GRASS, tileSheet.getSubimage(16, 0, TILE_LENGTH, TILE_LENGTH));
        textures.addImage(TEXTURE_GRASS, tileSheet.getSubimage(32, 0, TILE_LENGTH, TILE_LENGTH));
       
        textures.addImage(TEXTURE_ROCK_0, tileSheet.getSubimage(0, 16, TILE_LENGTH, TILE_LENGTH));
        textures.addImage(TEXTURE_ROCK_1, tileSheet.getSubimage(16, 16, TILE_LENGTH, TILE_LENGTH));
        textures.addImage(TEXTURE_ROCK_2, tileSheet.getSubimage(32, 16, TILE_LENGTH, TILE_LENGTH));
       
        textures.addImage(TEXTURE_DKROCK_0, tileSheet.getSubimage(0, 32, TILE_LENGTH, TILE_LENGTH));
        textures.addImage(TEXTURE_DKROCK_1, tileSheet.getSubimage(16, 32, TILE_LENGTH, TILE_LENGTH));
        textures.addImage(TEXTURE_DKROCK_2, tileSheet.getSubimage(32, 32, TILE_LENGTH, TILE_LENGTH));

        textures.addImage(TEXTURE_WATER, tileSheet.getSubimage(0, 48, TILE_LENGTH, TILE_LENGTH));
        textures.addImage(TEXTURE_VOLCANIC, tileSheet.getSubimage(16, 48, TILE_LENGTH, TILE_LENGTH));
        textures.addImage(TEXTURE_LAVA, tileSheet.getSubimage(32, 48, TILE_LENGTH, TILE_LENGTH));
        
        textures.addImage(TEXTURE_CRATER_0, tileSheet.getSubimage(0, 64, TILE_LENGTH, TILE_LENGTH));
        textures.addImage(TEXTURE_CRATER_1, tileSheet.getSubimage(16, 64, TILE_LENGTH, TILE_LENGTH));
        textures.addImage(TEXTURE_CRATER_2, tileSheet.getSubimage(32, 64, TILE_LENGTH, TILE_LENGTH));
       
    }
    
    
}