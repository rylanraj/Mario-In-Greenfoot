import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Menu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Menu extends World
{
    /**
     * Constructor for objects of class Menu.
     * 
     */
    public Menu()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        prepare();
        generalizeProject(600,400);
    }    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        addMenuObjects();    
    }
    // Add all the menu stuff
    public void addMenuObjects()
    {
        setBackground("bg.png");
        Text text = new Text();
        addObject(text,290,317);
        Logo logo = new Logo();
        addObject(logo,284,129);
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
    // Copied from the levels generalizer, see those comments
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
}
