import javafx.animation.*;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * Class representing the falling animation of a bird.
 */
public class AnimationFalling {
    private static final double ANIMATION_TIME_FRAME = Settings.ANIMATION_TIME_FRAME;
    private static final int NUM_FRAMES = 1;
    private static final double PAUSE_DURATION = 200;

    private Bird bird;
    private ImageView birdImage;
    private Animation fallAnimation;

   /**
     * Constructs an AnimationFalling object for the given bird.
     *
     * @param bird the bird object associated with the animation
     * @param birdImage the image of the bird
     */
    public AnimationFalling(Bird bird) {
        this.bird = bird;
        this.birdImage = bird.getBirdImage();
        fallAnimationCreate();
        birdImage.setImage(bird.getBirdFallFrame());
        birdImage.setRotate(0);
        fallAnimation.setCycleCount(Animation.INDEFINITE);
    }

    /**
     * Creates the fall animation for the bird.
     */
    public void fallAnimationCreate() {
        MediaManager.playBirdFallingSound();
        fallAnimation = new Transition() {
            {
                setCycleDuration(Duration.millis(ANIMATION_TIME_FRAME * NUM_FRAMES));
                setInterpolator(Interpolator.LINEAR);
            }

            @Override
            protected void interpolate(double frac) {

                double velocity = bird.getVelocity();
                double degree = bird.getDegree();

                double xVelocity = (velocity * Math.cos(Math.toRadians(degree)));
                double yVelocity = velocity;

                double newY = bird.getCoordinates()[1] + yVelocity;

                birdImage.setImage(bird.getBirdFallFrame());

                bird.setCoordinates(bird.getCoordinates()[0], newY);
                birdImage.setRotate(0);
                if (xVelocity < 0) {
                    birdImage.setScaleX(-1 * Settings.SCALE);
                    birdImage.setScaleY(1 * Settings.SCALE);
                }
            }
        };
        
        if (bird.getCoordinates()[1] < -Settings.WINDOW_SIZE) {
            fallAnimation.stop();
        }
    }

    /**
     * Starts the falling animation.
     * @param displayShot the pause transition before the animation starts
     */
    public void start() {
        PauseTransition displayShot = new PauseTransition(Duration.millis(PAUSE_DURATION));
        displayShot.setOnFinished(event -> {
            fallAnimation.play();
        });
        birdImage.setImage(bird.getBirdShootFrame());
        if (bird.getVelocity() * Math.cos(Math.toRadians(bird.getDegree())) < 0) {
            birdImage.setScaleX(-1 * Settings.SCALE);
            birdImage.setScaleY(1 * Settings.SCALE);
        }
        displayShot.play();
    }

    /**
     * Stops the falling animation.
     */
    public void stop() {
        fallAnimation.stop();
    }
}