import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Mushroom here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mushroom extends Powerup
{
    /**
     * Act - do whatever the Mushroom wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    // Mushrooms can just exist normally, or they can be a descending mushroom, so included that in the constructer
    public Mushroom(boolean canMove, boolean descendOnKey, int size1, int size2)
    {
        super(canMove, descendOnKey);
        GreenfootImage img = getImage();
        img.scale(size1,size2);
    }
    public void act()
    {
        super.act();
    }
}
