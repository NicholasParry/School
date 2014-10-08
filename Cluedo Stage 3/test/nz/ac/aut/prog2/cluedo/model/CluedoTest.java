package nz.ac.aut.prog2.cluedo.model;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test framework for the Cluedo class.
 *
 * @author  Anne Philpott & Stefan Marks
 * @version 1.0 - 2012.02: Created
 * @version 1.1 - 2012.04: Updated for assignment stage 3
 */
public class CluedoTest
{
    Cluedo cluedoGame;

    /**
     * Default constructor for test class CluedoTest
     */
    public CluedoTest()
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
        cluedoGame = new Cluedo();
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
        cluedoGame = null;
    }
    
    @Test
    public void testNewGame()
    {
        assertEquals(GameState.NOTSTARTED, cluedoGame.getGameState());
    }
    
    @Test
    public void testStartGame()
    {
        cluedoGame.startGame();
        assertEquals(24, cluedoGame.getNumberOfRows());
        assertEquals(25, cluedoGame.getNumberOfColumns());
        assertNotNull("There is no player object.", cluedoGame.getPlayer());
        assertNotNull("The player is not inside a room.", cluedoGame.getCurrentRoom());
    }
    
    //=========================== Movement related tests ===========================================
    
    @Test
    public void testMovementIsPossibleNotPlaying()
    {   
        assertFalse(cluedoGame.moveIsPossible(MoveDirection.EAST));
    }
        
    @Test
    public void testMovementIsPossibleValidMove()
    {   
        //Ensure a game with know positions is created
        cluedoGame.startNonRandomTestGame();
        assertTrue(cluedoGame.moveIsPossible(MoveDirection.NORTH));
    }
        
    @Test
    public void testMovementIsPossibleInvalidMove()
    {   
        //Ensure a game with known positions is created
        cluedoGame.startNonRandomTestGame();
        assertFalse(cluedoGame.moveIsPossible(MoveDirection.EAST));
    }   
    
    @Test
    public void testMovePlayerNotPlaying()
    {
         assertFalse(cluedoGame.movePlayer(MoveDirection.EAST));
    }
    
    @Test
    public void testMovePlayerValidMove()
    {
        cluedoGame.startNonRandomTestGame();
        assertTrue(cluedoGame.movePlayer(MoveDirection.NORTH));
        //check player has moved correctly.
        Player player = cluedoGame.getPlayer();
        Position newPosition = new Position(cluedoGame.getBoard(), 3,4);
        assertEquals(newPosition, player.getPosition());
    }
    
    
    @Test
    public void testMovePlayerInvalidMove()
    {
        cluedoGame.startNonRandomTestGame();
        assertFalse(cluedoGame.movePlayer(MoveDirection.EAST));
        //check player has not moved.
        Position oldPosition = new Position(cluedoGame.getBoard(), 4,4);
        Player player = cluedoGame.getPlayer();
        assertEquals(oldPosition.getRow(), player.getPosition().getRow());
        assertEquals(oldPosition.getColumn(), player.getPosition().getColumn());
    } 

    //==================================== Action points & moves related test methods =========================

    @Test
    public void testMovementIsPossibleNoPoints()
    {
        cluedoGame.startNonRandomTestGame();
        Player player = cluedoGame.getPlayer();
        player.removeActionPoints(100);
        assertFalse(cluedoGame.moveIsPossible(MoveDirection.NORTH));        
    }
    
    @Test
    public void testMovePlayerCostsPoints()
    {
        cluedoGame.startNonRandomTestGame();
        // move player outside of room
        cluedoGame.movePlayer(MoveDirection.SOUTH);       
        //check points deducted.
        Player player = cluedoGame.getPlayer();
        assertEquals(99, player.getActionPoints());        
    }
    
    @Test
    public void testMovePlayerNoCost()
    {
        cluedoGame.startNonRandomTestGame();
        // move player inside of room
        cluedoGame.movePlayer(MoveDirection.NORTH);       
        //check  no points deducted.
        Player player = cluedoGame.getPlayer();
        assertEquals(100, player.getActionPoints());        
    }

    @Test
    public void testMovePlayerEndsGame()
    {
        cluedoGame.startNonRandomTestGame();
        Player player = cluedoGame.getPlayer();
        player.removeActionPoints(99); 
        // move player outside of room
        cluedoGame.movePlayer(MoveDirection.SOUTH);
        assertEquals(GameState.LOST, cluedoGame.getGameState());
    } 

    //==================================== Clue related test methods =========================    
    
    @Test
    public void testIsClueAvailablePositive()
    {
        cluedoGame.startNonRandomTestGame();
        assertTrue(cluedoGame.isClueAvailable());
    }
    
    @Test
    public void testIsClueAvailableNoSuspect()
    {
        cluedoGame.startNonRandomTestGame();
        //Player is in the kitchen with a suspect.
        //Move player to Ballroom where there is no suspect.
        cluedoGame.movePlayer(MoveDirection.SOUTH);
        cluedoGame.movePlayer(MoveDirection.SOUTH);
        cluedoGame.movePlayer(MoveDirection.EAST);
        cluedoGame.movePlayer(MoveDirection.EAST);
        cluedoGame.movePlayer(MoveDirection.EAST);
        cluedoGame.movePlayer(MoveDirection.NORTH);
        cluedoGame.movePlayer(MoveDirection.NORTH);
        cluedoGame.movePlayer(MoveDirection.EAST);
        cluedoGame.movePlayer(MoveDirection.EAST);
       
        assertFalse(cluedoGame.isClueAvailable());
    }
    
    @Test
    public void testIsClueAvailableNoEnoughPoints()
    { 
        cluedoGame.startNonRandomTestGame();
        Player player = cluedoGame.getPlayer();
        //Remove points so fewer than EXAMINE_COST (10) remain
        player.removeActionPoints(91);
        
        assertFalse(cluedoGame.isClueAvailable());
    }
    
    @Test
    public void testIsClueAvailableNoGameInPlay()
    {      
        assertFalse(cluedoGame.isClueAvailable());
    }    

    //==================================== Accusation related test methods =========================
        
    @Test
    public void testMakeAccusationCorrect()
    {
        cluedoGame.startNonRandomTestGame();
        MurderDetails accusation = new MurderDetails(SuspectName.GREEN, WeaponName.DAGGER, RoomName.STUDY);
        assertTrue(cluedoGame.makeAccusation(accusation));
        assertEquals(GameState.WON,cluedoGame.getGameState() );
    }
    
    @Test
    public void testMakeAccusationWrong()
    {
        cluedoGame.startNonRandomTestGame();
        MurderDetails accusation = new MurderDetails(SuspectName.GREEN, WeaponName.DAGGER, RoomName.KITCHEN);
        assertFalse(cluedoGame.makeAccusation(accusation));
        assertEquals(GameState.PLAYING,cluedoGame.getGameState() );
        //check  points deducted.
        Player player = cluedoGame.getPlayer();
        assertEquals(80, player.getActionPoints());        
        
    }
    
    @Test
    public void testMakeAccusationNoGameInPlay()
    {
        MurderDetails accusation = new MurderDetails(SuspectName.GREEN, WeaponName.DAGGER, RoomName.KITCHEN);
        assertFalse(cluedoGame.makeAccusation(accusation));       
    }    
    
}
