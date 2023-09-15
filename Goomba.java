import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Goomba here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Goomba extends Enemy
{
    // Animation attributes
    private GreenfootImage [] LEFT;
    private GreenfootImage [] RIGHT;
    private GreenfootImage [] LEFTFLIPPED;
    private GreenfootImage [] RIGHTFLIPPED;
    private GreenfootImage [] animation;
    // General ints
    public int frame;
    public double speed;
    private final int TIMER;
    private int timer;
    // Movement and state attributes
    public int edgeTimer;
    private int flipTimer;
    private boolean flipTimerSet;
    public boolean flipped;
    // World related
    private int nativeW;
    private int nativeH;
    public Goomba(int timer, GreenfootImage[] LEFT, GreenfootImage [] RIGHT, GreenfootImage[] LEFTFLIPPED, GreenfootImage [] RIGHTFLIPPED, boolean floored, int nativeW, int nativeH)
    {
        super(true, false, floored);
        this.LEFT = LEFT;
        this.RIGHT = RIGHT;
        this.LEFTFLIPPED = LEFTFLIPPED;
        this.RIGHTFLIPPED = RIGHTFLIPPED;
        this.TIMER = timer;
        this.nativeW = nativeW;
        this.nativeH = nativeH;
        animation = LEFT;
        speed = 1;
    }
    public void act()
    {
        generalizeSpeed();
        nearMarioChecker();
        timer--;
        edgeTimer --;
        actCounter++;
        setAnimation(animation);
        checkDescend();
        animationMovement();
        platformBelow();
        platformLeft();
        platformRight();
        checkFlip();
        turnAround();
        generalizeGoomba(getWorld().getWidth() * 0.04, getWorld().getHeight() * 0.0375);
    }
    public void generalizeSpeed()
    {
        if(nativeW != getWorld().getWidth())
        {
            speed = getWorld().getWidth() * 0.0025;   
        }
    }
    public void generalizeGoomba(double xs, double ys)
    {
        int x = (int)xs;
        int y = (int)ys;
        GreenfootImage img = getImage();
        if(getWorld().getWidth() != nativeW || getWorld().getHeight() != nativeH)
        {
            img.scale(x,y);
        }
    }
    // Turn around if enemy timer is at 0
    public void turnAround()
    {
        if(timer < 0 && animation == LEFT)
        {
            animation = RIGHT;
            timer = TIMER;
        }
        if(timer < 0 && animation == RIGHT)
        {
            animation = LEFT;
            timer = TIMER;
        }
    }
    // Check if goomba is flipped
    private void checkFlip()
    {
        if(flipped && !flipTimerSet)
        {
            flipTimer = 250;
            flipTimerSet = true;
        }
        if(flipped)
        {
            if(animation == LEFT)
            {
                animation = LEFTFLIPPED;
            }
            if(animation == RIGHT)
            {
                animation = RIGHTFLIPPED;
            }
            speed = 0;
            flipTimer--;
        }
        if(!flipped)
        {
            speed = 1;
            if(animation == LEFTFLIPPED)
            {
                animation = LEFT;
            }
            if(animation == RIGHTFLIPPED)
            {
                animation = RIGHT;
            }
        }
        if(flipTimer <= 0)
        {
            flipped = false;
            flipTimerSet = false;
        }
    }
    // Move when there's a certain animation
    private void animationMovement()
    {
        if(animation == LEFT)
        {
            move((int)speed * -1);
        }
        if(animation == RIGHT)
        {
            move((int)speed);
        }
        if(isAtEdge() && animation == LEFT && edgeTimer <= 0)
        {
            animation = RIGHT;
            edgeTimer = 100;
        }
        if(isAtEdge() && animation == RIGHT && edgeTimer <= 0)
        {
            animation = LEFT;
            edgeTimer = 100;
        }
    }
    // Check if gravity needs to be applied
    private void checkGravity()
    {
        if(!isGrounded && floored)
        {
            applyGravity(375);
        }
        else if(!isGrounded && !floored)
        {
            applyGravity(400);
        }
    }
    // Platform below the goomba (taken from mario)
    public void platformBelow()
    {
        // For every platform actor at mario's feet
        //Actor leftfoot = getOneObjectAtOffset(0 - getImage().getWidth() / 2,feet, Platform.class);
        //Actor rightfoot = getOneObjectAtOffset(getImage().getWidth(),feet, Platform.class);
        for(Actor actor : getObjectsAtOffset(0,feet + 2,Platform.class))
        {
            // If the platform exists
            if(actor != null)
            {
                // If mario intersects it, and he's above it and it's not a pipe and he doesn't have a floor
                if(intersects(actor) && getY() < actor.getY() && actor.getClass() != Pipe.class && !floored)
                {
                    gravity = 1.5;
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
        for(Actor actor : getObjectsAtOffset(getImage().getWidth(),feet, Platform.class))
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
        for(Actor actor : getObjectsAtOffset(0 - getImage().getWidth() / 2,feet, Platform.class))
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
    }
    // Check if there's platform right of the koopa
    public void platformRight()
    {
        // For each platform at mario's offset to the right
        for(Actor actor : getObjectsAtOffset(12,0,Platform.class))
        {
            // If the platform exists and we're intersecting it(from the right)
            if(actor != null && intersects(actor) && animation == RIGHT)
            {
                animation = LEFT;
            }
        }
    }
    // Check if there's a platform left of the koopa
    public void platformLeft()
    {
        // For each platform at mario's offset to the right
        for(Actor actor : getObjectsAtOffset(-12,0,Platform.class))
        {
            // If the platform exists and we're intersecting it(from the right)
            if(actor != null && intersects(actor) && animation == LEFT)
            {
                animation = RIGHT;
            }
        }
    }
}
