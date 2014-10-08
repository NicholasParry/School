package nz.ac.aut.prog2.cluedo.model;

import java.awt.Color;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Test framework for the Floor class.
 *
 * @author  Anne Philpott & Stefan Marks
 * @version 1.0 - 2012.02: Created
 * @version 1.1 - 2012.04: Updated for assignment stage 3
 */
public class FloorTest
{
    private static final int BOARD_ROWS = 24;
    private static final int BOARD_COLS = 25;
    private Floor     floor;
    private Position  position;
    private Board     board;
    /**
     * Default constructor for test class FloorTest
     */
    public FloorTest()
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
        floor = new Floor(position, Color.white);
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
        floor = null;        
    }
    
    @Test
    public void testGetStringRepresentation()
    {
       assertEquals("Wrong string for floor", ".", floor.getStringRepresentation()); 
    }
}
