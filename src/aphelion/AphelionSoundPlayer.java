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
    
    private ArrayList<Track> getTracks() {
        ArrayList<Track> tracks = new ArrayList<>();
        
        tracks.add(new Track(DARK_TIMES, Source.RESOURCE, "/resources/Dark_Times.wav"));
        tracks.add(new Track(LOST_FRONTIER, Source.RESOURCE, "/resources/Lost_Frontier.wav"));
        tracks.add(new Track(PAST_THE_EDGE_2, Source.RESOURCE, "/resources/Past_the_Edge 2.wav"));
        tracks.add(new Track(PEPPERS_THEME, Source.RESOURCE, "/resources/Pepper's_Theme_Full_Mix.wav"));
        tracks.add(new Track(WATER_LILY, Source.RESOURCE, "/resources/Water_Lily.wav"));
        
        return tracks;
    }
    
    public void play(String trackName) {
        soundManager.play(trackName);
    }
    
    public void stop(String trackName) {
        soundManager.stop(trackName);
    }
    
}
