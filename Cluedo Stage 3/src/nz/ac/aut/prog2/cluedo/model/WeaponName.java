package nz.ac.aut.prog2.cluedo.model;

/**
 * Enumeration class for weapon names
 * 
 * @author Stefan Marks & Anne Philpott
 * @version 1.0 - 2012.02: Created
 * @version 1.1 - 2012.04: Updated for assignment stage 3
 */
public enum WeaponName
{
    REVOLVER("Revolver"), 
    CANDLESTICK("Candlestick"),
    ROPE("Rope"),
    LEADPIPE("Lead Pipe"),
    SPANNER("Spanner"),
    DAGGER("Dagger");
    
    // the name of the weapon
    private String name;
    
    private WeaponName(String name)
    {
        this.name = name;
    }
    
    /**
     * Returns the weapon name as a string instead of the enumeration value.
     * 
     * @return string for the weapon name.
     */
    @Override
    public String toString()
    {
        return name;
    }
}
