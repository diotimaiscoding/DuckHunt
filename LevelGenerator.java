import java.util.ArrayList;

/*
 * Generates a level based on the specified level number.
 */
public class LevelGenerator {
    
    /*
    private static ArrayList<Bird> birdsUp;
    private static ArrayList<Bird> birdsDown;
    private static ArrayList<Bird> birdsClockwise;
    private static ArrayList<Bird> birdsCounterClockwise;
    
    static {
        birdsUp = new ArrayList<Bird>();
        birdsDown = new ArrayList<Bird>();
        birdsClockwise = new ArrayList<Bird>();
        birdsCounterClockwise = new ArrayList<Bird>();
        for (int i = 0; i < 3; i++) {
            birdsUp.add(new Bird(i, -Settings.WINDOW_SIZE/2 + birdWidth/2, -1 * Settings.WINDOW_SIZE/3, 0));
            birdsDown.add(new Bird(i, -Settings.WINDOW_SIZE/2 + birdWidth/2, -1 * Settings.WINDOW_SIZE/7, 0));
            birdsClockwise.add(new Bird(i, -Settings.WINDOW_SIZE/2 + birdWidth/2, -1 * Settings.WINDOW_SIZE/3, 45));
            birdsCounterClockwise.add(new Bird(i, Settings.WINDOW_SIZE/2 - birdWidth/2, -1 * Settings.WINDOW_SIZE/3, 45));
        }
    }
    */
    
    /**
     * Generates a level based on the specified level number.
     *
     * @param level the level number
     * @return the generated Level object
     */
    public Level generateLevel(int level) {

        Bird blackBird = new Bird(0, 0, 0, 0);
        Bird redBird = new Bird(1, 0, 0, 0);
        Bird blueBird = new Bird(2, 0, 0, 0);

        switch (level)  {
            case 1:
                ArrayList<Bird> generateBirds1 = new ArrayList<Bird>();
                blackBird.setCoordinates(-Settings.WINDOW_SIZE/2 + blackBird.getBirdBounds().getWidth()/2, -1 * Settings.WINDOW_SIZE/3);
                generateBirds1.add(blackBird);
                return new Level(generateBirds1, 1);
            case 2:
                ArrayList<Bird> generateBirds2 = new ArrayList<Bird>();
                blackBird.setCoordinates(-Settings.WINDOW_SIZE/2 + blackBird.getBirdBounds().getWidth()/2, -1 * Settings.WINDOW_SIZE/3 + Settings.OFFSET);
                blackBird.setDegree(45);
                generateBirds2.add(blackBird);
                return new Level(generateBirds2, 2);
            case 3:
                ArrayList<Bird> generateBirds3 = new ArrayList<Bird>();
                blueBird.setCoordinates(-Settings.WINDOW_SIZE/2 + blueBird.getBirdBounds().getWidth()/2, -1 * Settings.WINDOW_SIZE/3);
                redBird.setCoordinates(Settings.WINDOW_SIZE/2 - redBird.getBirdBounds().getWidth()/2, -1 * Settings.WINDOW_SIZE/7);
                generateBirds3.add(blueBird);
                generateBirds3.add(redBird);
                return new Level(generateBirds3, 3);
            case 4:
                ArrayList<Bird> generateBirds4 = new ArrayList<Bird>();
                blueBird.setCoordinates(-Settings.WINDOW_SIZE/2 + blueBird.getBirdBounds().getWidth()/2, -1 * Settings.WINDOW_SIZE/3 + Settings.OFFSET);
                blueBird.setDegree(45);
                redBird.setCoordinates(Settings.WINDOW_SIZE/2 - redBird.getBirdBounds().getWidth()/2, -1 * Settings.WINDOW_SIZE/3 + Settings.OFFSET);
                redBird.setDegree(45);
                generateBirds4.add(blueBird);
                generateBirds4.add(redBird);
                return new Level(generateBirds4, 4);
            case 5:
                ArrayList<Bird> generateBirds5 = new ArrayList<Bird>();
                redBird.setCoordinates(-Settings.WINDOW_SIZE/2 + redBird.getBirdBounds().getWidth()/2, -1 * Settings.WINDOW_SIZE/3);
                redBird.setDegree(0);
                blueBird.setCoordinates(Settings.WINDOW_SIZE/2 - blueBird.getBirdBounds().getWidth()/2, -1 * Settings.WINDOW_SIZE/7);
                blueBird.setDegree(0);
                blackBird.setCoordinates(-Settings.WINDOW_SIZE/2 + blackBird.getBirdBounds().getWidth()/2, -1 * Settings.WINDOW_SIZE/3 + Settings.OFFSET);
                blackBird.setDegree(45);
                generateBirds5.add(redBird);
                generateBirds5.add(blueBird);
                generateBirds5.add(blackBird);
                return new Level(generateBirds5, 5);
            case 6:
                ArrayList<Bird> generateBirds6 = new ArrayList<Bird>();
                blackBird.setCoordinates(Settings.WINDOW_SIZE/2 - blackBird.getBirdBounds().getWidth()/2, -1 * Settings.WINDOW_SIZE/7);
                blackBird.setDegree(0);
                redBird.setCoordinates(-Settings.WINDOW_SIZE/2 + redBird.getBirdBounds().getWidth()/2, -1 * Settings.WINDOW_SIZE/3 + Settings.OFFSET);
                redBird.setDegree(45);
                blueBird.setCoordinates(Settings.WINDOW_SIZE/2 - blueBird.getBirdBounds().getWidth()/2, -1 * Settings.WINDOW_SIZE/3 + Settings.OFFSET);
                blueBird.setDegree(45);
                generateBirds6.add(blackBird);
                generateBirds6.add(redBird);
                generateBirds6.add(blueBird);
                return new Level(generateBirds6, 6);
            default:
                ArrayList<Bird> generateBirds0 = new ArrayList<Bird>();
                return new Level(generateBirds0, 0);
        }
    }
}