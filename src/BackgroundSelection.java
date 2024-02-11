package src;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

/**
 * The BackgroundSelection class represents the scene for selecting the background and crosshair options.
 * It allows the user to change the background and crosshair by pressing arrow keys.
 */
public class BackgroundSelection extends SceneLayout<Pane>{
    
    private ImageView background;
    private ImageView crossHair;

    /**
     * Constructs a BackgroundSelection object.
     * Initializes the root pane and scene with the layout created by createLayout() method.
     * Sets the focus on the root pane.
     */
    public BackgroundSelection() {
        BackgroundPair.reset();
        root = createLayout();
        scene = new Scene(root, WINDOW_SIZE, WINDOW_SIZE);
        root.requestFocus();
    }

    /**
     * Returns the scene associated with the BackgroundSelection object.
     *
     * @return The scene.
     */
    public Scene getScene() {
        return scene;
    }
    
    @Override
    protected Pane createLayout() {

        Pane layout = new Pane();
        StackPane components = new StackPane();

        background = BackgroundPair.getBackground().getBackground();
        crossHair = BackgroundPair.getCrossHair();


        // Set the properties of the VBox
        components.setBackground(new Background(createBackground(background.getImage())));
        components.setAlignment(Pos.CENTER);
        // Bind the size of the VBox to the size of the root pane
        components.prefHeightProperty().bind(layout.heightProperty());
        components.prefWidthProperty().bind(layout.widthProperty());
        components.getChildren().add(crossHair);

        // Add components to layout
        layout.getChildren().add(components);

        updateComponents(components);

        return layout;
    }

    /**
     * Changes the background image.
     * @param background
     */
    public void changeBackground(ImageView background) {
        this.background = background;
    }

    /**
     * Changes the crosshair image.
     * @param crossHair
     */
    public void changeCrossHair(ImageView crossHair) {
        this.crossHair = crossHair;
    }

    /**
     * Updates the components of the scene.
     * @param change
     */
    public void updateComponents(StackPane change) {
        change.setBackground(new Background(createBackground(background.getImage())));
        change.getChildren().clear();
        change.getChildren().add(crossHair);
        // Add text
        Text text = Settings.createText(Settings.BACKGROUND_SELECTION_TEXT, 12, 1);
        change.getChildren().addAll(text);
    }
    
    /**
     * Updates the background.
     */
    public void updateBackground() {
        StackPane change = (StackPane) root.getChildren().get(0);
        updateComponents(change);
    }
}