import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class World1Level1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class World1Level1 extends Levels
{
    // Attributes used to control generalization
    public int nativeresolutionX = 400;
    public int nativeresolutionY = 400;
    public World1Level1()
    {    
        super(400,400,false);
        // Prepare worlds first then generalize them 
        prepare();
        generalizeProject(400,400);
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    // All objects are added in prepare
    private void prepare()
    {
        // Objects associated with the level marios in
        addLevelObjects();
        // Enemies
        addEnemies();
        // Powerups
        addPowerups();
        //Mario
        addMario();
    }

    public void addEnemies()
    {
        addKoopa(300,700,10,false,false, nativeresolutionX, nativeresolutionY);
        addKoopa(300,1750,97,true,false, nativeresolutionX, nativeresolutionY);
        addKoopa(300,2000,30,true,false, nativeresolutionX, nativeresolutionY);
        addParaTroopa(300,1200,300,nativeresolutionX, nativeresolutionY);
        addParaTroopa(250,900,320, nativeresolutionX, nativeresolutionY);
        addParaTroopa(300,1600,325, nativeresolutionX, nativeresolutionY);
        addGoomba(200,1500,200,nativeresolutionX,nativeresolutionY);
        addGoomba(150,1900, 200,nativeresolutionX,nativeresolutionY);
    }
    public void addPowerups()
    {
        Mushroom mushroom = new Mushroom(false, false,25,25);
        addObject(mushroom,163,319);
    }
    public void addMario()
    {
        Mario mario = new Mario(false,false,false,true,nativeresolutionX,nativeresolutionY);
        addObject(mario,62,53);
        mario.containsMushroom = false;
        mario.powerupMoved = false;
    }
    public void addLevelObjects()
    {
        // Background
        GreenfootImage bg = new GreenfootImage("bg.png");
        bg.scale(500,425);
        setBackground(bg);
        // Make a 100 brick wide floor at the height of 390 that has a finish line at the end and other bricks
        addBricksToFloor(0,115, 390, true, false);
        addBrickWall(-15,15,944,false);
        addBrickWall(-15,15,966,false);
        addBrickWall(-15,15,988,false);
        // Staircase
        addStairCase(2316,343,10,false,false);
        // Tutorial Text
        TutorialText tutorialText = new TutorialText("Press P to use powerups");
        addObject(tutorialText,301,216);
        TutorialText tutorialText2 = new TutorialText("Press S to duck/slide");
        addObject(tutorialText2,700,216);
        TutorialText tutorialText3 = new TutorialText("Press UP ARROW to sprint");
        addObject(tutorialText3,700,270);
        addTutorialText("Stomp on enemies and dodge them",1250,300);
    }
}
