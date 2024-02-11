package src;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * The MediaManager class is responsible for managing and playing sound effects in the game.
 */
public class MediaManager {
    private static final String PATH = Settings.ASSETS_PATH + "effects/";
    private static MediaPlayer titleScreenSound;
    private static MediaPlayer introSound;
    private static MediaPlayer ammoSound;
    private static MediaPlayer birdFallingSound;
    private static MediaPlayer levelWinSound;
    private static MediaPlayer gameOverSound;
    private static MediaPlayer gameWinSound;

    // Initialize all the sounds
    static {
        Media titleScreenSoundMedia = new Media(MediaManager.class.getResource(PATH + "Title.mp3").toString());
        Media introSoundMedia = new Media(MediaManager.class.getResource(PATH + "Intro.mp3").toString());
        Media ammoSoundMedia = new Media(MediaManager.class.getResource(PATH + "GunShot.mp3").toString());
        Media birdFallingSoundMedia = new Media(MediaManager.class.getResource(PATH + "DuckFalls.mp3").toString());
        Media levelWinSoundMedia = new Media(MediaManager.class.getResource(PATH + "LevelCompleted.mp3").toString());
        Media gameOverSoundMedia = new Media(MediaManager.class.getResource(PATH + "GameOver.mp3").toString());
        Media gameWinSoundMedia = new Media(MediaManager.class.getResource(PATH + "GameCompleted.mp3").toString());
        
        titleScreenSound = new MediaPlayer(titleScreenSoundMedia);
        introSound = new MediaPlayer(introSoundMedia);
        ammoSound = new MediaPlayer(ammoSoundMedia);
        birdFallingSound = new MediaPlayer(birdFallingSoundMedia);
        levelWinSound = new MediaPlayer(levelWinSoundMedia);
        gameOverSound = new MediaPlayer(gameOverSoundMedia);
        gameWinSound = new MediaPlayer(gameWinSoundMedia);
    }

    /**
     * Plays the title screen sound effect.
     */
    public static void playTitleScreenSound() {
        titleScreenSound.setVolume(Settings.VOLUME);
        // play on loop
        titleScreenSound.setCycleCount(MediaPlayer.INDEFINITE);
        titleScreenSound.play();
    }

    /**
     * Stops the title screen sound effect.
     */
    public static void stopTitleScreenSound() {
        titleScreenSound.stop();
    }

    /*
     * Returns the intro sound effect.
     * @return the intro sound effect
     */
    public static MediaPlayer returnIntroSound() {
        introSound.setVolume(Settings.VOLUME);
        return introSound;
    }

    /*
     * Plays the ammo sound effect.
     */
    public static void playAmmoSound() {
        ammoSound.stop();
        ammoSound.setVolume(Settings.VOLUME);
        ammoSound.play();
    }

    /*
     * Plays the bird falling sound effect.
     */
    public static void playBirdFallingSound() {
        birdFallingSound.stop();
        birdFallingSound.setVolume(Settings.VOLUME);
        birdFallingSound.play();
    }

    /*
     * Plays the level win sound effect.
     */
    public static void playLevelWinSound() {
        levelWinSound.setVolume(Settings.VOLUME);
        levelWinSound.play();
    }

    /*
     * Plays the game over sound effect.
     */
    public static void playGameOverSound() {
        gameOverSound.setVolume(Settings.VOLUME);
        gameOverSound.play();
    }

    /*
     * Plays the game win sound effect.
     */
    public static void playGameWinSound() {
        gameWinSound.setVolume(Settings.VOLUME);
        gameWinSound.play();
    }

    /*
     * Stops all the sounds.
     */
    public static void stopAllSounds() {
        titleScreenSound.stop();
        ammoSound.stop();
        birdFallingSound.stop();
        levelWinSound.stop();
        gameOverSound.stop();
        gameWinSound.stop();
    }
}