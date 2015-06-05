/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aphelion;

import audio.Playlist;
import audio.SoundManager;
import audio.Source;
import audio.Track;
import java.util.ArrayList;

/**
 *
 * @author YazGruno
 */
public class AphelionSoundPlayer {
    private final SoundManager soundManager;
    
    public AphelionSoundPlayer() {
        
        Playlist myPlaylist = new Playlist(getTracks());
        soundManager = new SoundManager(myPlaylist); //, new AudioEventListener())
    }

    public static final String DARK_TIMES = "Dark Times";
    public static final String LOST_FRONTIER = "Lost Frontier";
    public static final String PAST_THE_EDGE_2 = "Past the Edge 2";
    public static final String PEPPERS_THEME = "Pepper's Theme";
    public static final String WATER_LILY = "Water Lily";    
    
    public static final String ATTACK_HIT_1 = "Attack Hit 1";
    public static final String ATTACK_HIT_2 = "Attack Hit 2";        
    public static final String BUTTON_GENERAL = "Button General";
    public static final String DEEP_GROWL = "Deep Growl";
    public static final String DENY = "Deny";
    public static final String HUD_ROLLOVER = "HUD Rollover";
    public static final String HIGH_PICKUP = "High Pickup";
    public static final String LASER_GUN = "Laser Gun";
    public static final String MECHANIC_PLACEMENT = "Mechanic Placement";
    public static final String MENU_ROLLOVER = "Menu Rollover";
    public static final String RINGING_RASP = "Ringing Rasp";
    
    
    private ArrayList<Track> getTracks() {
        ArrayList<Track> tracks = new ArrayList<>();
        
        tracks.add(new Track(DARK_TIMES, Source.RESOURCE, "/resources/Dark_Times.wav"));
        tracks.add(new Track(LOST_FRONTIER, Source.RESOURCE, "/resources/Lost_Frontier.wav"));
        tracks.add(new Track(PAST_THE_EDGE_2, Source.RESOURCE, "/resources/Past_the_Edge 2.wav"));
        tracks.add(new Track(PEPPERS_THEME, Source.RESOURCE, "/resources/Pepper's_Theme_Full_Mix.wav"));
        tracks.add(new Track(WATER_LILY, Source.RESOURCE, "/resources/Water_Lily.wav"));
        
        tracks.add(new Track(ATTACK_HIT_1, Source.RESOURCE, "/resources/Attack Hit 1.wav"));
        tracks.add(new Track(ATTACK_HIT_2, Source.RESOURCE, "/resources/Attack Hit 1.wav"));
        tracks.add(new Track(BUTTON_GENERAL, Source.RESOURCE, "/resources/Button General.wav"));
        tracks.add(new Track(DEEP_GROWL, Source.RESOURCE, "/resources/Deep Growl.wav"));
        tracks.add(new Track(DENY, Source.RESOURCE, "resources/Deny Sound New.wav"));
        tracks.add(new Track(HUD_ROLLOVER, Source.RESOURCE, "resources/HUD Rollover.wav"));
        tracks.add(new Track(HIGH_PICKUP, Source.RESOURCE, "resources/High Pickup.wav"));
        tracks.add(new Track(LASER_GUN, Source.RESOURCE, "resources/Laser Gun.wav"));
        tracks.add(new Track(MECHANIC_PLACEMENT, Source.RESOURCE, "resources/Mechanic_placement.wav"));
        tracks.add(new Track(MENU_ROLLOVER, Source.RESOURCE, "resources/Menu rollover.wav"));
        tracks.add(new Track(RINGING_RASP, Source.RESOURCE, "resources/Ringing Rasp.wav"));
        
        
        return tracks;
    }
    
    public void play(String trackName) {
        soundManager.play(trackName);
    }
    
    public void stop(String trackName) {
        soundManager.stop(trackName);
    }
    
}
