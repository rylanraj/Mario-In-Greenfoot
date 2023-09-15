import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Pipe here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pipe extends Platform
{
    /**
     * Act - do whatever the Pipe wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    // Send mario to a world using a pipe
    public World worldToGo;
    public Pipe(double x, double y, World worldToGo)
    {
        super(false);
        GreenfootImage img = getImage();
        img.scale((int)x,(int)y);
        this.worldToGo = worldToGo;
    }
    public World getWorldToSend()
    {
        return worldToGo;
    }
}
