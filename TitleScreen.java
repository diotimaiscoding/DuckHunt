import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

/**
 * Represents the title screen of the Duck Hunt game.
 */
public class TitleScreen extends SceneLayout<Pane> {

    private String title = "Press Enter to Start";
    private String subtitle = "Press Esc to Exit";

    /**
     * Constructs a new instance of the TitleScreen class.
     * Sets up the layout and creates the scene for the title screen.
     */
    public TitleScreen() {
        root = createLayout();
        scene = new Scene(root, Settings.WINDOW_SIZE, Settings.WINDOW_SIZE);
        root.requestFocus();
    }

    /**
     * Creates the layout for the title screen.
     *
     * @return The root pane containing the title screen layout.
     */
    @Override
    protected Pane createLayout() {
        Pane root = new Pane();

        // Get background image
        Image image = new Image(getClass().getResourceAsStream(Settings.ASSETS_PATH + "welcome/1.png"));

        Text titleText = new Text(this.title);
        titleText.setText(titleText.getText() + "\n  " + this.subtitle);
        titleText.setFont(Settings.USEFONT);
        titleText.setFill(Color.YELLOW);
        titleText.setLayoutX(Settings.WINDOW_SIZE / 2 - titleText.getLayoutBounds().getWidth() / 2);
        titleText.setTranslateY(Settings.WINDOW_SIZE / 7);

        // Create VBox and add GUI components to it
        StackPane stack = new StackPane();
        stack.setAlignment(Pos.CENTER);

        // Bind the size of the VBox to the size of the root pane
        stack.prefWidthProperty().bind(root.widthProperty());
        stack.prefHeightProperty().bind(root.heightProperty());
        ImageView imageView = new ImageView(image);
        imageView.fitWidthProperty().bind(root.widthProperty());
        imageView.fitHeightProperty().bind(root.heightProperty());
        stack.getChildren().add(imageView);
        stack.getChildren().add(titleText);

        // Add blinking effect to the title text
        BlinkTextEffect blinkTextEffect = new BlinkTextEffect(titleText);
        blinkTextEffect.start();
        
        root.getChildren().add(stack);

        root.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ENTER:
                    break;
                case ESCAPE:
                    System.exit(0);
                default:
                    break;
            }
        });

        return root;
    }
}