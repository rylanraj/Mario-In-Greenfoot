import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Entity here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Entity extends Actor
{
    /**
     * Act - do whatever the Entity wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    // All entities inherit some form of Gravity
    public double gravity = 1.5;
    public double gForce = 0.2;
    public boolean isGrounded;
    public boolean floored;
    public boolean platformBelow;
    // And gravity related integers (can be overrided in their class)
    public int height, feet;
    public int floor;
    // Most enemies need to apply gravity in some form
    public void applyGravity(int floor)
    {
        setLocation(getX(), getY() + (int)gravity);
        gravity += gForce;
        if(getY() >= floor)
        {
            isGrounded = true;
        }
    }
    // Getters for img width and height
    public int getImgWidth()
    {
        return  getImage().getWidth();
    }
    public int getImgHeight()
    {
        return getImage().getHeight();
    }
    // Checker for a platform below
    public boolean platformChecker()
    {
        return platformBelow;
    }
    // Check for a single platform
    public void singlePlatformDetectionBelow()
    {
        height = getImage().getHeight();
        feet = (int)(height - 4);
        // Get an a platform a little bit past mario's feet
        Actor middlefoot = getOneObjectAtOffset(0,feet, Platform.class);
        Actor middlefoot2 = getOneObjectAtOffset(-3,feet, Platform.class);
        Actor middlefoot3 = getOneObjectAtOffset(+3,feet, Platform.class);
        // If it doesn't exist and mario doesn't have a floor
        if(middlefoot == null && middlefoot2 == null && middlefoot3 == null && !floored)
        {
            // Mario is not grounded
            isGrounded = false;
            platformBelow = false;
        }
        // If it exists, and they doesn't have a floor
        if(middlefoot != null && !floored)
        {
            if(intersects(middlefoot))
            {
                isGrounded = true;
                platformBelow = true;
            }
            // If mario is grounded
            if(isGrounded && intersects(middlefoot))
            {
                setLocation(getX(), getY() - 1);    
            }
        }
    }
    // Platform detection below enemies
    public void platformBelowEnemy()
    {
        // For every platform actor at their feet
        for(Actor actor : getObjectsAtOffset(0,feet + 2,Platform.class))
        {
            // If the platform exists
            if(actor != null)
            {
                // If they intersects it, and he's above it and it's not a pipe and he doesn't have a floor
                if(intersects(actor) && getY() < actor.getY() && !floored)
                {
                    gravity = 1.5;
                    // Set their location higher since we don't want him intersecting it, we want him on top of it
                    setLocation(getX(), getY() - 1);
                    // Now their on the platform so is grounded should be true
                    isGrounded = true;
                }
            }
        }
        for(Actor actor : getObjectsAtOffset(getImage().getWidth(),feet, Platform.class))
        {
            // If the platform exists
            if(actor != null)
            {
                // If they intersects it, and he's above it and it's not a pipe and he doesn't have a floor
                if(intersects(actor) && getY() < actor.getY() && !floored)
                {
                    // Set his location higher since we don't want him intersecting it, we want him on top of it
                    setLocation(getX(), getY() - 1);
                    // Now he's on the platform so is grounded should be true
                    isGrounded = true;
                }
            }
        }
        for(Actor actor : getObjectsAtOffset(0 - getImage().getWidth() / 2,feet, Platform.class))
        {
            // If the platform exists
            if(actor != null)
            {
                // If they intersects it, and he's above it and it's not a pipe and he doesn't have a floor
                if(intersects(actor) && getY() < actor.getY() && !floored)
                {
                    // Set his location higher since we don't want him intersecting it, we want him on top of it
                    setLocation(getX(), getY() - 1);
                    // Now he's on the platform so is grounded should be true
                    isGrounded = true;
                }
            }
        }
        // If thye have a floor and is not touching the platform in any way
        if(!isTouching(Platform.class) && floored)
        {
            // THey are not grounded since he's not touching the platform
            isGrounded = false;
            // If he is on the floor, he is grounded
            if(getY() >= floor)
            {
                isGrounded = true;
            }
        }
    }
}
