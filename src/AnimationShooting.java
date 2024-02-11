package src;
import java.util.ArrayList;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

/**
 * The AnimationShooting class manages the shooting animation when the user clicks on birds.
 * It listens for mouse click events on the scene and checks if a bird is clicked.
 * If a bird is clicked and in the flying state, it initiates the falling animation for the bird.
 */
public class AnimationShooting {
    
    private ArrayList<Bird> birds;
    private Level level;
    
    /**
     * Constructs an AnimationShooting object with the specified list of birds, scene, and level.
     *
     * @param birds the list of birds to manage the shooting animation
     * @param scene the scene to listen for mouse click events
     * @param level the level associated with the animation
     */
    public AnimationShooting(ArrayList<Bird> birds, Scene scene, Level level) {
        this.birds = birds;
        this.level = level;      
        scene.setOnMouseClicked(event -> {handleMouseClick(event);});
    }

    /**
     * Handles the mouse click event and initiates the shooting animation for the clicked bird, if applicable.
     *
     * @param event the MouseEvent representing the mouse click event
     */
    private void handleMouseClick(MouseEvent event) {
        if (level.isGameOver() || level.isLevelWin()) {
            return;
        }
        Rectangle2D birdBounds;
        MediaManager.playAmmoSound();
        level.shoot();

        for (Bird bird : birds) {
            // Create a Rectangle2D to store bounds of the bird
            birdBounds = bird.getBirdBounds();
            if (birdBounds.contains(event.getX(), event.getY()) && bird.getAnimationState() == AnimationState.FLYING) {
                bird.shoot();
                AnimationFalling animationFalling = new AnimationFalling(bird);
                animationFalling.start();
            }
        }
        level.checkBirds();
    }
}