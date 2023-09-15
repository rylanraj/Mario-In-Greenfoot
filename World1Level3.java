import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class World1Level3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class World1Level3 extends Levels
{
    /**
     * Constructor for objects of class World1Level3.
     * 
     */
    public int nativeresolutionX = 400;
    public int nativeresolutionY = 400;
    public World1Level3()
    {
        super(400,400,false);
        prepare();
        generalizeProject(400,400);
    }
    
    private void prepare()
    {
        GreenfootImage bg = new GreenfootImage("bg.png");
        bg.scale(500,425);
        setBackground(bg);
        addPlatforms();
        addEntities();
    }
    // Add objects that are of the entity class
    private void addEntities()
    {
        Mario mario = new Mario(false,true, false,false,nativeresolutionX,nativeresolutionY);
        mario.containsMushroom = false;
        mario.powerupMoved = false;
        addObject(mario,62,53);
        addParaTroopa(75,325,135,nativeresolutionX,nativeresolutionY);
        addGoomba(150,600,375,nativeresolutionX,nativeresolutionY);
        addGoomba(200,590,375,nativeresolutionX,nativeresolutionY);
        addGoomba(250,590,375,nativeresolutionX,nativeresolutionY);
        addGoomba(250,1900,375,nativeresolutionX,nativeresolutionY);
        addParaTroopa(200,1800,340,nativeresolutionX,nativeresolutionY);
    }
    // Add platforms
    private void addPlatforms()
    {
        addBricksToFloor(0,130,390,true,false);
        addBrickWall(-13,15,800,false);
        addBrickWall(-13,15,824,false);
        addBrickWall(-13,15,848,false);
        addStairCase(200,342,9,false,false);
        addGroupOfBlocks(1000,286,true);
        addGroupOfBlocks(1300,286,false);
        addGroupOfBlocks(1600,286,false);
        addStairCase(2000,342,4,false,false);
        addStairCase(2220,342,4,true,false);
    }
}
