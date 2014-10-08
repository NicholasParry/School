package nz.ac.aut.prog2.cluedo.model;

/**
 *  Clue represents a clue that exist about the murder.
 *  A clue is either about a room, a suspect, or a weapon
 * 
 * @author Stefan Marks & Anne Philpott
 * @version 1.0 - 2012.02: Created
 * @version 1.1 - 2012.04: Modified for assignment stage 3
 */
public class Clue
{
    // the name of a suspect who was not the murderer
    private SuspectName suspectName;
    // the name of a room where the murder did not happen
    private RoomName roomName;
    // the name of a weapon that was not used
    private WeaponName weaponName;
    
    /**
     * Constructor for a clue with a suspect name.
     * 
     * @param suspect the name of the suspect
     */
    public Clue(SuspectName suspect)
    {
        this(suspect, null, null);    
    }

    /**
     * Constructor for a clue with a weapon name.
     * 
     * @param weapon the name of the weapon
     */    
    public Clue(WeaponName weapon)
    {
        this(null, weapon, null);    
    }

    /**
     * Constructor for a clue with a room name.
     * 
     * @param room the name of the room
     */    
    public Clue(RoomName room)
    {
        this(null, null, room);    
    }

    /**
     * This private constructor ensures that clues cannot be create 
     * with more than a single piece of data.
     */
    private Clue(SuspectName suspect, WeaponName weapon, RoomName room)
    {
       this.suspectName = suspect;
       this.weaponName  = weapon;
       this.roomName    = room;
    }
    
    /**
     * Gets the name of the suspect for this clue.
     * 
     * @return suspect name for this clue or null if not a suspect clue.
     */
    public SuspectName getSuspectName()
    {
        return suspectName;
    }
    
    /**
     * Gets the name of the weapon for this clue.
     * 
     * @return weapon name for this clue or null if not a weapon clue.
     */
    public WeaponName getWeaponName()
    {
        return weaponName;
    }

    /**
     * Gets the name of the room for this clue.
     *
     * @return room name for this clue or null if not a room clue.
     */
    public RoomName getRoomName()
    {
        return roomName;
    }

    @Override
    public String toString()
    {
        String ret = "";
        if ( suspectName != null )
        {
            ret = "The murderer was NOT " + suspectName + "."; 
        }
        else if ( roomName != null ) 
        {
            ret = "Dr. Black was NOT murdered in the " + roomName + ".";
        }
        else if ( weaponName != null ) 
        {
            ret = "The murder weapon was NOT a " + weaponName + ".";
        }
        return ret;
    }
}
