package nz.ac.aut.prog2.cluedo.model;

/**
 * Class for the player.
 * 
 * @author Anne Philpott and Stefan Marks
 * @version 1.0 - 2012.02: Created
 * @version 1.1 - 2012.04: Updated for assignment stage 3
 */
public class Player extends Occupant
{
    private static final int STARTING_ACTION_POINTS = 100;
    
    // name of the player
    private String name; 

    // action points of the player
    private int actionPoints;

    /**
     * Creates a new player.
     * 
     * @param name the name of the player
     */
    public Player(String name)
    {
       super();
       this.name = name;        
       actionPoints = STARTING_ACTION_POINTS;
    }
    
    /**
     * Sets the clue and action values back to their initial values.
     */
    public void reset()
    {
        resetClues();
        actionPoints = STARTING_ACTION_POINTS;
    }        

    /**
     * Gets the name of the player.
     * 
     * @return player's name
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * Changes the name of the player.
     * 
     * @param name the new player's name
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * Gets the number of action points available.
     * 
     * @return number of actionPoints available
     */
    public int getActionPoints()
    {
        return this.actionPoints;
    }
    
    /**
     * Tests if the player has any action points left.
     *
     * @return true if player has actionPoints available
     */
    public boolean hasActionPoints()
    {
        return this.actionPoints > 0;
    }

    /**
     * Adds action points.
     *
     * @param number of actionPoints to add
     */
    public void addActionPoints(int toAdd)
    {
        if ( toAdd > 0 )
        {
            this.actionPoints += toAdd;
        }
    }

    /**
     * Removes action points. The number of points cannot drop below 0.
     *
     * @param number of actionPoints to remove
     */    
    public void removeActionPoints(int toRemove)
    {
        if ( toRemove > 0 )
        {
            this.actionPoints -= toRemove;
        }
        if ( actionPoints < 0 )
        {
            this.actionPoints = 0;
        }
    }
 
    @Override
    public String getStringRepresentation()
    {
        return "P";
    }

}
