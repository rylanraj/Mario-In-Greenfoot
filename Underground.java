import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Underground here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Underground extends Levels
{
    /**
     * Constructor for objects of class Underground.
     * 
     */
    public Underground()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(400,400,true); 
        prepare();
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    // The underground should contain at least one pipe to make it seem like mario is traveling, it doesn't have to be real
    private void prepare()
    {
        // All underground levels should contain a Fakepipe mario appears to exit from
        addUgObjects();
    }
    private void addUgObjects()
    {
        Fakepipe fakepipe = new Fakepipe(180, this);
        addObject(fakepipe,111,48);
    }
}

