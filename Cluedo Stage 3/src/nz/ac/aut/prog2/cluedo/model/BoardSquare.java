package nz.ac.aut.prog2.cluedo.model;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

/**
 * Class to represent a square in Tudor Mansion.
 * 
 * @author Anne Philpott & Stefan Marks
 * @version 1.0 - 2012.02: Created
 * @version 1.1 - 2012.04: Updated for assignment stage 3
 */
public abstract class BoardSquare
{
    // maximum number of coccupants per board square
    public static final int MAX_OCCUPANTS = 2; 
    // the position of the board square
    private Position position;  
    // the colour to use when drawing the square    
    private Color colour;
    // the occupants of the square
    private Set<Occupant> occupants; 

    /**
     * Creates a new board square.
     * 
     * @param pos     the position of the board square
     * @param colour  the colour of the square to use for drawing
     */
    public BoardSquare(Position pos, Color colour)
            throws IllegalArgumentException
    {
        // check position for validity
        if ( pos == null )
        {
            throw new IllegalArgumentException(
                    "Cannot create a square without a valid position.");
        }
        
        // check colour for validity
        if ( colour == null )
        {
            throw new IllegalArgumentException(
                    "Cannot create a square without a valid colour.");
        }
        
        // all fine -> create board square instance
        this.position  = pos;
        this.colour    = colour;
        this.occupants = new HashSet<>();
    }

    /**
     * Gets the position of the board square.
     * 
     * @return the position of the square
     */
    public Position getPosition()
    {
        return position;
    }
    
    /**
     * Gets the colour of the board square.
     * 
     * @return the colour of the board square
     */
    public Color getColour()
    {
        return colour;
    }
    
    /**
     * Adds an occupant to the board square.
     * 
     * @param occ the new occupant to add to the board square
     * @return true if occ successfully added, false if not
     */
    public boolean addOccupant(Occupant occ) 
    {
        boolean success = false;
        if ( (occ != null) &&                     // occupant valid?
             (occupants.size() < MAX_OCCUPANTS) ) // size constraints 
        {
            // try adding occupant (may fail when duplicate)
            success = occupants.add(occ);
            if ( success )
            {
                // occupant added -> update occupant's position
                occ.setPosition(this.position);
            }
        }
        return success;
    }

    /**
     * Removes an occupant from the board square.
     * 
     * @param occ the occupant to remove
     * @return true if occupant was removed, false if not
     */
    public boolean removeOccupant(Occupant occ) 
    {
        boolean removed = false;
        if ( occ != null )
        {
            // Occupant is valid. Try to remove
            removed = occupants.remove(occ);
            if ( removed )
            {            
                // occupant removed -> update occupant's position
                occ.setPosition(null);
            }
        }
        return removed;
    }

    /**
     * Checks if the board square has any occupants.
     * 
     * @return true if this square has occupants
     */
    public boolean hasOccupants() 
    {
        return !occupants.isEmpty();
    }
    
    /**
     * Gets the occupants as an array.
     * 
     * @return an array with the occupants
     */
    public Occupant[] getOccupantsAsArray() 
    {
        return occupants.toArray(new Occupant[occupants.size()]);
    }

    /**
     * Gets any suspect that is on the board square.
     * 
     * @return a suspect on the board square, or null if there is no suspect
     */
    public Suspect getSuspect() 
    {
        Suspect suspect = null;
        for ( Occupant occupant : occupants )
        {
            if ( occupant instanceof Suspect )
            {
                suspect = (Suspect) occupant;
            }
        }
        return suspect;
    }

    /**
     * Gets a string representation of the board square for printing to the screen.
     * 
     * @return a string representation of the occupants of the square
     */
    public String getOccupantStringRepresentation()
    {
        String occRep = "";
        for ( Occupant occ : occupants )
        {
            occRep += occ.getStringRepresentation();
        }
        return occRep;
    }
    
    /**
     * @return a string representation of the square.
     */
    public abstract String getStringRepresentation();
 
 }
