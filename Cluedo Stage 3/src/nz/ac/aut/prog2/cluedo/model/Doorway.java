package nz.ac.aut.prog2.cluedo.model;

import java.awt.Color;

/**
 * This class represents Doors in Tudor Mansion.
 * 
 * @author  Stefan Marks & Anne Philpott
 * @version 1.0 - 2012.02: Created
 * @version 1.1 - 2012.04: Modified for assignment stage 3
 */
public class Doorway extends BoardSquare
{
    /**
     * Creates a doorway.
     * 
     * @param pos the position of the doorway
     */
    public Doorway(Position pos) 
    {
        super(pos, Color.LIGHT_GRAY);
    }

    /**
     * Gets a string representation of the doorway, e.g., for printing to the screen.
     * 
     * @return string representation for doorway
     */
    @Override
    public String getStringRepresentation()
    {
        return "D";
    }
}
