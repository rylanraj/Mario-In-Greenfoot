import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Text here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Text extends Actor
{
    /**
     * Act - do whatever the Text wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    // If enter is pressed take the player to the level selction so they can start playing
    public void act()
    {
        if(Greenfoot.isKeyDown("Enter"))
        {
            LevelSelection ls = new LevelSelection(false,false);
            Greenfoot.setWorld(ls);
        }
    }
}
