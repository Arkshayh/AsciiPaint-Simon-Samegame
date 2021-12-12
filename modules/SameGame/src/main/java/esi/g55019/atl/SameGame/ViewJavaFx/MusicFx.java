package esi.g55019.atl.SameGame.ViewJavaFx;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represent a playlist. A list of all song that you can use.
 */
public class MusicFx {
    private List<MediaPlayer> playList;
    private List<String> playListName;

    /**
     * Constructor
     */
    public MusicFx() {
        playList = new ArrayList<>();
        playListName = new ArrayList<>();
    }

    /**
     * Add a sound to the playlist
     * @param media Media
     * @param name String
     */
    public void add(Media media, String name){
        playList.add(new MediaPlayer(media));
        playListName.add(name);
    }

    /**
     * Play a sound. You need the name of the song to play it.
     * @param name
     */
    public void playSound(String name){
        for (int i = 0; i < playListName.size(); i++) {
            if(playListName.get(i).equals(name)){
                playList.get(i).play();
                break;
            }
        }
    }

    /**
     * Play the sound again and again. You need the name of the sound to play it
     * @param name
     */
    public void playMusicLoop(String name){
        for (int i = 0; i < playListName.size(); i++) {
            if(playListName.get(i).equals(name)){
                int finalI = i;
                playList.get(i).setOnEndOfMedia(new Runnable() {
                    public void run() {
                        playList.get(finalI).seek(Duration.ZERO);
                    }
                });
                playList.get(i).play();
                break;
            }
        }
    }

    /**
     * stop every music playing
     */
    public void stop(){
        for (int i = 0; i < playList.size(); i++) {
            try{
                playList.get(i).stop();
            }
            catch (Exception e){

            }
        }
    }

    /**
     * set the volume of all music playing
     * @param volume
     */
    public void setVolume(double volume){
        for (int i = 0; i < playList.size(); i++) {
            try{
                playList.get(i).setVolume(volume);
            }
            catch (Exception e){

            }
        }
    }
}
