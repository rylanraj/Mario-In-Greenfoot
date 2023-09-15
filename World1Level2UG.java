import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class World1Level2UG here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class World1Level2UG extends Underground
{
    /**
     * Constructor for objects of class World1Level2UG.
     * 
     */
    public World1Level2UG()
    {
        prepare();
    }
    public void prepare()
    {
        setNewBackground();
        // Methods to add platforms and Mario
        addPlatformObjects();
        addMario();
    }
    public void addPlatformObjects()
    {
        addBrickWall(-8,20, 50, false);
        addBrickWall(-8,20, 25, false);
        addBricksToFloor(0,25,390,false,false);
        World1Level2Ext mw = new World1Level2Ext();
        Leftpipe lp = new Leftpipe(false,100,100,mw);
        addObject(lp,575,330);
    }
    public void addMario()
    {
        Mario newMario = new Mario(false,false,false,false,400,400);
        addObject(newMario,100,10);
    }
    public void setNewBackground()
    {
        GreenfootImage img = new GreenfootImage("UGbackground.gif");
        setBackground(img);
    }
}
