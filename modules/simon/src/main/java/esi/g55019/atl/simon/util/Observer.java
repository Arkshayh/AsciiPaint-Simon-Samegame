package esi.g55019.atl.simon.util;

/**
 * An Observer object in the Observer/Observable pattern.
 * Essentially presents an update method which permits the Observable
 * to notify it of any changes.
 *
 * @author Cotton Ian | g55019
 */

public interface Observer {

    /**
     * This method is called whenever the observed object has changed.
     */
    void update();
}
