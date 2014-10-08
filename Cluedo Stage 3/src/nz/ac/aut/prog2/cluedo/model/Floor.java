package nz.ac.aut.prog2.cluedo.model;

import java.awt.Color;

/**
 * This class represents hall & room floor squares 
 * on which players can move.
 * 
 * @author  Anne Philpott and Stefan Marks
 * @version 1.0 - 2012.02: Created
 * @version 1.1 - 2012.04: Modified for assignment stage 3
 */
public class Floor extends BoardSquare
{
    /**
     * Creates a floor square.
     * 
     * @param pos the position of the square
     * @param colour the colour of the square
     */
    public Floor(Position pos, Color colour) 
    {
        super(pos, colour);
    }

    /**
     * Gets a string representation of the floor tile, e.g., for printing to the screen.
     * 
     * @return string representation for floor
     */
    @Override
    public String getStringRepresentation() 
    {
        return ".";
    }

}
