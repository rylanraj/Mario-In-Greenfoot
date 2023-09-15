import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TutorialText here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TutorialText extends Actor
{
    // Tutorial text can be set to whatever text
    public TutorialText(String text)
    {
        GreenfootImage img = new GreenfootImage(600,400);
        Font font = new Font("Arial", true, true, 20);
        img.setFont(font);
        img.setColor(Color.WHITE);
        img.drawString(text,200,200);
        setImage(img);
    }
}
