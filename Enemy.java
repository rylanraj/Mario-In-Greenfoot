import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.GreenfootImage;

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Entity
{
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    // Attributes
    public int frame;
    public int skipRate = 10;
    public int actCounter;
    public boolean isGrounded;
    private boolean canFall;
    private boolean isKoopa;
    public boolean nearMario;
    public boolean floored;
    public int floor = 380;
    public int height, feet;
    // Enemy constructer
    public Enemy(boolean canFall, boolean isKoopa, boolean floored)
    {
        // These booleans control the enemies abilities
        this.canFall = canFall;
        this.isKoopa = isKoopa;
        this.floored = floored;
        height = getImage().getHeight();
        feet = (int)(height - 5);
    }
    // Check if the enemy is near mario
    public void nearMarioChecker()
    {
        Object [] marioArray = getWorld().getObjects(Mario.class).toArray();
        if(marioArray[0].getClass() == Mario.class)
        {
            Mario mario = (Mario)marioArray[0];
            if(getObjectsInRange(45,Mario.class).contains(mario))
            {
                nearMario = true;
            }
            else
            {
                nearMario = false;
            }
        }
    }
    // Return if the koopa is near mario are not
    public boolean nearMario()
    {
        return nearMario;
    }
    // Detect platforms with a range
    public void rangePlatformDetection(double xp)
    {
        // Get an a platform a little bit past mario's feet
        int x = (int)xp;
        Actor actor = getOneObjectAtOffset(0,feet + 2, Platform.class);
        // If it doesn't exist and mario doesn't have a floor
        if(actor == null && !floored)
        {
            // Mario is not grounded
            isGrounded = false;
        }
        // If it exists, and mario doesn't have a floor
        if(actor != null && !floored)
        {
            // If mario is grounded
            if(isGrounded)
            {
                // Create a range for the platforms width
                Range range = new Range(actor.getX() - x, actor.getX() + x); // 12
                // If mario's not on the width of the platform while grounded
                if(!range.contains(getX()))
                {
                    // He's not grounded since there's no platform to be on
                    isGrounded = false;
                }
            }
        }
    }
    // Animation checker
    public boolean animate()
    {
        if(actCounter % skipRate == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    // Animation setter
    public void setAnimation(GreenfootImage [] animation)
    {
        if(animate())
        {
            if(frame >= animation.length)
            {
                frame = 0;
            }
            setImage(animation[frame]);
            frame++;
        }
    }
    // Descend checker
    public void checkDescend()
    {
        if(canFall && !isGrounded)
        {
            setLocation(getX(),getY() + (int)gravity);
            gravity = gravity + gForce;
        }
        if(getY() > floor && floored)
        {
            setLocation(getX(),floor);
            isGrounded = true;
        }
    }
    // Check if this object is a koopa
    public boolean isKoopa()
    {
        return isKoopa();
    }
    // Check if there's a platform below the Enemy, same logic as marios
    public void platformBelow()
    {
        for(Actor actor : getObjectsAtOffset(0,feet,Platform.class))
        {
            // If the platform exists
            if(actor != null)
            {
                // If mario intersects it, and he's above it and it's not a pipe and he doesn't have a floor
                if(intersects(actor) && getY() < actor.getY() && actor.getClass() != Pipe.class && !floored)
                {
                    // Set his location higher since we don't want him intersecting it, we want him on top of it
                    setLocation(getX(), getY() - 1);
                    // Now he's on the platform so is grounded should be true
                    isGrounded = true;
                }
                // The same thing if it's a pipe, but no set location because he'll need to be intersecting it to change worlds
                else if(intersects(actor) && getY() < actor.getY() && actor.getClass() == Pipe.class)
                {
                    isGrounded = true;    
                }
            }
        }
        // If mario has a floor and is not touching the platform in any way
        if(!isTouching(Platform.class) && floored)
        {
            // Mario is not grounded since he's not touching the platform
            isGrounded = false;
            // If he is on the floor, he is grounded
            if(getY() >= floor)
            {
                isGrounded = true;
            }
        }
        // If mario isn't touching the platform and he doesn't have a floor
        if(!isTouching(Platform.class) && !floored)
        {
            // If he's on the floor, isGrounded is false since he doesn't have a floor
            if(getY() >= floor)
            {
                isGrounded = false;
            }    
        }
    }
}
