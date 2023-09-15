import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LevelText here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LevelText extends Actor
{
    /**
     * Act - do whatever the LevelText wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    // Attributes
    public boolean mouseOver;
    public static int MAXTRANS = 255;
    public void act()
    {
        // Add your action code here.
    }
    // Check if the mouse if over this object, and change worlds if this object is clicked
    public void checkMouse(World world)
    {
        if(Greenfoot.mouseMoved(null))
        // If the mouse is being moved over nothing
        {
            mouseOver = Greenfoot.mouseMoved(this);
            // mouseOver = Greenfoot.mouseMoved boolean, will return true if over the object
        }
        if(mouseOver)
        // If the mouse is over the object
        {
            adjTrans(MAXTRANS / 2);
            // Divide it's transparency by 2
        }
        if(mouseOver == false)
        // If the mouse isn't over the object
        {
            adjTrans(MAXTRANS);
            // Set it's transparency to max if it isn't already
        }
        if(Greenfoot.mouseClicked(this))
        {
            Greenfoot.setWorld(world);    
        }
    }
    // Adjust this objects transparency
    public void adjTrans(int adjust)
    {
        GreenfootImage tempImage = getImage();
        tempImage.setTransparency(adjust);
    }
}
