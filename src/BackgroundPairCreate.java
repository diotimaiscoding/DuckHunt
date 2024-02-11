package src;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The BackgroundPairCreate class represents a pair of background and foreground images.
 * It provides methods to retrieve the background and foreground images,
 * with the option to resize them to fit the window size.
 */
public class BackgroundPairCreate {
    private Image background;
    private Image foreground;

    /**
     * Constructs a BackgroundPairCreate object with the specified background and foreground paths.
     *
     * @param backgroundPath The path to the background image file.
     * @param foregroundPath The path to the foreground image file.
     */
    public BackgroundPairCreate(String backgroundPath, String foregroundPath) {
        background = new Image(BackgroundPairCreate.class.getResource(backgroundPath).toExternalForm());
        foreground = new Image(BackgroundPairCreate.class.getResource(foregroundPath).toExternalForm());
    }

    /**
     * Returns the background image, resized to fit the window size.
     *
     * @return The background image.
     */
    public ImageView getBackground() {
        ImageView backgroundImageView = new ImageView(background);
        // Resize
        backgroundImageView.setFitHeight(Settings.WINDOW_SIZE);
        backgroundImageView.setFitWidth(Settings.WINDOW_SIZE);
        return backgroundImageView;
    }

    /**
     * Returns the foreground image, resized to fit the window size.
     *
     * @return The foreground image.
     */
    public ImageView getForeground() {
        ImageView foregroundImageView = new ImageView(foreground);
        // Resize
        foregroundImageView.setFitHeight(Settings.WINDOW_SIZE);
        foregroundImageView.setFitWidth(Settings.WINDOW_SIZE);
        return foregroundImageView;
    }
}