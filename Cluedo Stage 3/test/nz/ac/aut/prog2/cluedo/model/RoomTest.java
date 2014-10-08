package nz.ac.aut.prog2.cluedo.model;

import java.awt.Color;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test framework for the Room class.
 *
 * @author  Anne Philpott & Stefan Marks
 * @version 1.0 - 2012.02: Created
 * @version 1.1 - 2012.04: Updated for assignment stage 3
 * @version 1.2 - 2012.04: Added tests for getRandomSuspect()
 */
public class RoomTest
{
    private RoomName name;
    private Room     room;
    
 /**
     * Default constructor for test class RoomTest
     */
    public RoomTest()
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
        name = RoomName.KITCHEN;
        room = new Room(name);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
        name = null;
        room = null;
    }

    /**
     * Test method for getName method.
     */    
    @Test
    public void testGetName()
    {
        assertEquals("Wrong room name.", name, room.getName());
    }

    
    /**
     * Test method for adding a board square.
     */    
    @Test
    public void testAddBoardSquare()
    {
        Board board = new Board(10,10);
        Position  position = new Position(board, 0,0);
        Color  colour = Color.red;
        BoardSquare sq = new Floor(position, colour);
        assertTrue("Can't add square to room.",  room.addBoardSquare(sq));
        assertTrue("Square not added", room.containsPosition(position));
    }
 
    /**
     * Test methods for containsPosition() method.
     */    
    @Test
    public void testContainsPositionNotPresent()
    {
        Board board = new Board(10,10);
        Position position = new Position(board, 0,0);
        assertFalse("Reporting present when not present", room.containsPosition(position));
    } 
    
 
    @Test
    public void testContainsPositionPresent()
    {
        Board board = new Board(10,10);
        Position  position = new Position(board, 0,0);
        Color  colour = Color.red;
        BoardSquare sq = new Floor(position, colour);
        room.addBoardSquare(sq);
        assertTrue("Reporting not present when present", room.containsPosition(position));
    }    
    
    
    // Tests for getRandomSuspect
    
    @Test 
    public void testGetRandomSuspectNoSuspect()
    {
        assertNull(room.getRandomSuspect());
    }

    @Test 
    public void testGetRandomSuspectOneSuspect()
    {
        Board board = new Board(10,10);
        Position  position = new Position(board, 0,0);
        BoardSquare sq = new Floor(position, Color.WHITE);
        Suspect suspect = new Suspect(SuspectName.RED);
        sq.addOccupant(suspect);
        room.addBoardSquare(sq);
        
        assertEquals(suspect, room.getRandomSuspect());
    }
}
