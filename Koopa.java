import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.GreenfootImage;

/**
 * Write a description of class Koopa here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Koopa extends Enemy
{
    // Animation attributes
    public int skipRate;
    public int actCounter;
    public int frame;
    // Body and state attributes
    public double speed;
    public int edgeTimer;
    private int shellTimer;
    private final int SHELLTIMER;
    private final int TIMER;
    private int timer;
    // Relative location attributes
    private int feet,floor;
    // Booleans
    public boolean stomped;
    private boolean scaled;
    // Animation attributes
    private GreenfootImage [] LEFT = {};
    private GreenfootImage [] RIGHT = {};
    private GreenfootImage [] LEFTSHELL = {};
    private GreenfootImage [] RIGHTSHELL = {};
    private GreenfootImage [] animation = {};
    // World related
    private int nativeW;
    private int nativeH;
    public void act()
    {
        generalizeSpeed();
        generalizeGravity();
        timer--;
        edgeTimer --;
        actCounter++;
        nearMarioChecker();
        setAnimation();
        checkDescend();
        animationMovement();
        jumpAtRandom();
        platformRight();
        platformLeft();
        platformBelow();
        rangePlatformDetection(getWorld().getWidth() * 0.03);
        turnAround();
        generalizeCheckerKoopa();
    }
    // Para troopa act, paratroopas don't jump or fall
    public void paraAct()
    {
        generalizeSpeed();
        edgeTimer --;
        actCounter++;
        timer--;
        nearMarioChecker();
        turnAround();
        setAnimation();
        animationMovement();
        platformRight();
        platformLeft();
    }
    // Same logic as mario's speed and gravity generaliztion, if the world isn't in it's height, generalize gravity, if it's not in it's width, generalize speed
    public void generalizeSpeed()
    {
        if(nativeW != getWorld().getWidth())
        {
            speed = getWorld().getWidth() * 0.0025;   
        }
    }
    public void generalizeGravity()
    {
        if(nativeH != getWorld().getHeight() && !scaled)
        {
            gravity = getWorld().getHeight() * 0.00375;
            gForce = getWorld().getHeight() * 0.0005;
            scaled = true;
        }
    }
    public void generalize(double xs, double ys, double s1, double sy1)
    {
        int s = (int)s1;
        int sy = (int)sy1;
        int x = (int)xs;
        int y = (int)ys;
        GreenfootImage img = getImage();
        if(animation != LEFTSHELL || animation != RIGHTSHELL)
        {
            img.scale(x,y);
        }
        if(animation == LEFTSHELL || animation == RIGHTSHELL)
        {
            img.scale(s,sy);    
        }
    }
    public void generalizeCheckerParaTroopa()
    {
        if(getWorld().getWidth() != nativeW || getWorld().getHeight() != nativeH)
        {
            generalize(getWorld().getWidth() * 0.0575, getWorld().getHeight() * 0.0725, getWorld().getWidth() * 0.0725, getWorld().getHeight() * 0.0375);
        }    
    }
    public void generalizeCheckerKoopa()
    {
        if(getWorld().getWidth() != nativeW || getWorld().getHeight() != nativeH)    
        {
            generalize(getWorld().getWidth() * 0.055, getWorld().getHeight() * 0.08, getWorld().getWidth() * 0.04, getWorld().getHeight() * 0.0375);
        }
    }
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
    // Jump randomly
    public void jumpAtRandom()
    {
        if(isGrounded && Greenfoot.getRandomNumber(50) + 1 == 1)
        {
            gravity = getWorld().getHeight() * -0.0075;
            isGrounded = false;
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
            if(actor != null && intersects(actor) && animation == RIGHTSHELL)
            {
                animation = LEFTSHELL;
                edgeTimer = 50;
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
            if(actor != null && intersects(actor) && animation == LEFTSHELL)
            {
                animation = RIGHTSHELL;
                edgeTimer = 50;
            }
        }
    }
    // Smart movement, works but unused for regular koopas since it makes the game extremely robotic like,
    public void smartMovement()
    {
        Object [] marioArray = getWorld().getObjects(Mario.class).toArray();
        if(marioArray[0].getClass() == Mario.class)
        {
            Mario mario = (Mario)marioArray[0];
            if(getObjectsInRange(400,Mario.class).contains(mario))
            {
                if(getX() < mario.getX() && !stomped)
                {
                    animation = RIGHT;
                }
                if(getX() > mario.getX() && !stomped)
                {
                    animation = LEFT;
                }
            }
        }
    }
    // Check if gravity is needed to be applied
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
    // Check if the koopa is stomped or not
    private boolean stomped()
    {
        if(inShell())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    // Check if the animation is in the shell or not
    private boolean inShell()
    {
        if(animation == LEFTSHELL || animation == RIGHTSHELL)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    // Check if the koopa should animate
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
    //Set the koopas animation
    public void setAnimation()
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
    // Move the koopa with the corresponding animation
    private void animationMovement()
    {
        if(animation == LEFT)
        {
            move((int)speed * -1);
        }
        if(animation == LEFTSHELL)
        {
            move((int)speed * - 4);
        }
        if(animation == RIGHT || animation == RIGHTSHELL)
        {
            move((int)speed);
        }
        if(animation == RIGHTSHELL)
        {
            move((int)speed * 4);
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
        if(isAtEdge() && animation == LEFTSHELL && edgeTimer <= 0)
        {
            animation = RIGHTSHELL;
            edgeTimer = 50;
        }
        if(isAtEdge() && animation == RIGHTSHELL && edgeTimer <= 0)
        {
            animation = LEFTSHELL;
            edgeTimer = 50;
        }
        if(stomped && animation == LEFT && nearMario)
        {
            animation = LEFTSHELL;
            shellTimer = SHELLTIMER;
        }
        if(stomped && animation == RIGHT && nearMario)
        {
            animation = RIGHTSHELL;
            shellTimer = SHELLTIMER;
        }
        if(shellTimer <= 0 && animation == LEFTSHELL)
        {
            stomped = false;
            this.animation = LEFT;
        }
        if(shellTimer <=0 && animation == RIGHTSHELL)
        {
            stomped = false;
            this.animation = RIGHT;
        }
        if(shellTimer >= 0)
        {
            shellTimer--;
        }
    }
    // Koopa constructor, can control states and whether or not it has a floor
    public Koopa(int Timer, GreenfootImage [] LEFT, GreenfootImage [] RIGHT, GreenfootImage [] LEFTSHELL, GreenfootImage [] RIGHTSHELL, boolean stomped, boolean floored, int nativeW, int nativeH)
    {
        super(true,true,floored);
        this.LEFT = LEFT;
        this.RIGHT = RIGHT;
        this.LEFTSHELL = LEFTSHELL;
        this.RIGHTSHELL = RIGHTSHELL;
        this.stomped = stomped;
        this.nativeW = nativeW;
        this.nativeH = nativeH;
        this.TIMER = Timer;
        frame = 0;
        skipRate = 10;
        speed = 1;
        SHELLTIMER = 150;
        animation = LEFT;
        if(stomped)
        {
            animation = LEFTSHELL;
        }
    }
    
}

