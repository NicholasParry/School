package nz.ac.aut.prog2.cluedo.model;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test framework for the Suspect class.
 *
 * @author  Anne Philpott and Stefan Marks
 * @version 1.0 - 2012.02: Created
 * @version 1.1 - 2012.04: Updated for assignment stage 3
 * @version 1.2 - 2012.04: Added tests for getRandomClue()
 */
public class SuspectTest
{
    private Suspect     suspect;
    private Suspect     suspectWithPosition;
    private SuspectName name;
    private Board       board;    
    private Position    position, position2;
    
    /**
     * Default constructor for test class SuspectTest.
     */
    public SuspectTest()
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
        name = SuspectName.BLUE;
        suspect = new Suspect(name);

        board     = new Board(10, 10);
        position  = new Position(board, 1,2);
        position2 = new Position(board, 3,4);
        
        suspectWithPosition = new Suspect(SuspectName.RED);
        suspectWithPosition.setPosition(position);
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
        suspect = null;
        board     = null;
        position  = null;
        position2 = null;
        suspectWithPosition = null;
    }
    
    @Test
    public void testGetName()
    {
        assertEquals("Wrong name", name, suspect.getName());
    }
    
    @Test
    public void testGetStringRepresentation()
    {
        assertEquals("Wrong string", "S", suspect.getStringRepresentation());
    } 
    
    // =========================== Position related tests ======================
    
    @Test
    public void testGetPositionNoPosition()
    {
        assertNull(suspect.getPosition());
    }
    
    @Test
    public void testGetPositionWithPosition()
    {
        assertEquals(position, suspectWithPosition.getPosition());
    }
     
    @Test
    public void testSetPosition()
    {
        assertEquals(position, suspectWithPosition.getPosition());
        suspectWithPosition.setPosition(position2);
        assertEquals(position2, suspectWithPosition.getPosition());
    }

    @Test
    public void testSetPositionToNull()
    {
        assertEquals(position, suspectWithPosition.getPosition());
        suspectWithPosition.setPosition(null);
        assertNull(suspectWithPosition.getPosition());
    }

    // =================================== Clue related tests ==================
    
    /**
     * Tests for collecting and removing clues.
     */

    @Test
    public void testHasClueNegative()
    {
        Clue clue = new Clue(SuspectName.PURPLE);
        assertFalse(suspectWithPosition.hasClue(clue));
    }
    
    @Test
    public void testHasCluePositive()
    {
        Clue clue = new Clue(SuspectName.PURPLE);
        suspectWithPosition.collectClue(clue);
        assertTrue(suspectWithPosition.hasClue(clue));
    }
    
    @Test
    public void testCollectClueValid()
    {
        Clue clue = new Clue(SuspectName.PURPLE);
        assertTrue(suspectWithPosition.collectClue(clue));
    }
    
    @Test
    public void testCollectClueDuplicate()
    {
        Clue clue = new Clue(SuspectName.PURPLE);
        suspectWithPosition.collectClue(clue);
        assertFalse(suspectWithPosition.collectClue(clue));
    }
    
    @Test
    public void testCollectClueNull()
    {
        Clue clue = null;
        assertFalse(suspectWithPosition.collectClue(clue));
    }    

    @Test
    public void testResetClues()
    {
        Clue clue = new Clue(SuspectName.PURPLE);
        suspectWithPosition.collectClue(clue);
        assertTrue(suspectWithPosition.hasClue(clue));
        suspectWithPosition.resetClues();
        assertFalse(suspectWithPosition.hasClue(clue));               
    }    
 
    /**
     * Tests for acquiring random clues
     */
    
     @Test 
     public void testGetRandomClueNoClues()
     {
         assertNull(suspect.getRandomClue());
     }
 
     @Test 
     public void testGetRandomClueOneClue()
     {
         Clue clue = new Clue(RoomName.CONSERVATORY);
         suspect.collectClue(clue);
         assertEquals(clue, suspect.getRandomClue());
     }
}
