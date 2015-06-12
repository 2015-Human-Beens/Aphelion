/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aphelion;

import java.awt.Point;
import java.util.ArrayList;
import map.Map;
import tilesInfrastructure.SystemTexture;

/**
 *
 * @author Benjamin
 */
public class Generate {
    
    public static int[][] randomContinents() {
        int BACKGROUND_TERRAIN = 1000;
        int CONTINENT_TERRAIN = 200;
        int BEACH_TERRAIN = 100;

        int[][] array = new int[120][70];
        // Background terrain type
        for (int col = 0; col < array.length; col++) {
            for (int row = 0; row < array[col].length; row++) {
                array[col][row] = BACKGROUND_TERRAIN;
            }
        }
        ArrayList<Point> sparks = new ArrayList<>();
        int continents = (int) (Math.random() * 4) + 2;
        for (int i = 0; i < continents; i++) {
            int x = (int) (Math.random() * (array.length));
            int y = (int) (Math.random() * (array[x].length));
            sparks.add(new Point(x, y));
        }

        for (int k = 0; k < sparks.size(); k++) {
            ArrayList<Point> bumps = new ArrayList<>();
            int radius = (int) ((Math.random() * 9) + 8);
            for (int i = -radius; i <= radius; i++) {
                for (int j = -(radius - Math.abs(i)); j <= radius - Math.abs(i); j++) {
                    int newX = sparks.get(k).x + j;
                    int newY = sparks.get(k).y + i;
                    if ((newX >= 0) && (newX < array.length) && (newY >= 0) && (newY < array[0].length)) {
                        array[newX][newY] = CONTINENT_TERRAIN;
                        if (Math.abs(newX - sparks.get(k).x) + Math.abs(newY - sparks.get(k).y) == radius) {
                            array[newX][newY] = BEACH_TERRAIN;
                            if (Math.random() > .76) {
                                bumps.add(new Point(newX, newY));
                            }
                        }
                    }
                }
            }
            int randomBump = (int) (Math.random() * bumps.size() - 1);

            for (int l = 0; l < bumps.size(); l++) {
                int bumpRadius = 0;
                if (bumps.indexOf(l) == randomBump) {
                    bumpRadius = (int) ((Math.random() * 5) + 6);
                } else {
                    bumpRadius = (int) ((Math.random() * 4) + 1);
                }
                for (int i = -bumpRadius; i <= bumpRadius; i++) {
                    for (int j = -(bumpRadius - Math.abs(i)); j <= bumpRadius - Math.abs(i); j++) {
                        int newX = bumps.get(l).x + j;
                        int newY = bumps.get(l).y + i;
                        if ((newX >= 0) && (newX <= array.length - 1) && (newY >= 0) && (newY <= array[0].length - 1)) {
                            array[newX][newY] = CONTINENT_TERRAIN;
                            if (Math.abs(newX - bumps.get(l).x) + Math.abs(newY - bumps.get(l).y) == bumpRadius) {
                                array[newX][newY] = BEACH_TERRAIN;
                            }
                        }
                    }
                }
            }
        }
        return array;
    }

    public static int[][] solarSystem() {
        int[][] array = new int[120][70];
        // Background terrain type
        for (int col = 0; col < array.length; col++) {
            for (int row = 0; row < array[col].length; row++) {
                array[col][row] = SystemTexture.spaceTexture();
            }
        }

        return array;
    }
    
    public static Point randomPointIn(int[][] map) {
        int x = (int) (Math.random() * (map.length));
        int y = (int) (Math.random() * (map[x].length));
        return new Point(x, y);
    }
    
}
