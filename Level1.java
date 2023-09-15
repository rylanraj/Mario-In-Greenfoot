import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level1 extends LevelText
{
    /**
     * Act - do whatever the Level1 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    // All subclasses just check mouse and change levels based on their names
    World1Level1 w1l1 = new World1Level1();
    public void act()
    {
        checkMouse(w1l1);
    }
}
