import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PowerupBox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Powerbox extends Unmoveable
{
    // Static image that is there because it was there in the original mario games
    public Powerbox()
    {
        GreenfootImage img = getImage();
        img.scale(300,320);
    }
}
