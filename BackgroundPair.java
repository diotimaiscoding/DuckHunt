import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The BackgroundPair class manages the background and crosshair options for the game.
 * It provides methods to switch between different background images and crosshair options,
 * as well as retrieving the current selections.
 *
 * @see BackgroundPairCreate
 */
public class BackgroundPair {

    /**
     * The path to the assets folder.
     */
    private static final String PATH = Settings.ASSETS_PATH;
    /**
     * The number of background images.
     */
    private static final int NUM_IMAGES = 6;
    /**
     * The number of crosshair options.
     */
    private static final int NUM_COLORS = 7;

    /**
     * The index of the current background pair.
     */
    private static int currentPair = 0;
    /**
     * The index of the current crosshair option.
     */
    private static int currentCrossHair = 0;

    /**
     * An array of background pairs.
     */
    private static BackgroundPairCreate[] backgroundOptions = new BackgroundPairCreate[NUM_IMAGES];
    /**
     * An array of crosshair options.
     */
    private static Image[] crossHairOptions = new Image[NUM_COLORS];

    /**
     * The current background pair.
     */
    private static BackgroundPairCreate background;
    /**
     * The current crosshair option.
     */
    private static Image crossHair;

    static {
        loadBackgrounds();
    }

    /**
     * Loads the background images and crosshair options from the assets folder.
     * Each background pair consists of a background image and a foreground image.
     */
    private static void loadBackgrounds() {
        for (int i = 1; i < NUM_IMAGES+1; i++) {
            backgroundOptions[i-1] = new BackgroundPairCreate(PATH + "background/" + i + ".png", PATH + "foreground/" + i + ".png");
        }
        for (int i = 1; i < NUM_COLORS+1; i++) {
            Image newCrossHair = new Image(PATH + "crosshair/" + i + ".png");
            crossHairOptions[i-1] = newCrossHair;
        }
    }

    /**
     * Switches to the next background image in the sequence.
     * Wraps around to the first image if the end is reached.
     */
    public static void nextBackground() {
        background = backgroundOptions[++currentPair%NUM_IMAGES];
    }

    /**
     * Switches to the previous background image in the sequence.
     * Wraps around to the last image if the beginning is reached.
     */
    public static void previousBackground() {
        if (currentPair == 0) {
            currentPair += NUM_IMAGES;
        }
        background = backgroundOptions[--currentPair%NUM_IMAGES];
    }
    
    /**
     * Switches to the next crosshair option in the sequence.
     * Wraps around to the first option if the end is reached.
     */
    public static void nextCrossHair() {
        crossHair = crossHairOptions[++currentCrossHair%NUM_COLORS];
    }

    /**
     * Switches to the previous crosshair option in the sequence.
     * Wraps around to the last option if the beginning is reached.
     */
    public static void previousCrossHair() {
        if (currentCrossHair == 0) {
            currentCrossHair += NUM_COLORS;
        }
        crossHair = crossHairOptions[--currentCrossHair%NUM_COLORS];
    }

    /**
     * Retrieves the current background pair.
     * If no background pair is set, returns the first background pair.
     *
     * @return The current background pair.
     */
    public static BackgroundPairCreate getBackground() {
        if (background == null) {
            return backgroundOptions[0];
        }
        return background;
    }

    /**
     * Retrieves the current crosshair option.
     * If no crosshair option is set, returns the first crosshair option.
     *
     * @return The current crosshair option.
     */
    public static ImageView getCrossHair() {
        ImageView crossHairImageView;
        if (crossHair == null) {
            crossHairImageView = new ImageView(crossHairOptions[0]);
        }
        else {
            crossHairImageView = new ImageView(crossHair);
        }
        crossHairImageView.setFitHeight(crossHairImageView.getImage().getHeight() * Settings.SCALE);
        crossHairImageView.setFitWidth(crossHairImageView.getImage().getWidth() * Settings.SCALE);
        return crossHairImageView;
    }

    /**
     * Return to the initial state.
     */
    public static void reset() {
        currentPair = 0;
        currentCrossHair = 0;
        background = null;
        crossHair = null;
    }
}
