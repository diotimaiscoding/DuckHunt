package src;
import java.util.ArrayList;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The Bird class represents a bird in the game.
 */
public class Bird {
    // Bird images
    private static String[] birdOptions = {"black", "red", "blue"};
    private static int birdFrames = 8;
    private static Image[][] birdsImages = new Image[birdOptions.length][birdFrames];

    // Store all the Bird instances
    private static ArrayList<Bird> birds = new ArrayList<Bird>();
    // Store the coordinates
    private double x;
    private double y;
    // Store the degree
    private double degree;
    // Store the velocity
    private double velocity = Settings.ANIMATION_VELOCITY;
    // Images for this bird
    private Image[] birdFrameImages;
    // Image of the current state
    private ImageView birdImage;
    // Animations
    private AnimationManager flyingAnimation;
    // Keep track of the bird's state
    private AnimationState birdState;

    // Constructor
    static {
        loadBirds();
    }

    /**
     * Creates a Bird object.
     *
     * @param index  The index of the bird option.
     * @param x      The x-coordinate of the bird.
     * @param y      The y-coordinate of the bird.
     * @param degree The degree of rotation of the bird.
     */
    public Bird(int index, double x, double y, double degree) {
        birds.add(this);
        this.birdFrameImages = birdsImages[index%birdOptions.length];
        this.x = x;
        this.y = y;
        this.degree = degree;
        this.birdImage = new ImageView(this.birdFrameImages[0]);
        this.birdImage.setScaleX(Settings.SCALE);
        this.birdImage.setScaleY(Settings.SCALE);
        this.flyingAnimation = new AnimationManager(this);
        this.birdState = AnimationState.FLYING;
    }
    
    /*
     * Loads the bird images from the assets folder.
     */
    public static void loadBirds() {
        for (int i = 0; i < birdOptions.length; i++) {
            for (int j = 1; j < birdFrames+1; j++) {
                birdsImages[i][j-1] = new Image(Bird.class.getResource(Settings.ASSETS_PATH + "duck_" + birdOptions[i] + "/" + j + ".png").toExternalForm());
            }
        }
    }

    // Getters
    
    /**
     * Gets the bird frame images.
     *
     * @return The bird frame images.
     */
    public Image[] getBirdFrameImages() {
        return this.birdFrameImages;
    }

    /**
     * Gets the bird frames based on the degree of rotation.
     *
     * @return The bird frames.
     */
    public Image[] getBirdFrames() {
        Image[] birdFrames = new Image[3];
        if (this.degree == 0 || this.degree == 180) {
            for (int i=0; i<birdFrames.length; i++) {
                birdFrames[i] = birdFrameImages[i+3];
            }
        }
        else {
            for (int i=0; i<birdFrames.length; i++) {
                birdFrames[i] = birdFrameImages[i];
            }
        }
        return birdFrames;
    }

    /**
     * Gets the bird frame for shooting.
     *
     * @return The bird frame for shooting.
     */
    public Image getBirdShootFrame() {
        return this.birdFrameImages[6];
    }

    /**
     * Gets the bird frame for falling.
     *
     * @return The bird frame for falling.
     */
    public Image getBirdFallFrame() {
        return this.birdFrameImages[7];
    }

    /**
     * Gets the bounding rectangle of the bird.
     *
     * @return The bounding rectangle of the bird.
     */
    public Rectangle2D getBirdBounds() {
        // Set the centerX, centerY, width, height in any moment use bounds in parent values
        double centerX = this.birdImage.getBoundsInParent().getMinX() + this.birdImage.getBoundsInParent().getWidth()/2;
        double centerY = this.birdImage.getBoundsInParent().getMinY() + this.birdImage.getBoundsInParent().getHeight()/2;
        double width = this.birdImage.getBoundsInParent().getWidth();
        double height = this.birdImage.getBoundsInParent().getHeight();
        return new Rectangle2D(centerX - width/2, centerY - height/2, width, height);
    }

    /**
     * Sets the position and rotation of the bird image.
     */
    public void setBirdImagePos() {
        if (!(this.degree%360 == 0 || this.degree%360 == 180)) {
            this.birdImage.setRotate(this.degree + 45);
        }
        else {
            this.birdImage.setRotate(this.degree);
        }
        this.birdImage.setTranslateX(this.x);
        this.birdImage.setTranslateY(this.y);
    }

    /**
     * Gets the image of the bird.
     *
     * @return The image of the bird.
     */
    public ImageView getBirdImage() {
        setBirdImagePos();
        return this.birdImage;
    }

    /**
     * Gets the coordinates of the bird.
     *
     * @return The coordinates of the bird.
     */
    public double[] getCoordinates() {
        double[] coordinates = {this.x, this.y};
        return coordinates;
    }

    /**
     * Gets the degree of rotation of the bird.
     *
     * @return The degree of rotation of the bird.
     */
    public double getDegree() {
        return this.degree;
    }

    /**
     * Gets the velocity of the bird.
     *
     * @return The velocity of the bird.
     */
    public double getVelocity() {
        return this.velocity;
    }

    /**
     * Gets the animation state of the bird.
     *
     * @return The animation state of the bird.
     */
    public AnimationState getAnimationState() {
        return this.birdState;
    }

    // Setters

    /**
     * Sets the bird image.
     *
     * @param birdImage The bird image to set.
     */
    public void setBirdImage(ImageView birdImage) {
        this.birdImage = birdImage;
    }

    /**
     * Sets the coordinates of the bird.
     *
     * @param x The x-coordinate to set.
     * @param y The y-coordinate to set.
     */
    public void setCoordinates(double x, double y) {
        this.x = x;
        this.y = y;
        setBirdImagePos();
    }

    /**
     * Sets the degree of rotation of the bird.
     *
     * @param degree The degree of rotation to set.
     */
    public void setDegree(double degree) {
        this.degree = degree;
        setBirdImagePos();
    }

    // Methods

    /*
     * Mirrors the bird image to vertical axis.
     */
    public void mirrorImageX() {
        this.birdImage.setScaleX(-this.birdImage.getScaleX());
    }

    /*
     * Mirrors the bird image to horizontal axis.
     */
    public void mirrorImageY() {
        this.birdImage.setScaleY(-this.birdImage.getScaleY());
    }
    
    /*
     * Stops the flying animation.
     */
    public void stopFlying() {
        this.flyingAnimation.stop();
    }

    /**
     * Checks if the bird contains the specified coordinates.
     *
     * @param x The x-coordinate to check.
     * @param y The y-coordinate to check.
     * @return True if the bird contains the coordinates, false otherwise.
     */
    public boolean contains(double x, double y) {
        return this.birdImage.contains(x, y);
    }


    /**
     * Handles the bird being shot.
     * This stops the flying animation and updates the bird state.
     */
    protected void shoot() {
        this.flyingAnimation.stop();
        this.birdState = AnimationState.SHOT;
    }
}