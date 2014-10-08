package nz.ac.aut.prog2.cluedo.model;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

/**
 * Test framework for the Wall class.
 *
 * @author  Anne Philpott & Stefan Marks
 * @version 1.0 - 2012.02: Created
 * @version 1.1 - 2012.04: Updated for assignment stage 3
 */
public class WallTest
{
    private static final int BOARD_ROWS = 24;
    private static final int BOARD_COLS = 25;
    private Wall wall;
    private Position position;
    private Board    board;
    
    /**
     * Default constructor for test class WallTest
     */
    public WallTest()
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
        board = new Board(BOARD_ROWS, BOARD_COLS);
        position = new Position(board, 2,2);
        wall = new Wall(position);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
        board = null;
        position = null;
        wall = null;        
    }
    
    @Test
    public void testGetStringRepresentation()
    {
       assertEquals("Wrong string for wall", "W", wall.getStringRepresentation()); 
    }

    /**
     * Tests for the overridden addOccupant method
     */    
    @Test
    public void testAddOccupant()
    {
        Occupant occupant = new Player("Occupant example");
        assertFalse("Can add occupant to wall", wall.addOccupant(occupant));
    }    
}
