import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.GreenfootImage;

/**
 * Write a description of class Koopa here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Paratroopa extends Koopa
{
    public Paratroopa(int timer,GreenfootImage [] LEFT, GreenfootImage [] RIGHT, GreenfootImage [] LEFTSHELL, GreenfootImage [] RIGHTSHELL, boolean stomped, int nativeW, int nativeH)
    {
        super(timer,LEFT,RIGHT,LEFTSHELL,RIGHTSHELL,stomped,false,nativeW,nativeH);
    }
    public void act()
    {
        paraAct();
        generalizeCheckerParaTroopa();
    }
}

