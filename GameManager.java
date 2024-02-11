import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The GameManager class manages the game flow and transitions between different screens and levels.
 */
public class GameManager {

    private static Stage primaryStage;
    private static Scene titleScreen = new TitleScreen().getScene();
    private static BackgroundSelection backgroundSelection = new BackgroundSelection();
    private static int levelNum = 1;
    private static Level lastLevel;

    // Start with title screen, if user presses enter, go to background selection screen
    private static Scene currentScene = titleScreen;

    /**
     * Retrieves the current scene.
     *
     * @return the current scene
     */
    public static Scene getCurrentScene() {
        return currentScene;
    }

    /**
     * Sets the current scene and updates the primary stage.
     *
     * @param scene the new current scene
     */
    public static void setCurrentScene(Scene scene) {
        currentScene = scene;
        primaryStage.setScene(currentScene);
    }

    /**
     * Resets the game to its initial state.
     */
    public static void reset() {       
        currentScene = titleScreen;

        levelNum = 1;

        backgroundSelection = new BackgroundSelection();
    }

    /**
     * Starts the game by initializing the primary stage and setting up the title screen.
     *
     * @param primaryStage the primary stage of the game
     */
    public static void startGame(Stage primaryStage) {
        GameManager.primaryStage = primaryStage;
        reset();
        MediaManager.playTitleScreenSound();
        levelNum = 1;
        setCurrentScene(titleScreen);
        currentScene.setOnKeyPressed(event -> handleTitleScreenKeyPress(event.getCode()));
    }

    /**
     * Handles the key press events on the title screen.
     *
     * @param code the key code associated with the key press event
     */
    private static void handleTitleScreenKeyPress(KeyCode code) {
        if (code == KeyCode.ENTER) {
            setCurrentScene(backgroundSelection.getScene());
            currentScene.setOnKeyPressed(event -> handleBackgroundSelectionScreenKeyPress(event.getCode()));
        }
        else if (code == KeyCode.ESCAPE) {
            System.exit(0);
        }
    }

    /**
     * Handles the key press events on the title screen.
     *
     * @param code the key code associated with the key press event
     */
    private static void handleBackgroundSelectionScreenKeyPress(KeyCode code) {          
        // Add event handler to change background
        switch (code) {
            case RIGHT:
                BackgroundPair.nextBackground();
                backgroundSelection.changeBackground(BackgroundPair.getBackground().getBackground());
                backgroundSelection.updateBackground();
                break;
            case LEFT:
                BackgroundPair.previousBackground();
                backgroundSelection.changeBackground(BackgroundPair.getBackground().getBackground());
                backgroundSelection.updateBackground();
                break;
            case UP:
                BackgroundPair.previousCrossHair();
                backgroundSelection.changeCrossHair(BackgroundPair.getCrossHair());
                backgroundSelection.updateBackground();
                break;
            case DOWN:
                BackgroundPair.nextCrossHair();
                backgroundSelection.changeCrossHair(BackgroundPair.getCrossHair());
                backgroundSelection.updateBackground();
                break;
            case ENTER:
                // Disable the event handler for the background selection screen
                currentScene.setOnKeyPressed(null);
                MediaManager.stopTitleScreenSound();
                MediaPlayer introSound = MediaManager.returnIntroSound();
                introSound.stop();
                introSound.setStartTime(Duration.ZERO);
                introSound.setOnEndOfMedia(null);
              
                introSound.play();
                introSound.setOnEndOfMedia(() -> {
                    Level level1 = new LevelGenerator().generateLevel(levelNum);
                    level1.updateLevelNumber(1);
                    setCurrentScene(level1.getScene());
                    lastLevel = level1;
                    currentScene.setOnKeyPressed(event -> handleLevelKeyPress(event.getCode()));
                });
                break;
            case ESCAPE:
                reset();
                setCurrentScene(titleScreen);
                currentScene.setOnKeyPressed(event -> handleTitleScreenKeyPress(event.getCode()));
                break;
            default:
                break;
        }
    }

    /**
     * Handles the key press events during a level.
     *
     * @param code the key code associated with the key press event
     */
    private static void handleLevelKeyPress(KeyCode code) {
        if (lastLevel.isLevelWin() && code == KeyCode.ENTER) {
            Level newLevel = new LevelGenerator().generateLevel(++levelNum);
            newLevel.updateLevelNumber(levelNum);
            MediaManager.stopAllSounds();
            setCurrentScene(newLevel.getScene());
            lastLevel = newLevel;
            if (levelNum == 6){
                currentScene.setOnKeyPressed(event -> handleLastLevelKeyPress(event.getCode()));
            }
            else {
                currentScene.setOnKeyPressed(event -> handleLevelKeyPress(event.getCode()));
            }
        }
        else if (lastLevel.isGameOver() && code == KeyCode.ENTER) {            
            goBackToFirstLevel();
        }
        else if (lastLevel.isGameOver() && code == KeyCode.ESCAPE) {
            goBackToTitleScreen();
        }        
        else if (lastLevel.isLevelWin() && code == KeyCode.ESCAPE) {
            goBackToTitleScreen();
        }
    }

    /**
     * Handles the key press events on the last level.
     *
     * @param code the key code associated with the key press event
     */
    private static void handleLastLevelKeyPress(KeyCode code) {
        if (lastLevel.isGameOver()) {
            if (code == KeyCode.ENTER) {
                goBackToFirstLevel();
            }
            else if (code == KeyCode.ESCAPE) {
                goBackToTitleScreen();
            }
        }
        else if (lastLevel.isLevelWin()) {
            if (code == KeyCode.ENTER) {
                goBackToFirstLevel();
            }
            else if (code == KeyCode.ESCAPE) {
                goBackToTitleScreen();
            }
        }
    }

    private static void goBackToTitleScreen() {
        reset();
        MediaManager.stopAllSounds();
        MediaManager.playTitleScreenSound();
        setCurrentScene(titleScreen);
        currentScene.setOnKeyPressed(event -> handleTitleScreenKeyPress(event.getCode()));
    }

    private static void goBackToFirstLevel() {
        levelNum = 1;
        MediaManager.stopAllSounds();
        Level level1 = new LevelGenerator().generateLevel(levelNum);
        level1.updateLevelNumber(levelNum);
        setCurrentScene(level1.getScene());
        lastLevel = level1;
        currentScene.setOnKeyPressed(event -> handleLevelKeyPress(event.getCode()));
    }
}