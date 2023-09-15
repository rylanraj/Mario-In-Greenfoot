import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Range here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Range extends Object
{
    /**
     * Act - do whatever the Range wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    // Range attributes
    private int low;
    private int high;
    // When range is created, set low and high in its parameters
    public Range(int low, int high)
    {
        this.low = low;
        this.high = high;
    }
    // Returns true if the users number is in the range and false if it isn't in range
    public boolean contains(int number)
    {
        return(number >= low && high >= number);
    }
}