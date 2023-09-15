import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level2 extends LevelText
{
    /**
     * Act - do whatever the Level2 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    World1Level2 w1l2 = new World1Level2();
    public void act()
    {
        checkMouse(w1l2);
    }
}
