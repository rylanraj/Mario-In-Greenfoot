import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BoxMushroom here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BoxMushroom extends Mushroom
{
    /**
     * Act - do whatever the BoxMushroom wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int nativeW;
    private int nativeH;
    // Box mushrooms are specific mushrooms that stay in the powerbox and only descend when p is pressed
    public BoxMushroom(int size1, int size2, int nativeW, int nativeH)
    {
        super(false,true,size1,size2);
        this.nativeW = nativeW;
        this.nativeH = nativeH;
    }
    public void act()
    {
        super.act();
    }
    public void generalize(double xs, double ys)
    {
        int x = (int)xs;
        int y = (int)ys;
        GreenfootImage img = getImage();
        if(getWorld().getWidth() != nativeW)
        {
            img.scale(x,getImage().getHeight());    
        }
        if(getWorld().getHeight() != nativeH)
        {
            img.scale(getImage().getWidth(),y);    
        }
    }
}
