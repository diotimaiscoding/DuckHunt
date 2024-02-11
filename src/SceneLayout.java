package src;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

/**
 * The SceneLayout class represents an abstract layout for JavaFX scenes.
 * It provides common functionality for creating scene layouts.
 *
 * @param <T> the type of the layout pane
 */
public abstract class SceneLayout <T extends Pane> {
    protected static double WINDOW_SIZE = Settings.WINDOW_SIZE;
    protected T root;
    protected Scene scene;

    /**
     * Returns the JavaFX Scene associated with this layout.
     *
     * @return the JavaFX Scene
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Creates the layout for the scene.
     *
     * @return the layout pane
     */
    protected abstract T createLayout();
   /**
     * Creates a background image with the given image and settings.
     *
     * @param image the image for the background
     * @return the background image
     */
    protected BackgroundImage createBackground(Image image) {
        BackgroundImage backgroundImage = new BackgroundImage(
            image,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(WINDOW_SIZE, WINDOW_SIZE, false, false, true, true)
        );
        return backgroundImage;
    }
}