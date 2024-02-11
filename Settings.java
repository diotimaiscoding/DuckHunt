import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Settings {
    protected static final String ASSETS_PATH = "assets/";
    private static final Image IMAGE = new Image(ASSETS_PATH + "background/1.png");
    protected static final Image FAVICON_IMAGE = new Image("assets/welcome/1.png");
    protected static final String TITLE = "HUBBM Duck Hunt";
    
    protected static double SCALE = DuckHunt.SCALE;
    protected static double WINDOW_SIZE = IMAGE.getHeight() * SCALE;
    protected static Font USEFONT = Font.font("Times New Roman", FontWeight.NORMAL, 14 * SCALE);

    protected static final int NUM_LEVELS = 6;

    protected static final Color TEXT_COLOR = Color.GOLDENROD;

    protected static final String BACKGROUND_SELECTION_TEXT = "USE ARROW KEYS TO NAVIGATE\nPRESS ENTER TO START\nPRESS ESC TO EXIT";

    protected static double ANIMATION_TIME_FRAME = 100 * SCALE;
    protected static double ANIMATION_VELOCITY = 0.7 * SCALE;
    
    protected static double VOLUME = DuckHunt.VOLUME * 100;

    protected static double OFFSET = 7 * SCALE;

    protected static final String GAME_WIN_TEXT = "Press ENTER to play next level";
    protected static final String GAME_OVER_TEXT = "Game Over!";


    /**
     * Returns a font with the specified font size, scaled according to the global scale.
     *
     * @param fontSize the desired font size
     * @return the scaled font
     */
    public static Font fontWithSize(int fontSize) {
        return Font.font("Times New Roman", FontWeight.NORMAL, fontSize * SCALE);
    }

    /**
     * Creates textReturn,
     * 0 for center, 1 for up.
     */
    public static Text createText(String text, int fontSize, int position) {
        Text textReturn = new Text(text);
        textReturn.setFont(fontWithSize(fontSize));
        textReturn.setFill(TEXT_COLOR);
        textReturn.setTextAlignment(TextAlignment.CENTER);
        
        if (position == 0) {
            textReturn.setLayoutX((WINDOW_SIZE - textReturn.getLayoutBounds().getWidth()) / 2);
        } else if (position == 1) {
            textReturn.setLayoutX((WINDOW_SIZE - textReturn.getLayoutBounds().getWidth()) / 2);
            textReturn.setTranslateY(-WINDOW_SIZE / 2 + textReturn.getLayoutBounds().getHeight() / 2);
        }
        return textReturn;
    }
}