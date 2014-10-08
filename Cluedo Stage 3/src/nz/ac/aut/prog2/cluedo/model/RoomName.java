package nz.ac.aut.prog2.cluedo.model;

/**
 * Enumeration class for rooms in which murder could take place.
 * 
 * @author Stefan Marks & Anne Philpott
 * @version 1.0 - 2012.02: Created
 * @version 1.1 - 2012.04: Updated for assignment stage 3
 */
public enum RoomName
{
    CELLAR("Cellar"),
    KITCHEN("Kitchen"), 
    BALLROOM("Ballroom"),
    CONSERVATORY("Conservatory"),
    DINING("Dining Room"),
    BILLARD("Billard Room"),
    LIBRARY("Library"),
    LOUNGE("Lounge"),
    HALL("Hall"),
    STUDY("Study");
    
    // the name of the room
    private String name;

    private RoomName(String name)
    {
        this.name = name;
    }
    
    /**
     * Returns the room name as a string instead of the enumeration value.
     * 
     * @return string for the room name.
     */
    @Override
    public String toString()
    {
        return name;
    }
}
