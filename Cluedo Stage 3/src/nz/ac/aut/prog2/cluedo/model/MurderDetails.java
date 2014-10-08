package nz.ac.aut.prog2.cluedo.model;

/**
 * Class to represent details of the murder or any accusation.
 * 
 * @author Stefan Marks & Anne Philpott
 * @version 1.0 - 2012.02: Created
 * @version 1.1 - 2012.04: Modified for assignment stage 3
 */
public class MurderDetails
{
    // the name of the murderer
    private SuspectName suspectName;
    
    // the weapon used in the murder
    private WeaponName weaponName;
    
    // the room where the murder happened
    private RoomName roomName;
    
    /**
     * Constructor for the details of the murder case.
     * 
     * @param suspect the name of the suspect
     * @param weapon the weapon involved
     * @param room the room where the murder took place
     */
    public MurderDetails(SuspectName suspect, WeaponName weapon, RoomName room)
    {
        this.suspectName = suspect;
        this.weaponName  = weapon;
        this.roomName    = room;
    }
    
    /**
     * Gets the name of the murderer.
     * 
     * @return suspect name
     */
    public SuspectName getSuspectName()
    {
        return suspectName;
    }

    /**
     * Gets the name of the murder weapon.
     * 
     * @return weapon name
     */
    public WeaponName getWeaponName()
    {
        return weaponName;
    }
   
    /**
     * Gets the name of the room of the murder.
     * 
     * @return room name
     */
    public RoomName getRoomName()
    {
        return roomName;
    }
    
    /**
     * Gets a full description of the murder case.
     * 
     * @return a full text description of the murder details
     */
    public String getFullDescription()
    {
        return suspectName + " murdered Dr. Black with a " +
               weaponName + " in the " +
               roomName + ".";                
    }
    
    /**
     * Checks for equality with other objects.
     * 
     * @return true if the other object is an identical accusation, false if not
     */
    @Override
    public boolean equals(Object other)
    {
        boolean equals = false;
        if ( other instanceof MurderDetails )
        {
            MurderDetails accusation = (MurderDetails) other;
            equals = this.suspectName.equals(accusation.suspectName) &&
                     this.weaponName.equals(accusation.weaponName) &&
                     this.roomName.equals(accusation.roomName);
        }
        return equals;
    }
}
