package nz.ac.aut.prog2.cluedo.model;

import java.awt.Color;

/**
 * This class represents walls in Tudor mansion.
 * 
 * @author  Stefan Marks & Anne Philpott
 * @version 1.0 - 2012.02: Created
 * @version 1.1 - 2012.04: Updated for assignment stage 3
 */
public class Wall extends BoardSquare
{
    //Walls are always brown
    public static final Color BROWN = new Color(92,64,51);

    /**
     * Creates a wall.
     * 
     * @param pos the position of the wall
     */    
    public Wall(Position pos) 
    {
        super(pos, BROWN);
    }

    /**
     * Gets a string representation of the wall, e.g., for printing to the screen.
     * 
     * @return string representation for wall
     */
    @Override
    public String getStringRepresentation()
    {
        return "W";
    }
    
    /**
     * Override inherited method as you cannot add occupants to walls.
     * 
     * @return always false as walls cannot have occupants
     */
    @Override 
    public boolean addOccupant(Occupant occ) 
    {
        return false;
    }
}
