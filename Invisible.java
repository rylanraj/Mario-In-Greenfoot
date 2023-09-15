import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Invisible here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Invisible extends Block
{
    /**
     * Act - do whatever the Invisible wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Invisible()
    {
        super(false);
        GreenfootImage img = getImage();
        img.setTransparency(0);
    }
    public void act()
    {
        // Add your action code here.
    }
}
