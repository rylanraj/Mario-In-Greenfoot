import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level3 extends LevelText
{
    /**
     * Act - do whatever the Level3 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    World1Level3 w1l3 = new World1Level3();
    public void act()
    {
        checkMouse(w1l3);
    }
}
