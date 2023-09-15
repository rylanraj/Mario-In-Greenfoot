import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fakepipe here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fakepipe extends Pipe
{
    /**
     * Act - do whatever the Fakepipe wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Fakepipe(int rotation, World world)
    {
        super(100,100,world);
        GreenfootImage img = getImage();
        setRotation(rotation);
    }
}
