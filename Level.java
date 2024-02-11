import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * The Level class represents a game level with its layout, background, birds, and game logic.
 */
public class Level extends SceneLayout<Pane>{
    protected ImageView background;
    protected ImageView foreground;
    protected ImageView crossHair;

    protected ArrayList<Bird> birds;
    protected int numBirds;
    protected int ammoCount;
    protected int indexNum = 0;

    protected int levelNum;

    private Label levelLabel = new Label();
    private Label ammoLabel = new Label();

    private Text titleText;
    private Text statementText;

    private boolean gameOver = false;
    private boolean levelWin = false;

    /**
     * Constructs a new Level object.
     *
     * @param addBirds  the list of birds to add to the level
     * @param levelNum  the level number
     */
    public Level(ArrayList<Bird> addBirds, int levelNum) {
        this.birds = new ArrayList<Bird>();
        for (Bird bird: addBirds) {
            this.birds.add(bird);
        }
        this.numBirds = birds.size();
        this.ammoCount = this.numBirds * 3;
        this.root = createLayout();
        this.scene = new Scene(root, Settings.WINDOW_SIZE, Settings.WINDOW_SIZE);
        this.root.requestFocus();
        // Set crossHair as the cursor
        this.scene.setCursor(new ImageCursor(crossHair.getImage()));
        AnimationShooting animationShooting = new AnimationShooting(birds, scene, this);
    }

    /**
     * Retrieves the level number.
     *
     * @return the level number
     */
    public int getLevelNum() {
        return levelNum;
    }

    /**
     * Checks if the game is over.
     *
     * @return true if the game is over, false otherwise
     */
    public boolean isGameOver() {
        return gameOver;
    }   

    /**
     * Checks if the level is won.
     *
     * @return true if the level is won, false otherwise
     */
    public boolean isLevelWin() {
        return levelWin;
    }

    /**
     * Creates the layout for the level.
     *
     * @return the root pane of the level layout
     */
    protected Pane createLayout() {
        background = BackgroundPair.getBackground().getBackground();
        foreground = BackgroundPair.getBackground().getForeground();
        crossHair = BackgroundPair.getCrossHair();


        Pane root = new Pane();
        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.CENTER);
        // Set height and width of stackPane by binding
        stackPane.prefHeightProperty().bind(root.heightProperty());
        stackPane.prefWidthProperty().bind(root.widthProperty());

        stackPane.getChildren().add(background);
        for (Bird bird : birds) {
            stackPane.getChildren().add(bird.getBirdImage());
        }
        stackPane.getChildren().add(foreground);

        // Create labels for level number and ammo count
        levelLabel.setText("Level " + this.levelNum + "/" + Settings.NUM_LEVELS);
        ammoLabel.setText("Ammo Left: " + ammoCount);
        levelLabel.setFont(Settings.fontWithSize(6));
        ammoLabel.setFont(Settings.fontWithSize(6));

        levelLabel.setTextFill(Color.YELLOW);
        ammoLabel.setTextFill(Color.YELLOW);

        levelLabel.layoutXProperty().bind(root.widthProperty().multiply(0.5).subtract(levelLabel.widthProperty().multiply(0.5)));
        levelLabel.layoutYProperty().bind(root.heightProperty().multiply(0));
        ammoLabel.layoutXProperty().bind(root.widthProperty().subtract(ammoLabel.widthProperty()));
        ammoLabel.layoutYProperty().bind(root.heightProperty().multiply(0));


        titleText = Settings.createText(Settings.GAME_WIN_TEXT, 14, 0);
        BlinkTextEffect blinkTextEffect = new BlinkTextEffect(titleText);
        blinkTextEffect.start();                
        statementText = Settings.createText("YOU WIN!" + "\n", 20, 0);
        statementText.setLayoutY(Settings.WINDOW_SIZE / 2 - titleText.getLayoutBounds().getHeight());
        titleText.setLayoutY(Settings.WINDOW_SIZE / 2 - titleText.getLayoutBounds().getHeight() + statementText.getLayoutBounds().getHeight()/2);

        root.getChildren().addAll(stackPane, levelLabel, ammoLabel);
        return root;
    }

    /**
     * Updates the level number label.
     *
     * @param levelNum the new level number
     */
    public void updateLevelNumber(int levelNum) {
        this.levelNum = levelNum;
        levelLabel.setText("Level: " + levelNum + "/" + Settings.NUM_LEVELS);
        // Update on the screen
        root.getChildren().remove(levelLabel);
        root.getChildren().add(levelLabel);
    }

    /**
     * Updates the ammo count label.
     *
     * @param ammoCount the new ammo count
     */
    public void updateAmmoCount(int ammoCount) {
        this.ammoCount = ammoCount;
        ammoLabel.setText("Ammo Left: " + ammoCount);
        // Update on the screen
        root.getChildren().remove(ammoLabel);
        root.getChildren().add(ammoLabel);
    }

    /**
     * Checks the status of the birds and updates the game state.
     */
    public void checkBirds() {
        int shotBirds = 0;
        for (Bird bird: birds) {
            if (!(bird.getAnimationState() == AnimationState.FLYING)) {
                shotBirds++;
            }
        }
        if (shotBirds == numBirds) {
            if (levelNum >= Settings.NUM_LEVELS) {
                gameWin();
            }
            else {
                levelWin();
            }
        }
        else if (ammoCount <= 0) {
            gameOver();
        }
    }

    /**
     * Performs a shot action in the level.
     */
    public void shoot() {
        this.ammoCount--;
        updateAmmoCount(this.ammoCount);
    }

    /**
     * Handles the game win state.
     */
    private void gameWin() {
        levelWin = true;

        statementText.setText("You have completed the game!");
        statementText.setFont(Settings.fontWithSize(14));
        statementText.setLayoutX(Settings.WINDOW_SIZE / 2 - statementText.getLayoutBounds().getWidth() / 2);
        titleText.setText("Press ENTER to play again\nPress ESC to exit");
        titleText.setLayoutX(Settings.WINDOW_SIZE / 2 - titleText.getLayoutBounds().getWidth() / 2);

        root.getChildren().addAll(statementText, titleText);
        
        MediaManager.playGameWinSound();
        
    }

    /**
     * Handles the level win state.
     */
    private void levelWin() {
        levelWin = true;
        
        MediaManager.playLevelWinSound();
        
        titleText.setText(Settings.GAME_WIN_TEXT);

        root.getChildren().addAll(statementText, titleText);
        
    }

    /**
     * Handles the game over state.
     */
    private void gameOver() {
        gameOver = true;

        MediaManager.playGameOverSound();

        AnimationManager.clear();

        statementText.setText(Settings.GAME_OVER_TEXT);
        statementText.setLayoutX(Settings.WINDOW_SIZE / 2 - statementText.getLayoutBounds().getWidth() / 2);
        titleText.setText("Press ENTER to play again\nPress ESC to exit");
        titleText.setLayoutX(Settings.WINDOW_SIZE / 2 - titleText.getLayoutBounds().getWidth() / 2);

        root.getChildren().addAll(statementText, titleText);
    }
}