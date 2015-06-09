/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aphelion;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Benjamin
 */
public interface DrawDataIntf {
    int getCellHeight();
    int getCellWidth();
    int getColumns();
    int getRows();
    Point getCellSystemCoordinate(Point cellLocation);
}
