import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class World1Level2Ext here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class World1Level2Ext extends Levels
{
    /**
     * Constructor for objects of class World1Level2Ext.
     * 
     */
    public World1Level2Ext()
    {
        super(400,400,true);
        prepare();
    }
    private void prepare()
    {
        addLevelObjects();
        addMario();
    }
    private void addLevelObjects()
    {
        Fakepipe fp = new Fakepipe(180, this);
        addObject(fp,100,0); 
        FinishLine finishLine = new FinishLine();
        addObject(finishLine,304,286);
    }
    private void addMario()
    {
        Mario mario = new Mario(true,false,false,false,400,400);
        addObject(mario,100,0);    
    }
}
