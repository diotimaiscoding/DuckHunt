package src;
import java.util.ArrayList;

import javafx.animation.*;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * The AnimationManager class is responsible for managing the flying animation of a bird.
 */
public class AnimationManager {
    private static int ANIMATION_TIME_FRAME = (int) Settings.ANIMATION_TIME_FRAME;
    private static int NUM_FRAMES = 3;

    private static ArrayList<AnimationManager> animationManagers = new ArrayList<AnimationManager>();

    private Bird bird;
    private ImageView birdImage;
    private Animation flyAnimation;
    
    /**
     * Constructs an AnimationManager object for the given bird.
     *
     * @param bird the bird object associated with the animation
     * @param birdImage the image of the bird
     */
    public AnimationManager(Bird bird) {
        this.bird = bird;
        this.birdImage = bird.getBirdImage();
        flyAnimationCreate();
        birdImage.setImage(bird.getBirdFrames()[0]);
        flyAnimation.setCycleCount(Animation.INDEFINITE);
        flyAnimation.play();
    }

    /**
     * Creates the fly animation for the bird.
     */
    public void flyAnimationCreate() {
        flyAnimation = new Transition() {
            {
                setCycleDuration(Duration.millis(ANIMATION_TIME_FRAME * NUM_FRAMES));
                setInterpolator(Interpolator.LINEAR);
            }

            @Override
            protected void interpolate(double frac) {
                int index = (int) (frac * NUM_FRAMES) % NUM_FRAMES;
                birdImage.setImage(bird.getBirdFrames()[index]);
                
                // Bird will move in the direction of the degree,
                // if it hits the edges of the window it will reflect

                double velocity = bird.getVelocity();
                double degree = bird.getDegree();

                double xVelocity = (velocity * Math.cos(Math.toRadians(degree)));
                double yVelocity = (velocity * Math.sin(Math.toRadians(degree)));

                double newX = bird.getCoordinates()[0] + xVelocity;
                double newY = bird.getCoordinates()[1] + yVelocity;

                if (degree%360 == 0 || degree%360 == 180) {
                    if (newX - bird.getBirdBounds().getWidth()/2 < -Settings.WINDOW_SIZE/2 || newX + bird.getBirdBounds().getWidth()/2 > Settings.WINDOW_SIZE/2) {
                        bird.setDegree(180 - degree);
                            if (degree%360 == 0) {
                                birdImage.setScaleX(1 *Settings.SCALE);
                                birdImage.setScaleY(-1 * Settings.SCALE);
                            }
                            else {
                                birdImage.setScaleX(1 * Settings.SCALE);
                                birdImage.setScaleY(1 * Settings.SCALE);
                            }
                        newX = bird.getCoordinates()[0] + xVelocity;
                        xVelocity = -xVelocity;
                    }
                }
                else {
                    if (newX < -Settings.WINDOW_SIZE/2 || newX > Settings.WINDOW_SIZE/2) {
                        bird.setDegree(180 - degree);
                        newX = bird.getCoordinates()[0] + xVelocity;
                        xVelocity = -xVelocity;
                    }
                }

                if (newY < -Settings.WINDOW_SIZE/2 || newY > Settings.WINDOW_SIZE/2) {
                    bird.setDegree(-degree);
                    newY = bird.getCoordinates()[1] + yVelocity;
                    yVelocity = -yVelocity;
                }

                bird.setCoordinates(newX, newY);
        
                if (bird.getAnimationState() == AnimationState.SHOT) {
                    flyAnimation.stop();
                }
            }
        };
    }
    
    /**
     * Makes multiple birds start flying using AnimationManager.
     *
     * @param birds the array of birds to start flying
     */
    public static void makeBirdsFly(ArrayList<Bird> birds) {
        for (Bird bird: birds) {
            animationManagers.add(new AnimationManager(bird));
            
        }
    }

    /*
     * Stop all the fly animations.
     */
    public static void clear() {
        for (AnimationManager animationManager: animationManagers) {
            animationManager.stop();
        }
    }
  
    /**
     * Stops the fly animation.
     */
    public void stop() {
        flyAnimation.stop();
    }
}