import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LevelSelection here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LevelSelection extends World
{

    /**
     * Constructor for objects of class LevelSelection.
     * 
     */
    // Booleans to see if the level is unlocked when the player returns to this screen
    public boolean levelTwoUnlocked;
    public boolean levelThreeUnlocked;
    public LevelSelection(boolean l2unlocked, boolean l3unlocked)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        // Set the booleans in the class to whatever the set booleans are in the constructer
        levelTwoUnlocked = l2unlocked;
        levelThreeUnlocked = l3unlocked;
        prepare();
        generalizeProject(600,400);
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        setBackground("blackscreen.png");
        levelHandler();
    }
    private void levelHandler()
    {
        // Declare variables
        int yd = 86;
        int x1 = 117;
        // Add the option to play the level one at all times
        Level1 level1 = new Level1();
        addObject(level1,x1,86);
        // If level two is unlocked, create the option to play level 2.
        if(levelTwoUnlocked)
        {
            Level2 level2 = new Level2();
            addObject(level2, x1, 172);
            if(levelThreeUnlocked)
            {
                Level3 level3 = new Level3();
                addObject(level3, x1, level2.getY() + yd);
            }
        }
        // If level three is unlocked, create the option to play level 3.
    }
     // Methods I used to generalize the project
    public void generalizeScaleSingleActor(Actor actor,double xs, double ys)
    {
        int x = (int)xs;
        int y = (int)ys;
        GreenfootImage img = actor.getImage();
        img.scale(x,y);
        actor.setImage(img);
    }
    public void generalizePositionSingleActor(Actor actor,double xs, double ys)
    {
        int x = (int)xs;
        int y = (int)ys;
        actor.setLocation(x,y);
    }
    public void generalizeProject(double originalx, double originaly)
    {
        double multiplier = this.getWidth() / originalx;
        double heightmultiplier = this.getHeight() / originaly;
        // For every actor in this world
        for(Actor actor: this.getObjects(Actor.class))
        {
            if(actor.getClass() != Mario.class)
            {
                // Generalize the size of the actor so it can be scaled up if the user decides to
                generalizeScaleSingleActor(actor,actor.getImage().getWidth() * multiplier, actor.getImage().getHeight() * heightmultiplier);
            }
        }
        // For every image array in all the animations        
        generalizePostion((int)originalx,(int)originaly);
    }
    public void generalizePostion(double originalx, double originaly)
    {
        double multiplier = this.getWidth() / originalx;
        double heightmultiplier = this.getHeight() / originaly;
        // For every actor in this world
        for(Actor actor : this.getObjects(Actor.class))
        {
            if(actor.getClass() != Powerbox.class)
            {
                // Generalize the position of the actor so that its position stays consistent when scaled up
                generalizePositionSingleActor(actor,actor.getX() * multiplier, actor.getY() * heightmultiplier);
            }
        }
    }
}
