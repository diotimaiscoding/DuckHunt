import javafx.application.*;
import javafx.stage.Stage;

/**
 * The DuckHunt class is the main class that extends the JavaFX Application class.
 * It is responsible for launching the Duck Hunt game.
 */
public class DuckHunt extends Application {

    public static double SCALE = 3.0;
    public static double VOLUME = 0.0025;
    
    /**
     * The main method that launches the JavaFX application.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The start method of the JavaFX application.
     * It is called when the application is launched and sets up the game stage.
     *
     * @param primaryStage the primary stage for the game
     */
    @Override
    public void start(Stage primaryStage) {
        // Set the stage title
        primaryStage.setTitle(Settings.TITLE);
        // Add favicon
        primaryStage.getIcons().add(Settings.FAVICON_IMAGE);
        GameManager.startGame(primaryStage);
        // Show the title
        primaryStage.show();
    }
}