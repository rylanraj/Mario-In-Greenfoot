import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Block here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Block extends Platform
{
    /**
     * Act - do whatever the Block wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    // Blocks can either move or not move, however it is weird to have individual blocks move so I haven't used this feature much
    private boolean isMoveable;
    public Block(boolean moveable)
    {
        super(moveable);
        isMoveable = moveable;
    }
    public void act()
    {
        if(isMoveable)
        {
            move(-1);
        }
    }
}
