package nz.ac.aut.prog2.cluedo.model;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Test framework for the MurderDetails class.
 *
 * @author  Anne Philpott & Stefan Marks
 * @version 1.0 - 02.2012: Created
 * @version 1.1 - 2012.04: Updated for assignment stage 3
 */
public class MurderDetailsTest
{
    private SuspectName     suspectName1, suspectName2;
    private WeaponName      weaponName1,  weaponName2;
    private RoomName        roomName1,    roomName2;
    private MurderDetails   solution1,    solution2;

    /**
     * Default constructor for test class MurderDetailsTest
     */
    public MurderDetailsTest()
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
        suspectName1 = SuspectName.RED;
        weaponName1  = WeaponName.REVOLVER;
        roomName1    = RoomName.KITCHEN;
        solution1    = new MurderDetails(suspectName1, weaponName1, roomName1);

        suspectName2 = SuspectName.YELLOW;
        weaponName2  = WeaponName.ROPE;
        roomName2    = RoomName.BALLROOM;
        solution2    = new MurderDetails(suspectName2, weaponName2, roomName2);
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
        roomName1    = null;
        weaponName1  = null;
        solution1    = null;

        suspectName2 = null;
        roomName2    = null;
        weaponName2  = null;
        solution2    = null;
    }
    
    @Test
    public void testGetSuspectName()
    {
        assertEquals(suspectName1, solution1.getSuspectName());
    }
    
    @Test
    public void testGetWeaponName()
    {
        assertEquals(weaponName1, solution1.getWeaponName());
    }
    
    @Test
    public void testGetRoomName()
    {
        assertEquals(roomName1, solution1.getRoomName());
    }    

    @Test
    public void testGetFullDescription()
    {
        assertEquals("Miss Scarlett murdered Dr. Black with a Revolver in the Kitchen.", 
                     solution1.getFullDescription());
        assertEquals("Colonel Mustard murdered Dr. Black with a Rope in the Ballroom.", 
                     solution2.getFullDescription());
    }    
}
