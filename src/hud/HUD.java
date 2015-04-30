/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hud;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

///**
// *
// * @author kevin.lawrence
// */
//public class HUD implements ParentComponentIntf {
//
//    private static Color color = new Color(100, 240, 240, 80); 
//    
//    public void paint(Graphics graphics) {
//        if (visible) {
//            graphics.setColor(color);
//            graphics.fill3DRect(position.x, position.y, size.width, size.height, true);
//
//            getComponents().stream().forEach((HUDComponent component) -> {
//                component.paint(graphics);
//            });
//        }
//    }
//
////<editor-fold defaultstate="collapsed" desc="Constructors">
//    {
//        setComponents(new ArrayList<>());
//        visible = true;
//    }
//
//    public HUD(Point position, Dimension size) {
//        this.position = position;
//        this.size = size;
//    }
////</editor-fold>
//
////<editor-fold defaultstate="collapsed" desc="Properties">
//    private Point position;
//    private final Dimension size;
//    private boolean visible;
//    private boolean extended = true;
//
//    private ArrayList<HUDComponent> components;
//
//    /**
//     * @param position the position to set
//     */
//    public void setPosition(Point position) {
//        this.position = position;
//    }
//
////<editor-fold defaultstate="collapsed" desc="ParentComponentIntf">
//    /**
//     * @return the position
//     */
//    @Override
//    public Point getPositon() {
//        return position;
//    }
//
//    /**
//     * @return the visible
//     */
//    @Override
//    public boolean isVisible() {
//        return visible;
//    }
////</editor-fold>
//
//    /**
//     * @param visible the visible to set
//     */
//    public void setVisible(boolean visible) {
//        this.visible = visible;
//    }
//    
//    public int getRetractedX(){
//        return -this.size.width;
//    }
//
//    /**
//     * @param component the components to add
//     */
//    public void addComponent(HUDComponent component) {
//        component.setParent(this);
//        getComponents().add(component);
//    }
//
//    /**
//     * @param component the components to add
//     */
//    public void removeComponent(HUDComponent component) {
//        getComponents().remove(component);
//        component.clearParent();
//    }
//
//    /**
//     * @return the components
//     */
//    public ArrayList<HUDComponent> getComponents() {
//        return components;
//    }
//
//    /**
//     * @param components the components to set
//     */
//    public void setComponents(ArrayList<HUDComponent> components) {
//        this.components = components;
//    }
////</editor-fold>
//
//    /**
//     * @return the extended
//     */
//    public boolean isExtended() {
//        return extended;
//    }
//
//    /**
//     * @param extended the extended to set
//     */
//    public void setExtended(boolean extended) {
//        this.extended = extended;
//    }
//
//}
//=======
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package hud;
//
//import java.awt.Dimension;
//import java.awt.Graphics;
//import java.awt.Point;
//import java.util.ArrayList;

/**
 *
 * @author kevin.lawrence
 */
public class HUD implements PositionProviderIntf, EventListenerIntf {

//<editor-fold defaultstate="collapsed" desc="EventListenerIntf">
    @Override
    public void onEvent(String eventType) {
//        System.out.println(String.format("Event (%s)", eventType));

        if (eventType.equals(HUDEvent.OPEN_CLOSE_COMMAND)) {
            if (state.isOpen()) {
                close();
            } else {
                open();
            }
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Methods">
    private void close() {
        setPosition(state.getClosedPosition());
        state.setOpen(false);
    }
    
    private void open() {
        setPosition(state.getOpenPosition());
        state.setOpen(true);
    }
    
    public void paint(Graphics graphics) {
        if (visible) {
            graphics.setColor(Colors.HUD_PANEL);
            graphics.fill3DRect(position.x, position.y, size.width, size.height, true);
            
            getComponents().stream().forEach((HUDComponent component) -> {
                component.paint(graphics);
            });
        }
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Constructors">
    {
        components = new ArrayList<>();
        mouseEventListeners = new ArrayList<>();
        visible = true;
    }

    public HUD(Point position, Dimension size, HUDState state) {
        this.position = position;
        this.size = size;
        this.state = state;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Properties">
    private Point position;
    private Dimension size;
    private boolean visible;
    private HUDState state;

    private ArrayList<HUDComponent> components;
    private ArrayList<MouseEventListenerIntf> mouseEventListeners;
    

    /**
     * @param position the position to set
     */
    public void setPosition(Point position) {
        this.position = position;
    }

    //<editor-fold defaultstate="collapsed" desc="PositionProviderIntf">
    /**
     * @return the position
     */
    @Override
    public Point getPosition() {
        return position;
    }

//</editor-fold>
    /**
     * @param visible the visible to set
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * @return the visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * @param component the components to add
     */
    public void addComponent(HUDComponent component) {
        component.setPositionProvider(this);
        getComponents().add(component);

        if (component instanceof MouseEventListenerIntf){
            addMouseEventListener((MouseEventListenerIntf) component);
        }
    }

    /**
     * @param component the components to add
     */
    public void removeComponent(HUDComponent component) {
        getComponents().remove(component);
        component.clearPositionProvider();
    }

    /**
     * @return the components
     */
    public ArrayList<HUDComponent> getComponents() {
        return components;
    }

    /**
     * @param components the components to set
     */
    public void setComponents(ArrayList<HUDComponent> components) {
        this.components = components;
    }
    
    public ArrayList<MouseEventListenerIntf> getMouseEventListeners(){
        return mouseEventListeners;
    }
    
    public void addMouseEventListener(MouseEventListenerIntf mouseEventListener){
        this.mouseEventListeners.add(mouseEventListener);
    }
    
    public void removeMouseEventListeners(ArrayList<MouseEventListenerIntf> mouseEventListeners){
        this.mouseEventListeners.removeAll(mouseEventListeners);
    }
    
    
    public void getMouseEventListeners(MouseEventListenerIntf mouseEventListener){
        if (mouseEventListener == null){
            mouseEventListeners = new ArrayList<>();
        }
        mouseEventListeners.add(mouseEventListener);
    }
//</editor-fold>

}