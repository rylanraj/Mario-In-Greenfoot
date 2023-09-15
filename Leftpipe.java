import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Leftpipe here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Leftpipe extends Actor
{
    /**
     * Act - do whatever the Leftpipe wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    // The pipe needs a world to go to in order to be functional
    public World worldToGo;
    public Leftpipe(boolean moving, int scalex, int scaley, World worldToGo)
    {
        setRotation(-90);
        GreenfootImage img = getImage();
        img.scale(scalex,scaley);
        this.worldToGo = worldToGo;
    }
    public void act()
    {
        
    }
    public World getWorldToSend()
    {
        return worldToGo;
    }
}
