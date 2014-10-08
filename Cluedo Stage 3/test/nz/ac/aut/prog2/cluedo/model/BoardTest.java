package nz.ac.aut.prog2.cluedo.model;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test framework for the Board class.
 *
 * @author  Anne Philpott & Stefan Marks
 * @version 1.0 - 2012.02: Created
 * @version 2.0 - 2012.03: Added a diagnosis board file
 * @version 2.1 - 2012.04: Updated for assignment stage 3
 */
public class BoardTest
{
    private Board board;
    private Board boardFromFile;    

    /**
     * Default constructor for test class BoardTest
     */
    public BoardTest()
    {
    }

    /**
     * Sets up the test fixture.
     * Called before every test case method.
     * 
     * The boardFromFile has the following layout:
     * Size: 7x7, 1 Room (Kitchen: "0" in the middle)
     * One door to the south
     * Hallway around the room
     * 
     *     Col
     * Row 0 1 2 3 4 5 6
     * 0   . . . . . . .
     * 1   . W W W W W .
     * 2   . W 0 0 0 W .
     * 3   . W 0 0 0 W .
     * 4   . W 0 0 0 W .
     * 5   . W W D W W .
     * 6   . . . . . . .
     */
    @Before
    public void setUp()
    {
        board         = new Board(10, 12);
        boardFromFile = new Board("./TestBoard.txt");    
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
        boardFromFile = null;
    }

    /**
    * Tests to check exceptions thrown when data invalid
    */ 

    @Test (expected = IllegalArgumentException.class)
    public void testNewBoardRowTooSmall() throws IllegalArgumentException
    {
        Board invalid = new Board(0,1); 
    } 

    @Test (expected = IllegalArgumentException.class)
    public void testNewBoardRowTooLarge() throws IllegalArgumentException
    {
        Board invalid = new Board(51,1); 
    } 

    @Test (expected = IllegalArgumentException.class)
    public void testNewBoardColumnTooSmall() throws IllegalArgumentException
    {
        Board invalid = new Board(1,0); 
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNewBoardColumnTooLarge() throws IllegalArgumentException
    {
        Board invalid = new Board(1,51); 
    }

    /**
     * Test dimension getters.
     */    
    @Test
    public void testGetNumRows()
    {
        assertEquals("Wrong number of rows reported", 10, board.getNumRows());
    }

    @Test
    public void testGetNumColumns()
    {
        assertEquals("Wrong number of columns reported", 12, board.getNumColumns());
    }

    /**
     * Test getters for board squares and rooms.
     */
    @Test
    public void testGetSquare()
    {
        Position position = new Position(boardFromFile, 4, 2);
        BoardSquare sq = boardFromFile.getSquare(position);
        assertEquals("Does not get correct square", position, sq.getPosition());
    }

    @Test
    public void testGetRoomAtPositionWhenRoom()
    {
        Position position = new Position(boardFromFile, 2, 3);
        Room room =  boardFromFile.getRoomAtPosition(position);
        RoomName name =  room.getName();
        assertEquals("Not getting correct room", RoomName.KITCHEN, name );
    }

    @Test
    public void testGetRoomAtPositionWhenNotRoom()
    {
        Position position = new Position(boardFromFile, 6,6);
        assertNull("Not getting correct room", boardFromFile.getRoomAtPosition(position) );
    }   

    @Test
    public void testGetEmptyRandomSquareInRoom()
    {
        //Check a number of times. Doesn't prove method is correct but improves chances.
        for(int i =0; i<100; i++)
        {
            BoardSquare empty = boardFromFile.getEmptyRandomSquareInRoom();
            assertFalse("Square not empty", empty.hasOccupants());
            assertNotNull("Square not in a room",boardFromFile.getRoomAtPosition(empty.getPosition()) );
        }

    }
    
    //===================================== CalculateDestination related tests ======================

    @Test
    public void testCalculateDestinationValidMoveInRoom()
    {
        Position from = new Position(boardFromFile, 3, 3);
        Position toN  = new Position(boardFromFile, 2, 3);
        Position toE  = new Position(boardFromFile, 3, 4);
        Position toS  = new Position(boardFromFile, 4, 3);
        Position toW  = new Position(boardFromFile, 3, 2);
        assertEquals(toN, boardFromFile.calculateDestination(from, MoveDirection.NORTH)); 
        assertEquals(toE, boardFromFile.calculateDestination(from, MoveDirection.EAST)); 
        assertEquals(toS, boardFromFile.calculateDestination(from, MoveDirection.SOUTH)); 
        assertEquals(toW, boardFromFile.calculateDestination(from, MoveDirection.WEST)); 
    }
    
    @Test
    public void testCalculateDestinationValidMoveInHall()
    {
        Position from = new Position(boardFromFile, 6, 3);
        Position toE  = new Position(boardFromFile, 6, 4);
        Position toW  = new Position(boardFromFile, 6, 2);
        assertEquals(toE, boardFromFile.calculateDestination(from, MoveDirection.EAST)); 
        assertEquals(toW, boardFromFile.calculateDestination(from, MoveDirection.WEST)); 
    }
    
    @Test
    public void testCalculateDestinationValidMoveToDoorway()
    {
        Position from = new Position(boardFromFile, 4, 3);
        Position to   = new Position(boardFromFile, 5, 3);
        assertEquals(to, boardFromFile.calculateDestination(from, MoveDirection.SOUTH)); 
    }
        
    @Test
    public void testCalculateDestinationInvalidMoveToWall()
    {
        Position from = new Position(boardFromFile, 3, 4);
        assertNull( boardFromFile.calculateDestination(from, MoveDirection.EAST)); 
    }
    
    @Test
    public void testCalculateDestinationValidMoveToBoardEdgeTop()
    {
        Position from = new Position(boardFromFile, 1, 0);
        Position to   = new Position(boardFromFile, 0, 0);
        assertEquals(to, boardFromFile.calculateDestination(from, MoveDirection.NORTH)); 
    }
        
    @Test
    public void testCalculateDestinationValidMoveToBoardEdgeBottom()
    {
        Position from = new Position(boardFromFile, 5, 6);
        Position to   = new Position(boardFromFile, 6, 6);
        assertEquals(to, boardFromFile.calculateDestination(from, MoveDirection.SOUTH)); 
    }

    @Test
    public void testCalculateDestinationValidMoveToBoardEdgeLeft()
    {
        Position from = new Position(boardFromFile, 0, 1);
        Position to   = new Position(boardFromFile, 0, 0);
        assertEquals(to, boardFromFile.calculateDestination(from, MoveDirection.WEST)); 
    }

    @Test
    public void testCalculateDestinationValidMoveToBoardEdgeRight()
    {
        Position from = new Position(boardFromFile, 6, 5);
        Position to   = new Position(boardFromFile, 6, 6);
        assertEquals(to, boardFromFile.calculateDestination(from, MoveDirection.EAST)); 
    }

    @Test
    public void testCalculateDestinationInvalidMoveOffBoardTop()
    {
        Position from = new Position(boardFromFile, 0, 3);
        assertNull( boardFromFile.calculateDestination(from, MoveDirection.NORTH)); 
    } 

    @Test
    public void testCalculateDestinationInvalidMoveOffBoardBottom()
    {
        Position from = new Position(boardFromFile, 6, 3);
        assertNull( boardFromFile.calculateDestination(from, MoveDirection.SOUTH)); 
    } 

    @Test
    public void testCalculateDestinationInvalidMoveOffBoardLeft()
    {
        Position from = new Position(boardFromFile, 3, 0);
        assertNull( boardFromFile.calculateDestination(from, MoveDirection.WEST)); 
    } 

    @Test
    public void testCalculateDestinationInvalidMoveOffBoardRight()
    {
        Position from = new Position(boardFromFile, 3, 6);
        assertNull( boardFromFile.calculateDestination(from, MoveDirection.EAST)); 
    } 
}
