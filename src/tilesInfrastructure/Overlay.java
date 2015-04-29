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
public class Overlay {
    private ImageManager overlays;
    private BufferedImage tileSheet;
    private HashMap<Integer, String> overlayIDs;
    
    private static final int OVERLAY_SIDE_LENGTH = 16;
    
    private static final String OVERLAY_FOG = "FOG";
    
    private static final String OVERLAY_CRATER = "CRATER";
    private static final String OVERLAY_ICE = "ICE";
    
    public Image getOverlay(Integer iD){
        return overlays.getImage(overlayIDs.get(iD));
    }
    
    public String getOverlayType(Integer iD){
        return overlayIDs.get(iD);
    }

    {
        overlayIDs = new HashMap<>();
        overlayIDs.put(1, OVERLAY_FOG);
        
        overlayIDs.put(3, OVERLAY_CRATER);
        overlayIDs.put(4, OVERLAY_ICE);
        
        overlays = new ImageManager();
        tileSheet = (BufferedImage) ResourceTools.loadImageFromResource("resources/overlays.png");
        
        overlays.addImage(OVERLAY_FOG, tileSheet.getSubimage(0, 0, OVERLAY_SIDE_LENGTH, OVERLAY_SIDE_LENGTH));
        
        overlays.addImage(OVERLAY_CRATER, tileSheet.getSubimage(32, 0, OVERLAY_SIDE_LENGTH, OVERLAY_SIDE_LENGTH));
        overlays.addImage(OVERLAY_ICE, tileSheet.getSubimage(0, 16, OVERLAY_SIDE_LENGTH, OVERLAY_SIDE_LENGTH));
        
        
    }
}
