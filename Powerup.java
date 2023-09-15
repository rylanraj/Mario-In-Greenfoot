import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Powerups here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Powerup extends Actor
{
    /**
     * Act - do whatever the Powerups wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    // Boolean attributes
    public static boolean canMove;
    public static boolean allowedToDescend;
    public static boolean pPressed;
    private boolean allowedDescending;
    private int speed;
    public Powerup(boolean moving, boolean onKeyDescend)
    {
        canMove = moving;
        speed = 2;
        allowedDescending = onKeyDescend;
    }
    public void act()
    {
        // If the powerup can move, move it
        if(canMove)
        {
            move(speed);
        }
        // If its at the edge, move the opposite way
        if(isAtEdge())
        {
            speed *= -1;        
        }
        // Get the mario in the world, and cast it to mario
        Mario mario = (Mario)getOneObjectAtOffset(0,0,Mario.class);
        // If the powerup's getY() is greater than the height of its world
        if(getY() > getWorld().getHeight())
        {
            // Reset all of the logic since the powerup since it's no longer in the world
            this.pPressed = false;
            this.allowedToDescend = false;
            mario.powerupMoved = false;
            mario.containsMushroom = false;
            getWorld().removeObject(this);
        }
        // If the powerup is allowed to descend and the user presses p once or more
        if(allowedToDescend && Greenfoot.isKeyDown("p"))
        {
            // The user has pressed p = true
            pPressed = true;
        }
        // If p press is true and allowed descending is true
        if(pPressed && allowedDescending)
        {
            // Descend the powerup slowly
            setLocation(getX(), getY() + 3);
            // Mario no longer contains a powerup
            mario.containsMushroom = false;
            // Reset his ability to move a powerup back inside the powerbox
            mario.powerupMoved = false;
        }
    }
}
