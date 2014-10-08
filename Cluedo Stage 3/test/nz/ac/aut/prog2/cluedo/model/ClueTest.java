package nz.ac.aut.prog2.cluedo.model;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

/**
 * Test framework for the Clue class.
 *
 * @author  Anne Philpott & Stefan Marks
 * @version 1.0 - 2012.02: Created
 * @version 1.1 - 2012.04: Updated for assignment stage 3
 */
public class ClueTest
{
    private SuspectName suspectName1, suspectName2;
    private WeaponName  weaponName1,  weaponName2;
    private RoomName    roomName1,    roomName2;
    private Clue        suspectClue1, suspectClue2;
    private Clue        weaponClue1,  weaponClue2;
    private Clue        roomClue1,    roomClue2;
    
    /**
     * Default constructor for test class ClueTest
     */
    public ClueTest()
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
        suspectName1 = SuspectName.YELLOW;
        suspectClue1 = new Clue(suspectName1);

        suspectName2 = SuspectName.RED;
        suspectClue2 = new Clue(suspectName2);

        weaponName1 = WeaponName.DAGGER;
        weaponClue1 = new Clue(weaponName1);

        weaponName2 = WeaponName.REVOLVER;
        weaponClue2 = new Clue(weaponName2);
        
        roomName1 = RoomName.CELLAR;
        roomClue1 = new Clue(roomName1);

        roomName2 = RoomName.BALLROOM;
        roomClue2 = new Clue(roomName2);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
        suspectName1 = null;
        suspectClue1 = null;
        suspectName2 = null;
        suspectClue2 = null;
        weaponName1  = null;
        weaponClue1  = null;
        weaponName2  = null;
        weaponClue2  = null;
        roomName1    = null;
        roomClue1    = null;
        roomName2    = null;
        roomClue2    = null;
    }
    
    /**
     * Tests for the getter methods.
     */    
    @Test
    public void testGetSuspectName()
    {
        assertEquals(suspectName1, suspectClue1.getSuspectName());
        assertNull(weaponClue1.getSuspectName());
        assertNull(roomClue1.getSuspectName());
    }
    
    @Test
    public void testGetWeaponName()
    {
        assertEquals(weaponName1, weaponClue1.getWeaponName());
        assertNull(suspectClue1.getWeaponName());
        assertNull(roomClue1.getWeaponName());
    }
    
    @Test
    public void testGetRoomName()
    {
        assertEquals(roomName1, roomClue1.getRoomName());
        assertNull(suspectClue1.getRoomName());
        assertNull(weaponClue1.getRoomName());
    }    


    /**
     * Tests for the toString method.
     */    
    @Test
    public void testToStringSuspectClue()
    {
        assertEquals("The murderer was NOT Colonel Mustard.", suspectClue1.toString());
        assertEquals("The murderer was NOT Miss Scarlett.",   suspectClue2.toString());
    }

    @Test
    public void testToStringWeaponClue()
    {
        assertEquals("The murder weapon was NOT a Dagger.",   weaponClue1.toString());
        assertEquals("The murder weapon was NOT a Revolver.", weaponClue2.toString());
    }

    @Test
    public void testToStringRoomClue()
    {
        assertEquals("Dr. Black was NOT murdered in the Cellar.",   roomClue1.toString());
        assertEquals("Dr. Black was NOT murdered in the Ballroom.", roomClue2.toString());
    }    
    
}
