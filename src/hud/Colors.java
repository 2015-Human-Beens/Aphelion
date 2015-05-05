/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hud;

import java.awt.Color;

/**
 *
 * @author kevin.lawrence
 */
public class Colors {

    private StatusProviderIntf statusProvider;
    public static Color HUD_BLUE = new Color(0, 230, 220);
    public static Color HUD_GREY = new Color(80, 80, 80);
    public static Color HUD_GREY_TRANSPARENT = new Color(80, 80, 80, 200);
    
    public static Color HUD_PANEL = new Color(100, 240, 240, 80); 
    public static Color HUD_DETAIL = new Color(240, 240, 240, 128); 
    
    public Color CHANGE_COLOR = new Color(changeRed(), changeGreen(), changeBlue());
    
    private int blue = 255;
    private int green = 255;
    private int red = 0;
    private int changeBlue;
    private int changeRed;
    private int changeGreen;
    
    private int changeBlue(){
        if (blue >= 0 && blue <= 255 && green == 255 && red == 0) {
            changeBlue = (int) (blue * (statusProvider.getStatus() / 3));
        }
        return changeBlue;
    }
    private int changeRed(){
        if (red >= 0 && red <= 255 && blue == 0 && green == 255) {
            changeRed = 0 + ((int) (255 * (statusProvider.getStatus() / 3)));
        }
        return red;
    }
    private int changeGreen(){
        if (green >= 0 && green <= 0 && blue == 0 && red == 0) {
            changeGreen = (int) (255 * (statusProvider.getStatus() / 3));
        }
        return green;
    }
}
