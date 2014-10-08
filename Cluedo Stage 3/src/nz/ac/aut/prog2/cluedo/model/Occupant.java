package nz.ac.aut.prog2.cluedo.model;

import java.util.HashSet;
import java.util.Set;

/**
 * This class represents occupants of squares on the Cluedo board.
 * 
 * @author Stefan Marks & Anne Philpott
 * @version 1.0 - 2011.03: created for Minesweeper
 * @version 2.0 - 2011.06: adapted for Lemur Island
 * @version 3.0 - 2012.02: adapted for Cluedo game
 * @version 3.1 - 2012.03: skeleton code for stage 2 Cluedo
 * @version 3.2 - 2012.04: Modified for assignment stage 3
 */
public abstract class Occupant
{
    // the position of the occupant (null, when not on the board)
    private Position position;   
    
    // a collection to store clues
    private Set<Clue> clues;

    /**
     * Creates a new Occupant instance without a position and/or clues.
     */
    public Occupant()
    {
       this.position = null;
       this.clues    = new HashSet<>();
    }

    /**
     * Changes the position of the occupant.
     * 
     * @param the new position
     */
    public void setPosition(Position pos)
    {
        position = pos;
    }
   
    /**
     * Gets the occupant's position.
     * 
     * @return the occupant's position
     */
    public Position getPosition()
    {
        return position;
    }

    /**
     * Clears the list of clues.
     */
    public void resetClues()
    {
        clues.clear();
    }    

    /**
     * Gets the number of clues.
     * 
     * @return the number of clues the occupant holds
     */
    public int getNumClues()
    {
        return clues.size();
    }        
   
    /**
     * Enables subclasses to access the clues held.
     *
     * @return an array containing all the clues.
     */
    protected Clue[] getCluesAsArray()
    {
        Clue[] clueArray = new Clue[clues.size()];
        return clues.toArray(clueArray);
    }
    
    /**
     * Adds a clue to the list of clues.
     * A clue is only added if it is not null, and not already collected.
     * 
     * @param clue to add to collected clues
     * @return true if it is added
     */
    public boolean collectClue(Clue clue)
    {
        boolean added = false;
        if ( clue != null )
        {
            added = clues.add(clue);
        }
        return added;
    }
    
    /**
     * Checks if a specific clue has been collected.
     * 
     * @param clue to check
     * @return true if occupant has this clue.
     */
    public boolean hasClue(Clue clue)
    {
        return clues.contains(clue);
    }

    /**
     * Gets a string representation of the occupant.
     * 
     * @return string representation of this occupant
     */
    public abstract String getStringRepresentation();

}
