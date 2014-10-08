package nz.ac.aut.prog2.cluedo.model;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Test framework for the Position class.
 *
 * @author  Anne Philpott & Stefan Marks
 * @version 1.0 - 2012.02: Created
 * @version 1.1 - 2012.03: Added board into equality check, 
 *                         Added illegal row/col checks 
 * @version 1.2 - 2012.04: Updated for assignment stage 3
 */
public class PositionTest
{
    private static final int BOARD_ROWS = 24;
    private static final int BOARD_COLS = 25;
    private Board    board;
    private Position position;
    private int      row;
    private int      col;
    
    /**
     * Default constructor for test class PositionTest
     */
    public PositionTest()
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
        board    = new Board(BOARD_ROWS, BOARD_COLS);
        row      = 1;
        col      = 2;
        position = new Position(board, row, col);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
        board    = null;
        position = null;        
    }
    
    /**
     * Tests that check constructor throws correct exceptions 
     * if data is not valid.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testNewPositionInvalidBoard() throws IllegalArgumentException
    {
       Position invalid = new Position(null, 1, 2); 
    } 
    
    @Test (expected = IllegalArgumentException.class)
    public void testNewPositionRowTooSmall() throws IllegalArgumentException
    {
       Position invalid = new Position(board, -1, 2); 
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testNewPositionRowTooLarge() throws IllegalArgumentException
    {
       Position invalid = new Position(board, BOARD_ROWS, 2); 
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNewPositionColumnTooSmall() throws IllegalArgumentException
    {
       Position invalid = new Position(board, 1, -2); 
    }  
    
    @Test (expected = IllegalArgumentException.class)
    public void testNewPositionColumnTooLarge() throws IllegalArgumentException
    {
       Position invalid = new Position(board, 1, BOARD_COLS); 
    }  

    /**
     * Tests the getter methods.
     */
    @Test
    public void testGetRow()
    {
        assertEquals("Incorrect row", row, position.getRow());
    }
    
    @Test
    public void testGetColumn()
    {
        assertEquals("Incorrect column", col, position.getColumn());
    }    
}
