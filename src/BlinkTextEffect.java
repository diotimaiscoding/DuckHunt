package src;
import javafx.animation.Timeline;
import javafx.scene.text.Text;

/**
 * The BlinkTextEffect class provides a blink effect for a Text node.
 */
public class BlinkTextEffect {
    private static final int BLINK_INTERVAL = 500;
    
    private Text textNode;
    private Timeline blinkTimeline;

    /**
     * Constructs a BlinkTextEffect object with the specified Text node.
     *
     * @param textNode The Text node to apply the blink effect to.
     */
    public BlinkTextEffect(Text textNode) {
        this.textNode = textNode;
        initializeBlinkEffect();
    }

    /**
     * Initializes the blinking effect by creating a Timeline with a KeyFrame
     * that toggles the visibility of the Text node at the specified interval.
     */
    private void initializeBlinkEffect() {
        blinkTimeline = new Timeline();
        blinkTimeline.setCycleCount(Timeline.INDEFINITE);
        blinkTimeline.getKeyFrames().add(
            new javafx.animation.KeyFrame(
                javafx.util.Duration.millis(BLINK_INTERVAL),
                event -> textNode.setVisible(!textNode.isVisible())
            )
        );
    }

    /**
     * Starts the blinking effect.
     */
    public void start() {
        blinkTimeline.play();
    }

    /**
     * Stops the blinking effect.
     */
    public void stop() {
        blinkTimeline.stop();
    }
}