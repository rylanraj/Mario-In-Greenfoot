import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.GreenfootImage;
import java.util.ArrayList;

/**
 * Write a description of class Mario here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mario extends Entity
{
    /**
     * Act - do whatever the Mario wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    // Marios animations
    private static GreenfootImage [] LEFT = {
        new GreenfootImage("marioleft1.png"),
        new GreenfootImage("marioleft2.png"),
        new GreenfootImage("marioleft3.png")};
    private static GreenfootImage[] RIGHT = {
        new GreenfootImage("marioright1.png"),
        new GreenfootImage("marioright2.png"),
        new GreenfootImage("marioright3.png")};
    private static GreenfootImage[] LOOKUPRIGHT = {
        new GreenfootImage("mariolookupright.png")};
    private static GreenfootImage[] LOOKUPLEFT = {
        new GreenfootImage("mariolookupleft.png")};
    private static GreenfootImage[] DUCKLEFT = {
        new GreenfootImage("marioduckleft.png")};
    private static GreenfootImage [] DUCKRIGHT = {
        new GreenfootImage("marioduckright.png")};
    private static GreenfootImage [] RIGHTFALL = {
        new GreenfootImage("mariofallright.png")};
    private static GreenfootImage [] LEFTFALL = {
        new GreenfootImage("mariofallleft.png")};
    private static GreenfootImage [] RIGHTJUMP = {
        new GreenfootImage("mariojumpright.png")};
    private static GreenfootImage [] LEFTJUMP = {
        new GreenfootImage("mariojumpleft.png")};
    private static GreenfootImage [] MAXSPEEDLEFT = {
        new GreenfootImage("mariomaxspeedleft1.png"),
        new GreenfootImage("mariomaxspeedleft2.png"),
        new GreenfootImage("mariomaxspeedleft3.png")};
    private static GreenfootImage [] MAXSPEEDRIGHT = {
        new GreenfootImage("mariomaxspeedright1.png"),
        new GreenfootImage("mariomaxspeedright2.png"),
        new GreenfootImage("mariomaxspeedright3.png")};
    private static GreenfootImage [] MAXJUMPLEFT = {
        new GreenfootImage("mariomaxspeedjumpleft.png")};
    private static GreenfootImage [] MAXJUMPRIGHT = {
        new GreenfootImage("mariomaxspeedjumpright.png")};
    // Marios small animations
    private static GreenfootImage [] SMALLLEFT ={ 
        new GreenfootImage("smallMarioLeft1.png"), 
        new GreenfootImage("smallMarioLeft2.png")};
    private static GreenfootImage [] SMALLRIGHT = {
        new GreenfootImage("smallMarioRight1.png"),
        new GreenfootImage("smallMarioRight2.png")};
    private static GreenfootImage[] SMALLLOOKUPRIGHT = {
        new GreenfootImage("smallmariolookupright.png")};
    private static GreenfootImage[] SMALLLOOKUPLEFT = {
        new GreenfootImage("smallmariolookupleft.png")};
    private static GreenfootImage[] SMALLDUCKLEFT = {
        new GreenfootImage("smallmarioduckleft.png")};
    private static GreenfootImage[] SMALLDUCKRIGHT = {
        new GreenfootImage("smallmarioduckright.png")};
    private static GreenfootImage[] SMALLRIGHTFALL = {
        new GreenfootImage("smallmariorightfall.png")};
    private static GreenfootImage[] SMALLLEFTFALL = {
        new GreenfootImage("smallmarioleftfall.png")};
    private static GreenfootImage[] SMALLRIGHTJUMP = {
        new GreenfootImage("smallmariorightjump.png")};
    private static GreenfootImage[] SMALLLEFTJUMP = {
        new GreenfootImage("smallmarioleftjump.png")};
    private static GreenfootImage [] animation;
    // Speed attributes
    public double leftspeed;
    public double rightspeed;
    public double platformleftspeed;
    public double platformrightspeed;
    public double speedforce = 0.125;
    public double slidespeedforce = 0.05;
    public double MAXSPEED = 2;
    public double MAXSPRINTSPEED = 5;
    public double MAXPLATFORMSPEED = 2;
    // Size
    public int size;
    // Animation related integers
    private int skipRate;
    private int actCounter;
    private int frame;
    private int height, feet;
    // Floor
    public int floor = 375;
    // Booleans
    public boolean facingLeft;
    public boolean isGrounded;
    public boolean onPipe;
    public boolean damageTaken;
    public boolean floored;
    private boolean movedBack;
    private boolean platformBelow;
    private boolean onPlatform;
    private boolean airControl;
    // World booleans
    private boolean explorable;
    private boolean originalScrolling;
    public static boolean scrollingWorld;
    private boolean scaled;
    // World ints
    private int nativeW;
    private int nativeH;
    // Power up booleans
    public static boolean containsMushroom;
    public static boolean powerupMoved;
    // Timer integer
    public int damageTimer;
    public double getGravity()
    {
        return gravity;
    }
    public double getSpeed()
    {
        if(facingRightCheck())
        {
            return rightspeed;
        }
        else
        {
            return leftspeed;
        }
    }
    public double getPlatformSpeed()
    {
        if(facingRightCheck())
        {
            return platformrightspeed;
        }
        else
        {
            return platformleftspeed;
        }
    }
    private void enemyRemover()
    {
        for(Actor actor : getWorld().getObjects(Enemy.class))
        {
            if(scrollingWorld == true && actor.isAtEdge())
            {
                actor.getWorld().removeObject(actor);
            }
        }
    }
    private void leftPipeDetection()
    {
        Leftpipe pipe = (Leftpipe)getOneObjectAtOffset(0,0, Leftpipe.class);
        if(!getWorld().getObjects(Leftpipe.class).isEmpty())
        {
            if(pipe != null && intersects(pipe))
            {
                World world = pipe.getWorldToSend();
                Greenfoot.setWorld(world);
            }
        }
    }
    private void leftPipeFeetDetection()
    {
        Leftpipe pipe = (Leftpipe)getOneObjectAtOffset(0,feet, Leftpipe.class);
        if(pipe != null && !getWorld().getObjects(Leftpipe.class).isEmpty())
        {
            if(intersects(pipe))
            {
                isGrounded = true;
            }
        }
    }
    // Method for detecting power ups
    private void powerUpDetection(double respawnLocationX, double respawnLocationY, double widthsize, double heightsize)
    {
        int x = (int)respawnLocationX;
        int y = (int)respawnLocationY;
        int ws = (int)widthsize;
        int hs = (int)heightsize;
        Powerup powerup = (Powerup)getOneIntersectingObject(Powerup.class);
        // If the powerup exists and mario is intersecting it
        if(powerup != null && intersects(powerup))
        {
            // If the powerup is a mushroom
            if(powerup.getClass() == Mushroom.class || powerup.getClass() == BoxMushroom.class)
            {
                // If mario is small, increase size, else hold on to it if he's not
                if(size == 1)
                {
                    size += 1;
                    getWorld().removeObject(powerup);
                }
                else if(size == 2)
                {
                    containsMushroom = true;
                    getWorld().removeObject(powerup);
                }
            }
        }
        // If mario contains a mushroom, create it so it exists, and move it to the power up box
        if(containsMushroom && !powerupMoved)
        {
            BoxMushroom mushroom = new BoxMushroom(25,25,nativeW,nativeH);
            getWorld().addObject(mushroom,x,y);//50 y
            mushroom.generalize(getWorld().getWidth() * 0.0625, getWorld().getHeight() * 0.0625);
            powerupMoved = true;
            mushroom.pPressed = false;
        }
        // Once it's moved to the box, the powerup is allowed to descend
        if(powerupMoved)
        {
            powerup.allowedToDescend = true;
        }
    }
    // Detect if mario is at the finish line, if he is, take him back to level selection with the next level available
    private void finishLineDetection()
    {
        if(isTouching(FinishLine.class) && getWorld().getClass() == World1Level1.class)
        {
            LevelSelection LS = new LevelSelection(true,false);
            Greenfoot.setWorld(LS);
        }
        if(isTouching(FinishLine.class) && getWorld().getClass() == World1Level2Ext.class)
        {
            LevelSelection LS = new LevelSelection(true,true);
            Greenfoot.setWorld(LS);
        }
        if(isTouching(FinishLine.class) && getWorld().getClass() == World1Level3.class)
        {
            LevelSelection LS = new LevelSelection(true,true);
            Greenfoot.setWorld(LS);
        }
    }
    // Check if mario is in the world bounds, if mario falls off the platform when there's no floor, greenfoot should stop, indicating gameover
    private void checkWorldBounds(double b)
    {
        int bounds = (int)b;
        if(getY() >= bounds)
        {
            getWorld().showText("You died, press restart to play again", getWorld().getWidth() / 2, getWorld().getHeight() / 2);
            Greenfoot.stop();
        }
    }
    // Code for ensuring Mario's collision with ceilings is smooth and glitchless
    private void bopHead(Actor ceiling)
    {
        int ceilingHeight = ceiling.getImage().getHeight();
        int newY = ceiling.getY() + (ceilingHeight + getImage().getHeight())/2;
        if(intersects(ceiling))
        {
            Actor platform = getOneObjectAtOffset(0, feet + 2, Platform.class);
            if(platform != null && intersects(platform) && size == 2 && platform.getClass() != Pipe.class && platform.getClass() != Fakepipe.class)
            {
                size -= 1;
            }
        }
        if(intersects(ceiling))
        {
            Actor platform = getOneObjectAtOffset(0, feet + 2, Platform.class);
            if(platform != null && intersects(platform) && size == 2 && platform.getClass() != Pipe.class && platform.getClass() != Fakepipe.class)
            {
                size -= 1;
            }
        }
        if(ceiling.getClass() == CoinBlock.class)
        {
            Object [] blocks = getWorld().getObjects(Block.class).toArray();
            Block platform = (Block)blocks[0];
            ceiling.setImage("EmptyBlock.png");
            ceiling.getImage().scale(platform.getImage().getWidth(),platform.getImage().getHeight());
        }
        if(ceiling.getClass() == MushroomBlock.class)
        {
            Mushroom mushroom = new Mushroom(false, false,25,25);
            getWorld().addObject(mushroom,ceiling.getX(),ceiling.getY() - 25);
            EmptyBlock eb = new EmptyBlock();
            Object [] blocks = getWorld().getObjects(Block.class).toArray();
            Block platform = (Block)blocks[0];
            eb.setImage("EmptyBlock.png");
            eb.getImage().scale(platform.getImage().getWidth(),platform.getImage().getHeight());
            getWorld().addObject(eb,ceiling.getX(),ceiling.getY());
            getWorld().removeObject(ceiling);
        }
        setLocation(getX(), newY);
    }
    // Checking for platforms above
    private void platformAbove()
    {
        int spriteHeight = getImage().getHeight();
        int yDistance = (int)(spriteHeight/-2);
        Actor ceiling = getOneObjectAtOffset(0, yDistance, Platform.class);
        if(ceiling != null)
        {
            gravity = 0;
            bopHead(ceiling);
        }
    }
    // Checking for platforms below
    public boolean isGrounded()
    {
        return isGrounded;
    }
    // Cheking for platforms right of mario
    private void platformRight()
    {
        // For each platform at mario's offset to the right
        for(Actor actor : getObjectsAtOffset(12,0,Platform.class))
        {
            // If the platform exists and we're intersecting it(from the right)
            if(actor != null && intersects(actor))
            {
                platformrightspeed = 0;
                rightspeed = 0;
            }
        }
        for(Actor actor : getObjectsAtOffset(12,-10,Platform.class))
        {
            // If the platform exists and we're intersecting it(from the right) and mario's head
            if(actor != null && intersects(actor))
            {
                platformrightspeed = 0;
                rightspeed = 0;
            }
        }
    }
    // Checking for platforms left of mario same thing as right but for the left side
    private void platformLeft()
    {
        for(Actor actor : getObjectsAtOffset(-12,0,Platform.class))
        {
            if(actor != null && intersects(actor))
            {
                platformleftspeed = 0;
                leftspeed = 0;
            }
        }
        for(Actor actor : getObjectsAtOffset(-12,-10,Platform.class))
        {
            if(actor != null && intersects(actor))
            {
                platformleftspeed = 0;
                leftspeed = 0;
            }
        }
    }
    // Detecting if mario is on top of pipes, and if the user presses s, go in the pipe and change worlds
    public void pipeDetection()
    {
        // get one object at marios feet that is of the pipe class
        Pipe pipe = (Pipe)getOneObjectAtOffset(0,feet,Pipe.class);
        // If a pipe exists in the world
        if(!getWorld().getObjects(Pipe.class).isEmpty())
        {
            // If our y is greater than the pipe's y (account for height), we must be on the pipe since our feet is getting the object at a 0 x offset
            if(pipe != null && getY() <= pipe.getY())
            {
                onPipe = true;
            }
            // If we're on the pipe and pressing s and the pipe isn't fake
            if(pipe != null && onPipe && Greenfoot.isKeyDown("s") && pipe.getClass() != Fakepipe.class)
            {
                // Go down
                checkDescend();
                // Create a range to see whether or not we're fully in the pipe
                Range yrange = new Range(pipe.getY() - 20, pipe.getY() + 20);
                // If we're in the range, go to the world
                if(yrange.contains(getY()))
                {
                    World ug = pipe.getWorldToSend();
                    Greenfoot.setWorld(ug);
                }
            }
        }
    }
    // Gravity applier method 
    public void checkDescend()
    {
        setLocation(getX(),getY() + (int)gravity);
        gravity = gravity + gForce;
    }
    // Boolean to decide whether or not mario is facing right or right
    public boolean facingRightCheck()
    {
        if(animation == RIGHT || animation == SMALLRIGHT || animation == LOOKUPRIGHT || animation == SMALLLOOKUPRIGHT || animation == DUCKRIGHT ||
        animation == SMALLDUCKRIGHT || animation == RIGHTJUMP || animation == SMALLRIGHTJUMP || animation == RIGHTFALL || animation == SMALLRIGHTFALL
        || animation == MAXSPEEDRIGHT || animation == MAXJUMPRIGHT)
        {
            return true;
        }
        else
        {
            facingLeft = true;
            return false;
        }
    }
    // Mario constructor, not reccommended to make his world both explorable and scrolling, glitching may occur since mario will be moving while exploring
    public Mario(boolean floored, boolean explorable, boolean scrollingWorld, boolean originalScrolling, int nativeW, int nativeH)
    {
        skipRate = 10;
        size = 2;
        frame = 0;
        animation = SMALLRIGHT;
        leftspeed = -1.5;
        rightspeed = 1.5;
        platformleftspeed = -1.5;
        platformrightspeed = 1.5;
        height = getImage().getHeight();
        feet = (int)(height);
        this.floored = floored;
        this.explorable = explorable;
        this.scrollingWorld = scrollingWorld;
        this.originalScrolling = originalScrolling;
        this.nativeW = nativeW;
        this.nativeH = nativeH;
    }
    public void act()
    {
        // Generalize movement
        generalizeSpeed();
        generalizeGravity();
        // Check if mario is in bounds
        checkWorldBounds(getWorld().getHeight() * 1.125);
        // Add to the act counter every time act is called
        actCounter++;
        // After adding, set the appropiate animation
        setAnimation();
        // Update mario's animations
        updateAnimations();
        // Fix a bug where mario floats while ducking
        duckFixer();
        // Move mario, if he's supposed to be moving
        moveMario();
        // Apply gravity animations if mario is being affects by gravity in some way
        gravityAnimations();
        // Enemy detecting, for enemy collision around mario
        enemyDetection();
        // Koopa specific enemy detection
        koopaDetection();
        // Goomba specific enemy detection
        goombaDetection();
        // If mario isn't grounded, apply gravity
        checkGravityApplication();
        // Check if mario has a floor or not
        floorChecker();
        // Four way platform collision detection
        platformLeft();
        platformRight();
        platformBelow();
        singlePlatformDetectionBelow();
        platformAbove();
        // Pipe detection, for detecting when mario comes across a pipe
        pipeDetection();
        leftPipeDetection();
        leftPipeFeetDetection();
        // Finish line detection, so mario can beat the level
        finishLineDetection();
        // Power up detection so mario can detect power ups
        powerUpDetection(getWorld().getWidth() * 0.50333333333,getWorld().getHeight() * 0.125, getWorld().getWidth()  * 0.0625, getWorld().getHeight()  * 0.0625);
        // Check what world type mario is in
        worldTypeChecker();
        // Enemy remover, for certain world types
        enemyRemover();
        // Detecting platforms with ranges
        setFloor(getWorld().getHeight() * 0.9375);
        // Generalize mario's size
        generalizeMarioSize(getWorld().getWidth() * 0.0375,getWorld().getHeight() * 0.07,getWorld().getWidth() * 0.0375,getWorld().getHeight() * 0.0375,
        getWorld().getWidth() * 0.0375, getWorld().getHeight() * 0.05);
        // Generalize gravity
    }
    public void duckFixer()
    {
        if(Greenfoot.isKeyDown("s") && !isTouching(Platform.class) && !isTouching(Leftpipe.class))
        {
            setLocation(getX(),getY() + 1);
        }
    }
    public void generalizeSpeed()
    {
        // If the world is not in it's natural width
        if(nativeW != getWorld().getWidth())
        {
            MAXSPEED = getWorld().getWidth()  *  0.005;
            MAXSPRINTSPEED = getWorld().getWidth()  * 0.0125;
            MAXPLATFORMSPEED = getWorld().getWidth()  *  0.005;
            speedforce = getWorld().getWidth() * 0.0003125;
            slidespeedforce = getWorld().getWidth() * 0.000125;
        }
    }
    public void generalizeGravity()
    {
        // IF the world is not in its natural height
        if(nativeH != getWorld().getHeight() && !scaled)
        {
            gravity = getWorld().getHeight() * 0.00375;
            gForce = getWorld().getHeight() * 0.0005;
            scaled = true;
        }
    }
    public void generalizeMarioSize(double xs, double ys, double dx1, double dy1, double sx1, double sy1)
    {
        int x = (int)xs;
        int y = (int)ys;
        int dx = (int)dx1;
        int dy = (int)dy1;
        int sx = (int)sx1;
        int sy = (int)sy1;
        GreenfootImage img = getImage();
        // If the world isn't in its native resolution
        if(getWorld().getWidth() != nativeW || getWorld().getHeight() != nativeH)
        {
            if(animation != DUCKLEFT && animation != DUCKRIGHT && animation != SMALLLEFT && animation != SMALLRIGHT && size == 2)
            {
                img.scale(x,y);
            }
            else if(animation == DUCKLEFT || animation == DUCKRIGHT && size == 2)
            {
                img.scale(dx,dy);
            }
            else if(animation == SMALLLEFT || animation == SMALLRIGHT || animation == SMALLDUCKLEFT || animation == SMALLDUCKRIGHT || animation == SMALLRIGHTFALL || animation
            == SMALLLEFTFALL || animation == SMALLLOOKUPLEFT || animation == SMALLLOOKUPLEFT || animation == SMALLRIGHTJUMP || animation == SMALLLEFTJUMP)
            {
                img.scale(sx,sy);
            }
        }
        // Sizes in the native resoultion
        // Size 1 is 14 by 20
        // Size 1 ducking is 15 by 14
        // Size 2 is 15 by 28
        // Size 2 ducking is 15 by 15
    }
    // Floor is equal the double floor margin
    public void setFloor(double df)
    {
        int f = (int)df;
        floor = f;
    }
    // Check if the platform below is null or not
    public boolean platformChecker()
    {
        return platformBelow;
    }
    public void singlePlatformDetectionBelow()
    {
        height = getImage().getHeight();
        feet = (int)(height - 5);
        // Get an a platform a little bit past mario's feet
        Actor middlefoot = getOneObjectAtOffset(0,feet, Platform.class);
        Actor middlefoot2 = getOneObjectAtOffset(-3,feet, Platform.class);
        // If it doesn't exist and mario doesn't have a floor
        if(middlefoot == null && middlefoot2 == null && !floored)
        {
            // Mario is not grounded
            isGrounded = false;
            platformBelow = false;
        }
        // If it exists, and mario doesn't have a floor
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
    public int imgWidth()
    {
        return getImage().getWidth();
    }
    // Scroll similar to the original super mario world where mario stops moving and the platforms around him move
    public void originalScrolling()
    {
        if(getX() >= getWorld().getWidth() / 2 && Greenfoot.isKeyDown("d"))
        {
            rightspeed = 0;
            for(Actor actor : getWorld().getObjects(Actor.class))
            {
                if(actor.getClass() != Powerbox.class && actor.getClass() != Mario.class && actor.getClass() != BoxMushroom.class)
                {
                    actor.move((int)platformrightspeed * -1);
                }
            }
        }
        if(getX() >= getWorld().getWidth() / 2 && Greenfoot.isKeyDown("a"))
        {
            leftspeed = 0;
            for(Actor actor : getWorld().getObjects(Actor.class))
            {
                if(actor.getClass() != Powerbox.class && actor.getClass() != Mario.class && actor.getClass() != BoxMushroom.class)
                {
                    actor.move((int)platformleftspeed * -1);
                }
            }
        }
    }
    public boolean touchingPlatform()
    {
        if(isTouching(Platform.class))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    // Check whether world is explorable or scrolling
    public void worldTypeChecker()
    {
        if(explorable)
        {
            expandWorld();
        }
        if(scrollingWorld)
        {
            scrollingWorld();
        }
        if(originalScrolling)
        {
            originalScrolling();
        }
    }
    // Check if mario has a floor
    public void floorChecker()
    {
        if(floored)
        {
            makeMarioFloor();
        }
    }
    // Floor maker
    public void makeMarioFloor()
    {
        if(getY() > floor)
        {
            setLocation(getX(),(int)floor);
            isGrounded = true;
        }
    }
    // Method to make worlds explorable
    public void expandWorld()
    {
        if(isAtEdge() && facingRightCheck() == true)
        {
            rightspeed = 0;
            for(Actor actor : getWorld().getObjects(Actor.class))
            {
                if(actor.getClass() != Powerbox.class && actor.getClass() != BoxMushroom.class)
                {
                    actor.move((getWorld().getWidth() - 20) * -1); 
                }  
            }
        }
        if(isAtEdge() && facingRightCheck() == false)
        {
            leftspeed = 0;
            for(Actor actor : getWorld().getObjects(Actor.class))
            {
                if(actor.getClass() != Powerbox.class && actor.getClass() != BoxMushroom.class)
                {
                    actor.move((getWorld().getWidth() - 20)); 
                }  
            }
        }
    }
    // Scrolling world
    public void scrollingWorld()
    {
        for(Actor actor : getWorld().getObjects(Actor.class))
        {
            if(actor.getClass() != Powerbox.class && actor.getClass() != Mario.class && actor.getClass() != BoxMushroom.class)
            {
                actor.move(-1);    
            }
        }
    }
    public void checkGravityApplication()
    {
        if(!isGrounded)
        {
            checkDescend();
        }
        else
        {
            gravity = 1.5;
        }
    }
    public void enemyDetection()
    {
        Actor enemy = getOneObjectAtOffset(0,0,Enemy.class);
        if( enemy != null && intersects(enemy) && !damageTaken)
        {
            size -= 1;
            damageTaken = true;
            damageTimer = 75;
        }
        if(damageTaken && damageTimer > 0)
        {
            damageTaken = true;
            damageTimer--;
        }
        if(damageTaken && damageTimer <= 0)
        {
            damageTaken = false;
        }
        if(damageTaken)
        {
            getImage().setTransparency(255 / 2);
        }
        else
        {
            getImage().setTransparency(255);
        }
        if(size == 0)
        {
            getWorld().showText("You died, press restart to play again", getWorld().getWidth() / 2, getWorld().getHeight() / 2);
            Greenfoot.stop();
        }
    }
    // Detecting koopas at mario's feet
    public void koopaDetection()
    {
        Koopa koopa = (Koopa)getOneObjectAtOffset(0,feet, Koopa.class);
        Koopa shellkoopa = (Koopa)getOneObjectAtOffset(0,feet + 2, Koopa.class);
        if(koopa != null && intersects(koopa) && !isGrounded)
        {
            gravity = -6.5;
            koopa.stomped = true;
        }
        if(shellkoopa != null && koopa != null && koopa.stomped == true && intersects(shellkoopa))
        {
            gravity = -6.5;
        }
    }
    // Detecting goombas at mario's feet
    public void goombaDetection()
    {
        Goomba goomba = (Goomba)getOneObjectAtOffset(0,feet, Goomba.class);
        if(goomba != null && intersects(goomba) && !isGrounded)
        {
            gravity = -6.5;
            goomba.flipped = true;
        }
    }
    // Animations related to gravity that I implemented
    public void gravityAnimations()
    {
        // If gravity is less than 0 zero, mario is jumping
        if(gravity < 0)
        {
            if(size == 1)
            {
                if(facingRightCheck() && !Greenfoot.isKeyDown("s"))
                {
                    animation = SMALLRIGHTJUMP;
                }
                else if(!facingRightCheck() && !Greenfoot.isKeyDown("s"))
                {
                    animation = SMALLLEFTJUMP;
                }
            }
            // Logic for deciding when mario should have a normal jump animation or a MAXSPEEd jump animation
            if(size == 2 && getY() < getWorld().getHeight() - 20)
            {
                if(facingRightCheck() && rightspeed != 5.0)
                {
                    animation = RIGHTJUMP;
                }
                if(facingRightCheck() && rightspeed == 5.0)
                {
                    animation = MAXJUMPRIGHT;
                }
                if(!facingRightCheck() && leftspeed != -5.0)
                {
                    animation = LEFTJUMP;
                }
                if(!facingRightCheck() && leftspeed == -5.0)
                {
                    animation = MAXJUMPLEFT;
                }
                if(facingRightCheck() && platformrightspeed == 5.0 && Greenfoot.isKeyDown("Space"))
                {
                    animation = MAXJUMPRIGHT;
                }
                if(!facingRightCheck() && platformleftspeed == -5.0 && Greenfoot.isKeyDown("Space"))
                {
                    animation = MAXJUMPLEFT;
                }
            }
        }
        // If there is gravity and mario isn't grounded or on a pipe, he must be falling
        else if(gravity > 0 && !isGrounded && !onPipe)
        {
            if(size == 1)
            {
                if(animation == SMALLRIGHT)
                {
                    animation = SMALLRIGHTFALL;
                }
                if(animation == SMALLLEFT)
                {
                    animation = SMALLLEFTFALL;
                }
            } 
            if(size == 2)
            {
                if(animation == RIGHT)
                {
                    animation = RIGHTFALL;
                }
                if(animation == LEFT)
                {
                    animation = LEFTFALL;
                }
            } 
        }  
        if(animation == RIGHTJUMP || animation == MAXJUMPRIGHT && isTouching(Platform.class))
        {
            animation = RIGHT;    
        }
    }
    public void moveMario()
    {
        // If the animation is one of these and we're pressing a
        if(animation == LEFT || animation == SMALLLEFT || animation == SMALLLEFTFALL || animation == SMALLLEFTJUMP || animation == MAXSPEEDLEFT 
        && Greenfoot.isKeyDown("a"))
        {
            move((int)leftspeed);
            // If leftspeed is greater than the speed force times -1
            if(leftspeed > MAXSPEED * -1)
            {
                // Go faster
                leftspeed -= speedforce;
            }
            // Same thing for platform speed
            if(platformleftspeed > MAXPLATFORMSPEED * -1)
            {
                platformleftspeed -= speedforce;
            }
            // Same logic as the other movement but if we're not at the sprint limit while pressing control, increase speed
            if(Greenfoot.isKeyDown("up") && leftspeed > MAXSPRINTSPEED * -1)
            {
                leftspeed -= speedforce;
            }
            if(originalScrolling && Greenfoot.isKeyDown("up") && platformleftspeed > MAXSPRINTSPEED * -1)
            {
                platformleftspeed -= speedforce;
            }
        }
        // And if we're not pressing control, make our speed go back to normal gradually
        if(!Greenfoot.isKeyDown("up"))
        {
            if(leftspeed < MAXSPEED * -1)
            {
                leftspeed += speedforce;
            }
            if(originalScrolling && platformleftspeed < MAXSPEED * -1)
            {
                platformleftspeed += speedforce;
            }
        }
        // IF the animation is a variation of right
        if(animation == RIGHT || animation == SMALLRIGHT || animation == SMALLRIGHTFALL || animation == SMALLRIGHTJUMP || animation == MAXSPEEDRIGHT
        && Greenfoot.isKeyDown("d"))
        {
            move((int)rightspeed);
            // If we can go faster, increase the speed
            if(rightspeed < MAXSPEED)
            {
                rightspeed += speedforce;
            }
            // same for platform speed
            if(platformrightspeed < MAXPLATFORMSPEED)
            {
                platformrightspeed += speedforce;
            }
            // Logic for sprinting
            if(Greenfoot.isKeyDown("up") && rightspeed < MAXSPRINTSPEED)
            {
                rightspeed += speedforce;
            }
            if(Greenfoot.isKeyDown("up") && platformrightspeed < MAXSPRINTSPEED)
            {
                platformrightspeed += speedforce;
            }
        }
        // If we're not sprinting, set right speed back to normal
        if(!Greenfoot.isKeyDown("up"))
        {
            if(rightspeed > MAXSPEED)
            {
                rightspeed -= speedforce;
            }
            if(originalScrolling && platformrightspeed > MAXSPEED)
            {
                platformrightspeed -= speedforce;
            }
        }
        // If space is pressed and we're ready to jump
        if(Greenfoot.isKeyDown("Space") && isGrounded)
        {
            gravity = getWorld().getHeight() * -0.01875;
            isGrounded = false;
            airControl = true;
        }
        // If we have aircontrol and don't want to jump any more, apply gravity
        if(airControl && !Greenfoot.isKeyDown("Space") && !Greenfoot.isKeyDown("s"))
        {
            gravity = gravity + gForce;
        }
    }
    private boolean animate()
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
    public void updateAnimations()
    {
        if(Greenfoot.isKeyDown("a") && animation != LEFT || Greenfoot.isKeyDown("a") && animation != SMALLLEFT || 
        Greenfoot.isKeyDown("a") && animation != MAXSPEEDLEFT)
        {
            skipRate = 7;
            if(size == 1)
            {
                animation = SMALLLEFT;
            }
            // If size equals 2, lefspeed doesn't equal maxspeed, and leftspeed is greater that it (since movement left is negative)
            if(size == 2 && platformleftspeed == -5.0)
            {
                skipRate = 8;
                animation = MAXSPEEDLEFT;
            }
            if(size == 2 && leftspeed == -5.0)
            {
                skipRate = 8;
                animation = MAXSPEEDLEFT;
            }
            else if(size == 2 && getSpeed() != -5.0 && platformleftspeed != -5.0)
            {
                animation = LEFT;
            }
        }
        if(Greenfoot.isKeyDown("d") && animation != RIGHT || Greenfoot.isKeyDown("d") && animation != SMALLRIGHT || 
        Greenfoot.isKeyDown("d") && animation != MAXSPEEDRIGHT)
        {
            if(size == 1)
            {
                animation = SMALLRIGHT;
            }
            if(size == 2 && platformrightspeed == 5.0)
            {
                animation = MAXSPEEDRIGHT;
            }
            if(size == 2 && rightspeed == 5.0)
            {
                animation = MAXSPEEDRIGHT;
            }
            else if(size == 2 && getSpeed() != 5.0 && platformrightspeed != 5.0)
            {
                animation = RIGHT;
            }
        }
        if(!Greenfoot.isKeyDown("a") && !Greenfoot.isKeyDown("d"))
        {
            frame = 0;
            rightspeed = 0;
            leftspeed = 0;
            platformleftspeed = 0;
            platformrightspeed = 0;
        }
        if(Greenfoot.isKeyDown("w") && !Greenfoot.isKeyDown("a") && !Greenfoot.isKeyDown("d"))
        {
            if(facingRightCheck() == false && size == 1)
            {
                animation = SMALLLOOKUPLEFT;
            }
            if(facingRightCheck() && size == 1)
            {
                animation = SMALLLOOKUPRIGHT;
            }
            if(facingRightCheck() == false && size == 2)
            {
                animation = LOOKUPLEFT;
            }
            if(facingRightCheck() && size == 2)
            {
                animation = LOOKUPRIGHT;
            }
        }
        // Logic for handling animation when not looking up or ducking
        if(!Greenfoot.isKeyDown("s") && !Greenfoot.isKeyDown("w"))
        {
            // It must be left or right since we're facing that direction
            if(facingRightCheck() == false && size == 1)
            {
                animation = SMALLLEFT;
            }
            if(facingRightCheck() && size == 1)
            {
                animation = SMALLRIGHT;
            }
            // We're facing left and we're not going at the max speed, so animation should be left
            if(facingRightCheck() == false && size == 2 && platformleftspeed != -5.0)
            {
                animation = LEFT;
            }
            // Likewise for this but for different scrolling (same logic for right side)
            else if(facingRightCheck() == false && size == 2 && leftspeed != -5.0 && !originalScrolling)
            {
                animation = LEFT;
            }
            if(facingRightCheck() && size == 2 && platformrightspeed != 5.0)
            {
                animation = RIGHT;
            }
            else if(facingRightCheck() && size == 2 && platformrightspeed == 5.0)
            {
                animation = MAXSPEEDRIGHT;
            }
            else if(facingRightCheck() && size == 2 && rightspeed != 5.0 && !originalScrolling)
            {
                animation = RIGHT;
            }
        }
        // Ducking logic
        if(Greenfoot.isKeyDown("s"))
        {
            // Make our animation match our size
            if(facingRightCheck() == false && size == 1)
            {
                animation = SMALLDUCKLEFT;
                if(leftspeed < 0)
                {
                    move((int)leftspeed);
                    leftspeed += slidespeedforce;
                }
                // Slide on duck, same logic for other ducks
                if(platformleftspeed < 0)
                {
                    platformleftspeed += slidespeedforce;
                }
            }
            if(facingRightCheck() && size == 1)
            {
                animation = SMALLDUCKRIGHT;
                if(rightspeed > 0)
                {
                    move((int)rightspeed);
                    rightspeed -= slidespeedforce;
                }
                if(platformrightspeed > 0)
                {
                    platformrightspeed -= slidespeedforce;
                }
            } 
            if(facingRightCheck() == false && size == 2)
            {
                animation = DUCKLEFT;
                if(leftspeed < 0)
                {
                    move((int)leftspeed);
                    leftspeed += slidespeedforce;
                }
                if(platformleftspeed < 0)
                {
                    platformleftspeed += slidespeedforce;
                }
            }
            if(facingRightCheck() && size == 2)
            {
                animation = DUCKRIGHT;
                if(rightspeed > 0)
                {
                    move((int)rightspeed);
                    rightspeed -= slidespeedforce;
                }
                if(platformrightspeed > 0)
                {
                    platformrightspeed -= slidespeedforce;
                }
            }
        }
    }
}