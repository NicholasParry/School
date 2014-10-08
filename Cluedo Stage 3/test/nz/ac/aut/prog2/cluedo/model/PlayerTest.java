package nz.ac.aut.prog2.cluedo.model;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test framework for the Player class.
 *
 * @author  Anne Philpott and Stefan Marks
 * @version 1.0 - 2012.02: Created
 * @version 1.1 - 2012.04: Updated for assignment stage 3
 */
public class PlayerTest
{
    private Player   player;
    private Player   playerWithPosition;
    private String   name;
    private Board    board;    
    private Position position, position2;    
    /**
     * Default constructor for test class PlayerTest
     */
    public PlayerTest()
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
        name = "Test Player";
        player = new Player(name);
        
        board     = new Board(10, 10);
        position  = new Position(board, 1,2);
        position2 = new Position(board, 3,4);
        
        playerWithPosition = new Player("Player with position");
        playerWithPosition.setPosition (position);        
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
        player = null;
        board     = null;
        position  = null;
        position2 = null;
        playerWithPosition = null;         
    }
    
    @Test
    public void testGetName()
    {
        assertEquals("Wrong name", name, player.getName());
    }
    
    @Test
    public void testGetStringRepresentation()
    {
        assertEquals("Wrong string", "P", player.getStringRepresentation());
    }
    
    /**
     * Tests for action points related methods.
     */    
    @Test
    public void testGetActionPoints()
    {
        assertEquals("Points wrong", 100, player.getActionPoints());
    }
    
    @Test
    public void testAddActionPoints()
    {
        player.addActionPoints(5);
        assertEquals("Points wrong", 105, player.getActionPoints());
    }
    
    @Test
    public void testHasActionPointsWithPoints()
    {
        assertTrue( player.hasActionPoints());
    }
    
    @Test
    public void testHasActionPointsNoPoints()
    {
        player.removeActionPoints(100);
        assertFalse( player.hasActionPoints());
    }     
    
    @Test
    public void testRemoveActionPointsEnoughPoints()
    {
        player.removeActionPoints(21);
        assertEquals("Points wrong", 79, player.getActionPoints());
    }    

    @Test
    public void testRemoveActionPointsNotEnoughPoints()
    {
        player.removeActionPoints(105);
        assertEquals("Points wrong", 0, player.getActionPoints());
    } 
    
    
    
    //===================================== Position related tests ======================
    @Test
    public void testGetPositionNoPosition()
    {
        assertNull(player.getPosition());
    }
    
    @Test
    public void testGetPositionWithPosition()
    {
        assertEquals(position, playerWithPosition.getPosition());
    }
     
    @Test
    public void testSetPosition()
    {
        assertEquals(position, playerWithPosition.getPosition());
        playerWithPosition.setPosition(position2);
        assertEquals(position2, playerWithPosition.getPosition());
    }

    @Test
    public void testSetPositionToNull()
    {
        assertEquals(position, playerWithPosition.getPosition());
        playerWithPosition.setPosition(null);
        assertNull(playerWithPosition.getPosition());
    }

    //===================================== Clue related tests ======================
    /**
     * Tests for collecting and removing clues.
     */

    @Test
    public void testHasClueNegative()
    {
        Clue clue = new Clue(SuspectName.PURPLE);
        assertFalse(playerWithPosition.hasClue(clue));
    }
    
    @Test
    public void testHasCluePositive()
    {
        Clue clue = new Clue(SuspectName.PURPLE);
        playerWithPosition.collectClue(clue);
        assertTrue(playerWithPosition.hasClue(clue));
    }
    
    @Test
    public void testCollectClueValid()
    {
        Clue clue = new Clue(SuspectName.PURPLE);
        assertTrue(playerWithPosition.collectClue(clue));
    }
    
    @Test
    public void testCollectClueDuplicate()
    {
        Clue clue = new Clue(SuspectName.PURPLE);
        playerWithPosition.collectClue(clue);
        assertFalse(playerWithPosition.collectClue(clue));
    }
    
    @Test
    public void testCollectClueNull()
    {
        Clue clue = null;
        assertFalse(playerWithPosition.collectClue(clue));
    }    

    @Test
    public void testResetClues()
    {
        Clue clue = new Clue(SuspectName.PURPLE);
        Boolean dummy = playerWithPosition.collectClue(clue);
        assertTrue(playerWithPosition.hasClue(clue));
        playerWithPosition.resetClues();
        assertFalse(playerWithPosition.hasClue(clue));               
    }
}

