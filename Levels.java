import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Levels here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Levels extends World
{
    // Animations for all level subclasses to inherit 
    public final GreenfootImage[] KOOPALEFT = {
    new GreenfootImage("KoopaLeft0.png"),
    new GreenfootImage("KoopaLeft2.png")};
    
    public final GreenfootImage[] KOOPARIGHT = {
    new GreenfootImage("KoopaRight0.png"), 
    new GreenfootImage("KoopaRight1.png"),
    new GreenfootImage("KoopaRight2.png")};
    
    public final GreenfootImage[] LEFTSHELL = {
    new GreenfootImage("LeftShell0.png"),
    new GreenfootImage("LeftShell1.png"),
    new GreenfootImage("LeftShell2.png"),
    new GreenfootImage("LeftShell3.png")};
    
    public final GreenfootImage[] RIGHTSHELL = {
    new GreenfootImage("RightShell0.png"),
    new GreenfootImage("RightShell1.png"),
    new GreenfootImage("RightShell2.png"),
    new GreenfootImage("RightShell3.png")};
    
    public final GreenfootImage[] PARALEFT = {
    new GreenfootImage("Paratroopaleft1.png"),
    new GreenfootImage("Paratroopaleft2.png")};
    
    public final GreenfootImage[] PARARIGHT = {
    new GreenfootImage("Paratrooparight1.png"),
    new GreenfootImage("Paratrooparight2.png")};
    
    public final GreenfootImage[] GOOMBALEFT = {
    new GreenfootImage("goombaLeft1.png"),
    new GreenfootImage("goombaLeft2.png")};
    
    public final GreenfootImage[] GOOMBARIGHT = {
    new GreenfootImage("goombaRight1.png"),
    new GreenfootImage("goombaRight2.png")};
    
    public final GreenfootImage[] LEFTFLIPPED = {
    new GreenfootImage("goombaLeftFlipped1.png"),
    new GreenfootImage("goombaLeftFlipped2.png")};
    
    public final GreenfootImage[] RIGHTFLIPPED = {
    new GreenfootImage("goombaRightFlipped1.png"),
    new GreenfootImage("goombaRightFlipped2.png")};
    
    // Constructer for a level
    public Levels(int width, int height, boolean borders)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        // All Levels should have a powerbox at the center of them
        super(width, height, 1, borders);
        Powerbox pb = new Powerbox();
        // 210, 50 is original position (generalize would be 0.525 x 0.125)
        addPowerBox(width * 0.505, height * 0.125);
    }
    // Add a power box at a margin
    public void addPowerBox(double xp, double yp)
    {
        int x = (int)xp;
        int y = (int)yp;
        Powerbox pb = new Powerbox();
        addObject(pb, x, y);
    }
    // Methods I used to generalize the project
    public void generalizeScaleSingleActor(Actor actor,double xs, double ys)
    {
        int x = (int)xs;
        int y = (int)ys;
        GreenfootImage img = actor.getImage();
        img.scale(x,y);
        actor.setImage(img);
    }
    public void generalizePositionSingleActor(Actor actor,double xs, double ys)
    {
        int x = (int)xs;
        int y = (int)ys;
        actor.setLocation(x,y);
    }
    public void generalizeProject(double originalx, double originaly)
    {
        double multiplier = this.getWidth() / originalx;
        double heightmultiplier = this.getHeight() / originaly;
        // For every actor in this world
        for(Actor actor: this.getObjects(Actor.class))
        {
            if(actor.getClass() != Mario.class && actor.getClass() != Entity.class)
            {
                // Generalize the size of the actor so it can be scaled up if the user decides to
                generalizeScaleSingleActor(actor,actor.getImage().getWidth() * multiplier, actor.getImage().getHeight() * heightmultiplier);
            }
        }
        // For every image array in all the animations        
        generalizePostion((int)originalx,(int)originaly);
    }
    public void generalizePostion(double originalx, double originaly)
    {
        double multiplier = this.getWidth() / originalx;
        double heightmultiplier = this.getHeight() / originaly;
        // For every actor in this world
        for(Actor actor : this.getObjects(Actor.class))
        {
            if(actor.getClass() != Powerbox.class)
            {
                // Generalize the position of the actor so that its position stays consistent when scaled up
                generalizePositionSingleActor(actor,actor.getX() * multiplier, actor.getY() * heightmultiplier);
            }
        }
    }
    // Tool to add horizontal bricks
    public void addBricksToFloor(int offset,int amount, int location, boolean finishLine, boolean moving)
    {
        // Intial brick distance
        int brickdistance = -12 + offset;
        // Add a specific amount of bricks
        for(int i = 0; i < amount; i++)
        {
            brickdistance += 24;
            Block block = new Block(moving);
            // Add it at whatever height we choose (location)
            addObject(block,brickdistance,location);
            // Add a finishline at the end of the bricks if we want it to
            if(i == amount - 1 && finishLine)
            {
                addFinishLine(block.getX(), 300);    
            }
        }
    }
    // Tool to add vertical bricks
    public void addBrickWall(int startheight, int amount, int location, boolean moving)
    {
        // Control where the height of the bricks starts
        int brickdistancey = startheight;
        // Add a specific amount of bricks
        for(int i = 0; i < amount; i++)
        {
            brickdistancey += 24;
            Block block = new Block(moving);
            addObject(block,location,brickdistancey);
        }    
    }
    // Tool to add a stair case
    public void addStairCase(int xposition, int yposition, int amount, boolean inverted, boolean flipped)
    {
        //original height height = 343;
        //original x = 2316;
        int brickdistance = 0;
        int brickdistancex = 0;
        int brickstartamount = 1;
        for(int i = 0; i < amount; i++)
        {
            addBrickWall(yposition - brickdistance,brickstartamount,xposition + brickdistancex,false); // To create stair case, make blocks 24 apart, -24 for height, + 24 for x
            if(!inverted && !flipped)
            {
                brickdistance += 24;
                brickdistancex += 24;
            }
            if(inverted && !flipped)
            {
                brickdistancex -= 24;
                brickdistance += 24;
            }
            if(inverted && flipped)
            {
                brickdistance += 24;
                brickdistancex += 24;
            }
            if(!inverted && flipped)
            {
                brickdistancex -= 24;
            }
            brickstartamount += 1;
        }
    }
    // Tool to add a single brick
    public void addBrick()
    {
        Block block = new Block(false);
        addObject(block,10,390);
    }
    // Tool to add a finish line
    public void addFinishLine(int pos1, int pos2)
    {
        FinishLine FL = new FinishLine();
        addObject(FL, pos1,pos2);
    }
    // Tool to add regular Koopas, with option of them having a floor or being stomped
    public void addKoopa(int timer,int pos1, int pos2, boolean stomped, boolean floored, int nativeW, int nativeH)
    {
        Koopa koopa = new Koopa(timer,KOOPALEFT,KOOPARIGHT,LEFTSHELL,RIGHTSHELL, stomped,floored, nativeW, nativeH);
        addObject(koopa, pos1, pos2);
    }
    // Tool to add paratroopa
    public void addParaTroopa(int timer,int pos1, int pos2, int nativeW, int nativeH)
    {
        Paratroopa parakoopa = new Paratroopa(timer,PARALEFT, PARARIGHT, LEFTSHELL, RIGHTSHELL,true, nativeW, nativeH);
        addObject(parakoopa, pos1, pos2);
    }
    // Tool to add text to guide the player
    public void addTutorialText(String text, int pos1, int pos2)
    {
        TutorialText tutorialText = new TutorialText(text);
        addObject(tutorialText,pos1,pos2);
    }
    // Tool to add goomba enemies
    public void addGoomba(int timer,int pos1, int pos2, int nativeW, int nativeH)
    {
        Goomba goomba = new Goomba(timer,GOOMBALEFT,GOOMBARIGHT, LEFTFLIPPED, RIGHTFLIPPED, false, nativeW, nativeH);
        addObject(goomba, pos1, pos2);
    }
    // Tool to add a group of blocks, one of the blocks can have a mushroom if mushroom is true
    public void addGroupOfBlocks(int xoffset, int yoffset, boolean mushroom)
    {
        // Controls the distance between blocks
        int blockdistance = 24;
        Block block292 = new Block(false);
        addObject(block292,xoffset,yoffset);
        CoinBlock coinBlock = new CoinBlock();
        addObject(coinBlock,xoffset + 24,yoffset);
        Block block291 = new Block(false);
        addObject(block291,xoffset + 48,286);
        if(mushroom)
        {
            MushroomBlock mushroomBlock = new MushroomBlock();
            addObject(mushroomBlock,xoffset + 72,yoffset);
        }
        if(!mushroom)
        {
            CoinBlock cb = new CoinBlock();
            addObject(cb,xoffset + 72,yoffset);
        }
        Block block293 = new Block(false);
        addObject(block293,xoffset + 96,yoffset);
    }
}
