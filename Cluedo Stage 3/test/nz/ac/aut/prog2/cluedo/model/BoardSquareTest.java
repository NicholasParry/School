package nz.ac.aut.prog2.cluedo.model;

import java.awt.Color;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test framework for the BoardSquare class.
 *
 * @author  Anne Philpott & Stefan Marks
 * @version 1.0 - 2012.02: Created
 * @version 1.1 - 2012.04: Updated for assignment stage 3
 */
public class BoardSquareTest
{
    private static final int BOARD_ROWS = 24;
    private static final int BOARD_COLS = 25;
    
    private BoardSquare square;
    private Position    position;
    private Color       colour;
    private Board       board;
    private Occupant occupant;
    private Occupant secondOccupant;
    private Occupant extraOccupant;     
    
    /**
     * Default constructor for test class BoardSquareTest
     */
    public BoardSquareTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        colour = Color.green;
        board = new Board(BOARD_ROWS, BOARD_COLS);
        position = new Position(board, 1,2);
        square = new Floor(position, colour);
        occupant = new Suspect(SuspectName.RED);
        secondOccupant = new Player("Second occupant");
        extraOccupant = new Suspect(SuspectName.BLUE);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
        colour = null;
        board = null;
        position = null;
        square = null;
        occupant = null;
        secondOccupant = null;
        extraOccupant = null;        
    }
    
    @Test
    public void testGetPosition()
    {
       assertEquals("Position not correct", position, square.getPosition()); 
    }

    /**
     * Tests for addOccupant() method.
     */
    
    @Test
    public void testAddOccupantWhenEmpty()
    {
       assertTrue("Can't add occupant to empty BoardSquare",
                  square.addOccupant(occupant));
       assertEquals("Occupant's position is not updated.",
                    position, occupant.getPosition());
    }
    
    @Test
    public void testAddOccupantWhenFull()
    {
       square.addOccupant(occupant);
       square.addOccupant(secondOccupant);
       assertFalse("Can add occupant when full", 
                   square.addOccupant(extraOccupant));
       assertNull("Occupant's position is changed although occupant is not added.",
                  extraOccupant.getPosition());
    }    

    @Test
    public void testAddOccupantWhenDuplicate()
    {
       square.addOccupant(occupant);
       assertFalse("Can add duplicate occupant.", 
                   square.addOccupant(occupant));
    }

    
    /**
     * Tests for GetOccupantStringRepresentation method.
     */
    @Test
    public void testGetOccupantStringRepresentationWhenEmpty()
    {
        assertEquals("Wrong occupant string representation when empty.", 
                     "", square.getOccupantStringRepresentation());
    }    

    @Test
    public void testGetOccupantStringRepresentationWithOccupants()
    {
        square.addOccupant(occupant);
        square.addOccupant(secondOccupant);
        String occupantsString = square.getOccupantStringRepresentation();
        assertTrue(occupantsString.contains("P"));
        assertTrue(occupantsString.contains("S"));
    }    
   

    /**
     * Tests for removeOccupant()
     */    
    
    @Test
    public void testRemoveOccupantWhenEmpty()
    {
       assertFalse("Can remove occupant from an empty BoardSquare.",
                   square.removeOccupant(occupant));
    }    
    
    @Test
    public void testRemoveOccupantNotThere()
    {
       square.addOccupant(occupant);
       //Create another position & square and add another occupant
       Position otherPos = new Position(board, 3,4);
       BoardSquare otherSq = new Floor(otherPos, colour);
       otherSq.addOccupant(secondOccupant);
       //Attempt to remobve secondOccupant from wrong square
       assertFalse("Can remove an occupant that is not there.",
                   square.removeOccupant(secondOccupant));
       //Ensure secondOccupant's position has not been changed            
       assertEquals("Position of non present occupant changed.", otherPos, secondOccupant.getPosition());            
    }   
    
    @Test
    public void testRemoveOccupantWhenPresent()
    {
       square.addOccupant(occupant);
       assertTrue("Cannot remove existing occupant.",
                  square.removeOccupant(occupant));
       assertNull("Occupan't position is not set to null after remove.",
                  occupant.getPosition());
    }
    
   
    /**
     * Test for hasOccupant()
     */   
    
    @Test
    public void testHasOccupantsNoOccupants()
    {
       assertFalse("Wrongly reporting occupants on an empty BoardSquare",
                   square.hasOccupants()); 
    }    
    
    @Test
    public void testHasOccupantsWithOccupants()
    {
       square.addOccupant(occupant);
       assertTrue("Wrongly reporting no occupants on a BoardSquare with occupants",
                  square.hasOccupants()); 
    }
    
}