package nz.ac.aut.prog2.cluedo.model;

/**
 * Enumeration class for the suspect names 
 * 
 * @author Stefan Marks & Anne Philpott
 * @version 1.0 - 2012.02: Created
 * @version 1.1 - 2012.04: Updated for assignment stage 3
 */
public enum SuspectName
{
    RED("Miss Scarlett"), 
    YELLOW("Colonel Mustard"),
    WHITE("Mrs. White"),
    GREEN("Reverend Green"),
    BLUE("Mrs. Peacock"),
    PURPLE("Professor Plum");
   
    // the name of the suspect
    private String name;

    private SuspectName(String name)
    {
        this.name = name;
    }
    
    /**
     * Returns the suspect name as a string instead of the enumeration value.
     * 
     * @return string for the suspect name.
     */
    @Override
    public String toString()
    {
        return name;
    }  
}
