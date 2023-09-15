import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class World1Level2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class World1Level2 extends Levels
{
    /**
     * Constructor for objects of class World1Level2.
     * 
     */
    public World1Level2()
    {
        super(400,400,true);
        prepare();
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        // Methods to organize the level
        updateBackground();
        addMario();
        addPlatformObjects();
    }
    private void addPlatformObjects()
    {
        World1Level2UG ug = new World1Level2UG();
        Pipe pipe = new Pipe(100, 100, ug);
        addObject(pipe,82,390);
        TutorialText txt = new TutorialText("Press S to go down pipes");
        addObject(txt,200,200);
    }
    private void addMario()
    {
        Mario mario = new Mario(true,false,false,false,400,400);
        mario.containsMushroom = false;
        mario.powerupMoved = false;
        addObject(mario,65,75);
    }
    private void updateBackground()
    {
        GreenfootImage bg = new GreenfootImage("bg.png");
        bg.scale(500,425);
        setBackground(bg);
    }
}
